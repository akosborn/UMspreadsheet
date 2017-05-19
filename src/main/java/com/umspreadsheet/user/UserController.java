package com.umspreadsheet.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
