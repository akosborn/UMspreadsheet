package com.umspreadsheet.user;

import com.umspreadsheet.user.CurrentUser;
import com.umspreadsheet.user.User;
import com.umspreadsheet.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;


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
