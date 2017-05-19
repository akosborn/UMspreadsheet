package com.umspreadsheet.track;

import com.umspreadsheet.show.Show;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class TrackService
{
    private TrackRepository trackRepository;

    @Autowired
    public TrackService(TrackRepository trackRepository)
    {
        this.trackRepository = trackRepository;
    }

    public List<Track> getTopThreeSongs()
    {
        return trackRepository.findTop3ByOrderByAverageRatingDesc();
    }

    public Track findById(Long id)
    {
        return trackRepository.findOne(id);
    }

    public Page<Track> getByAverageRating(PageRequest pageRequest)
    {
        return trackRepository.findByAverageRatingIsNotNullOrderByAverageRatingDesc(pageRequest);
    }

    public Page<Track> criteriaTest(Specification<Track> specifications, PageRequest pageRequest)
    {
        return trackRepository.findAll(specifications, pageRequest);
    }

    public Track save(Track track)
    {
        return trackRepository.save(track);
    }

    public List<Track> getByShow(Show show)
    {
        return trackRepository.findByShow(show);
    }

    public List<BigInteger> findAllIds()
    {
        return trackRepository.findAllIds();
    }
}
