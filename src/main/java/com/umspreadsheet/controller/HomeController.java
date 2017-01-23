package com.umspreadsheet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController
{
    @RequestMapping("/")
    public String home()
    {
        return "index";
    }

    @RequestMapping("/login")
    public String login()
    {
        return "auth/login";
    }
}
