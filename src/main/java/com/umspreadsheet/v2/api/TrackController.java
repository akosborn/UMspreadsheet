package com.umspreadsheet.v2.api;

import com.umspreadsheet.v1.track.Track;
import com.umspreadsheet.v1.track.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(value = "http://localhost:4200")
@RestController
@RequestMapping("/api/tracks")
public class TrackController {

    private TrackService trackService;

    @Autowired
    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    /**
     *
     * @param page Zero-based page of shows to return. Default is 0.
     * @param size Number of shows per page. Default is 5.
     * @param sortBy Property to sort shows by
     * @param sortDir Sort direction: asc or dsc
     * @return Tracks sorted by default values or request params
     */
    @RequestMapping("")
    public Page<Track> getAll(@RequestParam(value = "song", required = false) String song,
                              @RequestParam(value = "page", defaultValue = "0") int page,
                              @RequestParam(value = "size", defaultValue = "5") int size,
                              @RequestParam(value = "sort-by", defaultValue = "show.date") String sortBy,
                              @RequestParam(value = "sort-dir", defaultValue = "desc") String sortDir) {
        return trackService.loadAll(
                song,
                new PageRequest(page, size,
                        new Sort(
                                sortDir.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC,
                                sortBy
                        )
                )
        );
    }
}
