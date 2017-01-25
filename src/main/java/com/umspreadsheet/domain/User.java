package com.umspreadsheet.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User
{
    private User() {}

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String passwordHash;
    private String twitter;

    @JoinTable(
            name = "users_roles",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    private Set<Role> roleSet = new HashSet<Role>();
}
