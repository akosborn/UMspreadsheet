package com.umspreadsheet.controller;

import com.umspreadsheet.service.ReviewService;
import com.umspreadsheet.service.TrackService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController
{
    private TrackService trackService;
    private ReviewService reviewService;

    public HomeController(TrackService trackService,
                          ReviewService reviewService)
    {
        this.trackService = trackService;
        this.reviewService = reviewService;
    }

    @RequestMapping("/")
    public String home(Model model)
    {
        model.addAttribute("topThreeSongs", trackService.getTopThreeSongs());
        model.addAttribute("topThreeShows", reviewService.getTopThreeShows());
        return "index";
    }
}
