package com.umspreadsheet.track;

import com.umspreadsheet.criteria.SearchCriteria;
import com.umspreadsheet.helper.ControllerHelper;
import com.umspreadsheet.review.TrackReviewService;
import com.umspreadsheet.user.SimpleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TrackController
{
    private TrackService trackService;
    private TrackReviewService trackReviewService;
    private SimpleUserService userService;

    @Autowired
    public TrackController(TrackService trackService,
                            TrackReviewService trackReviewService, SimpleUserService userService)
    {
        this.trackService = trackService;
        this.trackReviewService = trackReviewService;
        this.userService = userService;
    }

    // Top-rated songs page
    @RequestMapping("/songs")
    public String topSongsPage(@RequestParam(value = "page", required = false) Integer pageNumber,
                               Model model)
    {
        // Default page to display is the first
        if (pageNumber == null)
            pageNumber = 0;

        Page<Track> page = trackService.getByAverageRating(new PageRequest(pageNumber, 15));

        model.addAttribute("totalPages", (int)page.getTotalElements()/15);
        model.addAttribute("topFortyTracks", page.getContent());
        model.addAttribute("recentReviews", trackReviewService.getTenMostRecentReviews());

        return "/track/topSongs";
    }

    @RequestMapping(value = "/songs/search")
    public String submitSongFilter(@RequestParam(value = "year", required = false) String year,
                                   @RequestParam(value = "month", required = false) String month,
                                   @RequestParam(value = "day", required = false) String day,
                                   @RequestParam(value = "rating", required = false) String rating,
                                   @RequestParam(value = "type", required = false) String type,
                                   @RequestParam(value = "song", required = false) String song,
                                   Model model)
    {
        TrackSpecificationsBuilder builder = new TrackSpecificationsBuilder();

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

        if (song != null)
            builder.with("song", ":", song);

        Specification<Track> specification = builder.build();
        model.addAttribute("trackResults", trackService.criteriaTest(specification));

        return "/track/songSearchResults";
    }

    /*@RequestMapping(value = "/track", method = RequestMethod.PUT)
    public String updateTrackReview(TrackReview trackReview, RedirectAttributes redirectAttributes)
    {
        redirectAttributes.addAttribute("showId", trackReviewService.save(trackReview).getTrack().getShow().getId());
        redirectAttributes.addFlashAttribute("edited", "true");

        return "redirect:/user/review";
    }*/
}
