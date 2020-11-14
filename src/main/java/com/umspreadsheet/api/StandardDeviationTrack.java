package com.umspreadsheet.api;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.Date;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class StandardDeviationTrack {
    private Long id;
    private Long showId;
    private Date date;
    private String city;
    private String state;
    private String venue;
    private String song;
    private int pctDeviation;
    private int duration;
    private int mean;
    private String archiveId;
    private String nugsId;

    public StandardDeviationTrack(Date date, Long id, Long showId, String city, String state, String venue, String song, int pctDeviation, int duration, int mean, String archiveId, String nugsId) {
        this.date = date;
        this.id = id;
        this.showId = showId;
        this.city = city;
        this.state = state;
        this.venue = venue;
        this.song = song;
        this.pctDeviation = pctDeviation;
        this.duration = duration;
        this.mean = mean;
        this.archiveId = archiveId;
        this.nugsId = nugsId;
    }
}
