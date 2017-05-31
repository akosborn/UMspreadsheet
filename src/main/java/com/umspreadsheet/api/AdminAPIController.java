package com.umspreadsheet.api;

import com.umspreadsheet.set.Set;
import com.umspreadsheet.set.SetService;
import com.umspreadsheet.show.Show;
import com.umspreadsheet.show.ShowService;
import com.umspreadsheet.track.Track;
import com.umspreadsheet.track.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminAPIController
{
    private TrackService trackService;
    private ShowService showService;
    private SetService setService;

    @Autowired
    public AdminAPIController(TrackService trackService, ShowService showService, SetService setService)
    {
        this.trackService = trackService;
        this.showService = showService;
        this.setService = setService;
    }

    @RequestMapping(value = "/api/tracks")
    public @ResponseBody
    List findAllTracks()
    {
        List<Track> tracks = trackService.findByShowId((long) 304);

        return tracks;
    }

    @RequestMapping(value = "/api/tracks/{id}")
    public @ResponseBody Track findATrack(@PathVariable Long id)
    {
        Track track = trackService.findById(id);

        return track;
    }

    @RequestMapping(value = "/api/shows/{id}")
    public @ResponseBody
    Show findShow(@PathVariable Long id)
    {
        Show show = showService.findById(id);

        return show;
    }

    @RequestMapping(value = "/api/shows")
    public @ResponseBody List findShow()
    {
        List<Show> shows = new ArrayList<>();
        shows.add(showService.findById((long) 400));
        shows.add(showService.findById((long) 401));

        return shows;
    }

    @RequestMapping(value = "/api/shows/{id}", method = RequestMethod.PUT)
    public @ResponseBody Show updateShow(@RequestBody Show show, @PathVariable Long id)
    {
        return showService.save(show);
    }

    @RequestMapping(value = "/api/tracks", method = RequestMethod.POST)
    public @ResponseBody Track addTrack(@RequestBody Track track)
    {
        Track savedTrack = trackService.save(track);

        return savedTrack;
    }

    @RequestMapping(value = "/api/tracks/{id}", method = RequestMethod.PUT)
    public @ResponseBody Track updateTrack(@RequestBody Track track, @PathVariable Long id)
    {
        return trackService.save(track);
    }

    @RequestMapping(value = "/api/tracks/{id}", method = RequestMethod.DELETE)
    public @ResponseBody void deleteTrack(@PathVariable Long id)
    {
        trackService.delete(id);
    }


    @RequestMapping(value = "/api/sets/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Set findSet(@PathVariable Long id)
    {
        Set set = setService.findById(id);

        return set;
    }

    @RequestMapping(value = "/api/sets/{id}", method = RequestMethod.PUT)
    public @ResponseBody Set updateSet(@RequestBody Set set, @PathVariable Long id)
    {
        Set savedSet = setService.save(set);

        return savedSet;
    }

    @RequestMapping(value = "/api/sets", method = RequestMethod.POST)
    public @ResponseBody Set addSet(@RequestBody Set set)
    {
        Set savedSet = setService.save(set);

        return set;
    }

    @RequestMapping(value = "/api/sets/{id}")
    public @ResponseBody void deleteSet(@PathVariable Long id)
    {
        setService.delete(id);
    }
}
