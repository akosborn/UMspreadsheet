package com.umspreadsheet.show;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ShowService
{
    private ShowRepository showRepository;

    @Autowired
    public ShowService(ShowRepository showRepository)
    {
        this.showRepository = showRepository;
    }

    public List<Show> getTopFiveShows()
    {
        return showRepository.findTop5ByOrderByAverageRatingDesc();
    }

    public List<Show> findLastTwentyShows()
    {
        return showRepository.findTop20ByOrderByDateDesc();
    }

    public List<Show> findTopTenShows()
    {
        return showRepository.findTop10ByOrderByAverageRatingDesc();
    }

    public Page<Show> getByAverageRating(PageRequest pageRequest)
    {
        return showRepository.findByAverageRatingIsNotNullOrderByAverageRatingDesc(pageRequest);
    }

    public List<Show> getLastTwoShows()
    {
        return showRepository.findTop2ByOrderByDateDesc();
    }

    public List<Show> getLastThreeShows()
    {
        return showRepository.findTop3ByOrderByDateDesc();
    }

    public Optional<Show> findById(Long id)
    {
        return showRepository.findById(id);
    }

    public List<BigInteger> findAllShowIds()
    {
        return showRepository.findAllIds();
    }

    public Page<Show> getShowsByFilter(Specification<Show> specification, PageRequest pageRequest)
    {
        return showRepository.findAll(specification, pageRequest);
    }

    public Show save(Show show)
    {
        return showRepository.save(show);
    }

    public Show findPrevious(Date date)
    {
        return showRepository.findTop1ByDateBeforeOrderByDateDesc(date);
    }

    public Show findNext(Date date)
    {
        return showRepository.findTop1ByDateAfterOrderByDateAsc(date);
    }

    public Show findByDate(Date date) {
        return showRepository.findByDate(date);
    }
}
