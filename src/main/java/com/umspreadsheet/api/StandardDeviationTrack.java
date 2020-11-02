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
    private String song;
    private int pctDeviation;
    private int duration;
    private int mean;

    public StandardDeviationTrack(Date date, Long id, Long showId, String song, int pctDeviation, int duration, int mean) {
        this.date = date;
        this.id = id;
        this.showId = showId;
        this.song = song;
        this.pctDeviation = pctDeviation;
        this.duration = duration;
        this.mean = mean;
    }
}
