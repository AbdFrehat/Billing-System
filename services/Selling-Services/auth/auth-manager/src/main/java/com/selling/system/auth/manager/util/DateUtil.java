package com.selling.system.auth.manager.util;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateUtil {

    private DateUtil() {
    }

    public static Date plus(LocalDateTime localDateTime, Long seconds) {
        return new Date(localDateTime.plus(Duration.ofSeconds(seconds)).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
    }
}
