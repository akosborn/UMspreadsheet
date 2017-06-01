package com.umspreadsheet.api;

import com.umspreadsheet.set.Set;
import com.umspreadsheet.set.SetService;
import com.umspreadsheet.show.Show;
import com.umspreadsheet.show.ShowService;
import com.umspreadsheet.track.Track;
import com.umspreadsheet.track.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api/public")
public class PublicAPIController
{
    private TrackService trackService;
    private ShowService showService;
    private SetService setService;

    @Autowired
    public PublicAPIController(TrackService trackService, ShowService showService, SetService setService)
    {
        this.trackService = trackService;
        this.showService = showService;
        this.setService = setService;
    }

    @RequestMapping(value = "/tracks")
    public @ResponseBody
    List findAllTracks()
    {
        List<Track> tracks = trackService.findByShowId((long) 304);

        return tracks;
    }

    @RequestMapping(value = "/tracks/{id}")
    public @ResponseBody Track findATrack(@PathVariable Long id)
    {
        Track track = trackService.findById(id);

        return track;
    }

    @RequestMapping(value = "/shows/{id}")
    public @ResponseBody
    Show findShow(@PathVariable Long id)
    {
        Show show = showService.findById(id);

        return show;
    }

//    @RequestMapping(value = "/shows")
//    public @ResponseBody List findShow()
//    {
//        List<Show> shows = new ArrayList<>();
//        shows.add(showService.findById((long) 400));
//        shows.add(showService.findById((long) 401));
//
//        return shows;
//    }

    @RequestMapping(value = "/sets/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Set findSet(@PathVariable Long id)
    {
        Set set = setService.findById(id);

        return set;
    }
}
