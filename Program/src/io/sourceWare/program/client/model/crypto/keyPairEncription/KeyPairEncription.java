package io.sourceWare.program.client.model.crypto.keyPairEncription;

import io.sourceWare.program.client.model.crypto.EncriptionClass;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

public abstract class KeyPairEncription implements EncriptionClass {
    protected static KeyPair keyPair = null;
    protected abstract void generateKeyPair();
    protected abstract String encrypt(String plainText);
    protected abstract String decrypt(String encryptedText);
    protected abstract PublicKey getPublicKey();
    protected abstract PrivateKey getPrivateKey();
}
