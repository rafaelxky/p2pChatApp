package io.sourceWare.program.client.model.crypto.TwoWayEncryptionHandlers;

public interface TwoWayEncryption {

    public String encrypt(String message, String key) throws Exception;
    public String decrypt(String message, String key) throws Exception;

}
