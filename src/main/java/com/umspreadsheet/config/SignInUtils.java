package com.umspreadsheet.config;

import com.umspreadsheet.user.SimpleUserDetails;
import com.umspreadsheet.user.SimpleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

//@Slf4j
@Component
public class SignInUtils
{
    private SimpleUserService simpleUserService;

    @Autowired
    public SignInUtils(SimpleUserService simpleUserService)
    {
        this.simpleUserService = simpleUserService;
    }

    public void signin(String userId)
    {
        //log.info("### userId = {}", userId);
        UserDetails userDetails = new SimpleUserDetails(simpleUserService.findByUserId(userId));
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails,
                null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
