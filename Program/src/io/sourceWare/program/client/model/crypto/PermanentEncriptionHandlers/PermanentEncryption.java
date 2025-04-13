package io.sourceWare.program.client.model.crypto.PermanentEncriptionHandlers;

import io.sourceWare.program.client.model.crypto.EncriptionClass;

import java.security.NoSuchAlgorithmException;

public interface PermanentEncryption extends EncriptionClass {
    public String encrypt (String input);
}
