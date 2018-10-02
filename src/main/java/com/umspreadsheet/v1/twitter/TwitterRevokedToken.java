package com.umspreadsheet.v1.twitter;

import org.springframework.social.ExpiredAuthorizationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TwitterRevokedToken
{
    @RequestMapping("/twitter/revoked")
    public void simulateExpiredToken()
    {
        throw new ExpiredAuthorizationException("twitter");
    }
}
