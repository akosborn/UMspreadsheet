package com.umspreadsheet.controller;

import com.umspreadsheet.service.TrackService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController
{
    private TrackService trackService;

    public HomeController(TrackService trackService)
    {
        this.trackService = trackService;
    }

    @RequestMapping("/")
    public String home(Model model)
    {
        model.addAttribute("googleChartTopThree" , trackService.getTopThreeSongs());
        return "index";
    }
}
