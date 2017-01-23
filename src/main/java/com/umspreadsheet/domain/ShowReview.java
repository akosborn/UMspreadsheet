package com.umspreadsheet.domain;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ShowReview
{
    // Needed for JPA
    private ShowReview() {}

    @Id
    @GeneratedValue
    private Long id;
    private Show show;

    @ManyToOne
    private Author author;

    @Column(columnDefinition = "TEXT")
    private String comment;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date reviewedOn;

    public Long getId()
    {
        return id;
    }

    public Show getShow()
    {
        return show;
    }

    public void setShow(Show show)
    {
        this.show = show;
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

    public Date getReviewedOn()
    {
        return reviewedOn;
    }

    public void setReviewedOn(Date reviewedOn)
    {
        this.reviewedOn = reviewedOn;
    }
}
