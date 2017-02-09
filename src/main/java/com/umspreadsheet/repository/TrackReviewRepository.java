package com.umspreadsheet.repository;

import com.umspreadsheet.domain.TrackReview;
import com.umspreadsheet.domain.UmShow;
import com.umspreadsheet.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackReviewRepository extends CrudRepository<TrackReview, Long>
{
    @Query(value = "select review from TrackReview review join review.track track where track.show = (:show) and " +
            "review.user = (:user)")
    List<TrackReview> findAllByUserAndShow(@Param("show")UmShow show, @Param("user")User user);
}
