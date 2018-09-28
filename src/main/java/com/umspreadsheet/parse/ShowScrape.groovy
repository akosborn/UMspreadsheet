package com.umspreadsheet.parse

import com.fasterxml.jackson.databind.ObjectMapper
import com.umspreadsheet.model.Transition
import com.umspreadsheet.set.Set
import com.umspreadsheet.show.Show
import com.umspreadsheet.track.Track
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements

import java.nio.file.Files
import java.nio.file.Paths
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Month

ObjectMapper mapper = new ObjectMapper()
mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"))

int startYear = 1998
int endYear = 2018

for (int y = startYear; y <= endYear; y++) {

    List<Show> shows = new ArrayList<>()

    LocalDate start = LocalDate.of(y, Month.JANUARY, 1)
    LocalDate end = LocalDate.of(y, Month.DECEMBER, 31)

    for (LocalDate d = start; !d.isAfter(end); d = d.plusDays(1)) {

        int year = d.getYear()
        int month = d.getMonthValue()
        int day = d.getDayOfMonth()

        Document document = Jsoup.connect("http://allthings.umphreys.com/setlists/?date=" + year + "-" +
                String.format("%02d", month) + "-" + String.format("%02d", day)).get()

        // Check if show exists
        Element header = document.getElementsByTag("h3").first()
        String headerText = header.text()

        if (headerText != ("No Shows Found")) {
            println "Show found on ${d}"

            Elements setlistSections = document.getElementsByClass("setlistsolo")

            // Loop through <section>'s with class="setlist"
            for (Element setlistSection : setlistSections)
            {
                Show show = new Show()

                // Parse and set date
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd")
                show.setDate(dateFormat.parse(setlistSection.id()))

                // Get <h3> containing date, city, state, and venue
                Elements anchors = setlistSection.getElementsByClass("splashtitle").get(0).getAllElements()

                show.setVenue(anchors.get(2).text())
                show.setCity(anchors.get(3).text())
                show.setState(anchors.get(4).text())

                List<Set> sets = new ArrayList<>()

                // Get set-containing paragraphs
                Elements setParagraphs = setlistSection.select("section > p")
                for (Element setParagraph : setParagraphs) {

                    Set set = new Set()

                    String setName = setParagraph.getElementsByTag("b").text()
                    setName = setName.split(":")[0]
                    set.setName(setName)

                    List<Track> tracks = new ArrayList<>()
                    Elements songAnchors = setParagraph.getElementsByTag("a")
                    int position = 1
                    for (Element songAnchor : songAnchors)
                    {
                        Track track = new Track()
                        track.setSong(songAnchor.text())
                        track.setSetPosition(position)

                        String transition
                        // Check if the end of the show has been reached and break out of loop if so
                        if (songAnchor.nextSibling() != null)
                            transition = songAnchor.nextSibling().toString()
                        else
                            break

                        // If song has notes, add them
                        if (songAnchor.nextSibling().nodeName() == "sup")
                            track.setNotes(songAnchor.nextSibling().attr("title"))

                        track.setTransition(Transition.NONE)

                        if (transition.contains("&gt;"))
                            track.setTransition(Transition.ROUTINE)
                        else if (transition.contains("-&gt;"))
                            track.setTransition(Transition.IMPROV)

                        tracks.add(track)
                        position++
                    }
                    set.setTracks(tracks)
                    sets.add(set)
                }
                show.setSets(sets)
                shows.add(show)

//            Show savedShow = showService.save(show)
//            for (Set set : show.getSets()) {
//                set.setShow(savedShow);
//                Set savedSet = setService.save(set);
//                for (Track track : set.getTracks()) {
//                    track.setSet(savedSet);
//                    track.setShow(savedShow);
//                    trackService.save(track);
//                }
//            }
            }
        }
        else {
            println "No show on ${d}"
        }
    }
    println "Writing file ${y}.json"
    String json = mapper.writeValueAsString(shows)
    Files.write(Paths.get("C:/Users/andre/Desktop/shows/${y}.json"), json.getBytes())
}