package com.umspreadsheet.service;

import com.umspreadsheet.domain.Track;
import com.umspreadsheet.domain.UmShow;

import java.util.List;

public interface GoogleChartService
{
    public List<GoogleChartTrackDao> getTopThreeSongs();
}
