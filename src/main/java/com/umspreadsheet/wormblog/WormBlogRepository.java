package com.umspreadsheet.wormblog;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WormBlogRepository extends CrudRepository<WormBlogPost, Long>
{
}
