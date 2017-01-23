package com.umspreadsheet.domain;

import javax.persistence.*;

@Entity
public class TrackReview
{
    // Needed for JPA
    private TrackReview() {}

    @Id
    @GeneratedValue
    private Long id;
    private Author author;

    @Column(columnDefinition = "TEXT")
    private String comment;

    @ManyToOne
    private Track track;

    public Long getId()
    {
        return id;
    }


    public Author getAuthor()
    {
        return author;
    }

    public void setAuthor(Author author)
    {
        this.author = author;
    }

    public String getComment()
    {
        return comment;
    }

    public void setComment(String comment)
    {
        this.comment = comment;
    }

    public Track getTrack()
    {
        return track;
    }

    public void setTrack(Track track)
    {
        this.track = track;
    }
}
