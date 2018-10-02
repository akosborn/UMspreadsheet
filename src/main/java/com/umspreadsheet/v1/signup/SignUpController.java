package com.umspreadsheet.v1.signup;


import com.umspreadsheet.v1.user.SimpleUserService;
import com.umspreadsheet.v1.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.*;

@Controller
public class SignUpController
{
    private SimpleUserService simpleUserService;
    private ProviderSignInUtils providerSignInUtils;
    private ApplicationEventPublisher applicationEventPublisher;
    private MessageSource messageSource;

    @Autowired
    public SignUpController(SimpleUserService simpleUserService, ProviderSignInUtils providerSignInUtils,
                            ApplicationEventPublisher applicationEventPublisher, MessageSource messageSource)
    {
        this.simpleUserService = simpleUserService;
        this.providerSignInUtils = providerSignInUtils;
        this.applicationEventPublisher = applicationEventPublisher;
        this.messageSource = messageSource;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signUpForm(WebRequest webRequest, Model model)
    {
        Connection<?> connection = providerSignInUtils.getConnectionFromSession(webRequest);
        if (connection != null)
        {
            webRequest.setAttribute("message", "Your " + StringUtils.capitalize(connection.getKey().getProviderId()
            ) + " is not associated with a UMSpreadsheet account. Please, sign up.", WebRequest.SCOPE_REQUEST);
            model.addAttribute("signUpForm", SignUpForm.fromProviderUser(connection.fetchUserProfile()));
        }
        else
        {
            model.addAttribute("signUpForm", new SignUpForm());
        }

        return "/auth/signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signUp(@Valid SignUpForm signUpForm, BindingResult formBinding,
                         WebRequest webRequest, Model model, RedirectAttributes redirectAttributes)
    {
        if (formBinding.hasErrors())
        {
            return "/auth/signup";
        }

        User user = registerNewUser(signUpForm, formBinding, model);

        if (user == null)
        {
            return "/auth/signup";
        }

        try
        {
            String appURL = webRequest.getContextPath();
            applicationEventPublisher.publishEvent(new OnRegistrationCompleteEvent(user, webRequest.getLocale(), appURL));
        } catch (Exception ex)
        {
            return "/auth/signup";
        }


//      SignInUtils.signin(new SimpleUserDetails(user));
//      providerSignInUtils.doPostSignUp(user.getUserId(), webRequest);

        String message = messageSource.getMessage("signup.checkEmail", null, webRequest.getLocale());
        redirectAttributes.addFlashAttribute("successMessages", Collections.singleton(message));

        return "redirect:/signin";
    }

    @RequestMapping(value = "/signup-confirm")
    public String confirmSignUp(@RequestParam("token") String token, WebRequest webRequest, Model model)
    {
        Locale locale = webRequest.getLocale();

        VerificationToken verificationToken = simpleUserService.getVerificationToken(token);
        if (verificationToken == null)
        {
            String message = messageSource.getMessage("message.invalidToken", null, locale);
            model.addAttribute("dangerMessages", Collections.singleton(message));

            return "redirect:/signup";
        }

        User user = verificationToken.getUser();
        Calendar calendar = Calendar.getInstance();
        if ((verificationToken.getExpirationDate().getTime() - calendar.getTime().getTime()) <= 0)
        {
            String message = messageSource.getMessage("message.expiredToken", null, locale);
            model.addAttribute("dangerMessages", Collections.singletonList(message));

            return "redirect:/signup";
        }

        // Enable user and update
        user.setIsEnabled(true);
        simpleUserService.save(user);

        String message = messageSource.getMessage("signup.verificationSuccess", null, webRequest
                .getLocale());
        model.addAttribute("successMessages", Collections.singleton(message));

        return "/auth/signin";
    }

    private User registerNewUser(SignUpForm signUpForm, BindingResult formBinding, Model model)
    {
        List<String> messages = new ArrayList<>();

        // Check for duplicate email
        if (simpleUserService.findByEmail(signUpForm.getEmail()) != null)
        {
            String message = "Email \"" + signUpForm.getEmail() + "\" is already in use.";
            messages.add(message);

            if (simpleUserService.findByUsername(signUpForm.getUsername()) != null)
            {
                message = "Username \"" + signUpForm.getUsername() + "\" is taken.";
                messages.add(message);
            }

            model.addAttribute("dangerMessages", messages);

            return null;
        }

        // Check for duplicate username
        if (simpleUserService.findByUsername(signUpForm.getUsername()) != null)
        {
            String message = "Username \"" + signUpForm.getUsername() + "\" is in use.";
            messages.add(message);

            if (simpleUserService.findByEmail(signUpForm.getEmail()) != null)
            {
                message = "Email \"" + signUpForm.getEmail() + "\" is in use.";
                messages.add(message);
            }

            model.addAttribute("dangerMessages", messages);

            return null;
        }

        return simpleUserService.save(signUpForm);
    }
}
