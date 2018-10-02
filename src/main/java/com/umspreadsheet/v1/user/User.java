package com.umspreadsheet.v1.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.umspreadsheet.v1.role.Role;
import com.umspreadsheet.v1.model.ShowReview;
import com.umspreadsheet.v1.review.TrackReview;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
public class User
{
    public User()
    {
        super();
        this.isEnabled = false;
    }

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    @NotNull
    private String username;

    private boolean isEnabled;

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
    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"users"})
    @JoinTable(
            name = "users_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private Collection<Role> roles;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date joinedOn = new Date();

    @OneToMany(mappedBy = "user")
    private List<ShowReview> showReviews;

    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties("user")
    @OrderBy("reviewedOn DESC")
    private List<TrackReview> trackReviews;

    @NotNull
    @Column(columnDefinition = "TINYINT(1)")
    private boolean isNotSuspended = true;

    @NotNull
    @Column(columnDefinition = "TINYINT(1)")
    private boolean isNotBanned = true;

    @Transient
    private String transientRole;

    @SafeHtml
    @Length(max = 100)
    private String location;

    @SafeHtml
    @Length(max = 100)
    private String favoriteSongs;

    @SafeHtml
    @Length(max = 15)
    private String twitterHandle;

    private String avatarUrl;

    public String getTransientRole()
    {
        return transientRole;
    }

    public void setTransientRole(String transientRole) {
        this.transientRole = transientRole;
    }

    public boolean getIsNotSuspended()
    {
        return isNotSuspended;
    }

    public void setIsNotSuspended(boolean notSuspended)
    {
        isNotSuspended = notSuspended;
    }

    public boolean getIsNotBanned()
    {
        return isNotBanned;
    }

    public void setIsNotBanned(boolean notBanned)
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

    public Collection<Role> getRoles()
    {
        return roles;
    }

    public void setRoles(Collection<Role> roles)
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

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public boolean getIsEnabled()
    {
        return isEnabled;
    }

    public void setIsEnabled(boolean enabled)
    {
        isEnabled = enabled;
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public String getFavoriteSongs()
    {
        return favoriteSongs;
    }

    public void setFavoriteSongs(String favoriteSongs)
    {
        this.favoriteSongs = favoriteSongs;
    }

    public String getTwitterHandle()
    {
        return twitterHandle;
    }

    public void setTwitterHandle(String twitterHandle)
    {
        this.twitterHandle = twitterHandle;
    }

    public String getAvatarUrl()
    {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl)
    {
        this.avatarUrl = avatarUrl;
    }

    @Override
    public String toString()
    {
        return "User [id = " + id + ", email = " + email + ", password = " + password + "]";
    }
}