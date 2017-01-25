package com.umspreadsheet.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class TrackReview
{
    // Needed for JPA
    private TrackReview() {}

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private UserProfile userProfile;

    @Column(columnDefinition = "TEXT")
    private String comment;

    @ManyToOne
    private Track track;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date reviewedOn;

    @Column(columnDefinition = "TINYINT")
    private int score;

    public Long getId()
    {
        return id;
    }

    public UserProfile getUserProfile()
    {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile)
    {
        this.userProfile = userProfile;
    }

    public String getComment()
    {
        return comment;
    }

    public void setComment(String comment)
    {
        this.comment = comment;
    }

    public Track getTrack()
    {
        return track;
    }

    public void setTrack(Track track)
    {
        this.track = track;
    }
}
