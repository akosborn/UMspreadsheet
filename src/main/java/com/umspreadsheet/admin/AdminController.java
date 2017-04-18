package com.umspreadsheet.admin;

import com.umspreadsheet.set.Set;
import com.umspreadsheet.set.SetDTO;
import com.umspreadsheet.review.TrackReviewService;
import com.umspreadsheet.set.SetService;
import com.umspreadsheet.show.Show;
import com.umspreadsheet.show.ShowDTO;
import com.umspreadsheet.show.ShowService;
import com.umspreadsheet.track.TrackService;
import com.umspreadsheet.user.SimpleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

        return "redirect:/shows/show";
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
}
