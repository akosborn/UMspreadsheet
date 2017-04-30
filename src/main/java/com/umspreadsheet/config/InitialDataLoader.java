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

        // If privileges don't exist, create them, "ROLE" prefix is required by Spring unless changed
        Privilege readPrivilege = createPrivilegeIfNotFound("ROLE_READ_PRIVILEGE");
        Privilege writePrivilege = createPrivilegeIfNotFound("ROLE_WRITE_PRIVILEGE");

        // Add appropriate privileges to adminPrivileges list
        List<Privilege> adminPrivileges = Arrays.asList(readPrivilege, writePrivilege);
        // If roles don't exist, create them
        createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
        createRoleIfNotFound("ROLE_USER", Collections.singletonList(readPrivilege));

        // Find adminRole in database
        Role adminRole = roleService.findByName("ROLE_ADMIN");
        // Create a test user and assign it admin privileges
        User user = new User();
        user.setEmail("test@umspreadsheet.com");
        user.setPassword("password");
        user.setUsername("test");
        user.setNotBanned(true);
        user.setRoles(Collections.singletonList(adminRole));
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
