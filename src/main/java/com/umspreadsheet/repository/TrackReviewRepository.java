package com.umspreadsheet.repository;

import com.umspreadsheet.domain.TrackReview;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackReviewRepository extends CrudRepository<TrackReview, Long>
{
}
