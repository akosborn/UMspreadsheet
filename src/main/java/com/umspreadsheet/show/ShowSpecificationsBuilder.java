package com.umspreadsheet.show;


import com.umspreadsheet.criteria.SpecificationsBuilder;
import com.umspreadsheet.criteria.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ShowSpecificationsBuilder implements SpecificationsBuilder
{
    private final List<SearchCriteria> params;

    public ShowSpecificationsBuilder()
    {
        params  = new ArrayList<>();
    }

    public ShowSpecificationsBuilder with(String key, String operation, Object value)
    {
        params.add(new SearchCriteria(key, operation, value));

        return this;
    }

    public ShowSpecificationsBuilder with(String key, String operation, Object value, String dateSegment)
    {
        params.add(new SearchCriteria(key, operation, value, dateSegment));

        return this;
    }

    public Specification<Show> build()
    {
        if (params.size() == 0)
        {
            return null;
        }

        List<Specification<Show>> specifications = new ArrayList<>();
        for (SearchCriteria param : params)
        {
            specifications.add(new ShowSpecification(param));
        }

        Specification<Show> result = specifications.get(0);
        for (int i = 1; i < specifications.size(); i++)
        {
            result = Specification.where(result).and(specifications.get(i));
        }

        return result;
    }
}
