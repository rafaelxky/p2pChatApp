package io.sourceWare.program.client.model.crypto.SaltHandler;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Arrays;

public class SaltHandler {

    public static String addSalt(String in, String salt) {
        byte[] saltArr = salt.getBytes();

        byte[] inputBytes = in.getBytes(StandardCharsets.UTF_8);
        byte[] saltedIn = new byte[inputBytes.length + saltArr.length];

        System.arraycopy(inputBytes, 0, saltedIn, 0, inputBytes.length);
        System.arraycopy(saltArr, 0, saltedIn, inputBytes.length, saltArr.length);


        return Arrays.toString(saltedIn);
    }

    public static String generateSalt() {
        byte[] salt = new byte[16];
        new SecureRandom().nextBytes(salt);
        return Arrays.toString(salt);
    }

}
