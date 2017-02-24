package com.umspreadsheet.track;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

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

    public List<Track> getTopFortySongs()
    {
        return trackRepository.findTop40ByOrderByAverageRatingDesc();
    }

    public List<Track> criteriaTest(Specification<Track> spec)
    {
        return trackRepository.findAll(spec);
    }
}
