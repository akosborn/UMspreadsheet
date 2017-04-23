package com.umspreadsheet.helper;

import com.umspreadsheet.criteria.SpecificationsBuilder;

public class ControllerHelper
{
    public static void addRatingConstraints(String rating, SpecificationsBuilder builder)
    {
        if (rating.equals("diamond"))
        {
            builder.with("averageRating", ">", "8.99");
        }

        else if (rating.equals("gold"))
        {
            builder.with("averageRating", ">", "7.99");
            builder.with("averageRating", "<", "9");
        }

        else if (rating.equals("silver"))
        {
            builder.with("averageRating", ">", "6.99");
            builder.with("averageRating", "<", "8.00");
        }

        else if (rating.equals("bronze"))
        {
            builder.with("averageRating", ">", "5.99");
            builder.with("averageRating", "<", "7.00");
        }

        else if (rating.equals("unranked"))
        {
            builder.with("averageRating", "<", "7.00");
        }
    }
}
