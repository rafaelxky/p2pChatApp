package io.sourceWare.program.client.model.crypto.keyPairEncryptionHandlers;

import io.sourceWare.program.client.model.crypto.EncriptionAlgorytm;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public abstract class KeyPairEncryption implements EncriptionAlgorytm {
    public KeyPair keyPair = null;

    public abstract void generateKeyPair();

    public abstract String encrypt(String plainText);

    public abstract String decrypt(String encryptedText);


    public PublicKey getPublicKey() {
        return keyPair.getPublic();
    }


    public PrivateKey getPrivateKey() {
        return keyPair.getPrivate();
    }

    public KeyPair getKeyPair(){
        return keyPair;
    }


    public void setPrivateKey(RSAPrivateKey privateKey) {
        keyPair = new KeyPair( keyPair.getPublic(), privateKey);

    }


    public void setPublicKey(RSAPublicKey publicKey) {
         keyPair = new KeyPair(publicKey,  keyPair.getPrivate());
    }
}
