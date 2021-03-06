package com.umspreadsheet.track;

import com.umspreadsheet.criteria.SearchCriteria;
import com.umspreadsheet.criteria.SpecificationsBuilder;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class TrackSpecificationsBuilder implements SpecificationsBuilder
{
    private final List<SearchCriteria> params;

    public TrackSpecificationsBuilder()
    {
        params  = new ArrayList<>();
    }

    public TrackSpecificationsBuilder with(String key, String operation, Object value)
    {
        params.add(new SearchCriteria(key, operation, value));

        return this;
    }

    public TrackSpecificationsBuilder with(String key, String operation, Object value, String dateSegment)
    {
        params.add(new SearchCriteria(key, operation, value, dateSegment));

        return this;
    }

    public Specification<Track> build()
    {
        if (params.size() == 0)
        {
            return null;
        }

        List<Specification<Track>> specifications = new ArrayList<>();
        for (SearchCriteria param : params)
        {
            specifications.add(new TrackSpecification(param));
        }

        Specification<Track> result = specifications.get(0);
        for (int i = 1; i < specifications.size(); i++)
        {
            result = Specification.where(result).and(specifications.get(i));
        }

        return result;
    }
}
