package com.umspreadsheet.wormblog;

import com.umspreadsheet.user.User;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "wormblog_posts")
public class WormBlogPost
{
    public WormBlogPost(){}

    @Id
    @GeneratedValue
    private Long id;
    private String title;

    @Column(columnDefinition = "TEXT")
    private String body;

    @Column(columnDefinition = "TEXT")
    private String teaser;

    private String slug;

    @Temporal(TemporalType.TIMESTAMP)
    private Date postedOn = new Date();

    @ManyToOne
    private User author;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getBody()
    {
        return body;
    }

    public void setBody(String body)
    {
        this.body = body;
    }

    public String getTeaser()
    {
        return teaser;
    }

    public void setTeaser(String teaser)
    {
        this.teaser = teaser;
    }

    public String getSlug()
    {
        return slug;
    }

    public void setSlug(String slug)
    {
        this.slug = slug;
    }

    public Date getPostedOn()
    {
        return postedOn;
    }

    public void setPostedOn(Date postedOn)
    {
        this.postedOn = postedOn;
    }

    public User getAuthor()
    {
        return author;
    }

    public void setAuthor(User author)
    {
        this.author = author;
    }
}
