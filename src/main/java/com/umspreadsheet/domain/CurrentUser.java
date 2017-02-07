package com.umspreadsheet.domain;


import java.util.*;
import java.util.Set;

public class CurrentUser
{
    private Long id;
    private String username;
    private String email;
    private Set<Role> roles;
    private Date joinedOn;
    private List<ShowReview> showReviews;
    private List<TrackReview> trackReviews;

    public CurrentUser(User user)
    {
        id = user.getId();
        username = user.getUsername();
        email = user.getEmail();
        joinedOn = user.getJoinedOn();
        showReviews = user.getShowReviews();
        trackReviews = user.getTrackReviews();
    }

    public Long getId()
    {
        return id;
    }

    public String getUsername()
    {
        return username;
    }

    public String getEmail()
    {
        return email;
    }

    public Date getJoinedOn()
    {
        return joinedOn;
    }

    public List<ShowReview> getShowReviews()
    {
        return showReviews;
    }

    public List<TrackReview> getTrackReviews()
    {
        return trackReviews;
    }
}
