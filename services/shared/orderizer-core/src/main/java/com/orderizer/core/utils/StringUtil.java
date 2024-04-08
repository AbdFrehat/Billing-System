package com.orderizer.core.utils;

public class StringUtil {

    public static boolean isNotEmpty(String value) {
        return value != null && !value.isEmpty();
    }

    public static boolean isEmpty(String value) {
        return value == null || value.isEmpty();
    }
}
