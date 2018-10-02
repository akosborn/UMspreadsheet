package com.umspreadsheet.v1.show;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Repository
public interface ShowRepository extends JpaRepository<Show, Long>, JpaSpecificationExecutor<Show>
{
    String FIND_ALL_IDS = "SELECT id FROM shows";

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
    */

    List<Show> findTop5ByOrderByAverageRatingDesc();

    Page<Show> findByAverageRatingIsNotNullOrderByAverageRatingDesc(Pageable pageable);

    List<Show> findTop2ByOrderByDateDesc();

    List<Show> findTop3ByOrderByDateDesc();

    List<Show> findTop20ByOrderByDateDesc();

    Show findById(Long id);

    @Query(value = FIND_ALL_IDS, nativeQuery = true)
    List<BigInteger> findAllIds();

    List<Show> findTop10ByOrderByAverageRatingDesc();

    Show findTop1ByDateBeforeOrderByDateDesc(Date date);

    Show findTop1ByDateAfterOrderByDateAsc(Date date);

    Page<Show> findAll(Pageable pageable);
}
