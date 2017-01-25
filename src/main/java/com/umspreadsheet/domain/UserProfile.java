package com.umspreadsheet.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
public class UserProfile
{
    // Needed for JPA
    private UserProfile() {}

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String firstName;
    private String LastName;

    @NotNull
    private String username;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date joinedOn;

    @OneToMany(mappedBy = "userProfile")
    private List<ShowReview> showReviews;

    @OneToMany(mappedBy = "userProfile")
    private List<TrackReview> trackReviews;

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

    public Date getJoinedOn()
    {
        return joinedOn;
    }

    public void setJoinedOn(Date joinedOn)
    {
        this.joinedOn = joinedOn;
    }

    public Long getId()
    {
        return id;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return LastName;
    }

    public void setLastName(String lastName)
    {
        LastName = lastName;
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
}
