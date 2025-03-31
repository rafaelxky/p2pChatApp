package io.sourceWare.program.client.model.crypto;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

public class EncryptionHandler {
    // handles everything related to encryption and description

    public static String encrypt(String message, String key) throws Exception {

        // create secret key from key
        byte[] keyBytes = Arrays.copyOf(key.getBytes(StandardCharsets.UTF_8),16);
        SecretKeySpec secretKey = new SecretKeySpec(keyBytes, "AES");

        // create cipher object
        Cipher cipher = Cipher.getInstance("AES");

        // start cipher for encryption
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        // encrypt message
        byte[] encryptedMessage = cipher.doFinal(message.getBytes());

        // return encrypted message as Base64 encoded string
        return Base64.getEncoder().encodeToString(encryptedMessage);
    }

    public static String decrypt (String encryptedMessage, String key) throws Exception{

        // Create a secret key from key
        byte[] keyBytes = Arrays.copyOf(key.getBytes(StandardCharsets.UTF_8),16);
        SecretKeySpec secretKey = new SecretKeySpec(keyBytes, "AES");
        // Create cipher object
        Cipher cipher = Cipher.getInstance("AES");

        // init cypher
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        // Decode Base64 string
        byte[] decodedMsg = Base64.getDecoder().decode(encryptedMessage);

        // Decrypt string
        byte[] decryptedMsg = cipher.doFinal(decodedMsg);

        return new String(decryptedMsg);
    }

    // add a permanent encription that cant be decripted

    public static String permanentEncryption(){
        System.out.println("Unimplemented feature - permanentEncryption");
       return null;
    }
    
}
