package com.selling.system.auth.manager.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class RedisUtil {

    private RedisUtil() {
    }

    public static String buildKeyName(String appName, String... parts) {
        return appName + "." + Base64.getEncoder().encodeToString(String.join(".", parts).getBytes(StandardCharsets.UTF_8));
    }
}
