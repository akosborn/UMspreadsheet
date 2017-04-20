package com.umspreadsheet.wormblog;

import com.umspreadsheet.user.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "wormblog_posts")
public class WormBlogPost
{
    public WormBlogPost(){}

    @Id
    @GeneratedValue
    private Long id;

    private User author;
}
