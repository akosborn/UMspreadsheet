package com.umspreadsheet.track;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.umspreadsheet.model.Set;
import com.umspreadsheet.review.TrackReview;
import com.umspreadsheet.show.Show;

import javax.persistence.*;
import java.util.List;

@Entity
public class Track
{
    // Needed for JPA
    private Track() {}

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Show show;

    @ManyToOne
    @JsonIgnore
    private Set set;
    private Integer showTrackNumber;

    private String song;
    private Double songLength;

    @Column(columnDefinition = "TINYINT(1)")
    private Boolean segue;

    @Column(columnDefinition = "TINYINT(1)")
    private Boolean fluidSegue;
    private Double averageRating;
    private String notes;

    @JsonIgnore
    @OneToMany(mappedBy = "track")
    private List<TrackReview> reviews;

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

    public Double getSongLength()
    {
        return songLength;
    }

    public void setSongLength(Double songLength)
    {
        this.songLength = songLength;
    }

    public Boolean getSegue()
    {
        return segue;
    }

    public void setSegue(Boolean segue)
    {
        this.segue = segue;
    }

    public Boolean getFluidSegue()
    {
        return fluidSegue;
    }

    public void setFluidSegue(Boolean fluidSegue)
    {
        this.fluidSegue = fluidSegue;
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

    @Override
    public String toString()
    {
        return "Track{" +
                "id=" + id +
                ", show=" + show +
                ", set=" + set +
                ", showTrackNumber=" + showTrackNumber +
                ", song='" + song + '\'' +
                ", songLength=" + songLength +
                ", segue=" + segue +
                ", averageRating=" + averageRating +
                ", notes='" + notes + '\'' +
                ", reviews=" + reviews +
                '}';
    }
}
