package com.umspreadsheet.v1.criteria;

public interface SpecificationsBuilder
{
    SpecificationsBuilder with(String key, String operation, Object value);
}
