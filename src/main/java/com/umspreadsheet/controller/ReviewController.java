package com.umspreadsheet.controller;

import com.umspreadsheet.domain.ShowReview;
import com.umspreadsheet.domain.UmShow;
import com.umspreadsheet.service.ShowService;
import com.umspreadsheet.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;

@Controller
@RequestMapping("/user/review")
public class ReviewController
{
    private ShowService showService;
    private TrackService trackService;

    @Autowired
    public ReviewController(ShowService showService,
                            TrackService trackService)
    {
        this.showService = showService;
        this.trackService = trackService;
    }

//    TODO return view with show review form
    // Returns an instance of ShowReview to be used in the review form
    @RequestMapping(value = "", method = RequestMethod.GET)
    public void reviewShow(@RequestParam("showId") Long showId,
                           Model model, ShowReview showReview)
    {
        model.addAttribute("show", showService.findById(showId));
        model.addAttribute("showReview", showReview);
    }
}
