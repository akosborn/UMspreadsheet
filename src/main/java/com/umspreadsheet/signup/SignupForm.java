package com.umspreadsheet.signup;

import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;

import javax.sql.DataSource;

public class SignupForm
{
    private String username;
    private String userId;
    private String password;
    private String passwordConfirmation;
    private String email;

    public static SignupForm fromProviderUser(UserProfile userProfile)
    {
        SignupForm signupForm = new SignupForm();
        signupForm.userId = userProfile.getUsername();
        signupForm.email = userProfile.getEmail();

        return signupForm;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getPasswordConfirmation()
    {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation)
    {
        this.passwordConfirmation = passwordConfirmation;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }
}
