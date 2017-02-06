package com.umspreadsheet.service;

import com.umspreadsheet.domain.Track;
import com.umspreadsheet.domain.UmShow;
import com.umspreadsheet.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class ReviewService
{
    private ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository)
    {
        this.reviewRepository = reviewRepository;
    }

    // Find all shows by year,  but don't load setlist entity
    public List<UmShow> getAllShowsByYearWithoutTracks(int year) throws ParseException
    {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.DAY_OF_YEAR, 1);
        Date firstDay = cal.getTime();

        cal.set(Calendar.MONTH, 11); // 11 = december
        cal.set(Calendar.DAY_OF_MONTH, 31); // new years eve
        Date lastDay = cal.getTime();

        return (List<UmShow>) reviewRepository.findByDateBetween(firstDay, lastDay);
    }

    public UmShow getShowByIdWithTracks(Long id)
    {
        return reviewRepository.findByIdAndFetchTracksEagerly(id);
    }

    public UmShow getShowByIdWithoutTracks(Long id)
    {
        return reviewRepository.findOne(id);
    }

    public List<UmShow> getAllShowsHavingReviews()
    {
        return reviewRepository.findAllWithReviews();
    }

    public List<UmShow> getTopThreeShows()
    {
        System.out.println("made it...........");
        return reviewRepository.findTopThreeShows();
    }
}
