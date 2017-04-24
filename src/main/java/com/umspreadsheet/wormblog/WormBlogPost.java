package com.umspreadsheet.wormblog;

import com.umspreadsheet.helper.ControllerHelper;
import com.umspreadsheet.user.User;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.SafeHtml;

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

    @SafeHtml
    @NotEmpty
    @Length(max = 200)
    private String title;

    @SafeHtml
    @NotEmpty
    @Column(columnDefinition = "TEXT")
    private String body;

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
        slug = ControllerHelper.toSlug(title) + "-" + ControllerHelper.dateToString(postedOn);
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
