package io.sourceWare.program.client.model.crypto.keyHandler;

import io.sourceWare.program.client.Data;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.util.Arrays;
import java.util.Base64;

public class KeyHandler {

    /*
    * Converts a string to an AES secret key
     */
    public static SecretKey stringToSecretKey(String keyString){
        //byte[] keyBytes = keyString.getBytes();
        byte[] keyBytes = Arrays.copyOf(keyString.getBytes(StandardCharsets.UTF_8), Data.SIZE);
        return new SecretKeySpec(keyBytes, "AES");
    }

    /*
    * Converts a byte array to an AES secret key
     */
    public static SecretKey bytesToSecretKey(byte[] keyBytes){
        return new SecretKeySpec(keyBytes, "AES");
    }

    /*
    * Converts an AES secret key to a string
     */
    public static String secretKeytoString(SecretKey secretKey){
        byte[] encoded = secretKey.getEncoded();
        return Base64.getEncoder().encodeToString(encoded);
    }

    // creates a key from a string, a modulus and a publicExponent for a key exchange such as DH
    // probably not needed
    public static PublicKey stringToPublicKey(String keyString, BigInteger modulus, BigInteger publicExponent){
        try {
            byte[] keyBytes = Base64.getDecoder().decode(keyString);
            RSAPublicKeySpec keySpec = new RSAPublicKeySpec(modulus, publicExponent);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            return kf.generatePublic(keySpec);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static RSAPublicKey stringToRsaPublicKey(String keyString){
        // todo: implement
        return null;
    }

    // todo: replace all string keys with SecretKey keys.

    // todo: see if there is any code redundancy regarding encryption and decryption in some
    //  places, see the key exchange and keyPairEncryptionHandler they might have some issues.

}
