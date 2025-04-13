package io.sourceWare.program.client.model.crypto.keyHandler;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class keyGenerator {

    public static SecretKey StringToSecretKey(String keyString){
        byte[] keyBytes = keyString.getBytes();
        return new SecretKeySpec(keyBytes, 0, 16, "AES");
    }

    public static SecretKey BytesToSecretKey(byte[] keyBytes){
        return new SecretKeySpec(keyBytes, "AES");
    }

    public static String SecretKeytoString(SecretKey secretKey){
        byte[] encoded = secretKey.getEncoded();
        return Base64.getEncoder().encodeToString(encoded);
    }

    // todo: replace all string keys with SecretKey keys.

    // todo: see if there is any code redundancy regarding enryption and decryption in some
    //  places, see the key exchange and keyPairEncriptionHandler they might have some issues.

}
