package com.umspreadsheet.wormblog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/wormblog")
public class WormBlogController
{
    private WormBlogService wormBlogService;

    @Autowired
    public WormBlogController(WormBlogService wormBlogService)
    {
        this.wormBlogService = wormBlogService;
    }

    @RequestMapping("")
    public String wormBlogHome(Model model)
    {
        model.addAttribute("posts", wormBlogService.findAll());

        return "/wormblog/wormblog";
    }
}
