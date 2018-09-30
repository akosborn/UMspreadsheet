package com.umspreadsheet.home;

import com.umspreadsheet.parse.JsonReader;
import com.umspreadsheet.set.Set;
import com.umspreadsheet.set.SetService;
import com.umspreadsheet.show.Show;
import com.umspreadsheet.show.ShowService;
import com.umspreadsheet.track.Track;
import com.umspreadsheet.track.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.FileNotFoundException;
import java.util.List;

@Controller
public class HomeController
{
    private TrackService trackService;
    private ShowService showService;
    private SetService setService;

    @Autowired
    public HomeController(TrackService trackService, ShowService showService, SetService setService)
    {
        this.trackService = trackService;
        this.showService = showService;
        this.setService = setService;
    }

    @RequestMapping("/")
    public String home(Model model)
    {
        JsonReader reader = new JsonReader(showService, setService, trackService);
        try {
            reader.readFromJson();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        model.addAttribute("topTenSongs", trackService.findTopTen());
        model.addAttribute("topTenShows", showService.findTopTenShows());
        model.addAttribute("lastThreeShows", setNumberOfReviews(showService.getLastThreeShows()));
        model.addAttribute("lastTwoShows", showService.getLastTwoShows());

        Show show = showService.findById((long) 401);

        return "index";
    }

    private List<Show> setNumberOfReviews(List<Show> shows)
    {
        for (Show show : shows)
        {
            Long numberOfReviews = 0L;
            for (Set set: show.getSets())
            {
                for (Track track : set.getTracks())
                {
                    numberOfReviews += track.getReviews().size();
                }
            }
            show.setNumberOfReviews(numberOfReviews);
        }

        return shows;
    }
}
