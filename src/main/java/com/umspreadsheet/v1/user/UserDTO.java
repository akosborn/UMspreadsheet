package com.umspreadsheet.v1.user;

import com.umspreadsheet.v1.review.TrackReviewDTO;

import java.util.List;

public class UserDTO
{
    private String username;
    private List<TrackReviewDTO> trackReviews;

    public UserDTO() {}

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public List<TrackReviewDTO> getTrackReviews()
    {
        return trackReviews;
    }

    public void setTrackReviews(List<TrackReviewDTO> trackReviews)
    {
        this.trackReviews = trackReviews;
    }
}
