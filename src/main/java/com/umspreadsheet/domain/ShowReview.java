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

    @ManyToOne
    private UmShow show;

    @ManyToOne
    private User user;

    @Column(columnDefinition = "TEXT")
    private String comment;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date reviewedOn;

    public Long getId()
    {
        return id;
    }

    public UmShow getShow()
    {
        return show;
    }

    public void setShow(UmShow show)
    {
        this.show = show;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
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
