package com.umspreadsheet.service;

import com.umspreadsheet.domain.User;

public interface UserService
{
    public User findByUsername(String username);

    public User findByEmail(String email);

    public User save(User user);
}
