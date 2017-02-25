package com.umspreadsheet.show;


import com.umspreadsheet.track.SearchCriteria;

public interface SpecificationsBuilder
{
    SpecificationsBuilder with(String key, String operation, Object value);
}
