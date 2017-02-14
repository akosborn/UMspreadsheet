package com.umspreadsheet.review;

import com.umspreadsheet.model.*;
import com.umspreadsheet.show.Show;
import com.umspreadsheet.show.ShowService;
import com.umspreadsheet.track.Track;
import com.umspreadsheet.track.TrackService;
import com.umspreadsheet.user.SimpleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user/review")
public class ReviewController
{
    private ShowService showService;
    private TrackService trackService;
    private TrackReviewService trackReviewService;
    private SimpleUserService userService;

    @Autowired
    public ReviewController(ShowService showService,
                            TrackService trackService,
                            TrackReviewService trackReviewService,
                            SimpleUserService userService)
    {
        this.showService = showService;
        this.trackService = trackService;
        this.trackReviewService = trackReviewService;
        this.userService = userService;
    }

    // Returns view for all reviewable tracks for the specified show
    @RequestMapping(value = "", params = "showId", method = RequestMethod.GET)
    public String reviewShow(@RequestParam("showId") Long showId, Model model)
    {
        Show show = showService.findById(showId);
        List<TrackReview> trackReviews = trackReviewService.findByUserAndShow(show, userService.findByUsername
                ("akosborn"));

        Map<String, Map<Track, TrackReview>> trackAndReviewMap = new LinkedHashMap<>();
        for (Set set : show.getSets())
        {
            Map<Track, TrackReview> map = new LinkedHashMap<>();
            trackAndReviewMap.put(set.getName(), map);

            for (Track track : set.getTracks())
            {
                map.put(track, null);
                for (TrackReview trackReview : trackReviews)
                {
                    if (track.getId().equals(trackReview.getTrack().getId()))
                    {
                        map.put(track, trackReview);
                        break;
                    }
                }
            }
        }

        model.addAttribute("trackAndReviewMap", trackAndReviewMap);
        model.addAttribute("show", showService.findById(showId));

        return "/user/showReview";
    }

    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public String deleteTrackReview(@RequestParam("reviewId") Long reviewId,
                                    @RequestParam("showId") Long showId, RedirectAttributes redirectAttributes)
    {
        trackReviewService.delete(reviewId);

        redirectAttributes.addAttribute("showId", showId);
        redirectAttributes.addFlashAttribute("reviewDeleted", "true");

        return "redirect:/user/review";
    }

    @RequestMapping(value = "/track", params = "trackId")
    public String reviewTrack(@RequestParam("trackId") Long trackId, Model model)
    {
        model.addAttribute("trackReview", new TrackReview(trackService.findById(trackId)));

        return "/user/trackReviewForm";
    }

    @RequestMapping(value = "/track", params = "reviewId", method = RequestMethod.GET)
    public String updateTrackReview(@RequestParam("reviewId") Long reviewId, Model model)
    {
        model.addAttribute("trackReview", trackReviewService.findById(reviewId));
        model.addAttribute("update", "true");

        return "/user/trackReviewForm";
    }

    @RequestMapping(value = "/track", method = RequestMethod.POST)
    public String saveTrackReview(TrackReview trackReview, RedirectAttributes redirectAttributes)
    {
        trackReview.setUser(userService.findByUsername("akosborn"));
        TrackReview savedReview = trackReviewService.save(trackReview);

        redirectAttributes.addAttribute("showId", savedReview.getTrack().getShow().getId());
        redirectAttributes.addFlashAttribute("submitted", "true");

        return "redirect:/user/review";
    }

    @RequestMapping(value = "/track", method = RequestMethod.PUT)
    public String updateTrackReview(TrackReview trackReview, RedirectAttributes redirectAttributes)
    {
        redirectAttributes.addAttribute("showId", trackReviewService.save(trackReview).getTrack().getShow().getId());
        redirectAttributes.addFlashAttribute("edited", "true");

        return "redirect:/user/review";
    }
}
