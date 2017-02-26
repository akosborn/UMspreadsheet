package com.umspreadsheet.criteria;

public class SearchCriteria
{
    private String key;
    private String operation;
    private Object value;
    private String dateSegment;

    public static final String DATE_SEGMENT_YEAR = "YEAR";
    public static final String DATE_SEGMENT_MONTH = "MONTH";
    public static final String DATE_SEGMENT_DAY = "DAY";


    public SearchCriteria(String key) {}

    public SearchCriteria(final String key, final String operation, final Object value)
    {
        this.key = key;
        this.operation = operation;
        this.value = value;
    }

    // Used for filtering by month, date, and/or year
    public SearchCriteria(String key, String operation, Object value, String dateSegment)
    {
        this.key = key;
        this.operation = operation;
        this.value = value;
        this.dateSegment = dateSegment;
    }

    public String getKey()
    {
        return key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }

    public String getOperation()
    {
        return operation;
    }

    public void setOperation(String operation)
    {
        this.operation = operation;
    }

    public Object getValue()
    {
        return value;
    }

    public void setValue(Object value)
    {
        this.value = value;
    }

    public String getDateSegment() {
        return dateSegment;
    }

    public void setDateSegment(String dateSegment) {
        this.dateSegment = dateSegment;
    }
}
