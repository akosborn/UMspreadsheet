package com.umspreadsheet.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class TrackReview
{
    // Needed for JPA
    public TrackReview() {}

    public TrackReview(Track track)
    {
        this.track = track;
    }

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User user;

    @Column(columnDefinition = "TEXT")
    private String comment;

    @ManyToOne
    private Track track;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date reviewedOn = new Date();

    @Column(columnDefinition = "TINYINT")
    private int score;

    public Long getId()
    {
        return id;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
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

    public void setId(Long id)
    {
        this.id = id;
    }

    public Date getReviewedOn()
    {
        return reviewedOn;
    }

    public void setReviewedOn(Date reviewedOn)
    {
        this.reviewedOn = reviewedOn;
    }

    public int getScore()
    {
        return score;
    }

    public void setScore(int score)
    {
        this.score = score;
    }
}
