package io.sourceWare.program.client.model.crypto.keyExchangeHandlers.DH;

import io.sourceWare.program.client.model.crypto.symmetricEncryptionHandlers.SymmetricEncryptionAlgorythm;
import io.sourceWare.program.client.model.crypto.keyExchangeHandlers.KeyExchanger;

import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;
import javax.crypto.spec.DHParameterSpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class DhKeyExchanger implements KeyExchanger {
    private KeyPair ownKeyPair;
    private SecretKey sharedSecretKey;



    // Generate shared Diffie-Hellman parameters
    public DHParameterSpec generateDHParameters(){
    try {
        AlgorithmParameterGenerator paramGen = AlgorithmParameterGenerator.getInstance("DH");
        paramGen.init(2048);
        AlgorithmParameters params = paramGen.generateParameters();
        return params.getParameterSpec(DHParameterSpec.class);
    } catch (Exception e){
        e.printStackTrace();
    }
    return null;
    }

   // Generate a DH key pair based on provided parameters
    public KeyPair generateKeyPair(DHParameterSpec dhSpecs){
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DH");
            keyGen.initialize(dhSpecs);
            return keyGen.generateKeyPair();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    // Generate a random Initialization Vector
   public IvParameterSpec generateIV(){
        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);
        return new IvParameterSpec(iv);
   }

    // High-level method to encrypt a message using derived key
    // encryption algo should be symmetric like AES
    public String encryptMessage(String plainText, SecretKey sharedKey, IvParameterSpec iv, SymmetricEncryptionAlgorythm algorithm){
       // todo: implement the option to encrypt with iv
        return algorithm.encrypt(plainText,sharedKey,iv);
    }

    public String decryptMessage(String encriptedText, SecretKey sharedKey, IvParameterSpec iv, SymmetricEncryptionAlgorythm algorithm){
        // todo: same here but for decrypt
        return algorithm.decrypt(encriptedText,sharedKey, iv);
    }

    public void generateSecretKey(String peerBase64PublicKey){
        try {
        byte[]  peerBytes = Base64.getDecoder().decode(peerBase64PublicKey);
        KeyFactory keyFactory = KeyFactory.getInstance("DH");
        PublicKey peerPublicKey = keyFactory.generatePublic(new X509EncodedKeySpec(peerBytes));

            KeyAgreement keyAgreement = KeyAgreement.getInstance("DH");
            keyAgreement.init(ownKeyPair.getPrivate());
            keyAgreement.doPhase(peerPublicKey, true);

            byte[] rawSecret = keyAgreement.generateSecret();

            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            byte[] hashedSecret = sha256.digest(rawSecret);

            sharedSecretKey = new SecretKeySpec(hashedSecret,0,16,"AES");

        } catch (Exception e) {
            System.out.println("Failed to generate key");
            throw new RuntimeException(e);
        }
    }

    public SecretKey getSharedSecretKey(){
        return sharedSecretKey;
    }





}
