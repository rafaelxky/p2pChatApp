package io.sourceWare.program.client.model.crypto.hashingHandlers;

import io.sourceWare.program.client.model.crypto.EncriptionAlgorytm;

public interface HashAlgorithm extends EncriptionAlgorytm {
    public String encrypt (String input);
}
