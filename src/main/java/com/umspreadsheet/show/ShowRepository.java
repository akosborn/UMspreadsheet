package com.umspreadsheet.show;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface ShowRepository extends CrudRepository<Show, Long>
{
    public static final String FIND_ALL_IDS = "SELECT id FROM shows";

    // SHOW WITH TRACKS ==================================================
    /*@Query("SELECT u FROM Show u JOIN FETCH u.tracks WHERE u.id = (:id)")
    public Show findByIdAndFetchTracksEagerly(@Param("id") Long id);

    @Query(value = "select shows.* " +
            "from shows " +
            "join " +
            "(select distinct track.show_id " +
            "from track " +
            "join track_review on track.id = track_review.track_id) as p where um_show.id = p.show_id",
            nativeQuery = true)
    public List<Show> findAllWithReviews();

    public List<Show> findByDateBetween(Date first, Date last);

    @Query(value = "SELECT um_show.* " +
            "FROM um_show " +
            "ORDER BY average_rating DESC LIMIT 3",
            nativeQuery = true)
    public List<Show> findTopThreeShows();*/

    List<Show> findTop3ByOrderByAverageRatingDesc();

    List<Show> findTop20ByOrderByAverageRatingDesc();

    List<Show> findTop2ByOrderByDateDesc();

    List<Show> findTop3ByOrderByDateDesc();

    Show findById(Long id);

    @Query(value = FIND_ALL_IDS, nativeQuery = true)
    List<BigInteger> findAllIds();
}
