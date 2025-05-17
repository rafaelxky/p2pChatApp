package io.sourceWare.program.client.model.crypto.saltHandler;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Arrays;

public class SaltHandler {
    private static final String STATIC_SALT = "I_Love_Peanuts87";

    public static String addSalt(String in, String salt) {
        byte[] saltArr = salt.getBytes();

        byte[] inputBytes = in.getBytes(StandardCharsets.UTF_8);
        byte[] saltedIn = new byte[inputBytes.length + saltArr.length];

        System.arraycopy(inputBytes, 0, saltedIn, 0, inputBytes.length);
        System.arraycopy(saltArr, 0, saltedIn, inputBytes.length, saltArr.length);


        return Arrays.toString(saltedIn);
    }

    public static String addSalt(String in) {
        return addSalt(in, STATIC_SALT);
    }

    public static String removeSalt(String in) {
        // todo: implement
        return null;
    }

    public static String generateSalt() {
        byte[] salt = new byte[16];
        new SecureRandom().nextBytes(salt);
        return Arrays.toString(salt);
    }

    public static String getStaticSalt(){
        return STATIC_SALT;
    }



}
