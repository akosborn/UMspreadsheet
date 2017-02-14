package com.umspreadsheet.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService, UserDetailsService
{
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    @Override
    public User findByUsername(String username)
    {
        return userRepository.findByUsername(username);
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
        User user = findByUsername(username);
        if (user == null)
        {
            throw new UsernameNotFoundException(username);
        }

        return new UserDetailsImpl(user);
    }
}
