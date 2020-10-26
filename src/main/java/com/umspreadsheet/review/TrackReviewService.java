package com.umspreadsheet.review;

import com.umspreadsheet.show.Show;
import com.umspreadsheet.track.Track;
import com.umspreadsheet.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        trackReviewRepository.deleteById(id);
    }

    public List<TrackReview> findByUserAndShow(Show show, User user)
    {
        return trackReviewRepository.findAllByUserAndShow(show, user);
    }

    public Optional<TrackReview> findById(Long id)
    {
        return trackReviewRepository.findById(id);
    }

    public List<TrackReview> getTenMostRecentReviews()
    {
        return trackReviewRepository.findTop10ByOrderByReviewedOnDesc();
    }

    public List<TrackReview> getAllByShow(Show show)
    {
        return trackReviewRepository.findTop20ByTrackShowOrderByReviewedOnDesc(show);
    }

    public List<TrackReview> findThirtyByTrack(Track track)
    {
        return trackReviewRepository.findTop30ByTrackOrderByReviewedOnDesc(track);
    }

    public TrackReview findByUsernameAndTrackId(String username, Long trackId)
    {
        return trackReviewRepository.findAllByUserUsernameAndTrackIdOrderByTrackSetPositionAsc(username, trackId);
    }

    public List<TrackReview> findByUsernameAndShow(String username, Long id)
    {
        return trackReviewRepository.findAllByUserUsernameAndTrackShowIdOrderByTrackSetPositionAsc(username, id);
    }

    public List<TrackReview> findByShowId(Long id)
    {
        return trackReviewRepository.findTop20ByTrackShowIdOrderByReviewedOnDesc(id);
    }
}
