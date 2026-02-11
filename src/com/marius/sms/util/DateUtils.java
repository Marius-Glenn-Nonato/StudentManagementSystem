package com.marius.sms.util;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateUtils {
    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static String formatTimeStamp (Timestamp ts) {
        if(ts == null){
            return null;
        }
        return new SimpleDateFormat(DEFAULT_DATE_FORMAT).format(ts);
    }

    public static Date parseData(String dateStr){
        try{
            java.util.Date utilDate = new SimpleDateFormat(DEFAULT_DATE_FORMAT).parse(dateStr);
            return new Date(utilDate.getTime());
        } catch (ParseException e) {
            throw new RuntimeException("Invalid date format: " + dateStr);
        }
    }
}
