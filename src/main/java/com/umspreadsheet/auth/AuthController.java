package com.umspreadsheet.auth;

import com.umspreadsheet.user.User;
import com.umspreadsheet.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController
{
    private UserService userService;

    @Autowired
    public AuthController(UserService userService)
    {
        this.userService = userService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String saveUser(User user, RedirectAttributes redirectAttributes)
    {
        User savedUser = userService.save(user);
        if (savedUser == null)
        {
            redirectAttributes.addFlashAttribute("signup", "false");
            return "redirect:/register";
        }

        redirectAttributes.addFlashAttribute("signup", "You have successfully signed up.");
        return "redirect:/login";
    }
}
