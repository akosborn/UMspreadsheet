package com.umspreadsheet.user;

import com.umspreadsheet.privilege.Privilege;
import com.umspreadsheet.role.Role;
import com.umspreadsheet.role.RoleService;
import com.umspreadsheet.signup.SignUpForm;
import com.umspreadsheet.signup.VerificationToken;
import com.umspreadsheet.signup.VerificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SimpleUserService implements UserService
{
    private UserRepository userRepository;
    private RoleService roleService;
    private VerificationTokenService verificationTokenService;

    private static final String TOKEN_INVALID = "invalidToken";
    private static final String TOKEN_EXPIRED = "expiredToken";
    private static final String TOKEN_VALID = "validToken";

    @Autowired
    public SimpleUserService(UserRepository userRepository, RoleService roleService, VerificationTokenService verificationTokenService)
    {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.verificationTokenService = verificationTokenService;
    }

    public User findByEmail(String email)
    {
        return userRepository.findByEmail(email);
    }

    public User save(User user)
    {
        return userRepository.save(user);
    }

    public User save(SignUpForm signUpForm)
    {
        User user = new User();
        user.setUsername(signUpForm.getUsername());
        user.setEmail(signUpForm.getEmail());

        // if the user signed up via social
        if (!signUpForm.getUserId().equals(""))
            user.setUserId(signUpForm.getUserId());

        user.setPassword(signUpForm.getPassword());
        // Assign ROLE_USER role to the new user
        user.setRoles(Collections.singletonList(roleService.findByName("ROLE_USER")));

        try
        {
            return userRepository.save(user);
        }
        catch (DataAccessException ex)
        {
            return null;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        final boolean accountNonExpired = true;
        final boolean credentialsNonExpired = true;

        try
        {
            User user = findByUsername(username);
            if (user == null)
            {
                throw new UsernameNotFoundException("No user found with username: " + username);
            }

            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(),
                    user.getPassword(),
                    user.getIsEnabled(),
                    accountNonExpired,
                    credentialsNonExpired,
                    user.getIsNotSuspended() && user.getIsNotBanned(),
                    getAuthorities(user.getRoles()));
        } catch (Exception ex)
        {
            throw new RuntimeException(ex);
        }
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles)
    {
        return getGrantedAuthorities(getPrivileges(roles));
    }

    private Collection<? extends GrantedAuthority> getGrantedAuthorities(List<String> privileges)
    {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege : privileges)
        {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }

        return authorities;
    }

    private List<String> getPrivileges(Collection<Role> roles)
    {
        List<String> privileges = new ArrayList<>();
        List<Privilege> privilegeList = new ArrayList<>();
        for (Role role : roles)
        {
            privilegeList.addAll(role.getPrivileges());
        }
        for (Privilege privilege : privilegeList)
        {
            privileges.add(privilege.getName());
        }
        
        return privileges;
    }

    public User getUser(String verificationToken)
    {
        return verificationTokenService.findByToken(verificationToken).getUser();
    }

    public VerificationToken getVerificationToken(String VerificationToken)
    {
        return verificationTokenService.findByToken(VerificationToken);
    }

    public User findByUserId(String userId)
    {
        return userRepository.findByUserId(userId);
    }

    @Override
    public void createVerificationToken(User user, String token)
    {
        VerificationToken verificationToken = new VerificationToken(token, user);
        verificationTokenService.save(verificationToken);
    }

    public User findByUsername(String username)
    {
        return userRepository.findByUsername(username);
    }

    public Iterable<User> findAll()
    {
        return userRepository.findAll();
    }
}
