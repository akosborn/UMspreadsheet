package com.umspreadsheet.review;

import com.umspreadsheet.show.Show;
import com.umspreadsheet.track.Track;
import com.umspreadsheet.user.User;
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
    List<TrackReview> findAllByUserAndShow(@Param("show")Show show, @Param("user")User user);

    List<TrackReview> findTop20ByTrackShowOrderByReviewedOnDesc(Show show);

    List<TrackReview> findTop10ByOrderByReviewedOnDesc();

    List<TrackReview> findTop30ByTrackOrderByReviewedOnDesc(Track track);

    TrackReview findAllByUserUsernameAndTrackIdOrderByTrackSetPositionAsc(String username, Long trackId);

    List<TrackReview> findAllByUserUsernameAndTrackShowIdOrderByTrackSetPositionAsc(String username, Long showId);

    List<TrackReview> findTop20ByTrackShowIdOrderByReviewedOnDesc(Long id);

    TrackReview findFirstByUserAndTrack(User user, Track track);
}
