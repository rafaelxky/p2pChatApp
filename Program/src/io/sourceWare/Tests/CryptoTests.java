package io.sourceWare.Tests;

import io.sourceWare.program.client.model.crypto.keyHandler.KeyHandler;
import io.sourceWare.program.client.model.crypto.keyPairEncryptionHandlers.KeyPairEncryption;
import io.sourceWare.program.client.model.crypto.keyPairEncryptionHandlers.RSA.RsaEncryption;
import io.sourceWare.program.client.model.crypto.permanentEncryptionHandlers.PermanentEncryption;
import io.sourceWare.program.client.model.crypto.permanentEncryptionHandlers.SHA_256.Sha256EncryptionHandler;
import io.sourceWare.program.client.model.crypto.saltHandler.SaltHandler;
import io.sourceWare.program.client.model.crypto.symmetricEncryptionHandlers.AES.AESEncryptionHandler;
import io.sourceWare.program.client.model.crypto.symmetricEncryptionHandlers.SymmetricEncryptionAlgorythm;

import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

public class CryptoTests {
    public static void main(String[] args) {
        SymmetricEncryptionAlgorythm eh = new AESEncryptionHandler();
        PermanentEncryption peh = new Sha256EncryptionHandler();
        SaltHandler sh = new SaltHandler();
        KeyPairEncryption kpe = new RsaEncryption();

        // 2 way encryption
        System.out.println(encryptionTest(eh).equals("zoVIE66EIk8op3GyzV2/Cw==") ? " - encryption success  ✅" : " - encryption failed ❌");
        System.out.println("Encrypted message \uD83D\uDD12: " + encryptionTest(eh));
        System.out.println();

        // 2 way decryption
        System.out.println(decryptionTest(eh).equals("Hello World") ? " - decryption test success ✅" : " - decryption test failed ❌");
        System.out.println("Decrypted message \uD83D\uDD13: " + decryptionTest(eh));
        System.out.println();

        // encryption and decryption test
        System.out.println("Encrypt and decrypt test \uD83D\uDD12➡\uFE0F\uD83D\uDD13: " + (encryptionAndDecryptionTest(eh).equals("Hello World")? "success ✅" : "failed ❌"));
        System.out.println();

        // permanent encryption
        System.out.println("- permanentEncryptionTestNoSalt \uD83D\uDD12♾\uFE0F: " + (permanentEncryptionTestNoSalt(peh).equals("a591a6d40bf420404a011733cfb7b190d62c65bf0bcda32b57b277d9ad9f146e") ? "success ✅" : "failed ❌"));
        System.out.println(permanentEncryptionTestNoSalt(peh));
        System.out.println();

        // permanent encryption with salt
        System.out.println(" - permanentEncryptionTestWithStaticSalt \uD83D\uDD12♾\uFE0F\uD83E\uDDC2\uD83D\uDCCC: " + (permanentEncryptionTestStaticSalt(peh, sh).equals("4fb8894f57c227081e6da59ee7771a55082a88137b7ddf5b423448ae4beb1ca0") ? "success ✅" : "failed ❌"));
        System.out.println(permanentEncryptionTestStaticSalt(peh, sh));
        System.out.println();

        // add salt test
        System.out.println(" - addSaltTest \uD83D\uDD11\uD83E\uDDC2: " + (addSaltTest(sh).equals("[72, 101, 108, 108, 111, 32, 87, 111, 114, 108, 100, 83, 97, 108, 116]") ? "success ✅" : "failed ❌"));
        System.out.println(addSaltTest(sh));
        System.out.println();

        // generate salt test
        System.out.println(" - Generated salt \uD83E\uDDC2: " + generateSalt(sh) + "  ");
        System.out.println();

        // starting edge cases
        System.out.println(" - \uD83D\uDD27 Starting edge case tests \uD83D\uDD27 - ");
        System.out.println();

        // empty string encryption case test
        System.out.println(" - empty string encryption test \uD83D\uDEAB\uFE0F\uD83D\uDD11: " + (emptyStringEncryptionTest(eh).equals("AUPbY+5msM3/n2mRdoAVHg==") ? "success ✅" : "failed ❌"));
        System.out.println(emptyStringEncryptionTest(eh));
        System.out.println();

        // null string encryption test case
        System.out.println(" - null string encryption test \uD83D\uDEAB\uFE0F\uD83D\uDD11: " + (nullStringEncryptionTest(eh) == null ? "success ✅" : "failed ❌"));
        System.out.println(nullStringEncryptionTest(eh));
        System.out.println();



        // keyExchange tests
        // keyHandler tests
        // KeyPairEncryption tests
        System.out.println(" - key Pair Encryption test " + (keyPairEncriptionTest(kpe)));

        // messageSign tests


    }

    private static String keyPairEncriptionTest(KeyPairEncryption kpe, String key, String message) {
        // todo: fix, convert string to RSAPublicKey
        kpe.setPublicKey(KeyHandler.StringToSecretKey(key));
        kpe.encrypt(message);
    }


    public static String encryptionTest(SymmetricEncryptionAlgorythm encryptionHandler){
        try {
            return encryptionHandler.encrypt("Hello World","Super secret key");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String decryptionTest(SymmetricEncryptionAlgorythm encryptionHandler){
        try {
            return encryptionHandler.decrypt("zoVIE66EIk8op3GyzV2/Cw==","Super secret key");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String permanentEncryptionTestNoSalt(PermanentEncryption permanentEncryption){
        return permanentEncryption.encrypt("Hello World");
    }

    public static String permanentEncryptionTestStaticSalt(PermanentEncryption permanentEncryption, SaltHandler salt){
        return permanentEncryption.encrypt(salt.addSalt("Hello World", "salt"));
    }

    public static String addSaltTest(SaltHandler salt){
        return salt.addSalt("Hello World", "Salt");
    }

    public static String generateSalt(SaltHandler salt){
        return salt.generateSalt();
    }

    public static String nullStringEncryptionTest(SymmetricEncryptionAlgorythm twe){
        try {
            return twe.encrypt((String) null, (SecretKey) null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String emptyStringEncryptionTest(SymmetricEncryptionAlgorythm twe){
        try {
            return twe.encrypt("", "");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String encryptionAndDecryptionTest(SymmetricEncryptionAlgorythm twe){
        try {
            return twe.decrypt(twe.encrypt("Hello World", "key"), "key");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
