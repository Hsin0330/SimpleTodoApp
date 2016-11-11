package com.example.hsinhung.simpletodo.utilities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by SymPhoNy on 10/11/2016.
 */

public class Utility {

    private static DateFormat dateTimeParser = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.TAIWAN);
    private static DateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd", Locale.TAIWAN);

    public static Date parseStringToDateTime(String dateString) {
        Date date = null;
        try {
            date = dateTimeParser.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String formatDateTimeToString(Date date) {
        return dateTimeParser.format(date);
    }

    public static String formatDateToString(Date date) {
        return dateParser.format(date);
    }
}
