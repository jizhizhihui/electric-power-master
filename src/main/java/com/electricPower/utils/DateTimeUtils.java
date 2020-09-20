package com.electricPower.utils;

import lombok.extern.log4j.Log4j2;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

@Log4j2
public class DateTimeUtils {

    /**
     * Date To LocalDateTime
     * @param date Date
     * @return LocalDateTime
     */
    public static LocalDateTime dateToLocalDateTime(Date date){
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDateTime();
    }

    /**
     * LocalDateTime To Date
     * @param localDateTime LocalDateTime
     * @return Date
     */
    public static Date LocalDateTimeToDate(LocalDateTime localDateTime){
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        return Date.from(zdt.toInstant());
    }

    /**
     * 获取时间戳；format：yy MM dd HH mm ss
     * @return String
     */
    public static String getStringTime(){
        Date date = new Date();
        SimpleDateFormat  df = new SimpleDateFormat("yy MM dd HH mm ss");
        return df.format(date);
    }
}
