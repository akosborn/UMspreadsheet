package com.umspreadsheet.config;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

//@Slf4j
public class SignInUtils
{
    public static void signin(UserDetails userDetails)
    {
        //log.info("### userId = {}", userId);
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails,
                userDetails.getPassword(), userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
