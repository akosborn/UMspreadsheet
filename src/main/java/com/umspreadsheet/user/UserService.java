package com.umspreadsheet.user;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService
{
    User save(User user);

    User findByUserId(String userId);

    void createVerificationToken(User user, String token);
}
