package com.umspreadsheet.v1.signup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerificationTokenService
{
    private VerificationTokenRepository verificationTokenRepository;

    @Autowired
    public VerificationTokenService(VerificationTokenRepository verificationTokenRepository)
    {
        this.verificationTokenRepository = verificationTokenRepository;
    }

    public VerificationToken findByToken(String verificationToken)
    {
        return verificationTokenRepository.findByToken(verificationToken);
    }

    public VerificationToken save(VerificationToken verificationToken)
    {
        return verificationTokenRepository.save(verificationToken);
    }
}
