package com.umspreadsheet.parse;

import com.umspreadsheet.set.Set;
import com.umspreadsheet.set.SetService;
import com.umspreadsheet.show.Show;
import com.umspreadsheet.show.ShowService;
import com.umspreadsheet.track.Track;
import com.umspreadsheet.track.TrackService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ATUParser
{
    public static void parse(ShowService showService, SetService setService, TrackService trackService) throws ParseException
    {
        try
        {
            for (int i = 2013; i > 1997; i--)
            {
                Document document = Jsoup.connect("http://allthings.umphreys.com/setlists/" + i + ".html").get();

                Elements setlistSections = document.getElementsByClass("setlist");

                // Loop through <section>'s with class="setlist"
                for (Element setlistSection : setlistSections)
                {
                    Show show = new Show();

                    // Parse and set date
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = dateFormat.parse(setlistSection.id());
                    show.setDate(date);

                    // Get <h3> containing date, city, state, and venue
                    Elements anchors = setlistSection.getElementsByClass("splashtitle").get(0).getAllElements();

                    String venue = anchors.get(2).text();
                    show.setVenue(venue);

                    String city = anchors.get(3).text();
                    show.setCity(city);

                    String state = anchors.get(4).text();
                    show.setState(state);

                    List<Set> sets = new ArrayList<>();
                    // Get set-containing paragraphs
                    //Elements setParagraphs = setlistSection.getElementsByTag("p");
                    Elements setParagraphs = setlistSection.select("section > p");
                    for (Element setParagraph : setParagraphs)
                    {
                        Set set = new Set();

                        String setName = setParagraph.getElementsByTag("b").text();
                        setName = setName.split(":")[0];
                        set.setName(setName);

                        List<Track> tracks = new ArrayList<>();
                        Elements songAnchors = setParagraph.getElementsByTag("a");
                        for (Element songAnchor : songAnchors)
                        {
                            Track track = new Track();

                            String song = songAnchor.text();
                            track.setSong(song);

                            String transition;
                            // Check if the end of the show has been reached and break out of loop if so
                            if (songAnchor.nextSibling() != null)
                                transition = songAnchor.nextSibling().toString();
                            else
                                break;

                            // If song has notes, add them
                            if (songAnchor.nextSibling().nodeName().equals("sup"))
                                track.setNotes(songAnchor.nextSibling().attr("title"));

                            track.setSegue(false);
                            track.setFluidSegue(false);

                            if (transition.contains("&gt;"))
                                track.setSegue(true);
                            else if (transition.contains("-&gt;"))
                                track.setFluidSegue(true);

                            tracks.add(track);
                        }
                        set.setTracks(tracks);
                        sets.add(set);
                    }
                    show.setSets(sets);

                    Show savedShow = showService.save(show);
                    for (Set set : show.getSets())
                    {
                        set.setShow(savedShow);
                        Set savedSet = setService.save(set);
                        for (Track track : set.getTracks())
                        {
                            track.setSet(savedSet);
                            track.setShow(savedShow);
                            trackService.save(track);
                        }
                    }
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
