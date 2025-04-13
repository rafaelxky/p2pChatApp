package io.sourceWare.program.client.model.crypto.TwoWayEncryptionHandlers;

import io.sourceWare.program.client.model.crypto.EncriptionClass;

public interface TwoWayEncryption extends EncriptionClass {

    public String encrypt(String message, String key);
    public String decrypt(String message, String key);

}
