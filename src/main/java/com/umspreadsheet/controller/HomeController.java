package com.umspreadsheet.controller;

import com.umspreadsheet.service.GoogleChartServiceImpl;
import com.umspreadsheet.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController
{
    private GoogleChartServiceImpl googleChartService;
    private TrackService trackService;

    public HomeController(GoogleChartServiceImpl googleChartService,
                          TrackService trackService)
    {
        this.googleChartService = googleChartService;
        this.trackService = trackService;
    }

    @RequestMapping("/")
    public String home(Model model)
    {
        model.addAttribute("googleChartTopThree" , googleChartService.getTopThreeSongs());
        return "index";
    }
}
