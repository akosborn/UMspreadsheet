package com.umspreadsheet.user;

public interface UserService
{
    public User findByUsername(String username);

    public User findByEmail(String email);

    public User save(User user);
}
