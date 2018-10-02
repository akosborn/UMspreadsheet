package com.umspreadsheet.v1.playlist;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PlaylistController
{
    @RequestMapping("/playlists")
    public String playlistPage()
    {
        return "/playlist/playlists";
    }
}
