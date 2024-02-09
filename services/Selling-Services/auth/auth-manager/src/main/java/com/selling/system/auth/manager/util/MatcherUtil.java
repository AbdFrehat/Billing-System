package com.selling.system.auth.manager.util;

public class MatcherUtil {

    public static boolean isClientMatch(String clientId, String clientSecret, String storedClientId,
                                        String storedClientSecret) {
        return clientId.equals(storedClientId) && clientSecret.equals(storedClientSecret);
    }
}
