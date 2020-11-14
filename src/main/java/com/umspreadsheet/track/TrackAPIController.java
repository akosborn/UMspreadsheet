package com.umspreadsheet.track;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RequestMapping("/api")
@RestController
public class TrackAPIController {
    private final TrackService trackService;

    private static final int PAGE_SIZE = 50;

    @Autowired
    public TrackAPIController(TrackService trackService) {
        this.trackService = trackService;
    }

    @GetMapping("/songs")
    public List<Track> getTopTracks(@RequestParam(value = "page") Integer pageNumber) {
        return trackService.getByAverageRating(PageRequest.of(pageNumber, PAGE_SIZE)).getContent();
    }
}
