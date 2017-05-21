package com.umspreadsheet.utils;

import com.umspreadsheet.user.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class SessionUtils
{
    private SessionRegistry sessionRegistry;

    public SessionUtils(SessionRegistry sessionRegistry)
    {
        this.sessionRegistry = sessionRegistry;
    }

    public void expireUserSessions(String username)
    {
        for (Object principal : sessionRegistry.getAllPrincipals())
        {
            if (principal instanceof org.springframework.security.core.userdetails.User)
            {
                UserDetails userDetails = (UserDetails) principal;
                if (userDetails.getUsername().equals(username))
                {
                    for (SessionInformation sessionInformation : sessionRegistry.getAllSessions(userDetails, true))
                    {
                        sessionInformation.expireNow();
                    }
                }
            }
        }
    }

    public String getCurrentUsername()
    {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;

        if (principal instanceof UserDetails)
        {
            username = ((UserDetails) principal).getUsername();
        } else
        {
            username = principal.toString();
        }

        return username;
    }
}
