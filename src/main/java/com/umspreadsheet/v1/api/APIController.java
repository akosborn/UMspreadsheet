//package com.umspreadsheet.v1.api;
//
//import com.umspreadsheet.v1.review.TrackReview;
//import com.umspreadsheet.v1.review.TrackReviewService;
//import com.umspreadsheet.v1.set.Set;
//import com.umspreadsheet.v1.set.SetService;
//import com.umspreadsheet.v1.show.Show;
//import com.umspreadsheet.v1.show.ShowService;
//import com.umspreadsheet.v1.track.Track;
//import com.umspreadsheet.v1.track.TrackService;
//import com.umspreadsheet.v1.user.SimpleUserService;
//import com.umspreadsheet.v1.user.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Controller;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.validation.Valid;
//import java.util.ArrayList;
//import java.util.List;
//
//@Controller
//@RequestMapping("/api")
//public class APIController
//{
//    private TrackService trackService;
//    private ShowService showService;
//    private SetService setService;
//    private TrackReviewService trackReviewService;
//    private SimpleUserService userService;
//
//    @Autowired
//    public APIController(TrackService trackService, ShowService showService, SetService setService,
//                         TrackReviewService trackReviewService, SimpleUserService userService)
//    {
//        this.trackService = trackService;
//        this.showService = showService;
//        this.setService = setService;
//        this.trackReviewService = trackReviewService;
//        this.userService = userService;
//    }
//
//    @RequestMapping(value = "/tracks")
//    public @ResponseBody
//    List findAllTracks()
//    {
//        List<Track> tracks = trackService.findByShowId((long) 304);
//
//        return tracks;
//    }
//
//    @RequestMapping(value = "/tracks/{id}")
//    public @ResponseBody Track findATrack(@PathVariable Long id)
//    {
//        Track track = trackService.findById(id);
//
//        return track;
//    }
//
//    @RequestMapping(value = "/shows/{id}")
//    public @ResponseBody
//    Show findShow(@PathVariable Long id, HttpServletRequest request)
//    {
//        String referer = request.getHeader("referer");
//
//        Show show = showService.findById(id);
//        String username = getCurrentUsername();
//        List<TrackReview> trackReviews = trackReviewService.findByUsernameAndShow(username, show.getId());
//
//        boolean userAnon = username.equals("anonymousUser");
//        boolean showEdit = referer.contains("edit");
//
//
//        if (!username.equals("anonymousUser") && !referer.contains("edit"))
//        {
//            User user = userService.findByUsername(username);
//            for (Set set : show.getSets())
//            {
//                for (Track track : set.getTracks())
//                {
//                    for (TrackReview trackReview : trackReviews)
//                    {
//                        if (track.getId() == trackReview.getTrack().getId())
//                        {
//                            track.setUserTrackReview(trackReview);
//
//                            break;
//                        }
//                    }
//
//                    if (track.getUserTrackReview() == null)
//                    {
//                        TrackReview newReview = new TrackReview();
//                        newReview.setUser(user);
//                        newReview.setTrack(track);
//                        track.setUserTrackReview(newReview);
//                    }
//                }
//            }
//        }
//
//        return show;
//    }
//
//    @RequestMapping(value = "/shows")
//    public @ResponseBody List findShow()
//    {
//        List<Show> shows = new ArrayList<>();
//        shows.add(showService.findById((long) 400));
//        shows.add(showService.findById((long) 401));
//
//        return shows;
//    }
//
//    @RequestMapping(value = "/shows/{id}", method = RequestMethod.PUT)
//    public @ResponseBody Show updateShow(@RequestBody Show show, @PathVariable Long id)
//    {
//        return showService.save(show);
//    }
//
//    @RequestMapping(value = "/tracks", method = RequestMethod.POST)
//    public @ResponseBody Track addTrack(@RequestBody Track track)
//    {
//        Set set = setService.findById(track.getSet().getId());
//        track.setShow(set.getShow());
//
//        Track savedTrack = trackService.save(track);
//
//        return savedTrack;
//    }
//
//    @RequestMapping(value = "/tracks/{id}", method = RequestMethod.PUT)
//    public @ResponseBody Track updateTrack(@RequestBody Track track, @PathVariable Long id)
//    {
//        Track retrievedTrack = trackService.findById(track.getId());
//        track.setSet(retrievedTrack.getSet());
//
//        return trackService.save(track);
//    }
//
//    @RequestMapping(value = "/tracks/{id}", method = RequestMethod.DELETE)
//    public @ResponseBody void deleteTrack(@PathVariable Long id)
//    {
//        trackService.delete(id);
//    }
//
//
//    @RequestMapping(value = "/sets/{id}", method = RequestMethod.GET)
//    public @ResponseBody
//    Set findSet(@PathVariable Long id)
//    {
//        Set set = setService.findById(id);
//
//        return set;
//    }
//
//    @RequestMapping(value = "/sets/{id}", method = RequestMethod.PUT)
//    public @ResponseBody Set updateSet(@RequestBody Set set, @PathVariable Long id)
//    {
//        Set savedSet = setService.save(set);
//
//        return savedSet;
//    }
//
//    @RequestMapping(value = "/sets", method = RequestMethod.POST)
//    public @ResponseBody Set addSet(@RequestBody Set set)
//    {
//        Set savedSet = setService.save(set);
//
//        return set;
//    }
//
//    @RequestMapping(value = "/sets/{id}")
//    public @ResponseBody void deleteSet(@PathVariable Long id)
//    {
//        setService.delete(id);
//    }
//
//    @RequestMapping(value = "/track-reviews/{id}")
//    public @ResponseBody
//    TrackReview findTrackReview(@PathVariable Long id)
//    {
//        return trackReviewService.findById(id);
//    }
//
//    @RequestMapping(value = "/track-reviews", method = RequestMethod.POST)
//    public @ResponseBody
//    TrackReview addTrackReview(@RequestBody TrackReview trackReview)
//    {
//        // Make sure not a duplicate
//        TrackReview duplicateReview = trackReviewService.findByUsernameAndTrackId(getCurrentUsername(),
//                trackReview.getTrack().getId());
//
//        // If not a duplicate, save; if a duplicate, return the original
//        if (duplicateReview == null)
//            return trackReviewService.save(trackReview);
//        else
//            return duplicateReview;
//    }
//
//    @RequestMapping(value = "/track-reviews/{id}", method = RequestMethod.DELETE)
//    public @ResponseBody
//    void deleteTrackReview(@PathVariable Long id)
//    {
//        trackReviewService.delete(id);
//    }
//
//    @RequestMapping(value = "/track-reviews/{id}", method = RequestMethod.PUT)
//    public @ResponseBody
//    TrackReview updateTrackReview(@Valid @RequestBody TrackReview trackReview, @PathVariable Long id,
//                                  BindingResult bindingResult)
//    {
//        if (bindingResult.hasErrors())
//        {
//            return trackReview;
//        }
//
//        return trackReviewService.save(trackReview);
//    }
//
//    @RequestMapping(value = "/track-reviews/show/{id}", method = RequestMethod.GET)
//    public @ResponseBody List getTrackReviewsByShow(@PathVariable Long id)
//    {
//        return trackReviewService.findByShowId(id);
//    }
//
//
//    private String getCurrentUsername()
//    {
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        String username;
//
//        if (principal instanceof UserDetails)
//        {
//            username = ((UserDetails) principal).getUsername();
//        } else
//        {
//            username = principal.toString();
//        }
//
//        return username;
//    }
//}
