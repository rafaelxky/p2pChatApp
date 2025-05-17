package io.sourceWare.program.client.model.crypto.signMessageHandlers;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

public interface MessageSigner {
    Signature makeMessageSignature(String message, PrivateKey privateKey);

    String signMessage(String message, PrivateKey privateKey);
    boolean verifySignature(String message, String signedMessage, PublicKey publicKey);
}
