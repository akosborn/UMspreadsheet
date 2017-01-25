package com.umspreadsheet.repository;

import com.umspreadsheet.domain.UmShow;
import groovy.transform.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.SqlResultSetMapping;
import java.util.Date;
import java.util.List;

@Repository
public interface ShowRepository extends CrudRepository<UmShow, Long>
{
    // SHOW WITH TRACKS ==================================================
    @Query("SELECT u FROM UmShow u JOIN FETCH u.tracks WHERE u.id = (:id)")
    public UmShow findByIdAndFetchTracksEagerly(@Param("id") Long id);

    @Query(value = "select um_show.* " +
            "from um_show " +
            "join " +
            "(select distinct track.um_show_id " +
            "from track " +
            "join track_review on track.id = track_review.track_id) as p where um_show.id = p.um_show_id",
            nativeQuery = true)
    public Iterable<UmShow> findAllWithReviews();

    public Iterable<UmShow> findByDateBetween(Date first, Date last);
}
