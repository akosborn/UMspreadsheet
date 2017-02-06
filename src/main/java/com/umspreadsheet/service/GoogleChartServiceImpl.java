package com.umspreadsheet.service;

import com.umspreadsheet.domain.Track;
import com.umspreadsheet.repository.ReviewRepository;
import com.umspreadsheet.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GoogleChartServiceImpl implements GoogleChartService
{
    private ReviewRepository reviewRepository;
    private TrackRepository trackRepository;

    @Autowired
    public GoogleChartServiceImpl(ReviewRepository reviewRepository,
                              TrackRepository trackRepository)
    {
        this.reviewRepository = reviewRepository;
        this.trackRepository = trackRepository;
    }

    @Override
    public List<GoogleChartTrackDao> getTopThreeSongs()
    {
        List<GoogleChartTrackDao> chartRows = new ArrayList<>();
        for (Track track : trackRepository.findTopFiveSongs())
        {
            chartRows.add(new GoogleChartTrackDao(track));
        }

        return chartRows;
    }
}
