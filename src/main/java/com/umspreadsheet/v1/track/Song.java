package com.umspreadsheet.v1.track;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Song {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Integer mean;

    private Integer median;

    private Integer mode;

    private Integer max;

    private Integer min;

    public Song() {
    }
}
