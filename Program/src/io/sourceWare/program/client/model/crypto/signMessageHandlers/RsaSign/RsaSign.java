package io.sourceWare.program.client.model.crypto.signMessageHandlers.RsaSign;

import io.sourceWare.program.client.model.crypto.signMessageHandlers.MessageSigner;

import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.Base64;

public class RsaSign implements MessageSigner {

    // todo: fix later, it might not be needed
    @Override
    public Signature makeMessageSignature(String message, PrivateKey privateKey) {
        try {
            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initSign(privateKey);
            signature.update(message.getBytes(StandardCharsets.UTF_8));
            byte[] signedData = signature.sign();
            //return Base64.getEncoder().encodeToString(signedData);
            return signature;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String signMessage(String message, PrivateKey privateKey) {
        try {
            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initSign(privateKey);
            signature.update(message.getBytes(StandardCharsets.UTF_8));
            byte[] signedData = signature.sign();
            return Base64.getEncoder().encodeToString(signedData);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean verifySignature(String message, String signedMessage, PublicKey publicKey) {
        try {
            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initVerify(publicKey);
            signature.update(message.getBytes(StandardCharsets.UTF_8));
            byte[] signedData = Base64.getDecoder().decode(signedMessage);
            return signature.verify(signedData);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
