package com.umspreadsheet.show;

import com.umspreadsheet.set.Set;
import com.umspreadsheet.set.SetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RequestMapping("/api")
@RestController
public class ShowAPIController {
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

    @PostMapping("/show")
    public Show addShow(@RequestBody ShowDTO showDTO) {
        Show existingShow = showService.findByDate(showDTO.getDate());
        if (existingShow != null) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Show with date " + showDTO.getDate() + " already exists");
        }
        Show show = new Show();
        show.setDate(showDTO.getDate());
        show.setState(showDTO.getState());
        show.setCity(showDTO.getCity());
        show.setVenue(showDTO.getVenue());
        show.setSets(showDTO.getSets());

        return showService.save(show);
    }
}
