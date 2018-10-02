package com.umspreadsheet.v1.show;

import com.umspreadsheet.v1.set.Set;

import java.util.Date;
import java.util.List;

public class ShowDTO
{
    private Long id;
    private Date date;
    private String city;
    private String state;
    private String venue;
    private Double averageRating;
    private String notes;
    private List<Set> sets;

    public List<Set> getSets()
    {
        return sets;
    }

    public void setSets(List<Set> sets)
    {
        this.sets = sets;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
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

    public Double getAverageRating()
    {
        return averageRating;
    }

    public void setAverageRating(Double averageRating)
    {
        this.averageRating = averageRating;
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
