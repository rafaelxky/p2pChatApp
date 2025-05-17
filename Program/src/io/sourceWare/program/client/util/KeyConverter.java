package io.sourceWare.program.client.util;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class KeyConverter {

    // Helper method to clean PEM strings by removing headers, footers, and whitespace
    private static String cleanPemString(String pem) {
        return pem
                .replaceAll("-----BEGIN (.*)-----", "")
                .replaceAll("-----END (.*)-----", "")
                .replaceAll("\\s+", "");
    }

    // Convert PEM or Base64 String to PrivateKey (RSA, PKCS#8)
    public static PrivateKey stringToPrivateKey(String pemPrivateKey) {
        try {
            String base64PrivateKey = cleanPemString(pemPrivateKey);
            byte[] keyBytes = Base64.getDecoder().decode(base64PrivateKey);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePrivate(keySpec);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Convert PEM or Base64 String to PublicKey (RSA, X.509)
    public static PublicKey stringToPublicKey(String pemPublicKey) {
        try {
            String base64PublicKey = cleanPemString(pemPublicKey);
            byte[] keyBytes = Base64.getDecoder().decode(base64PublicKey);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePublic(keySpec);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Convert PrivateKey to Base64 String
    public static String privateKeyToString(PrivateKey privateKey) {
        return Base64.getEncoder().encodeToString(privateKey.getEncoded());
    }

    // Convert PublicKey to Base64 String
    public static String publicKeyToString(PublicKey publicKey) {
        return Base64.getEncoder().encodeToString(publicKey.getEncoded());
    }
}
