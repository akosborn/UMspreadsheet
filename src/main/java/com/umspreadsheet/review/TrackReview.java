package com.umspreadsheet.review;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.umspreadsheet.track.Track;
import com.umspreadsheet.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class TrackReview
{
    public TrackReview() {}

    public TrackReview(Track track)
    {
        this.track = track;
    }

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JsonIgnoreProperties({"trackReviews"})
    private User user;

    @Column(columnDefinition = "TEXT")
    private String comment;

    @ManyToOne
    @JsonIgnoreProperties(value = {"reviews", "userTrackReview", "show", "set"}, allowSetters = true)
    private Track track;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date reviewedOn = new Date();

    @Column(columnDefinition = "DECIMAL(4,2)")
    private Double score;

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

    public Double getScore()
    {
        return score;
    }

    public void setScore(Double score)
    {
        this.score = score;
    }
}
