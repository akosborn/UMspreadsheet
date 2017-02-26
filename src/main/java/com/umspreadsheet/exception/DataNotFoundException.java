package com.umspreadsheet.exception;

public class DataNotFoundException extends RuntimeException
{
    private static final long serialVersionUID = 3765179472465808885L;

    public DataNotFoundException(String message)
    {
        super(message);
    }
}
