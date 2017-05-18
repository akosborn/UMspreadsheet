package com.umspreadsheet.track;

import com.umspreadsheet.criteria.SearchCriteria;
import com.umspreadsheet.exception.DataNotFoundException;
import com.umspreadsheet.helper.ControllerHelper;
import com.umspreadsheet.review.TrackReviewService;
import com.umspreadsheet.user.SimpleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
        else
            pageNumber -= 1;

        Page<Track> page = trackService.getByAverageRating(new PageRequest(pageNumber, 15));
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

        if (song != null)
            builder.with("song", ":", song);

        Specification<Track> specification = builder.build();
        Page<Track> page = trackService.criteriaTest(specification, new PageRequest(pageNumber, 15 ));
        Integer totalPages = page.getTotalPages();

        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", pageNumber + 1);
        model.addAttribute("trackResults", page.getContent());

        return "/track/songSearchResults";
    }

    @RequestMapping(value = "/song/{id}/{slug}", method = RequestMethod.GET)
    public String songPage(@PathVariable Long id, Model model)
    {
        Track retrievedTrack = trackService.findById(id);
        model.addAttribute("track", retrievedTrack);

        return "/track/track";
    }
}
