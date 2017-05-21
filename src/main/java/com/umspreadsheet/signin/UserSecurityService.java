package com.umspreadsheet.signin;

import com.umspreadsheet.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Objects;

@Service
public class UserSecurityService
{
    private PasswordResetTokenRepository repository;

    @Autowired
    public UserSecurityService(PasswordResetTokenRepository repository)
    {
        this.repository = repository;
    }

    public String validatePasswordResetToken(Long id, String token)
    {
        final PasswordResetToken passwordResetToken = repository.findByToken(token);
        if ((passwordResetToken == null) || (!Objects.equals(passwordResetToken.getUser().getId(), id)))
        {
            return "invalid";
        }

        final Calendar cal = Calendar.getInstance();
        if ((passwordResetToken.getExpirationDate()
                .getTime() - cal.getTime()
                .getTime()) <= 0)
        {
            return "expired";
        }

        final User user = passwordResetToken.getUser();
        final Authentication auth = new UsernamePasswordAuthenticationToken(user, user.getPassword(),
                Arrays.asList(new SimpleGrantedAuthority("CHANGE_PASSWORD_PRIVILEGE")));
        SecurityContextHolder.getContext().setAuthentication(auth);

        return null;
    }
}
