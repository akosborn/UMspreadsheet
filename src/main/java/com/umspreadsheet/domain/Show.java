package com.umspreadsheet.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Show
{
    // Needed for JPA
    private Show(){}

    @Id
    @Column(name = "show_id")
    @GeneratedValue
    private Long id;
    private Date date;
    private String city;
    private String state;
    private String venue;

    // One show has many tracks
    @OneToMany(mappedBy = "show")
    private List<Track> tracks;

    @Column(columnDefinition = "TEXT")
    private String notes;

    public Long getId()
    {
        return id;
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
