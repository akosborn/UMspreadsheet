package com.umspreadsheet.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SimpleUserService implements UserService
{
    private UserRepository userRepository;

    @Autowired
    public SimpleUserService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    public User findByEmail(String email)
    {
        return userRepository.findByEmail(email);
    }

    public User save(User user)
    {
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User user = findByUsername(username);
        if (user == null)
        {
            throw new UsernameNotFoundException(username);
        }

        return new SimpleUserDetails(user);
    }

    public User findByUserId(String userId)
    {
        return userRepository.findByUserId(userId);
    }

    public User findByUsername(String username)
    {
        return userRepository.findByUsername(username);
    }
}
