package com.umspreadsheet.criteria;

public interface SpecificationsBuilder
{
    SpecificationsBuilder with(String key, String operation, Object value);
}
