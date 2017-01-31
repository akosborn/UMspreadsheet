package com.umspreadsheet.repository;

import com.umspreadsheet.domain.Track;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackRepository extends CrudRepository<Track, Long>
{
    @Query(value = "SELECT track.* " +
            "FROM track " +
            "ORDER BY track_average_rating DESC LIMIT 2",
            nativeQuery = true)
    public List<Track> findTopFiveSongs();
}
