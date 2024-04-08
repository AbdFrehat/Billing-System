package com.orderizer.core.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class BeansUtil {

    public static <T> T extract(Map<String, T> map, Class<? extends T> clazz) {
        return map.get(StringUtils.uncapitalize(clazz.getSimpleName()));
    }
}
