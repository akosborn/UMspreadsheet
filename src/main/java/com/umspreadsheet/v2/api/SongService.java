package com.umspreadsheet.v2.api;

import com.umspreadsheet.v1.track.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class SongService {

    private SongRepository repo;

    @Autowired
    public SongService(SongRepository repo) {
        this.repo = repo;
    }

    public Page<Song> loadAll(PageRequest pageRequest) {
        return repo.findAll(pageRequest);
    }
}
