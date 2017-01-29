package com.umspreadsheet.controller;

import com.umspreadsheet.domain.CurrentUser;
import com.umspreadsheet.domain.User;
import com.umspreadsheet.service.UserDetailsImpl;
import com.umspreadsheet.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;


@Controller
public class UserController
{
    private UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService)
    {
        this.userService = userService;
    }

    @RequestMapping("/profile")
    public String getProfile(Model model, Principal principal)
    {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("currentUser", new CurrentUser(user));

        return "user/profile";
    }
}
