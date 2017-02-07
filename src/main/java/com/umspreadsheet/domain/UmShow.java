package com.umspreadsheet.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

// 'UmShow' is a keyword in MySQL, so another name must be used
@Entity
@Table(name = "shows")
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

    @OneToMany(mappedBy = "show", fetch = FetchType.LAZY)
    private List<Set> sets;
    private Double averageRating;

    @Column(columnDefinition = "TEXT")
    private String notes;

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

    public List<ShowReview> getShowReviews()
    {
        return showReviews;
    }

    public void setShowReviews(List<ShowReview> showReviews)
    {
        this.showReviews = showReviews;
    }

    public List<Set> getSets()
    {
        return sets;
    }

    public void setSets(List<Set> sets)
    {
        this.sets = sets;
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
