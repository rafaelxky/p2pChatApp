package io.sourceWare.Tests;

import io.sourceWare.program.client.model.crypto.EncryptionHandler;

public class CryptoTests {
    public static void main(String[] args) {
        System.out.println(encryptionTest().equals("zoVIE66EIk8op3GyzV2/Cw==") ? " - encryption success" : " - encryption failed");
        System.out.println("Encrypted message: " + encryptionTest());

        System.out.println(decryptionTest().equals("Hello World") ? " - decryption test success" : " - decryption test failed");
        System.out.println("Decrypted message: " + decryptionTest());
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

    public static String permanentEncryptionTest(){
        EncryptionHandler.permanentEncryption();
        System.out.println("Unimplemented feature - permanentEncryption");
        return null;
    }

}
