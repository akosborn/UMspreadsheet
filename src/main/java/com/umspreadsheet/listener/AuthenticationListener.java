package com.umspreadsheet.listener;

import com.umspreadsheet.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AbstractAuthenticationEvent;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;


@Component
public class AuthenticationListener implements ApplicationListener<AbstractAuthenticationEvent>
{
    private UserServiceImpl userService;

    @Autowired
    public AuthenticationListener(UserServiceImpl userService)
    {
        this.userService = userService;
    }

    @Override
    public void onApplicationEvent(AbstractAuthenticationEvent abstractAuthenticationEvent)
    {
        if (abstractAuthenticationEvent instanceof AuthenticationSuccessEvent)
        {
            // do stuff
        }

        if (abstractAuthenticationEvent instanceof AuthenticationFailureBadCredentialsEvent)
        {
            // do stuff
        }
    }
}
