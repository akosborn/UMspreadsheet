package com.umspreadsheet.v1.helper;

import com.umspreadsheet.v1.criteria.SpecificationsBuilder;

import java.text.Normalizer;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

public class ControllerHelper
{
    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");

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

    // Sanitizes and converts post titles to slugs
    public static String toSlug(String input)
    {
        String noWhitespace = WHITESPACE.matcher(input).replaceAll("-");
        String normalized = Normalizer.normalize(noWhitespace, Normalizer.Form.NFD);
        String slug = NONLATIN.matcher(normalized).replaceAll("");

        return slug.toLowerCase(Locale.ENGLISH);
    }

    public static String dateToString(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        return year + "-" + month + "-" + day;
    }
}
