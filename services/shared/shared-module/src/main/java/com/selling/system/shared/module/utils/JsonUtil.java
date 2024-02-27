package com.selling.system.shared.module.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

    private static final ThreadLocal<ObjectMapper> objectMapperThreadLocal = ThreadLocal.withInitial(ObjectMapper::new);

    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return objectMapperThreadLocal.get().readValue(json, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
