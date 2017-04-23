package com.umspreadsheet.about;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/about")
public class AboutController
{
    @RequestMapping("")
    public String aboutPage()
    {
        return "/about/about";
    }

    @RequestMapping("/technologies")
    public String technologiesPage()
    {
        return "/about/technologies";
    }
}
