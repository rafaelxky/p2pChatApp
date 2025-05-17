package io.sourceWare.Tests;

import io.sourceWare.program.client.model.crypto.keyPairEncryptionHandlers.KeyPairEncryption;
import io.sourceWare.program.client.model.crypto.keyPairEncryptionHandlers.RSA.RsaEncryption;
import io.sourceWare.program.client.model.crypto.signMessageHandlers.MessageSigner;
import io.sourceWare.program.client.model.crypto.signMessageHandlers.RsaSign.RsaSign;
import io.sourceWare.program.client.util.KeyConverter;

import java.security.interfaces.RSAPublicKey;

public class RsaSignTests {
    public static void main(String[] args) {
        KeyPairEncryption rsa = new RsaEncryption();
        KeyPairEncryption rsa2 = new RsaEncryption();
        MessageSigner rsaSign = new RsaSign();
        rsa.generateKeyPair();
        rsa2.generateKeyPair();

        String signedMessage = rsaSign.signMessage("Hello World", rsa.getPrivateKey());
        boolean isVerificationCorrect = rsaSign.verifySignature("Hello World" ,signedMessage, rsa2.getPublicKey());

        System.out.println("Is verification correct: expected false  - " + isVerificationCorrect);

        String signedMessage2 = rsaSign.signMessage("Hello World", rsa.getPrivateKey());
        boolean isVerificationCorrect2 = rsaSign.verifySignature("Hello World" ,signedMessage2, rsa.getPublicKey());
        System.out.println("Is verification correct: expected true - " + isVerificationCorrect2);
    }

    public void testSucessSignAndverify(){

    }
    public void testFailSignAndverify(){

    }
}
