package com.seis739.gourmetcompass.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public abstract class Helper {
    
    public static String getMD5Hash(String str) throws NoSuchAlgorithmException {
        byte[] data = str.getBytes();
        byte[] hash = MessageDigest.getInstance("MD5").digest(data);

        return new BigInteger(1, hash).toString(16);
    }

    public static String getTokenFromHeaders(Map<String, String> headers) {
        String token = headers.get("authorization");

        if (token == null || token == "") {
            return null;
        }

        return token.split(" ")[1];
    }

    public static String formatForSearching(String query) {
        return "%" + query + "%";
    }
}
