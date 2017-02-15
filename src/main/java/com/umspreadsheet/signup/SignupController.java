package com.umspreadsheet.signup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

@Controller
public class SignupController
{
    private UserDetailsService userDetailsService;
    private ProviderSignInUtils providerSignInUtils;

    @Autowired
    public SignupController(UserDetailsService userDetailsService,
                            ProviderSignInUtils providerSignInUtils)
    {
        this.userDetailsService = userDetailsService;
        this.providerSignInUtils = providerSignInUtils;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signupForm(WebRequest webRequest, Model model)
    {
        Connection<?> connection = providerSignInUtils.getConnectionFromSession(webRequest);
        if (connection != null)
        {
            webRequest.setAttribute("message", "Your " + StringUtils.capitalize(connection.getKey().getProviderId()
            ) + " is not associated with a UMSpreadsheet account. Please, sign up.", WebRequest.SCOPE_REQUEST);
            model.addAttribute("signupForm", SignupForm.fromProviderUser(connection.fetchUserProfile()));
        }
        else
        {
            model.addAttribute("signupForm", new SignupForm());
        }

        return "/auth/signup";
    }
}
