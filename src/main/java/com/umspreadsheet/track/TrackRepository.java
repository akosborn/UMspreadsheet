package com.umspreadsheet.track;

import com.umspreadsheet.show.Show;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface TrackRepository extends JpaRepository<Track, Long>, JpaSpecificationExecutor<Track>
{
    String FIND_ALL_IDS = "SELECT id FROM track";

    List<Track> findTop5ByOrderByAverageRatingDesc();

    Page<Track> findByAverageRatingIsNotNullOrderByAverageRatingDesc(Pageable pageable);

    List<Track> findByShow(Show show);

    @Query(value = FIND_ALL_IDS, nativeQuery = true)
    List<BigInteger> findAllIds();

    List<Track> findTop10ByOrderByAverageRatingDesc();

    List<Track> findByShow_Id(Long id);
}
