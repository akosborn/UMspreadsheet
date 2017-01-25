package com.umspreadsheet.domain;

import javax.persistence.*;

@Entity
public class Song
{
    // Needed for JPA
    private Song() {}

    @Id
    @Column(name = "song_id")
    @GeneratedValue
    private Long id;
    private String name;
    private String album;

    public Long getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getAlbum()
    {
        return album;
    }

    public void setAlbum(String album)
    {
        this.album = album;
    }
}
