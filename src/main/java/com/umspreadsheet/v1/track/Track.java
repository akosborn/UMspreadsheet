package com.umspreadsheet.v1.track;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.umspreadsheet.v1.model.Jam;
import com.umspreadsheet.v1.model.Transition;
import com.umspreadsheet.v1.model.Type;
import com.umspreadsheet.v1.review.TrackReview;
import com.umspreadsheet.v1.review.TrackReviewForm;
import com.umspreadsheet.v1.set.Set;
import com.umspreadsheet.v1.show.Show;
import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Track {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JsonIgnoreProperties(value = {"sets", "showReviews"}, allowSetters = true)
    private Show show;

    @ManyToOne
    @JsonIgnoreProperties(value = {"tracks", "show"}, allowSetters = true)
    private Set set;

    private Integer setPosition;

    @ManyToOne
    private Song song;

    private Long length;

    @Transient
    private Integer lengthDeviation;

    @Enumerated(value = EnumType.STRING)
    private Transition transition;

    private Double averageRating;

    private String notes;

    @Enumerated(value = EnumType.STRING)
    private Type type;

    @Enumerated(value = EnumType.STRING)
    private Jam jam;

    @Cascade({CascadeType.DELETE})
    @JsonIgnoreProperties({"track", "user"})
    @OneToMany(mappedBy = "track")
    private List<TrackReview> reviews;

    @Transient
    private String slug;

    @Transient
    private TrackReviewForm trackReviewForm;

    @Transient
    private TrackReview userTrackReview;

    public Track() {
    }
}
