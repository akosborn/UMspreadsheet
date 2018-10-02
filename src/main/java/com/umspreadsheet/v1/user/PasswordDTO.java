package com.umspreadsheet.v1.user;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;

public class PasswordDTO
{
    @NotEmpty
    @Length(min = 8, max = 128)
    @Pattern(regexp = "([\\w-]+)") // only numbers, letters, and underscores
    private String newPassword;

    public String getNewPassword()
    {
        return newPassword;
    }

    public void setNewPassword(String newPassword)
    {
        this.newPassword = newPassword;
    }
}
