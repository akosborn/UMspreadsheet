package com.umspreadsheet.review;

import com.umspreadsheet.model.*;
import com.umspreadsheet.model.Set;
import com.umspreadsheet.show.Show;
import com.umspreadsheet.show.ShowService;
import com.umspreadsheet.track.Track;
import com.umspreadsheet.track.TrackService;
import com.umspreadsheet.user.SimpleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

@Controller
public class ReviewController
{
    private ShowService showService;
    private TrackService trackService;
    private TrackReviewService trackReviewService;
    private SimpleUserService userService;

    @Autowired
    public ReviewController(ShowService showService, TrackService trackService,
                            TrackReviewService trackReviewService, SimpleUserService userService)
    {
        this.showService = showService;
        this.trackService = trackService;
        this.trackReviewService = trackReviewService;
        this.userService = userService;
    }

    // Top-rated songs page
    @RequestMapping("/songs")
    public String topSongsPage(Model model)
    {
        model.addAttribute("topFourtyTracks", trackService.getTopFourtySongs());

        return "/track/topSongs";
    }

    @RequestMapping("/shows/find")
    public String findShowFilter()
    {
        return "redirect:/shows#show-filter";
    }

    // Top-rated shows page
    @RequestMapping("/shows")
    public String topShowsPage(Model model)
    {
        model.addAttribute("topTwentyShows", setNumberOfReviews(showService.getTopTwentyShows()));

        return "/show/topShows";
    }

    @RequestMapping("/songs/find")
    public String findSongFilter()
    {
        return "redirect:/songs#song-filter";
    }

    // Returns view for all reviewable tracks for the specified show
    @RequestMapping(value = "/shows/show", params = "showId", method = RequestMethod.GET)
    public String reviewShow(@RequestParam(value = "showId") Long showId, Model model)
    {
        // Find the current user's username
        String username = getCurrentUsername();
        Show show = showService.findById(showId);

        // List of the show's track IDs to be used for Javascript dynamic slider creation
        Map<Long, Double> trackIdMap = new HashMap<>();

        // Create a map for the show where the string represents a set
        Map<String, Map<Track, TrackReviewForm>> trackAndReviewMap =
                getUsersTracksAndReviews(username, show, trackIdMap);

        model.addAttribute("trackIdMap", trackIdMap);
        model.addAttribute("trackAndReviewMap", trackAndReviewMap);
        model.addAttribute("show", showService.findById(showId));
        model.addAttribute("trackReviewForm", new TrackReviewForm(username));

        return "/user/show";
    }

    // Delete a review
    @RequestMapping(value = "/shows/show", method = RequestMethod.DELETE)
    public String deleteTrackReview(@RequestParam("reviewId") Long reviewId,
                                    @RequestParam("showId") Long showId, RedirectAttributes redirectAttributes)
    {
        trackReviewService.delete(reviewId);

        redirectAttributes.addAttribute("showId", showId);
        redirectAttributes.addFlashAttribute("reviewDeleted", "true");

        return "redirect:/shows/show";
    }

    /*@RequestMapping(value = "/track", params = "trackId")
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
    }*/

    // Endpoint for new track review submission
    @RequestMapping(value = "/shows/show", method = RequestMethod.POST)
    public String saveTrackReview(TrackReviewForm trackReviewForm, RedirectAttributes redirectAttributes)
    {
        TrackReview trackReview = new TrackReview();
        if (trackReviewForm.getId() != null)
            trackReview.setId(trackReviewForm.getId());

        trackReview.setScore(trackReviewForm.getScore());
        trackReview.setUser(userService.findByUsername(trackReviewForm.getUsername()));
        trackReview.setTrack(trackService.findById(trackReviewForm.getTrackId()));
        trackReview.setComment(trackReviewForm.getComment());

        TrackReview savedTrackReview = trackReviewService.save(trackReview);

        redirectAttributes.addAttribute("showId", savedTrackReview.getTrack().getShow().getId());
        redirectAttributes.addFlashAttribute("submitted", "true");

        return "redirect:/shows/show";
    }


    /*@RequestMapping(value = "/track", method = RequestMethod.PUT)
    public String updateTrackReview(TrackReview trackReview, RedirectAttributes redirectAttributes)
    {
        redirectAttributes.addAttribute("showId", trackReviewService.save(trackReview).getTrack().getShow().getId());
        redirectAttributes.addFlashAttribute("edited", "true");

        return "redirect:/user/review";
    }*/

    private String getCurrentUsername()
    {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;

        if (principal instanceof UserDetails)
        {
            username = ((UserDetails) principal).getUsername();
        } else
        {
            username = principal.toString();
        }

        return username;
    }

    private Map<String, Map<Track, TrackReviewForm>> getUsersTracksAndReviews(String username, Show show,
                                                                          Map<Long, Double> trackIdMap)
    {
        // Find the current user's reviews for the specified show
        List<TrackReview> trackReviews = trackReviewService.findByUserAndShow
                (show, userService.findByUsername(username));

        Map<String, Map<Track, TrackReviewForm>> trackAndReviewMap = new LinkedHashMap<>();
        // Loop through each set
        for (Set set : show.getSets())
        {
            // Create a map for the set
            Map<Track, TrackReviewForm> map = new LinkedHashMap<>();
            // Add the set name(String) and the map of its tracks/reviews
            trackAndReviewMap.put(set.getName(), map);

            // Loop through all tracks in the set
            for (Track track : set.getTracks())
            {
                // Set default score to null for Javascript slider dynamic setting
                Double score = null;

                // Create new trackReviewForm and set username via constructor
                TrackReviewForm trackReviewForm = new TrackReviewForm(username, track.getId());

                // Loop through user's reviews for this show
                for (TrackReview trackReview : trackReviews)
                {
                    // Check if the user has reviewed the track
                    if (track.getId().equals(trackReview.getTrack().getId()))
                    {
                        // If user has reviewed the track, populate a new trackReviewForm
                        trackReviewForm = new TrackReviewForm(trackReview);
                        score = trackReview.getScore();
                        break;
                    }
                }

                map.put(track, trackReviewForm);
                trackIdMap.put(track.getId(), score);
            }
        }

        return trackAndReviewMap;
    }

    private List<Show> setNumberOfReviews(List<com.umspreadsheet.show.Show> shows)
{
    for (com.umspreadsheet.show.Show show : shows)
    {
        Long numberOfReviews = 0L;
        for (Set set: show.getSets())
        {
            for (Track track : set.getTracks())
            {
                numberOfReviews += track.getReviews().size();
            }
        }
        show.setNumberOfReviews(numberOfReviews);
    }

    return shows;
}
}
