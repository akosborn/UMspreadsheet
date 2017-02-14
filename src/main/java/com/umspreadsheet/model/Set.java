package com.umspreadsheet.model;

import com.umspreadsheet.show.Show;
import com.umspreadsheet.track.Track;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "sets")
public class Set
{
    public Set(){}

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @OneToMany(mappedBy = "set")
    private List<Track> tracks;
    private Double averageRating;

    @ManyToOne
    private Show show;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Show getShow()
    {
        return show;
    }

    public void setShow(Show show)
    {
        this.show = show;
    }

    public List<Track> getTracks()
    {
        return tracks;
    }

    public void setTracks(List<Track> tracks)
    {
        this.tracks = tracks;
    }

    public Double getAverageRating()
    {
        return averageRating;
    }

    public void setAverageRating(Double averageRating)
    {
        this.averageRating = averageRating;
    }
}
