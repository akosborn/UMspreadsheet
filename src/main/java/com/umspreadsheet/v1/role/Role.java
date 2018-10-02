package com.umspreadsheet.v1.role;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.umspreadsheet.v1.privilege.Privilege;
import com.umspreadsheet.v1.user.User;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Role
{
    public Role(){}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany(mappedBy = "roles")
    @JsonIgnoreProperties({"roles", "trackReviews"})
    private Collection<User> users;

    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"roles"})
    @JoinTable(
            name = "roles_privileges",
            joinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "privilege_id", referencedColumnName = "id"))
    private Collection<Privilege> privileges;

    public Role(String name)
    {
        this.name = name;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Collection<User> getUsers()
    {
        return users;
    }

    public void setUsers(Collection<User> users)
    {
        this.users = users;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Collection<Privilege> getPrivileges()
    {
        return privileges;
    }

    public void setPrivileges(Collection<Privilege> privileges)
    {
        this.privileges = privileges;
    }
}
