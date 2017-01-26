package com.umspreadsheet.controller;

import com.umspreadsheet.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController
{
    private ShowService showService;

    @Autowired
    public HomeController(ShowService showService)
    {
        this.showService = showService;
    }

    @RequestMapping("/")
    public String home()
    {
        return "index";
    }
}
