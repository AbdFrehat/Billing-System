package com.selling.system.auth.shared.module.util;

public class QueryUtil {

    private static final String BIND_SYMBOL = ":";

    public static String buildBindColumnName(String columnName) {
        return BIND_SYMBOL + columnName;
    }
}
