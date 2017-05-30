package com.umspreadsheet.show;

import com.umspreadsheet.criteria.SearchCriteria;
import com.umspreadsheet.helper.ControllerHelper;
import com.umspreadsheet.exception.DataNotFoundException;
import com.umspreadsheet.set.Set;
import com.umspreadsheet.set.SetDTO;
import com.umspreadsheet.review.TrackReview;
import com.umspreadsheet.review.TrackReviewForm;
import com.umspreadsheet.review.TrackReviewService;
import com.umspreadsheet.track.*;
import com.umspreadsheet.user.SimpleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigInteger;
import java.util.*;

@Controller
public class ShowController
{
    public static final int PAGE_SIZE = 10;
    private ShowService showService;
    private TrackService trackService;
    private TrackReviewService trackReviewService;
    private SimpleUserService userService;

    @Autowired
    public ShowController(ShowService showService, TrackService trackService,
                          TrackReviewService trackReviewService, SimpleUserService userService)
    {
        this.showService = showService;
        this.trackService = trackService;
        this.trackReviewService = trackReviewService;
        this.userService = userService;
    }

    // Top-rated shows page
    @RequestMapping("/shows")
    public String topShowsPage(@RequestParam(value = "page", required = false) Integer pageNumber, Model model)
    {
        // Default page to display is the first
        if (pageNumber == null)
            pageNumber = 0;
        else
            pageNumber -= 1;

        Page<Show> page = showService.getByAverageRating(new PageRequest(pageNumber, PAGE_SIZE));
        Integer totalPages = page.getTotalPages();

        // If user requests page that doesn't exist, throw DataNotFoundException
        if (pageNumber > totalPages)
            throw new DataNotFoundException("Page not found.");

        model.addAttribute("topTwentyShows", setNumberOfReviews(page.getContent()));
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", pageNumber + 1);

        return "/show/topShows";
    }

    // Returns a specified show's page
    @RequestMapping(value = "/shows/{id}/{slug}", method = RequestMethod.GET)
    public String showPage(@PathVariable Long id, Model model) throws
            DataNotFoundException
    {
        String username = getCurrentUsername();
        Show show = showService.findById(id);

        if (show == null)
            throw new DataNotFoundException("Show with id=" + id + " not found.");

        model.addAttribute("show", showService.findById(id));

        SetDTO setDTO = new SetDTO();
        setDTO.setShowId(id);
        model.addAttribute("set", setDTO);

        Track track = new Track();
        track.setShow(showService.findById(id));
        model.addAttribute("track", track);

        List<TrackReview> trackReviews = trackReviewService.findByUserAndShow(show, userService.findByUsername
                (username));

        List<Set> sets = show.getSets();
        List<TrackReviewForm> trackReviewForms = new ArrayList<>();

        for (Set setIter : sets)
        {
            for (Track trackIter : setIter.getTracks())
            {
                TrackReviewForm trackReviewForm = new TrackReviewForm(username, trackIter.getId());
                for (TrackReview review : trackReviews)
                {
                    if (trackIter.getId() == review.getTrack().getId())
                    {
                        trackReviewForm = new TrackReviewForm(review);
                        break;
                    }
                }
                trackIter.setTrackReviewForm(trackReviewForm);
                trackReviewForms.add(trackIter.getTrackReviewForm());
            }
        }

        model.addAttribute("trackReviewForms", trackReviewForms);
        model.addAttribute("reviews", trackReviewService.getAllByShow(show));

        return "/show/show";
    }

    // Delete a review
    @RequestMapping(value = "/shows/review", method = RequestMethod.DELETE)
    public String deleteTrackReview(@RequestParam("reviewId") Long reviewId,
                                    @RequestParam("showId") Long showId, RedirectAttributes redirectAttributes)
    {
        trackReviewService.delete(reviewId);

        Show show = showService.findById(showId);
        String slug = show.getSlug();

        redirectAttributes.addFlashAttribute("reviewDeleted", "true");

        return "redirect:/shows/" + showId + "/" + slug;
    }

    @RequestMapping("/shows/reviews")
    public String jumpToShowReviews(@RequestParam("showId") Long showId, RedirectAttributes redirectAttributes)
    {
        redirectAttributes.addAttribute("showId", showId);
        String slug = showService.findById(showId).getSlug();

        return "redirect:/shows/" + showId + "/" + slug + "#user-reviews";
    }

    @RequestMapping("/shows/random")
    public String randomShow()
    {
        Long randomShowId = getRandomShow();
        Show randomShow = showService.findById(randomShowId);
        String slug = randomShow.getSlug();

        return "redirect:/shows/" + randomShowId + "/" + slug;
    }

    // Submit a new review
    @RequestMapping(value = "/shows/review", method = RequestMethod.POST)
    public String saveTrackReview(TrackReviewForm trackReviewForm, RedirectAttributes redirectAttributes)
    {
        TrackReview trackReview = new TrackReview();
        if (trackReviewForm.getId() != null)
            trackReview.setId(trackReviewForm.getId());

        trackReview.setScore(trackReviewForm.getScore());
        trackReview.setUser(userService.findByUsername(trackReviewForm.getUsername()));
        trackReview.setTrack(trackService.findById(trackReviewForm.getTrackId()));
        trackReview.setComment(trackReviewForm.getComment());

        TrackReview savedTrackReview = trackReviewService.save(trackReview);

        Long showId = savedTrackReview.getTrack().getShow().getId();
        String slug = savedTrackReview.getTrack().getShow().getSlug();

        redirectAttributes.addFlashAttribute("submitted", "true");

        return "redirect:/shows/" + showId + "/" + slug;
    }

    // Submit or update a comment
    @RequestMapping(value = "/shows/comment", method = RequestMethod.POST)
    public String saveComment(TrackReviewForm trackReviewForm, RedirectAttributes redirectAttributes)
    {
        TrackReview retrievedReview = trackReviewService.findById(trackReviewForm.getId());
        retrievedReview.setComment(trackReviewForm.getComment());

        TrackReview savedReview = trackReviewService.save(retrievedReview);

        Long showId = savedReview.getTrack().getShow().getId();
        String showSlug = savedReview.getTrack().getSlug();

        return "redirect:/shows/" + showId + "/" + showSlug;
    }

    @RequestMapping(value = "/shows/search")
    public String submitShowFilter(@RequestParam(value = "page", required = false) Integer pageNumber,
                                   @RequestParam(value = "year", required = false) String year,
                                   @RequestParam(value = "month", required = false) String month,
                                   @RequestParam(value = "day", required = false) String day,
                                   @RequestParam(value = "rating", required = false) String rating,
                                   Model model)
    {
        // Default page to display is the first
        if (pageNumber == null)
            pageNumber = 0;
        else
            pageNumber -= 1;

        ShowSpecificationsBuilder builder = new ShowSpecificationsBuilder();

        if (year != null)
            builder.with("date", ":", year, SearchCriteria.DATE_SEGMENT_YEAR);
        if (month != null)
            builder.with("date", ":", month, SearchCriteria.DATE_SEGMENT_MONTH);
        if (day != null)
            builder.with("date", ":", day, SearchCriteria.DATE_SEGMENT_DAY);

        if (rating != null)
        {
            ControllerHelper.addRatingConstraints(rating, builder);
        }

        Specification<Show> specification = builder.build();
        Page<Show> page;

        // Sort the shows by date from newest to oldest if the page is visited via nav bar; otherwise, sort by average rating descending
        if (year == null && month == null && day == null & rating == null)
            page = showService.getShowsByFilter(specification, new PageRequest(pageNumber, PAGE_SIZE, Sort.Direction.DESC, "date"));
        else
            page = showService.getShowsByFilter(specification, new PageRequest(pageNumber, PAGE_SIZE, Sort.Direction.DESC, "averageRating"));

        Integer totalPages = page.getTotalPages();
        List<Show> shows = page.getContent();
        setNumberOfReviews(shows);

        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", pageNumber + 1);
        model.addAttribute("showResults", shows);

        return "/show/showSearchResults";
    }

    private String getCurrentUsername()
    {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;

        if (principal instanceof UserDetails)
        {
            username = ((UserDetails) principal).getUsername();
        } else
        {
            username = principal.toString();
        }

        return username;
    }

    private List<Show> setNumberOfReviews(List<com.umspreadsheet.show.Show> shows)
    {
        for (com.umspreadsheet.show.Show show : shows)
        {
            Long numberOfReviews = 0L;
            for (Set set: show.getSets())
            {
                for (Track track : set.getTracks())
                {
                    numberOfReviews += track.getReviews().size();
                }
            }
            show.setNumberOfReviews(numberOfReviews);
        }

        return shows;
    }

    private Long getRandomShow()
    {
        List<BigInteger> idList = showService.findAllShowIds();

        return idList.get(new Random().nextInt(idList.size())).longValue();
    }
}
