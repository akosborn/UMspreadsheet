package com.umspreadsheet.set;

import com.fasterxml.jackson.annotation.*;
import com.umspreadsheet.show.Show;
import com.umspreadsheet.track.Track;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
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

    //@JsonIgnoreProperties("set")
    @OneToMany(mappedBy = "set")
    @Cascade({CascadeType.DELETE})
    @JsonIgnoreProperties(value = {"set"}, allowSetters = true)
    private List<Track> tracks;

    private Double averageRating;

    @ManyToOne
    @JsonIgnoreProperties(value = "sets", allowSetters = true)
    private Show show;

    private Integer position;

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

    public Integer getPosition()
    {
        return position;
    }

    public void setPosition(Integer position)
    {
        this.position = position;
    }
}
