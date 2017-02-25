package com.umspreadsheet.track;

import javax.persistence.criteria.*;

/**
 * Created by andrew on 2/24/17.
 */
public abstract class CustomSpecification
{
    private SearchCriteria criteria;

    public Predicate toPredicate(Root<Track> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder)
    {
        if (criteria.getOperation().equalsIgnoreCase(">"))
        {
            return criteriaBuilder.greaterThan(root.get(criteria.getKey()), criteria.getValue().toString());
        }
        else if (criteria.getOperation().equalsIgnoreCase("<"))
        {
            return criteriaBuilder.lessThan(root.get(criteria.getKey()), criteria.getValue().toString());
        }
        else if (criteria.getOperation().equalsIgnoreCase(":"))
        {
            if (criteria.getDateSegment() != null && criteria.getDateSegment().equals(SearchCriteria.DATE_SEGMENT_YEAR))
            {
                Expression<Integer> year = criteriaBuilder.function(
                        "year", Integer.class, root.get("show").get("date"));

                return criteriaBuilder.equal(year, criteria.getValue());
            }

            if (criteria.getDateSegment() != null && criteria.getDateSegment().equals(SearchCriteria.DATE_SEGMENT_MONTH))
            {
                Expression<Integer> month = criteriaBuilder.function(
                        "month", Integer.class, root.get("show").get("date"));

                return criteriaBuilder.equal(month, criteria.getValue());
            }

            if (criteria.getDateSegment() != null && criteria.getDateSegment().equals(SearchCriteria.DATE_SEGMENT_DAY))
            {
                Expression<Integer> day = criteriaBuilder.function(
                        "day", Integer.class, root.get("show").get("date"));

                return criteriaBuilder.equal(day, criteria.getValue());
            }

            if (root.get(criteria.getKey()).getJavaType() == String.class)
            {
                return criteriaBuilder.like(
                        root.get(criteria.getKey()), "%" + criteria.getValue() + "%");
            }

            else
            {
                return criteriaBuilder.equal(root.get(criteria.getKey()), criteria.getValue());
            }
        }

        return null;
    }
}
