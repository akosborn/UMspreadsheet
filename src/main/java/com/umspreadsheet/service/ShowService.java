package com.umspreadsheet.service;

import com.umspreadsheet.domain.UmShow;
import com.umspreadsheet.repository.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class ShowService
{
    private ShowRepository showRepository;

    @Autowired
    public ShowService(ShowRepository showRepository)
    {
        this.showRepository = showRepository;
    }

    // Find all shows by year,  but don't load setlist entity
    /*public List<UmShow> getAllShowsByYearWithoutTracks(int year) throws ParseException
    {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.DAY_OF_YEAR, 1);
        Date firstDay = cal.getTime();

        cal.set(Calendar.MONTH, 11); // 11 = december
        cal.set(Calendar.DAY_OF_MONTH, 31); // new years eve
        Date lastDay = cal.getTime();

        return (List<UmShow>) showRepository.findByDateBetween(firstDay, lastDay);
    }

    public UmShow getShowByIdWithTracks(Long id)
    {
        return showRepository.findByIdAndFetchTracksEagerly(id);
    }

    public UmShow getShowByIdWithoutTracks(Long id)
    {
        return showRepository.findOne(id);
    }

    public List<UmShow> getAllShowsHavingReviews()
    {
        return showRepository.findAllWithReviews();
    }*/

    public List<UmShow> getTopThreeShows()
    {
        System.out.println(showRepository.findTop3ByOrderByAverageRating());
        return showRepository.findTop3ByOrderByAverageRating();
    }

    public List<UmShow> getLastTwoShows()
    {
        return showRepository.findTop2ByOrderByDateDesc();
    }

    public UmShow findById(Long id)
    {
        return showRepository.findById(id);
    }
}
