package io.sourceWare.program.client.model.crypto.PermanentEncriptionHandlers;

import java.security.NoSuchAlgorithmException;

public interface PermanentEncryption {
    public String encrypt (String input) throws NoSuchAlgorithmException;
}
