package com.umspreadsheet.signup;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.social.connect.UserProfile;

import javax.validation.constraints.Pattern;

public class SignupForm
{
    @NotEmpty
    @Length(min = 3, max = 25)
    @Pattern(regexp = ".*\\w") // only numbers, letters, and underscores
    private String username;
    private String userId;

    @NotEmpty
    @Length(min = 8, max = 128)
    @Pattern(regexp = ".*\\w") // only numbers, letters, and underscores
    private String password;

    @NotEmpty
    @Length(min = 7, max = 128)
    private String passwordConfirmation;

    @NotEmpty
    @Email
    @Length(max = 254)
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
