package com.umspreadsheet.show;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
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
    /*public List<Show> getAllShowsByYearWithoutTracks(int year) throws ParseException
    {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.DAY_OF_YEAR, 1);
        Date firstDay = cal.getTime();

        cal.set(Calendar.MONTH, 11); // 11 = december
        cal.set(Calendar.DAY_OF_MONTH, 31); // new years eve
        Date lastDay = cal.getTime();

        return (List<Show>) showRepository.findByDateBetween(firstDay, lastDay);
    }

    public Show getShowByIdWithTracks(Long id)
    {
        return showRepository.findByIdAndFetchTracksEagerly(id);
    }

    public Show getShowByIdWithoutTracks(Long id)
    {
        return showRepository.findOne(id);
    }

    public List<Show> getAllShowsHavingReviews()
    {
        return showRepository.findAllWithReviews();
    }*/

    public List<Show> getTopThreeShows()
    {
        return showRepository.findTop3ByOrderByAverageRatingDesc();
    }

    public List<Show> getTopTwentyShows()
    {
        return showRepository.findTop20ByOrderByAverageRatingDesc();
    }

    public List<Show> getLastTwoShows()
    {
        return showRepository.findTop2ByOrderByDateDesc();
    }

    public List<Show> getLastThreeShows()
    {
        return showRepository.findTop3ByOrderByDateDesc();
    }

    public Show findById(Long id)
    {
        return showRepository.findById(id);
    }

    public List<BigInteger> findAllShowIds()
    {
        return showRepository.findAllIds();
    }

    public List<Show> getShowsByFilter(Specification<Show> specification)
    {
        return showRepository.findAll(specification);
    }
}
