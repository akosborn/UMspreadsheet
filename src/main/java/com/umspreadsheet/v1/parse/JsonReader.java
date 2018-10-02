package com.umspreadsheet.v1.parse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.umspreadsheet.v1.set.Set;
import com.umspreadsheet.v1.set.SetService;
import com.umspreadsheet.v1.show.Show;
import com.umspreadsheet.v1.show.ShowService;
import com.umspreadsheet.v1.track.Track;
import com.umspreadsheet.v1.track.TrackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class JsonReader {

    private ShowService showService;
    private SetService setService;
    private TrackService trackService;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public JsonReader(ShowService showService, SetService setService, TrackService trackService) {
        this.showService = showService;
        this.setService = setService;
        this.trackService = trackService;
    }

    public void readFromJson() throws FileNotFoundException {

        List<String> fileNames = new ArrayList<>();

        File[] files = new File("C:/Users/andre/Desktop/shows/lengths").listFiles();

        Gson gson = new Gson();

        assert files != null;
        for (File file : files) {

            JsonArray showArray  = new JsonParser().parse(new FileReader(file)).getAsJsonArray();
            for (JsonElement showEl : showArray) {
                Show show = gson.fromJson(showEl, Show.class);
                Show savedShow = showService.save(gson.fromJson(showEl, Show.class));
                log.info("Read and saved show {} {},{}", show.getDate(), show.getCity(), show.getState());
                for (Set set : show.getSets()) {
                    set.setShow(savedShow);
                    Set savedSet = setService.save(set);
                    for (Track track : set.getTracks()) {
                        track.setSet(savedSet);
                        track.setShow(savedShow);
                        trackService.save(track);
                    }
                }
            }
        }

    }
}
