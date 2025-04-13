package io.sourceWare.program.client.model.crypto.signMessageHandlers;

import java.security.PrivateKey;
import java.security.PublicKey;

public interface MessageSigner {
    String signMessage(String message, PrivateKey privateKey);
    boolean verifySignature(String message, String signedMessage, PublicKey publicKey);
}
