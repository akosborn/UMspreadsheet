package com.umspreadsheet.controller;

import com.umspreadsheet.domain.Track;
import com.umspreadsheet.service.ReviewService;
import com.umspreadsheet.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;

@Controller
@RequestMapping("/reviews")
public class ReviewController
{
    private ReviewService reviewService;
    private TrackService trackService;

    @Autowired
    public ReviewController(ReviewService reviewService,
                            TrackService trackService)
    {
        this.reviewService = reviewService;
        this.trackService = trackService;
    }

    @RequestMapping("")
    public String reviewsHome(Model model)
    {
        model.addAttribute("topFiveSongs", trackService.getTopThreeSongs());
        return "layouts/modular";
    }

    @RequestMapping("/{year}")
    public String getShowsByYear(@PathVariable("year") int year, Model model) throws ParseException
    {
        model.addAttribute("shows", reviewService.getAllShowsByYearWithoutTracks(year));
        model.addAttribute("year", year);
        return "show/year";
    }
}
