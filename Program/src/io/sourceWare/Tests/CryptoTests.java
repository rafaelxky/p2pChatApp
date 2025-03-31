package io.sourceWare.Tests;

import io.sourceWare.program.client.model.crypto.EncryptionHandler;

import java.security.NoSuchAlgorithmException;

public class CryptoTests {
    public static void main(String[] args) {
        System.out.println(encryptionTest().equals("zoVIE66EIk8op3GyzV2/Cw==") ? " - encryption success" : " - encryption failed");
        System.out.println("Encrypted message: " + encryptionTest());
        System.out.println();

        System.out.println(decryptionTest().equals("Hello World") ? " - decryption test success" : " - decryption test failed");
        System.out.println("Decrypted message: " + decryptionTest());
        System.out.println();


        System.out.println(permanentEncryptionTestNoSalt().equals("a591a6d40bf420404a011733cfb7b190d62c65bf0bcda32b57b277d9ad9f146e") ? " - permanentEncryptionTestNoSalt, success" : " - permanentEncryptionTestNoSalt, failed");
        System.out.println(permanentEncryptionTestNoSalt());
        System.out.println();


        System.out.println(permanentEncryptionTestStaticSalt().equals("4fb8894f57c227081e6da59ee7771a55082a88137b7ddf5b423448ae4beb1ca0") ? " - permanentEncryptionTestWithStaticSalt, success" : " - permanentEncryptionTestWithStaticSalt, failed");
        System.out.println(permanentEncryptionTestStaticSalt());
        System.out.println();


        System.out.println(addSaltTest().equals("[72, 101, 108, 108, 111, 32, 87, 111, 114, 108, 100, 83, 97, 108, 116]") ? " - addSaltTest, success" : " - addSaltTest, failed");
        System.out.println(addSaltTest());
        System.out.println();


        System.out.println("Generated salt: " + generateSalt());
        System.out.println();


    }



    public static String encryptionTest(){
        try {
            return EncryptionHandler.encrypt("Hello World","Super secret key");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String decryptionTest(){
        try {
            return EncryptionHandler.decrypt("zoVIE66EIk8op3GyzV2/Cw==","Super secret key");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String permanentEncryptionTestNoSalt(){
        try {
            return EncryptionHandler.permanentEncryption("Hello World");
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static String permanentEncryptionTestStaticSalt(){
        try {
            return EncryptionHandler.permanentEncryption(EncryptionHandler.addSalt("Hello World", "salt"));
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static String addSaltTest(){
        return EncryptionHandler.addSalt("Hello World", "Salt");
    }

    public static String generateSalt(){
        return EncryptionHandler.generateSalt();
    }

}
