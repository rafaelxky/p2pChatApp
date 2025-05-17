package io.sourceWare.program.client.model.crypto.permanentEncryptionHandlers;

import io.sourceWare.program.client.model.crypto.EncriptionAlgorytm;

public interface PermanentEncryption extends EncriptionAlgorytm {
    public String encrypt (String input);
}
