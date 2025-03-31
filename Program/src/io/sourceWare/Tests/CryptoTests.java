package io.sourceWare.Tests;

import io.sourceWare.program.client.model.crypto.PermanentEncriptionHandlers.PermanentEncryption;
import io.sourceWare.program.client.model.crypto.PermanentEncriptionHandlers.SHA_256.Sha256EncryptionHandler;
import io.sourceWare.program.client.model.crypto.SaltHandler.SaltHandler;
import io.sourceWare.program.client.model.crypto.TwoWayEncryptionHandlers.AES.AESEncryptionHandler;
import io.sourceWare.program.client.model.crypto.TwoWayEncryptionHandlers.TwoWayEncryption;

import java.security.NoSuchAlgorithmException;

public class CryptoTests {
    public static void main(String[] args) {
        TwoWayEncryption eh = new AESEncryptionHandler();
        PermanentEncryption peh = new Sha256EncryptionHandler();
        SaltHandler sh = new SaltHandler();

        System.out.println(encryptionTest(eh).equals("zoVIE66EIk8op3GyzV2/Cw==") ? " - encryption success" : " - encryption failed");
        System.out.println("Encrypted message: " + encryptionTest(eh));
        System.out.println();

        System.out.println(decryptionTest(eh).equals("Hello World") ? " - decryption test success" : " - decryption test failed");
        System.out.println("Decrypted message: " + decryptionTest(eh));
        System.out.println();


        System.out.println(permanentEncryptionTestNoSalt(peh).equals("a591a6d40bf420404a011733cfb7b190d62c65bf0bcda32b57b277d9ad9f146e") ? " - permanentEncryptionTestNoSalt, success" : " - permanentEncryptionTestNoSalt, failed");
        System.out.println(permanentEncryptionTestNoSalt(peh));
        System.out.println();


        System.out.println(permanentEncryptionTestStaticSalt(peh, sh).equals("4fb8894f57c227081e6da59ee7771a55082a88137b7ddf5b423448ae4beb1ca0") ? " - permanentEncryptionTestWithStaticSalt, success" : " - permanentEncryptionTestWithStaticSalt, failed");
        System.out.println(permanentEncryptionTestStaticSalt(peh, sh));
        System.out.println();


        System.out.println(addSaltTest(sh).equals("[72, 101, 108, 108, 111, 32, 87, 111, 114, 108, 100, 83, 97, 108, 116]") ? " - addSaltTest, success" : " - addSaltTest, failed");
        System.out.println(addSaltTest(sh));
        System.out.println();


        System.out.println("Generated salt: " + generateSalt(sh));
        System.out.println();



    }



    public static String encryptionTest(TwoWayEncryption encryptionHandler){
        try {
            return encryptionHandler.encrypt("Hello World","Super secret key");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String decryptionTest(TwoWayEncryption encryptionHandler){
        try {
            return encryptionHandler.decrypt("zoVIE66EIk8op3GyzV2/Cw==","Super secret key");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String permanentEncryptionTestNoSalt(PermanentEncryption permanentEncryption){
        try {
            return permanentEncryption.encrypt("Hello World");
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static String permanentEncryptionTestStaticSalt(PermanentEncryption permanentEncryption, SaltHandler salt){
        try {
            return permanentEncryption.encrypt(salt.addSalt("Hello World", "salt"));
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static String addSaltTest(SaltHandler salt){
        return salt.addSalt("Hello World", "Salt");
    }

    public static String generateSalt(SaltHandler salt){
        return salt.generateSalt();
    }

}
