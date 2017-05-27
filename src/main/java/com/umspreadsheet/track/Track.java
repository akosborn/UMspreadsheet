package com.umspreadsheet.track;

import com.fasterxml.jackson.annotation.*;
import com.umspreadsheet.helper.ControllerHelper;
import com.umspreadsheet.model.Transition;
import com.umspreadsheet.review.TrackReviewForm;
import com.umspreadsheet.set.Set;
import com.umspreadsheet.review.TrackReview;
import com.umspreadsheet.show.Show;

import javax.persistence.*;
import java.util.List;

@Entity
public class Track
{
    // Needed for JPA
    public Track() {}

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JsonIgnoreProperties("sets")
    private Show show;

    @ManyToOne
    @JsonIgnoreProperties("tracks")
    private Set set;
    private Integer showTrackNumber;

    private String song;
    private Long length;

    @Enumerated(value = EnumType.STRING)
    private Transition transition;

    private Double averageRating;

    private String notes;
    private String type;
    private String jam;

    @JsonIgnore
    @OneToMany(mappedBy = "track")
    private List<TrackReview> reviews;

    @Transient
    private Long showId;

    @Transient
    private Long setId;

    @Transient
    private String slug;

    @Transient
    private TrackReviewForm trackReviewForm;

    public TrackReviewForm getTrackReviewForm()
    {
        return trackReviewForm;
    }

    public void setTrackReviewForm(TrackReviewForm trackReviewForm)
    {
        this.trackReviewForm = trackReviewForm;
    }

    public Long getSetId()
    {
        return setId;
    }

    public void setSetId(Long setId)
    {
        this.setId = setId;
    }

    public Long getShowId()
    {
        return showId;
    }

    public void setShowId(Long showId)
    {
        this.showId = showId;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Show getShow()
    {
        return show;
    }

    public void setShow(Show show)
    {
        this.show = show;
    }

    public Set getSet()
    {
        return set;
    }

    public void setSet(Set set)
    {
        this.set = set;
    }

    public Integer getShowTrackNumber()
    {
        return showTrackNumber;
    }

    public void setShowTrackNumber(Integer showTrackNumber)
    {
        this.showTrackNumber = showTrackNumber;
    }

    public String getSong()
    {
        return song;
    }

    public void setSong(String song)
    {
        this.song = song;
    }

    public Long getLength()
    {
        return length;
    }

    public void setLength(Long length)
    {
        this.length = length;
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

    public List<TrackReview> getReviews()
    {
        return reviews;
    }

    public void setReviews(List<TrackReview> reviews)
    {
        this.reviews = reviews;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getJam()
    {
        return jam;
    }

    public void setJam(String jam)
    {
        this.jam = jam;
    }

    public void setSlug(String slug)
    {
        this.slug = slug;
    }

    public String getSlug()
    {
        return ControllerHelper.toSlug(song);
    }

    public Transition getTransition()
    {
        return transition;
    }

    public void setTransition(Transition transition)
    {
        this.transition = transition;
    }
}
