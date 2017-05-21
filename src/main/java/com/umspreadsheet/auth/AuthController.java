package com.umspreadsheet.auth;

import com.umspreadsheet.signin.UserSecurityService;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.UUID;

@Controller
public class AuthController
{
    private SimpleUserService userService;
    private MailSender mailSender;
    private MessageSource messageSource;
    private UserSecurityService userSecurityService;

    @Autowired
    public AuthController(SimpleUserService userService, MailSender mailSender, MessageSource messageSource,
                          UserSecurityService userSecurityService)
    {
        this.userService = userService;
        this.mailSender = mailSender;
        this.messageSource = messageSource;
        this.userSecurityService = userSecurityService;
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
    public String resetPassword(@RequestParam("email") String email, HttpServletRequest request)
    {
        User user = userService.findByEmail(email);
        if (user == null)
            throw new UsernameNotFoundException("Email " + email + " not found.");

        String token = UUID.randomUUID().toString();
        userService.createPasswordResetTokenForUser(user, token);
        mailSender.send(constructResetTokenEmail(getAppUrl(request), request.getLocale(), token, user));

        return "redirect:/signin";
    }

    @RequestMapping("/change-password")
    public String changePassword(Model model, @RequestParam("id") Long id,
                                 @RequestParam("token") String token)
    {
        String result = userSecurityService.validatePasswordResetToken(id, token);
        if (result != null)
        {
            return "redirect:/signin";
        }

        return "redirect:/update-password";
    }

    @RequestMapping("/update-password")
    public String updatePassword()
    {
        return "/auth/updatePassword";
    }

    @RequestMapping(value = "save-password", method = RequestMethod.POST)
    public String savePassword(String password, RedirectAttributes redirectAttributes)
    {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        user.setPassword(password);
        userService.save(user);

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
