package com.umspreadsheet.service;

import com.umspreadsheet.domain.TrackReview;
import com.umspreadsheet.domain.UmShow;
import com.umspreadsheet.domain.User;
import com.umspreadsheet.repository.TrackReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public void delete(Long id)
    {
        trackReviewRepository.delete(id);
    }

    public List<TrackReview> findByUserAndShow(UmShow show, User user)
    {
        return trackReviewRepository.findAllByUserAndShow(show, user);
    }

    public TrackReview findById(Long id)
    {
        return trackReviewRepository.findOne(id);
    }
}
