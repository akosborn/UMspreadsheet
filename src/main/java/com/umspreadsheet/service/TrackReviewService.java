package com.umspreadsheet.service;

import com.umspreadsheet.domain.TrackReview;
import com.umspreadsheet.repository.TrackReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrackReviewService
{
    private TrackReviewRepository trackReviewRepository;

    @Autowired
    public TrackReviewService(TrackReviewRepository trackReviewRepository)
    {
        this.trackReviewRepository = trackReviewRepository;
    }

    public TrackReview save(TrackReview trackReview)
    {
        return trackReviewRepository.save(trackReview);
    }
}
