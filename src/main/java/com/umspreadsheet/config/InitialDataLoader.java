package com.umspreadsheet.config;

import com.umspreadsheet.privilege.Privilege;
import com.umspreadsheet.privilege.PrivilegeService;
import com.umspreadsheet.role.Role;
import com.umspreadsheet.role.RoleService;
import com.umspreadsheet.user.User;
import com.umspreadsheet.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

// http://www.baeldung.com/role-and-privilege-for-spring-security-registration
@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent>
{
    private boolean alreadySetup = false;
    private UserService userService;
    private RoleService roleService;
    private PrivilegeService privilegeService;

    @Autowired
    public InitialDataLoader(UserService userService, RoleService roleService,
                             PrivilegeService privilegeService)
    {
        this.userService = userService;
        this.roleService = roleService;
        this.privilegeService = privilegeService;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event)
    {
        if (alreadySetup)
            return;

        // Create admin privileges. "ROLE" prefix is required by Spring unless changed
        Privilege adminPrivilege = createPrivilegeIfNotFound("ROLE_ADMIN_PRIVILEGE");
        Privilege manageShowsPrivilege = createPrivilegeIfNotFound("ROLE_MANAGE_SHOWS_PRIVILEGE");
        Privilege manageUsersPrivilege = createPrivilegeIfNotFound("ROLE_MANAGE_USERS_PRIVILEGE");
        Privilege sendEmailPrivilege = createPrivilegeIfNotFound("ROLE_SEND_EMAIL_PRIVILEGE");
        Privilege postToWormBlogPrivilege = createPrivilegeIfNotFound("ROLE_POST_TO_WORMBLOG_PRIVILEGE");

        // Create mod privileges
        Privilege modPrivilege = createPrivilegeIfNotFound("ROLE_MOD_PRIVILEGE");

        // Create user privilege
        Privilege userPrivilege = createPrivilegeIfNotFound("ROLE_USER_PRIVILEGE");

        // Add appropriate privileges to lists
        List<Privilege> adminPrivileges = Arrays.asList(adminPrivilege, modPrivilege, manageShowsPrivilege,
                manageUsersPrivilege, sendEmailPrivilege, postToWormBlogPrivilege);
        List<Privilege> modPrivileges = Arrays.asList(modPrivilege, manageShowsPrivilege, postToWormBlogPrivilege);
        List<Privilege> userPrivileges = Arrays.asList(userPrivilege);

        // If roles don't exist, create them
        createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
        createRoleIfNotFound("ROLE_MOD", modPrivileges);
        createRoleIfNotFound("ROLE_USER", userPrivileges);

        User user;
        // Find adminRole in database and assign it to user "andrew"
        Role adminRole = roleService.findByName("ROLE_ADMIN");
        user = new User();
        user.setEmail("admin@umspreadsheet.com");
        user.setPassword("password");
        user.setUsername("admin");
        user.setNotBanned(true);
        user.setRoles(Collections.singletonList(adminRole));
        userService.save(user);

        // Find Role in database and assign it to user "andrew"
        Role modRole = roleService.findByName("ROLE_MOD");
        user = new User();
        user.setEmail("mod@umspreadsheet.com");
        user.setPassword("password");
        user.setUsername("mod");
        user.setNotBanned(true);
        user.setRoles(Collections.singletonList(modRole));
        userService.save(user);

        // Find Role in database and assign it to user "andrew"
        Role userRole = roleService.findByName("ROLE_USER");
        user = new User();
        user.setEmail("user@umspreadsheet.com");
        user.setPassword("password");
        user.setUsername("user");
        user.setNotBanned(true);
        user.setRoles(Collections.singletonList(userRole));
        userService.save(user);

        alreadySetup = true;
    }

    @Transactional
    private Privilege createPrivilegeIfNotFound(String name)
    {
        Privilege privilege = privilegeService.findByName(name);
        if (privilege == null)
        {
            privilege = new Privilege(name);
            privilegeService.save(privilege);
        }

        return privilege;
    }

    @Transactional
    private Role createRoleIfNotFound(String name, Collection<Privilege> privileges)
    {
        Role role = roleService.findByName(name);
        if (role == null)
        {
            role = new Role(name);
            role.setPrivileges(privileges);
            roleService.save(role);
        }

        return role;
    }
}
