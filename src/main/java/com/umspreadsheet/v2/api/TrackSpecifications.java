package com.umspreadsheet.v2.api;

import com.umspreadsheet.v1.track.Track;
import org.springframework.data.jpa.domain.Specification;

public class TrackSpecifications {

    public static Specification<Track> hasName(String name) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (name == null) {
                return criteriaBuilder.isTrue(criteriaBuilder.literal(true)); // No filtering
            }
            return criteriaBuilder.equal(root.get("song"), name);
        };
    }
}
