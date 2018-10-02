package com.umspreadsheet.v1.signin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SigninController
{
    @RequestMapping(value = "/signin", method = RequestMethod.GET)
    public String signin()
    {
        return "/auth/signin";
    }

    @RequestMapping(value = "/signin/reset-password", method = RequestMethod.GET)
    public String resetPassword()
    {
        return "/auth/forgotPassword";
    }
}
