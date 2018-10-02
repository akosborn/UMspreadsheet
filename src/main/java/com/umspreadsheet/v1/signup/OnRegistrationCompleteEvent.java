package com.umspreadsheet.v1.signup;

import com.umspreadsheet.v1.user.User;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

public class OnRegistrationCompleteEvent extends ApplicationEvent
{
    private String appURL;
    private Locale locale;
    private User user;

    public OnRegistrationCompleteEvent(User user, Locale locale, String appURL)
    {
        super(user);

        this.user = user;
        this.locale = locale;
        this.appURL = appURL;
    }

    public String getAppURL()
    {
        return appURL;
    }

    public void setAppURL(String appURL)
    {
        this.appURL = appURL;
    }

    public Locale getLocale()
    {
        return locale;
    }

    public void setLocale(Locale locale)
    {
        this.locale = locale;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }
}
