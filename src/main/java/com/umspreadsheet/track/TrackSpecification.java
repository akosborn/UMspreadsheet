package com.umspreadsheet.track;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class TrackSpecification implements Specification<Track>
{
    private SearchCriteria criteria;

    public TrackSpecification(final SearchCriteria criteria)
    {
        super();
        this.criteria = criteria;
    }

    @Override
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
