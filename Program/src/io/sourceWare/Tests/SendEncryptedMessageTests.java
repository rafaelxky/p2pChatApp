package io.sourceWare.Tests;

import io.sourceWare.program.client.model.MessageProtocol.EncryptedMessage;
import io.sourceWare.program.client.model.crypto.keyPairEncryptionHandlers.RSA.RsaEncryption;
import io.sourceWare.program.client.model.crypto.signMessageHandlers.RsaSign.RsaSign;
import io.sourceWare.program.client.model.crypto.encryptionHandlers.AES.AESEncryptionHandler;
import io.sourceWare.program.client.model.crypto.encryptionHandlers.EncryptionHandler;

public class SendEncryptedMessageTests {
    public static void main(String[] args) {
        RsaEncryption rsa = new RsaEncryption();
        RsaSign rsaSign = new RsaSign();
        EncryptionHandler encryptionHandler = new AESEncryptionHandler();
        rsa.generateKeyPair();

        // default message
        EncryptedMessage message = new EncryptedMessage("Hello World");
        System.out.println("raw message = " + message.getRawMessage());
        System.out.println("message = " + message.getMessage());

        // encrypt message (exclude "message: " from encryption and signing)
        String encryptedMsg = encryptionHandler.encrypt(message.getMessage(), "secret key");
        System.out.println(" encrypted msg = " + encryptedMsg);

        // sign message (add "signedBy: ID" and "sign: sign")
        String signedMsg = rsaSign.signMessage(encryptedMsg, rsa.getPrivateKey());
        System.out.println("signed msg = " + signedMsg);

        message.setMessage("Hello");
        System.out.println(message.getRawMessage());

        message.sign("User1", signedMsg);
        System.out.println(message.getSigns().get(0));
        System.out.println(rsa.getPublicKey());
        System.out.println(rsaSign.verifySignature(encryptedMsg, message.getSigns().get(0), rsa.getPublicKey()));

        System.out.println(" final message --------------------------");
        message.setMessage(encryptedMsg);
        System.out.println(message.getRawMessage());

        System.out.println(encryptionHandler.decrypt(message.getMessage(), "secret key"));


    }
}
