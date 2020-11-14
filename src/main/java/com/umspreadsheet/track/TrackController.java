package com.umspreadsheet.track;

import com.umspreadsheet.criteria.SearchCriteria;
import com.umspreadsheet.exception.DataNotFoundException;
import com.umspreadsheet.helper.ControllerHelper;
import com.umspreadsheet.model.Jam;
import com.umspreadsheet.model.Type;
import com.umspreadsheet.review.TrackReviewService;
import com.umspreadsheet.show.Show;
import com.umspreadsheet.show.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
public class TrackController
{
    private TrackService trackService;
    private TrackReviewService trackReviewService;

    private static final int PAGE_SIZE = 50;

    @Autowired
    public TrackController(TrackService trackService, TrackReviewService trackReviewService) {
        this.trackService = trackService;
        this.trackReviewService = trackReviewService;
    }

    // Top-rated songs page
    @RequestMapping("/songs")
    public String topSongsPage(@RequestParam(value = "page", required = false) Integer pageNumber,
                               Model model)
    {
        // Default page to display is the first
        if (pageNumber == null)
            pageNumber = 0;
        else
            pageNumber -= 1;

        Page<Track> page = trackService.getByAverageRating(PageRequest.of(pageNumber, 15));
        Integer totalPages = page.getTotalPages();

        // If user requests page that doesn't exist, throw DataNotFoundException
        if (pageNumber > totalPages)
            throw new DataNotFoundException("Page not found.");

        model.addAttribute("topFortyTracks", page.getContent());
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", pageNumber + 1);
        model.addAttribute("recentReviews", trackReviewService.getTenMostRecentReviews());

        return "/track/topSongs";
    }

    @RequestMapping(value = "/songs/search")
    public String submitSongFilter(@RequestParam(value = "page", required = false) Integer pageNumber,
                                   @RequestParam(value = "year", required = false) String year,
                                   @RequestParam(value = "month", required = false) String month,
                                   @RequestParam(value = "day", required = false) String day,
                                   @RequestParam(value = "rating", required = false) String rating,
                                   @RequestParam(value = "type", required = false) String type,
                                   @RequestParam(value = "jam", required = false) String jam,
                                   @RequestParam(value = "song", required = false) String song,
                                   Model model)
    {
        // Default page to display is the first
        if (pageNumber == null)
            pageNumber = 0;
        else
            pageNumber -= 1;

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

        if (type != null)
        {
            // Capitalize type string and get its enum value
            Type modelType = Type.valueOf(type.toUpperCase());
            builder.with("type", ":", modelType);
        }

        if (jam != null)
        {
            // Capitalize jam string and get its enum value
            Jam modelJam = Jam.valueOf(jam.toUpperCase());
            builder.with("jam", ":", modelJam);
        }

        if (song != null)
            builder.with("song", ":", song);

        Specification<Track> specification = builder.build();
        Page<Track> page;

        // Sort the tracks by date from newest to oldest if the page is visited via nav bar; otherwise, sort by average rating descending
        if (year == null && month == null && day == null && rating == null && jam == null && type == null && song == null)
            page = trackService.criteriaTest(specification, PageRequest.of(pageNumber, 15, Sort.Direction.DESC, "show.date"));
        else
        {
            Sort.Order averageRatingOrder = new Sort.Order(Sort.Direction.DESC, "averageRating");
            Sort.Order dateOrder = new Sort.Order(Sort.Direction.ASC, "show.date");

            List<Sort.Order> sortOrders = new ArrayList<>();
            sortOrders.add(averageRatingOrder);
            sortOrders.add(dateOrder);

            Sort sort = Sort.by(sortOrders);

            page = trackService.criteriaTest(specification, PageRequest.of(pageNumber, 15, sort));
        }
        Integer totalPages = page.getTotalPages();

        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", pageNumber + 1);
        model.addAttribute("trackResults", page.getContent());

        return "/track/songSearchResults";
    }

    @RequestMapping(value = "/songs/{id}/{slug}", method = RequestMethod.GET)
    public String songPage(@PathVariable Long id, Model model)
    {
        Track retrievedTrack = trackService.findById(id).orElse(null);
        model.addAttribute("track", retrievedTrack);
        model.addAttribute("reviews", trackReviewService.findThirtyByTrack(retrievedTrack));

        return "/track/track";
    }

    @RequestMapping("/songs/random")
    public String randomShow()
    {
        Long randomTrackId = getRandomTrack();
        Track randomTrack = trackService.findById(randomTrackId).orElse(null);
        String slug = "";
        if (randomTrack != null) {
            slug = randomTrack.getSlug();
        }

        return "redirect:/songs/" + randomTrackId + "/" + slug;
    }

    private Long getRandomTrack()
    {
        List<BigInteger> idList = trackService.findAllIds();

        return idList.get(new Random().nextInt(idList.size())).longValue();
    }
}
