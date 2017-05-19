package com.umspreadsheet.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

        return "user/profile";
    }

    @RequestMapping(value = "/user/profile", method = RequestMethod.POST)
    public String updateProfile(User user)
    {
        String username = user.getUsername();
        User retrievedUser = userService.findByUsername(username);

        retrievedUser.setTwitterHandle(user.getTwitterHandle());
        retrievedUser.setLocation(user.getLocation());
        retrievedUser.setFavoriteSongs(user.getFavoriteSongs());

        User savedUser = userService.save(retrievedUser);

        return "redirect:/user/" + username;
    }
}
