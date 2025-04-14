package io.sourceWare.program.client.model.crypto.keyHandler;

import io.sourceWare.program.client.Data;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.PublicKey;
import java.security.interfaces.RSAKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.util.Arrays;
import java.util.Base64;

public class KeyHandler {

    public static SecretKey StringToSecretKey(String keyString){
        //byte[] keyBytes = keyString.getBytes();
        byte[] keyBytes = Arrays.copyOf(keyString.getBytes(StandardCharsets.UTF_8), Data.SIZE);
        return new SecretKeySpec(keyBytes, "AES");
    }

    public static SecretKey BytesToSecretKey(byte[] keyBytes){
        return new SecretKeySpec(keyBytes, "AES");
    }

    public static String SecretKeytoString(SecretKey secretKey){
        byte[] encoded = secretKey.getEncoded();
        return Base64.getEncoder().encodeToString(encoded);
    }

    public static RSAKey StringToPublicRsaKey(String keyString){
        // todo: fix, convert string to RSAPublicKey
        byte[] keyBytes = Arrays.copyOf(keyString.getBytes(StandardCharsets.UTF_8));
        return new RSAPublicKeySpec(keyBytes, "RSA");
    }

    // todo: replace all string keys with SecretKey keys.

    // todo: see if there is any code redundancy regarding enryption and decryption in some
    //  places, see the key exchange and keyPairEncriptionHandler they might have some issues.

}
