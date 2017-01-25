package com.umspreadsheet.domain;

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
    private UmShow show;
    private Integer setNumber;
    private Integer trackNumber;

    @ManyToOne
    private Song song;
    private Double songLength;

    @Column(columnDefinition = "TINYINT(1)")
    private Boolean segueToNext;
    private Double trackAverageRating;
    private String trackNotes;

    @OneToMany(mappedBy = "track")
    private List<TrackReview> trackReviews;

    public Long getId()
    {
        return id;
    }

    public void setShow(UmShow show)
    {
        this.show = show;
    }

    public Integer getSetNumber()
    {
        return setNumber;
    }

    public void setSetNumber(Integer setNumber)
    {
        this.setNumber = setNumber;
    }

    public Integer getTrackNumber()
    {
        return trackNumber;
    }

    public void setTrackNumber(Integer trackNumber)
    {
        this.trackNumber = trackNumber;
    }

    public Song getSong()
    {
        return song;
    }

    public void setSong(Song song)
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

    public Boolean getSegueToNext()
    {
        return segueToNext;
    }

    public void setSegueToNext(Boolean segueToNext)
    {
        this.segueToNext = segueToNext;
    }

    public Double getTrackAverageRating()
    {
        return trackAverageRating;
    }

    public void setTrackAverageRating(Double trackAverageRating)
    {
        this.trackAverageRating = trackAverageRating;
    }

    public String getTrackNotes()
    {
        return trackNotes;
    }

    public void setTrackNotes(String trackNotes)
    {
        this.trackNotes = trackNotes;
    }

    public List<TrackReview> getTrackReviews()
    {
        return trackReviews;
    }

    public void setTrackReviews(List<TrackReview> trackReviews)
    {
        this.trackReviews = trackReviews;
    }


}
