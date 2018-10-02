package com.umspreadsheet.v1.review;

public class TrackReviewForm
{
    private Long id;
    private Double score;
    private String comment;
    private String username;
    private Long trackId;

    // Default constructor
    public TrackReviewForm(){}

    public TrackReviewForm(TrackReview trackReview)
    {
        this.id = trackReview.getId();
        this.score = trackReview.getScore();
        this.comment = trackReview.getComment();
        this.username = trackReview.getUser().getUsername();
        this.trackId =  trackReview.getTrack().getId();
    }

    public TrackReviewForm(String username, Long trackId)
    {
        this.username = username;
        this.trackId = trackId;
    }

    public TrackReviewForm(String username)
    {
        this.username = username;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Double getScore()
    {
        return score;
    }

    public void setScore(Double score)
    {
        this.score = score;
    }

    public String getComment()
    {
        return comment;
    }

    public void setComment(String comment)
    {
        this.comment = comment;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public Long getTrackId()
    {
        return trackId;
    }

    public void setTrackId(Long trackId)
    {
        this.trackId = trackId;
    }
}
