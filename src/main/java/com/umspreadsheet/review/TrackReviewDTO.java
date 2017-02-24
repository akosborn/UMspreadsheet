package com.umspreadsheet.review;

import com.umspreadsheet.user.UserDTO;

public class TrackReviewDTO
{
    private Long id;
    private Double score;
    private String comment;
    private UserDTO user;

    public TrackReviewDTO() {}

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
}
