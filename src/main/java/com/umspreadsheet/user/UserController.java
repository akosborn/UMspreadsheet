package com.umspreadsheet.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;


@Controller
public class UserController
{
    private SimpleUserService userService;

    @Autowired
    public UserController(SimpleUserService userService)
    {
        this.userService = userService;
    }

    @RequestMapping("/user/{username}")
    public String getProfile(@PathVariable String username, Model model, Principal principal)
    {
        User user = userService.findByUsername(username);
        model.addAttribute("user", user);

        // Check if this request was a redirect after a validation error from updateProfile()
        if (!model.containsAttribute("userForm"))
        {
            model.addAttribute("userForm", new User());
        }
        // Set a flag to notify the page that errors were present
        else
        {
            model.addAttribute("formErrors", true);
        }

        return "/user/profile";
    }

    @RequestMapping(value = "/user/{username}", method = RequestMethod.POST)
    public String updateProfile(@Valid @ModelAttribute("userForm") User user, BindingResult bindingResult,
                                RedirectAttributes redirectAttributes)
    {
        String username = user.getUsername();

        if (bindingResult.hasErrors())
        {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userForm", bindingResult);
            redirectAttributes.addFlashAttribute("userForm", user);

            return "redirect:/user/" + username;
        }

        User retrievedUser = userService.findByUsername(username);

        retrievedUser.setTwitterHandle(user.getTwitterHandle());
        retrievedUser.setLocation(user.getLocation());
        retrievedUser.setFavoriteSongs(user.getFavoriteSongs());

        User savedUser = userService.save(retrievedUser);

        return "redirect:/user/" + username;
    }
}
