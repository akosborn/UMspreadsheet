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

    @Override
    public User findByEmail(String email)
    {
        return userRepository.findByEmail(email);
    }

    @Override
    public User save(User user)
    {
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User user = userRepository.findByUsername(username);
        if (user == null)
        {
            throw new UsernameNotFoundException(username);
        }

        return new SimpleUserDetails(user);
    }
}
