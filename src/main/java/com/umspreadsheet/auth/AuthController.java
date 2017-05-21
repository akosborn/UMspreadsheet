package com.umspreadsheet.auth;

import com.umspreadsheet.signin.PasswordResetTokenService;
import com.umspreadsheet.signin.UserSecurityService;
import com.umspreadsheet.user.PasswordDTO;
import com.umspreadsheet.user.SimpleUserService;
import com.umspreadsheet.user.User;
import com.umspreadsheet.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Locale;
import java.util.UUID;

@Controller
public class AuthController
{
    private SimpleUserService userService;
    private MailSender mailSender;
    private MessageSource messageSource;
    private UserSecurityService userSecurityService;
    private PasswordResetTokenService passwordResetTokenService;

    @Autowired
    public AuthController(SimpleUserService userService, MailSender mailSender, MessageSource messageSource,
                          UserSecurityService userSecurityService, PasswordResetTokenService passwordResetTokenService)
    {
        this.userService = userService;
        this.mailSender = mailSender;
        this.messageSource = messageSource;
        this.userSecurityService = userSecurityService;
        this.passwordResetTokenService = passwordResetTokenService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String saveUser(User user, RedirectAttributes redirectAttributes)
    {
        User savedUser = userService.save(user);
        if (savedUser == null)
        {
            redirectAttributes.addFlashAttribute("signup", "false");
            return "redirect:/register";
        }

        redirectAttributes.addFlashAttribute("signup", "You have successfully signed up.");
        return "redirect:/login";
    }

    @RequestMapping(value = "/reset-password", method = RequestMethod.POST)
    public String resetPassword(@RequestParam("email") String email, HttpServletRequest request,
                                RedirectAttributes redirectAttributes)
    {
        User user = userService.findByEmail(email);

        try
        {
            if (user == null)
            {
                throw new UsernameNotFoundException("Email \'" + email + "\' not found.");
            }
        }catch (UsernameNotFoundException ex)
        {
            redirectAttributes.addFlashAttribute("invalidEmail", true);
            ex.printStackTrace();

            return "redirect:/signin/reset-password";
        }

        // Delete old password reset token before assigning a new one
        if (passwordResetTokenService.findByUser(user) != null)
            passwordResetTokenService.deleteByUser(user);

        String token = UUID.randomUUID().toString();
        userService.createPasswordResetTokenForUser(user, token);
        mailSender.send(constructResetTokenEmail(getAppUrl(request), request.getLocale(), token, user));

        return "redirect:/signin/reset-password";
    }

    @RequestMapping("/change-password")
    public String changePassword(Model model,
                                 @RequestParam("id") Long id,
                                 @RequestParam("token") String token, RedirectAttributes redirectAttributes)
    {
        String result = userSecurityService.validatePasswordResetToken(id, token);
        if (result != null)
        {
            if (result.equals("invalidToken"))
                redirectAttributes.addFlashAttribute("invalid", true);
            else if (result.equals("expired"))
                redirectAttributes.addFlashAttribute("expired", true);

            return "redirect:/signin/reset-password";
        }

        return "redirect:/update-password";
    }

    @RequestMapping("/update-password")
    public String updatePassword(Model model)
    {
        model.addAttribute("passwordDTO", new PasswordDTO());

        return "/auth/updatePassword";
    }

    @RequestMapping(value = "save-password", method = RequestMethod.POST)
    public String savePassword(@Valid PasswordDTO password, BindingResult bindingResult,
                               RedirectAttributes redirectAttributes)
    {
        if (bindingResult.hasErrors())
        {
            return "/auth/updatePassword";
        }

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        user.setPassword(password.getNewPassword());
        userService.save(user);

        // Delete the token
        passwordResetTokenService.deleteByUser(user);

        redirectAttributes.addFlashAttribute("reset", true);

        return "redirect:/signin";
    }

    private SimpleMailMessage constructResetTokenEmail(final String contextPath, final Locale locale,
                                                       final String token, final User user)
    {
        final String url = contextPath + "/change-password?id=" + user.getId() + "&token=" + token;
        final String message = messageSource.getMessage("message.resetPassword", null, locale);

        return constructEmail("Reset Password", message + " \r\n" + url, user);
    }

    private SimpleMailMessage constructEmail(String subject, String body, User user)
    {
        final SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject(subject);
        email.setText(body);
        email.setTo(user.getEmail());

        return email;
    }

    private String getAppUrl(HttpServletRequest request)
    {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }
}
