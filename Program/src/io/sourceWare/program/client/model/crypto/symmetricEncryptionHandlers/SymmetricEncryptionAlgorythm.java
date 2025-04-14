package io.sourceWare.program.client.model.crypto.symmetricEncryptionHandlers;

import io.sourceWare.program.client.model.crypto.EncriptionAlgorytm;
import io.sourceWare.program.client.model.crypto.keyHandler.KeyHandler;

import javax.crypto.SecretKey;

public abstract class SymmetricEncryptionAlgorythm implements EncriptionAlgorytm {

    String key;
    public abstract String encrypt(String message, SecretKey key);
    public String encrypt(String message, String key){
        return encrypt(message, KeyHandler.stringToSecretKey(key));
    }
    public abstract String decrypt(String message, SecretKey key);
    public String decrypt (String message, String key){
     return decrypt(message, KeyHandler.stringToSecretKey(key));
    }

    public String getKey(){
        return key;
    }

    public void setKey(String key){
        this.key = key;
    }


}
