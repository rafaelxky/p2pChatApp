package io.sourceWare.program.client.model.crypto.signMessage;

import java.security.PrivateKey;
import java.security.PublicKey;

public interface signMessage {
    String signMessage(String message, PrivateKey privateKey);
    boolean verifySignature(String message, String signedMessage, PublicKey publicKey);
}
