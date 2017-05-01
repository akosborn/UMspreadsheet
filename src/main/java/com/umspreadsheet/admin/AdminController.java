package com.umspreadsheet.admin;

import com.umspreadsheet.exception.DataNotFoundException;
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
import com.umspreadsheet.user.User;
import com.umspreadsheet.wormblog.WormBlogPost;
import com.umspreadsheet.wormblog.WormBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.lang.invoke.MethodType;

@Controller
@RequestMapping("/admin")
public class AdminController
{
    private ShowService showService;
    private TrackService trackService;
    private TrackReviewService trackReviewService;
    private SimpleUserService userService;
    private SetService setService;
    private WormBlogService wormBlogService;

    @Autowired
    public AdminController(ShowService showService, TrackService trackService,
                           TrackReviewService trackReviewService, SimpleUserService userService,
                           SetService setService, WormBlogService wormBlogService)
    {
        this.showService = showService;
        this.trackService = trackService;
        this.trackReviewService = trackReviewService;
        this.userService = userService;
        this.setService = setService;
        this.wormBlogService = wormBlogService;
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

        return "redirect:/admin/edit-show";
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

    @RequestMapping(value = "/publish-post")
    public String newPostPage(Model model)
    {
        model.addAttribute("post", new WormBlogPost());

        return "/wormblog/newPost";
    }

    @RequestMapping(value = "/publish-post", method = RequestMethod.POST)
    public String newPostPage(@Valid @ModelAttribute("post") WormBlogPost post, BindingResult bindingResult, Model
            model)
    {
        if (bindingResult.hasErrors())
        {
            return "/wormblog/newPost";
        }
        post.setAuthor(userService.findByUsername(getCurrentUsername()));

        WormBlogPost savedPost = wormBlogService.save(post);

        return "redirect:/wormblog";
    }

    @RequestMapping("/manage-wormblog")
    public String manageWormBlog(Model model)
    {
        model.addAttribute("posts", wormBlogService.findAll());

        return "/wormblog/manageWormBlog";
    }

    // Deletes a WormBlog post
    @RequestMapping(value = "/manage-wormblog", method = RequestMethod.DELETE)
    public String deleteWormBlogPost(@RequestParam("id") Long id)
    {
        wormBlogService.delete(id);

        return "redirect:/admin";
    }

    // Returns page for managing users
    @RequestMapping(value = "/manage-users")
    public String manageUsers(Model model)
    {
        model.addAttribute("users", userService.findAll());

        return "/admin/manageUsers";
    }

    // Deletes a WormBlog post
    @RequestMapping(value = "/manage-users", method = RequestMethod.PUT)
    public String suspendUser(User user, Model model)
    {
        User retrievedUser = userService.findByUsername(user.getUsername());

        // Suspend and update the user
        retrievedUser.setIsNotSuspended(false);
        userService.save(retrievedUser);

        return "redirect:/admin/manage-users";
    }

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
}
