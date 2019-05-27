package com.umspreadsheet.v2.api;

import com.umspreadsheet.v1.show.Show;
import com.umspreadsheet.v1.show.ShowService;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

@RestController
@RequestMapping("/api/shows")
public class ShowController {

    private ShowService showService;

    @Autowired
    public ShowController(ShowService showService) {
        this.showService = showService;
    }

    /**
     *
     * @param page Zero-based page of shows to return. Default is 0.
     * @param size Number of shows per page. Default is 5.
     * @param sortBy Property to sort shows by
     * @param sortDir Sort direction: asc or dsc
     * @return Shows sorted by recency and limited by params
     */
    @RequestMapping("")
    public Page<Show> getRecent(@RequestParam(value = "page", defaultValue = "0") int page,
                                @RequestParam(value = "size", defaultValue = "5") int size,
                                @RequestParam(value = "sort-by", defaultValue = "date") String sortBy,
                                @RequestParam(value = "sort-dir", defaultValue = "desc") String sortDir) {
        return showService.loadByDate(
                new PageRequest(page, size,
                        new Sort(
                                sortDir.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC,
                                sortBy
                        )
                )
        );
    }
}
