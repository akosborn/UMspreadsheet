package com.umspreadsheet.wormblog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/wormblog")
public class WormBlogController
{
    @RequestMapping("")
    public String wormBlogHome()
    {
        return "/wormblog/wormblog";
    }
}
