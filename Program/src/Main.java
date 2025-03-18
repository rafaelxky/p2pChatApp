import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

public class Main {

    // Generate AES key from seed
    // it takes the seed, transforms it into an array of encripted bytes and returns it
    private static SecretKey getKeyFromSeed(int seed) throws Exception {
        // MessageDigest - hash class. Used to call other related functions
        // .getInstance(algoritm); - returns a message digest (bytes) object that implements the specific algoritm
        // .digest(Byte array) - transforms byte array into a digest (finalized hashed bytes)
        // String.valueOf(seed).getBytes(StandardCharsets.UTF_8) - return a byte array of the seed using UTF-8
        byte[] keyBytes = MessageDigest.getInstance("SHA-256")
                .digest(String.valueOf(seed).getBytes(StandardCharsets.UTF_8));

        // SecreKeySpec(Byte[], algorithm); - constructs a secret key from the byte array and algorithm
        return new SecretKeySpec(Arrays.copyOf(keyBytes, 16), "AES"); // Use first 16 bytes for AES-128
    }

    // Encrypts the message
    public static String encrypt(String message, int seed) throws Exception {
        SecretKey key = getKeyFromSeed(seed);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = cipher.doFinal(message.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptedBytes); // Convert to Base64 for readability
    }

    // Decrypts the message
    public static String decrypt(String encryptedMessage, int seed) throws Exception {
        SecretKey key = getKeyFromSeed(seed);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedMessage));
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

    public static void main(String[] args) {
        try {
            String originalText = "HelloWorld";
            int seed = 42;

            System.out.println(originalText);

            String encryptedText = encrypt(originalText, seed);
            System.out.println("Encrypted: " + encryptedText);

            String decryptedText = decrypt(encryptedText, seed);
            System.out.println("Decrypted: " + decryptedText);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
