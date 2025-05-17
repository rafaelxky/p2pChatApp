package io.sourceWare.program.client.model.crypto.keyPairEncryptionHandlers.RSA;

import io.sourceWare.program.client.model.crypto.keyPairEncryptionHandlers.KeyPairEncryption;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;

public class RsaEncryption extends KeyPairEncryption {

    // todo: make so that you can generate a key from a String
    @Override
    public void generateKeyPair() {
        try {
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(2048);
            super.keyPair = generator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("RSA algorithm not found", e);
        }
    }

    @Override
    public String encrypt(String plainText) {
        try {
            Cipher encryptCipher = Cipher.getInstance("RSA");
            encryptCipher.init(Cipher.ENCRYPT_MODE, super.keyPair.getPublic());
            byte[] encryptedBytes = encryptCipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String decrypt(String encryptedText) {
        try {
            Cipher decryptCipher = Cipher.getInstance("RSA");
            decryptCipher.init(Cipher.DECRYPT_MODE, super.keyPair.getPrivate());
            byte[] decryptedBytes = decryptCipher.doFinal(Base64.getDecoder().decode(encryptedText));
            return new String(decryptedBytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setPrivateKey(RSAPrivateKey privateKey) {
        PublicKey publicKey = (super.keyPair != null) ? super.keyPair.getPublic() : null;
        super.keyPair = new KeyPair(publicKey, privateKey);
    }

    public void setPublicKey(RSAPublicKey publicKey) {
        PrivateKey privateKey = (super.keyPair != null) ? super.keyPair.getPrivate() : null;
        super.keyPair = new KeyPair(publicKey, privateKey);
    }
}
