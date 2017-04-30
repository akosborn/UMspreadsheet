package com.umspreadsheet.signup;

import com.umspreadsheet.config.SignInUtils;
import com.umspreadsheet.user.SimpleUserDetails;
import com.umspreadsheet.user.SimpleUserService;
import com.umspreadsheet.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;

@Controller
public class SignupController
{
    private SimpleUserService simpleUserService;
    private ProviderSignInUtils providerSignInUtils;
    private SignInUtils signInUtils;

    @Autowired
    public SignupController(SimpleUserService simpleUserService,
                            ProviderSignInUtils providerSignInUtils)
    {
        this.simpleUserService = simpleUserService;
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

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signup(@Valid SignupForm signupForm, BindingResult formBinding,
                         WebRequest webRequest, Model model)
    {
        if (formBinding.hasErrors())
        {
            return "/auth/signup";
        }

        User user = registerNewUser(signupForm, formBinding, model);

        if (user == null)
        {
            return "/auth/signup";
        }
        else
        {
            SignInUtils.signin(new SimpleUserDetails(user));
            providerSignInUtils.doPostSignUp(user.getUserId(), webRequest);

            return "redirect:/";
        }
    }

    private User registerNewUser(SignupForm signupForm, BindingResult formBinding, Model model)
    {
        // Check for duplicate email
        if (simpleUserService.findByEmail(signupForm.getEmail()) != null)
        {
            model.addAttribute("emailTaken", "Email \"" + signupForm.getEmail() + "\" is already in use.");

            if (simpleUserService.findByUsername(signupForm.getUsername()) != null)
            {
                model.addAttribute("usernameTaken", "Username \"" + signupForm.getUsername() + "\" is taken.");
            }

            return null;
        }

        // Check for duplicate username
        if (simpleUserService.findByUsername(signupForm.getUsername()) != null)
        {
            model.addAttribute("usernameTaken", "Username \"" + signupForm.getUsername() + "\" is taken.");

            if (simpleUserService.findByEmail(signupForm.getEmail()) != null)
            {
                model.addAttribute("emailTaken", "Email \"" + signupForm.getEmail() + "\" is already in use.");
            }

            return null;
        }

        return simpleUserService.save(signupForm);
    }
}
