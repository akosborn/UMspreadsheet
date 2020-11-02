package com.umspreadsheet.api;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackStatsService {

    private TrackStatsRepository repo;

    public TrackStatsService(TrackStatsRepository repo) {
        this.repo = repo;
    }

    public List<StandardDeviationTrack> getTracksByDeviation() {
        return repo.findByStdDeviationDesc();
    }
}