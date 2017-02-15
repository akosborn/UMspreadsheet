package com.umspreadsheet.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.umspreadsheet.model.Role;
import com.umspreadsheet.model.ShowReview;
import com.umspreadsheet.model.TrackReview;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class User
{
    // Needed for JPA
    public User() {}

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    @NotNull
    private String username;

    /**
     * A social user's social username (ie. @Twitter)
     *
     * This is not the displayed name on UMSpreadsheet
     */
    @Column(unique = true)
    private String userId;

    @Column(unique = true, nullable = false)
    private String email;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    // gives new users ROLE_USER privilege
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private java.util.Set<Role> roles = new HashSet<>(Collections.singletonList(Role.defaultRole()));

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date joinedOn = new Date();

    @OneToMany(mappedBy = "user")
    private List<ShowReview> showReviews;

    @OneToMany(mappedBy = "user")
    private List<TrackReview> trackReviews;

    @NotNull
    @Column(columnDefinition = "TINYINT(1)")
    private boolean isNotSuspended = true;

    @NotNull
    @Column(columnDefinition = "TINYINT(1)")
    private boolean isNotBanned = true;

    public boolean isNotSuspended()
    {
        return isNotSuspended;
    }

    public void setNotSuspended(boolean notSuspended)
    {
        isNotSuspended = notSuspended;
    }

    public boolean isNotBanned()
    {
        return isNotBanned;
    }

    public void setNotBanned(boolean notBanned)
    {
        isNotBanned = notBanned;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode(password);
    }

    public Set<Role> getRoles()
    {
        return roles;
    }

    public void setRoles(Set<Role> roles)
    {
        this.roles = roles;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public Date getJoinedOn()
    {
        return joinedOn;
    }

    public void setJoinedOn(Date joinedOn)
    {
        this.joinedOn = joinedOn;
    }

    public List<ShowReview> getShowReviews()
    {
        return showReviews;
    }

    public void setShowReviews(List<ShowReview> showReviews)
    {
        this.showReviews = showReviews;
    }

    public List<TrackReview> getTrackReviews()
    {
        return trackReviews;
    }

    public void setTrackReviews(List<TrackReview> trackReviews)
    {
        this.trackReviews = trackReviews;
    }

    @Override
    public String toString()
    {
        return "User [id = " + id + ", email = " + email + ", password = " + password + "]";
    }
}