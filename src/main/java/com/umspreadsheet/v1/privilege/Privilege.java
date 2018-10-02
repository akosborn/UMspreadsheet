package com.umspreadsheet.v1.privilege;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.umspreadsheet.v1.role.Role;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Privilege
{
    public Privilege(){}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "privileges")
    @JsonIgnoreProperties({"privileges"})
    private Collection<Role> roles;

    public Privilege(String name)
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

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Collection<Role> getRoles()
    {
        return roles;
    }

    public void setRoles(Collection<Role> roles)
    {
        this.roles = roles;
    }
}
