package com.umspreadsheet.review;

import com.umspreadsheet.model.TrackReview;
import com.umspreadsheet.show.Show;
import com.umspreadsheet.user.User;
import com.umspreadsheet.review.TrackReviewRepository;
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

    public List<TrackReview> findByUserAndShow(Show show, User user)
    {
        return trackReviewRepository.findAllByUserAndShow(show, user);
    }

    public TrackReview findById(Long id)
    {
        return trackReviewRepository.findOne(id);
    }

    public List<TrackReview> getTenMostRecentReviews()
    {
        return trackReviewRepository.findTop10ByOrderByReviewedOnDesc();
    }
}
