package com.umspreadsheet.controller;

import com.umspreadsheet.domain.ShowReview;
import com.umspreadsheet.domain.Track;
import com.umspreadsheet.domain.TrackReview;
import com.umspreadsheet.domain.UmShow;
import com.umspreadsheet.repository.TrackRepository;
import com.umspreadsheet.repository.TrackReviewRepository;
import com.umspreadsheet.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;

@Controller
@RequestMapping("/user/review")
public class ReviewController
{
    private ShowService showService;
    private TrackService trackService;
    private TrackReviewService trackReviewService;
    private UserServiceImpl userService;

    @Autowired
    public ReviewController(ShowService showService,
                            TrackService trackService,
                            TrackReviewService trackReviewService,
                            UserServiceImpl userService)
    {
        this.showService = showService;
        this.trackService = trackService;
        this.trackReviewService = trackReviewService;
        this.userService = userService;
    }

    // Returns view for all reviewable tracks for the specified show
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String reviewShow(@RequestParam("showId") Long showId,
                           Model model)
    {
        model.addAttribute("show", showService.findById(showId));

        return "/user/showReview";
    }

    @RequestMapping(value = "/track")
    public String reviewTrack(@RequestParam("trackId") Long trackId,
                              Model model)
    {
        Track track = trackService.findById(trackId);
        model.addAttribute("trackReview", new TrackReview(track));

        return "/user/trackReviewForm";
    }

    @RequestMapping(value = "/track", method = RequestMethod.POST)
    public String saveTrackReview(TrackReview trackReview,
                                  RedirectAttributes redirectAttributes)
    {
        System.out.println(trackReview);
        trackReview.setUser(userService.findByUsername("akosborn"));
        TrackReview savedReview = trackReviewService.save(trackReview);
        redirectAttributes.addAttribute("showId", savedReview.getTrack().getShow().getId());
        redirectAttributes.addFlashAttribute("submitted", "true");

        return "redirect:/user/review";
    }
}
