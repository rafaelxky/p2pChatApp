package io.sourceWare.program.client.model.crypto.keyPairEncryptionHandlers.RSA;

import io.sourceWare.program.client.model.crypto.keyPairEncryptionHandlers.KeyPairEncryption;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;

public class RsaEncryption extends KeyPairEncryption {

    @Override
    public void generateKeyPair(){
        KeyPairGenerator generator = null;
        try {
            generator = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        generator.initialize(2048);
        super.keyPair = generator.generateKeyPair();
    }

    @Override
    public String encrypt (String plainText){
        try {
            Cipher encryptCipher = Cipher.getInstance("RSA");
            encryptCipher.init(Cipher.ENCRYPT_MODE, super.keyPair.getPublic());

            byte[] encryptedBytes = encryptCipher.doFinal(plainText.getBytes("UTF-8"));
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String decrypt(String encryptedText){
        try {
            Cipher decryptCipher = Cipher.getInstance("RSA");
            decryptCipher.init(Cipher.DECRYPT_MODE, super.keyPair.getPrivate());
            byte[] decryptedBytes = decryptCipher.doFinal(Base64.getDecoder().decode(encryptedText));
            return new String(decryptedBytes, "UTF-8");
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void setPrivateKey(RSAPrivateKey privateKey){
        super.keyPair = new KeyPair(super.keyPair.getPublic(), privateKey);
    }


    public void setPublicKey(RSAPublicKey publicKey){
        super.keyPair = new KeyPair(publicKey, super.keyPair.getPrivate());
    }

}
