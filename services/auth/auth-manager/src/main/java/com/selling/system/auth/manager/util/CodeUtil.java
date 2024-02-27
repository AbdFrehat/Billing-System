package com.selling.system.auth.manager.util;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class CodeUtil {

    public static String generateAuthCode(int length) {
        SecureRandom secureRandom = new SecureRandom();
        byte[] randomBytes = new byte[length / 2];
        secureRandom.nextBytes(randomBytes);
        return new BigInteger(1, randomBytes).toString(16);
    }

    public static String generateChallengeCode(String codeVerifier) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = messageDigest.digest(codeVerifier.getBytes(StandardCharsets.UTF_8));
            return Base64.getUrlEncoder().withoutPadding().encodeToString(hashBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}
