package com.umspreadsheet.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TrackStatsRepository {

    String BY_DEVIATION = "select s.date, t.id, s.id as show_id, t.song, round((((t.length - sd.mean) / sd.mean) * 100), 0) deviation_pct, t.length, sd.deviation, sd.mean, sd.upper_bound, sd.lower_bound from track t left join shows s on s.id = t.show_id join ( select song, round(avg(length), 0) mean, round(std(length), 0) deviation, (round(avg(length) + std(length), 0)) upper_bound, (round(avg(length) - std(length), 0)) lower_bound, count(*) played from track where length is not null group by song HAVING count(*) > 5 ) as sd on sd.song = t.song where t.length is not null and t.length > sd.upper_bound order by deviation_pct desc";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TrackStatsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    List<StandardDeviationTrack> findByStdDeviationDesc() {
        return jdbcTemplate.query(BY_DEVIATION, (rs, rowNum) ->
                new StandardDeviationTrack(
                        rs.getDate("date"),
                        rs.getLong("id"),
                        rs.getLong("show_id"),
                        rs.getString("song"),
                        rs.getInt("deviation_pct"),
                        rs.getInt("length"),
                        rs.getInt("mean")
                )
        );
    }
}
