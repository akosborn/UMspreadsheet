package com.umspreadsheet.wormblog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WormBlogService
{
    private WormBlogRepository wormBlogRepository;

    @Autowired
    public WormBlogService(WormBlogRepository wormBlogRepository)
    {
        this.wormBlogRepository = wormBlogRepository;
    }

    public WormBlogPost save(WormBlogPost post)
    {
        return wormBlogRepository.save(post);
    }

    public Iterable<WormBlogPost> findAll()
    {
        return wormBlogRepository.findAllByOrderByPostedOnDesc();
    }
}
