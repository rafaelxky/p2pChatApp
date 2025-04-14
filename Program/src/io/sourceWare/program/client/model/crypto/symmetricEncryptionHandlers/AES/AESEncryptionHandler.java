package io.sourceWare.program.client.model.crypto.symmetricEncryptionHandlers.AES;

import io.sourceWare.program.client.model.crypto.symmetricEncryptionHandlers.SymmetricEncryptionAlgorythm;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

public class AESEncryptionHandler extends SymmetricEncryptionAlgorythm {
    @Override
    public String encrypt(String message, SecretKey key) {
        if (message.isEmpty() || key.isDestroyed()){
            System.out.println("Error, trying to encrypt an empty " + (message.isEmpty() ? "message" : "" ) + " " + (key.isDestroyed() ? "key" : ""));
            return null;
        }
        try{

        // create secret key from key
        //byte[] keyBytes = Arrays.copyOf(key.getBytes(StandardCharsets.UTF_8), SIZE);
        //SecretKeySpec secretKey = new SecretKeySpec(keyBytes, "AES");

        // create cipher object
        Cipher cipher = Cipher.getInstance("AES");

        // start cipher for encryption
        cipher.init(Cipher.ENCRYPT_MODE, key);

        // encrypt message
        byte[] encryptedMessage = cipher.doFinal(message.getBytes());

        // return encrypted message as Base64 encoded string
        return Base64.getEncoder().encodeToString(encryptedMessage);

        } catch (Exception  e) {
            throw new RuntimeException(e);
        }
    }

   @Override
    public String decrypt(String encryptedMessage, SecretKey key) {
       if (encryptedMessage.isEmpty() || key.isDestroyed()){
           System.out.println("Error, trying to encript an empty " + (encryptedMessage.isEmpty() ? "message" : "" ) + " " + (key.isDestroyed() ? "key" : ""));
           return null;
       }
        try {

        // Create a secret key from key
        //byte[] keyBytes = Arrays.copyOf(key.getBytes(StandardCharsets.UTF_8), SIZE);
        //SecretKeySpec secretKey = new SecretKeySpec(keyBytes, "AES");
        // Create cipher object
        Cipher cipher = Cipher.getInstance("AES");

        // init cypher
        cipher.init(Cipher.DECRYPT_MODE, key);

        // Decode Base64 string
        byte[] decodedMsg = Base64.getDecoder().decode(encryptedMessage);

        // Decrypt string
        byte[] decryptedMsg = cipher.doFinal(decodedMsg);

        return new String(decryptedMsg);

        } catch (Exception  e) {
            throw new RuntimeException(e);
        }
    }

}
