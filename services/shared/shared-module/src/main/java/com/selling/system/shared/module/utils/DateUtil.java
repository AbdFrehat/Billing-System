package com.selling.system.shared.module.utils;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtil {

    public static Date convertStringToDate(String value, DateTimeFormatter formatter) {
        return Date.from(OffsetDateTime.parse(value, formatter).toInstant());
    }
}
