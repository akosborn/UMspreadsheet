package com.umspreadsheet.user;

import com.umspreadsheet.role.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public class SimpleUserDetails implements UserDetails
{
    private User user;

    public SimpleUserDetails(User user)
    {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        List<GrantedAuthority> authorities = new ArrayList<>();
        Collection<Role> roles = user.getRoles();
        for (Role role : roles)
        {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return authorities;
    }

    @Override
    public String getPassword()
    {
        return user.getPassword();
    }

    @Override
    public String getUsername()
    {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return user.isNotSuspended() | user.isNotSuspended();
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return user.isNotSuspended() | user.isNotSuspended();
    }
}
