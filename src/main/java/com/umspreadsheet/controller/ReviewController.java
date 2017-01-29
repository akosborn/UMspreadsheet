package com.umspreadsheet.controller;

import com.umspreadsheet.service.ShowService;
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
    private ShowService showService;

    @Autowired
    public ReviewController(ShowService showService)
    {
        this.showService = showService;
    }

    @RequestMapping("")
    public String reviewsHome(Model model)
    {
        model.addAttribute("recentlyReviewedShows", showService.getAllShowsHavingReviews());
        return "layouts/modular";
    }

    @RequestMapping("/{year}")
    public String getShowsByYear(@PathVariable("year") int year, Model model) throws ParseException
    {
        model.addAttribute("shows", showService.getAllShowsByYearWithoutTracks(year));
        model.addAttribute("year", year);
        return "show/year";
    }
}
