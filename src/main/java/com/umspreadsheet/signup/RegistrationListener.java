package com.umspreadsheet.signup;

import com.umspreadsheet.user.User;
import com.umspreadsheet.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent>
{
    private UserService userService;
    private MessageSource messageSource;

    @Autowired
    public RegistrationListener(UserService userService, MessageSource messageSource)
    {
        this.userService = userService;
        this.messageSource = messageSource;
    }

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event)
    {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event)
    {
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.createVerificationToken(user, token);

        String recipientAddress = user.getEmail();
        String subject = "Registration Confirmation";
        String confirmationURL = event.getAppURL() + "/signup-confirm?token=" + token;
        String message = messageSource.getMessage("email.confirmEmail", null, event.getLocale());
        String ignoreEmailMessage = messageSource.getMessage("email.ignore", null, event.getLocale());

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(user.getUsername() + ", \n\n" + message + " " + "http://www.umspreadsheet.com" + confirmationURL +
                        "\n\n" + ignoreEmailMessage);
    }
}
