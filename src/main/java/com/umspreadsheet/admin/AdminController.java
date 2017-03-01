package com.umspreadsheet.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController
{
    @RequestMapping("")
    public String adminHome(Model model)
    {
        return "/admin/adminHome";
    }
}
