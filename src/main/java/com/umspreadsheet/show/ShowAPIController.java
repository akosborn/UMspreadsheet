package com.umspreadsheet.show;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RequestMapping("/api")
@RestController
public class ShowAPIController
{
    public static final int PAGE_SIZE = 50;

    private final ShowService showService;

    @Autowired
    public ShowAPIController(ShowService showService) {
        this.showService = showService;
    }

    @GetMapping("/shows")
    public List<Show> getTopShows(@RequestParam(value = "page") Integer pageNumber) {
        Page<Show> page = showService.getByAverageRating(PageRequest.of(pageNumber, PAGE_SIZE));
        return page.getContent();
    }
}
