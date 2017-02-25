package com.umspreadsheet.track;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackRepository extends JpaRepository<Track, Long>, JpaSpecificationExecutor<Track>
{
    List<Track> findTop3ByOrderByAverageRatingDesc();

    List<Track> findTop40ByAverageRatingIsNotNullOrderByAverageRatingDesc();
}
