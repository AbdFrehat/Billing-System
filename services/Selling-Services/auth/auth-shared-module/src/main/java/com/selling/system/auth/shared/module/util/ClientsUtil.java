package com.selling.system.auth.shared.module.util;

import java.security.SecureRandom;
import java.util.Base64;

public class ClientsUtil {

    public static String generateClientId() {
        byte[] clientIdBytes = new byte[8];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(clientIdBytes);
        return Base64.getEncoder().encodeToString(clientIdBytes);
    }

    public static String generateClientSecret() {
        byte[] clientSecretBytes = new byte[16];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(clientSecretBytes);
        return Base64.getEncoder().encodeToString(clientSecretBytes);
    }
}
