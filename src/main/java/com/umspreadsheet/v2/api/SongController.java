package com.umspreadsheet.v2.api;

import com.umspreadsheet.v1.track.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(value = "http://localhost:4200")
@RestController
@RequestMapping("/api/songs")
public class SongController {

    private SongService songService;

    @Autowired
    public SongController(SongService songService) {
        this.songService = songService;
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
    public Page<Song> getAll(@RequestParam(value = "page", defaultValue = "0") int page,
                             @RequestParam(value = "size", required = false) Integer size,
                             @RequestParam(value = "sort-by", defaultValue = "name") String sortBy,
                             @RequestParam(value = "sort-dir", defaultValue = "asc") String sortDir) {
        return songService.loadAll(
                new PageRequest(page, size == null ? Integer.MAX_VALUE : size,
                        new Sort(
                                sortDir.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC,
                                sortBy
                        )
                )
        );
    }
}
