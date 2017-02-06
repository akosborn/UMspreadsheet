package com.umspreadsheet.service;

import com.umspreadsheet.domain.Track;

public class GoogleChartTrackDao
{
    private String name;
    private double rating;

    public GoogleChartTrackDao(Track track)
    {
        this.name = track.getShow().getDate().toString();
        if (track.getTrackAverageRating() != null)
            this.rating = track.getTrackAverageRating();
        else
            this.rating = 85.45;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public double getRating()
    {
        return rating;
    }

    public void setRating(double rating)
    {
        this.rating = rating;
    }
}
