package com.umspreadsheet.signin;

import org.springframework.stereotype.Service;

@Service
public class PasswordResetTokenService
{
    private PasswordResetTokenRepository repository;

    public PasswordResetTokenService(PasswordResetTokenRepository repository)
    {
        this.repository = repository;
    }

    public PasswordResetToken save(PasswordResetToken token)
    {
        return repository.save(token);
    }

    public PasswordResetToken findByToken(String token)
    {
        return repository.findByToken(token);
    }
}
