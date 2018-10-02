package com.umspreadsheet.v2.api;

import com.umspreadsheet.v1.show.Show;
import com.umspreadsheet.v1.show.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/shows")
public class ShowController {

    private ShowService showService;

    @Autowired
    public ShowController(ShowService showService) {
        this.showService = showService;
    }

    @RequestMapping("")
    public List<Show> getRecent(@RequestParam(value = "page", defaultValue = "0") int page,
                                @RequestParam(value = "size", defaultValue = "5") int size) {
        return showService.loadByDate(new PageRequest(page, size)).getContent();
    }
}
