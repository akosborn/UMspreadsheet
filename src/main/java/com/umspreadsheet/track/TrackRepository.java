package com.umspreadsheet.track;

import com.umspreadsheet.track.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackRepository
        extends JpaRepository<Track, Long>, JpaSpecificationExecutor<Track>
{
    /*@Query(value = "SELECT track.* " +
            "FROM track " +
            "ORDER BY track_average_rating DESC LIMIT 3",
            nativeQuery = true)
    public List<Track> findTopThreeSongs();*/

    List<Track> findTop3ByOrderByAverageRatingDesc();

    List<Track> findTop40ByOrderByAverageRatingDesc();
}
