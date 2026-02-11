package com.marius.sms.util;

//SQL imports
import java.sql.Date;
import java.sql.Timestamp;

//JAVA IMPORTS
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DateUtils {
    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static String formatTimeStamp (Timestamp ts) {
        if(ts == null){
            return null;
        }
        return new SimpleDateFormat(DEFAULT_DATE_FORMAT).format(ts);
    }
    //Parsing a string to a SQL date
    public static Date parseData(String dateStr){
        try{
            java.util.Date utilDate = new SimpleDateFormat(DEFAULT_DATE_FORMAT).parse(dateStr);
            return new Date(utilDate.getTime());
        } catch (ParseException e) {
            throw new RuntimeException("Invalid date format: " + dateStr);
        }
    }
    // Convert java.sql.Date → java.time.LocalDate
    public static LocalDate toLocalDate(Date sqlDate) {
        return sqlDate != null ? sqlDate.toLocalDate() : null;
    }

    // Convert java.time.LocalDate → java.sql.Date
    public static Date toSqlDate(LocalDate localDate) {
        return localDate != null ? Date.valueOf(localDate) : null;
    }

    // Convert java.sql.Timestamp → java.time.LocalDateTime
    public static LocalDateTime toLocalDateTime(Timestamp timestamp) {
        return timestamp != null ? timestamp.toLocalDateTime() : null;
    }

    // Convert java.time.LocalDateTime → java.sql.Timestamp
    public static Timestamp toTimestamp(LocalDateTime localDateTime) {
        return localDateTime != null ? Timestamp.valueOf(localDateTime) : null;
    }

    //Get the current timestamp
    public static Timestamp getCurrentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }
}
