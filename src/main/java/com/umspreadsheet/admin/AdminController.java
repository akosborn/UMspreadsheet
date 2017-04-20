package com.umspreadsheet.admin;

import com.umspreadsheet.exception.DataNotFoundException;
import com.umspreadsheet.review.TrackReview;
import com.umspreadsheet.review.TrackReviewForm;
import com.umspreadsheet.set.Set;
import com.umspreadsheet.set.SetDTO;
import com.umspreadsheet.review.TrackReviewService;
import com.umspreadsheet.set.SetService;
import com.umspreadsheet.show.Show;
import com.umspreadsheet.show.ShowDTO;
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

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController
{
    private ShowService showService;
    private TrackService trackService;
    private TrackReviewService trackReviewService;
    private SimpleUserService userService;
    private SetService setService;

    @Autowired
    public AdminController(ShowService showService, TrackService trackService,
                          TrackReviewService trackReviewService, SimpleUserService userService, SetService setService)
    {
        this.showService = showService;
        this.trackService = trackService;
        this.trackReviewService = trackReviewService;
        this.userService = userService;
        this.setService = setService;
    }

    @RequestMapping("")
    public String adminHome(Model model)
    {
        return "/admin/adminHome";
    }

    @RequestMapping("/add-show")
    public String showForm(Model model)
    {
        model.addAttribute("show", new ShowDTO());

        return "/admin/addShow";
    }

    @RequestMapping(value = "/add-show", method = RequestMethod.POST)
    public String addShow(ShowDTO showDTO, RedirectAttributes redirectAttributes)
    {
        Show show = new Show();

        show.setDate(showDTO.getDate());
        show.setCity(showDTO.getCity());
        show.setState(showDTO.getState());
        show.setVenue(showDTO.getVenue());

        Show savedShow = showService.save(show);

        redirectAttributes.addAttribute("showId", savedShow.getId());

        // redirects to admin-specific editable show page
        return "redirect:/admin/edit-show";
    }

    @RequestMapping(value = "/add-set", method = RequestMethod.POST)
    public String addSet(SetDTO setDTO, RedirectAttributes redirectAttributes)
    {
        Set set = new Set();
        set.setName(setDTO.getName());
        set.setShow(showService.findById(setDTO.getShowId()));

        setService.save(set);

        redirectAttributes.addAttribute("showId", setDTO.getShowId());

        return "redirect:/shows/show";
    }

    @RequestMapping(value = "/add-track", method = RequestMethod.POST)
    public String addTrack(Track track, RedirectAttributes redirectAttributes)
    {
        track.setShow(showService.findById(track.getShowId()));
        track.setSet(setService.findById(track.getSetId()));

        Track savedTrack = trackService.save(track);

        redirectAttributes.addAttribute("showId", track.getShowId());

        return "redirect:/admin/edit-show";
    }

    // Edit and update a song
    @RequestMapping(value = "/edit-track", method = RequestMethod.PUT)
    public String editTrack(Track track, RedirectAttributes redirectAttributes)
    {
        // Retrieve specified track from database
        Track oldTrack = trackService.findById(track.getId());

        // Update variables that may have changed
        oldTrack.setSong(track.getSong());
        oldTrack.setSegue(track.getSegue());
        oldTrack.setFluidSegue(track.getFluidSegue());
        oldTrack.setJam(track.getJam());
        oldTrack.setLength(track.getLength());
        oldTrack.setNotes(track.getNotes());
        oldTrack.setType(track.getType());

        // Update track and assign to updatedTrack
        Track updatedTrack = trackService.save(oldTrack);

        redirectAttributes.addAttribute("showId", track.getShowId());

        return "redirect:/admin/edit-show";
    }

    // Returns requested show's edit page
    @RequestMapping(value = "/edit-show", params = "showId", method = RequestMethod.GET)
    public String editShowPage(@RequestParam(value = "showId") Long showId, Model model) throws
            DataNotFoundException
    {
        Show show = showService.findById(showId);

        if (show == null)
            throw new DataNotFoundException("Show with id=" + showId + " not found.");

        // Find the requested show using the showID request parameter
        model.addAttribute("show", showService.findById(showId));

        // Create and add SetDTO to be used in setAddForm
        SetDTO setDTO = new SetDTO();
        setDTO.setShowId(showId);
        model.addAttribute("set", setDTO);

        // Create and add Track to be used in trackAddForm
        Track track = new Track();
        track.setShowId(showId);
        model.addAttribute("track", track);

        return "/admin/editShow";
    }
}