package com.umspreadsheet.facebook;

import org.springframework.social.ExpiredAuthorizationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FacebookExpiredToken
{
    @RequestMapping("/facebook/expired")
    public void simulateExpiredToken()
    {
        throw new ExpiredAuthorizationException("facebook");
    }
}
