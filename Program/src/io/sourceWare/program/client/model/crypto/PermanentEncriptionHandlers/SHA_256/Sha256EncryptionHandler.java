package io.sourceWare.program.client.model.crypto.PermanentEncriptionHandlers.SHA_256;

import io.sourceWare.program.client.model.crypto.PermanentEncriptionHandlers.PermanentEncryption;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sha256EncryptionHandler implements PermanentEncryption {
    @Override
    public String encrypt(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        byte[] hashByte = md.digest(input.getBytes(StandardCharsets.UTF_8));

        StringBuilder hexString = new StringBuilder();
        for (byte b : hashByte) {
            hexString.append(String.format("%02x", b));
        }

        return hexString.toString();
    }
}
