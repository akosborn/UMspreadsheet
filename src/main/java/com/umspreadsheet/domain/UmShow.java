package com.umspreadsheet.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

// 'UmShow' is a keyword in MySQL, so another name must be used
@Entity
public class UmShow
{
    // Needed for JPA
    public UmShow(){}

    @Id
    @GeneratedValue
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date date;
    private String city;
    private String state;
    private String venue;

    @JsonIgnore
    @OneToMany(mappedBy = "show", fetch = FetchType.LAZY)
    private List<ShowReview> showReviews;

    // One show has many
    @JsonIgnore
    @OneToMany(mappedBy = "show", fetch = FetchType.LAZY)
    private List<Track> tracks;
    private Double averageRating;

    @Column(columnDefinition = "TEXT")
    private String notes;

    public Long getId()
    {
        return id;
    }

    public Double getAverageRating()
    {
        return averageRating;
    }

    public void setAverageRating(Double averageRating)
    {
        this.averageRating = averageRating;
    }

    public void setShowReviews(List<ShowReview> showReviews)
    {
        this.showReviews = showReviews;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public String getVenue()
    {
        return venue;
    }

    public void setVenue(String venue)
    {
        this.venue = venue;
    }

    @JsonIgnore
    public List<Track> getTracks()
    {
        return tracks;
    }

    public void setTracks(List<Track> tracks)
    {
        this.tracks = tracks;
    }

    public String getNotes()
    {
        return notes;
    }

    public void setNotes(String notes)
    {
        this.notes = notes;
    }
}
