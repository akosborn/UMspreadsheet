package com.umspreadsheet.service;

import com.umspreadsheet.domain.Track;
import com.umspreadsheet.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    /*public List<Track> getTopThreeSongs()
    {
        return trackRepository.findTopThreeSongs();
    }*/
}
