package com.umspreadsheet.controller;

import com.umspreadsheet.show.ShowService;
import com.umspreadsheet.track.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController
{
    private TrackService trackService;
    private ShowService showService;

    @Autowired
    public HomeController(TrackService trackService,
                          ShowService showService)
    {
        this.trackService = trackService;
        this.showService = showService;
    }

    @RequestMapping("/")
    public String home(Model model)
    {
        model.addAttribute("topThreeSongs", trackService.getTopThreeSongs());
        model.addAttribute("topThreeShows", showService.getTopThreeShows());
        model.addAttribute("lastTwoShows", showService.getLastTwoShows());
        return "index";
    }
}
