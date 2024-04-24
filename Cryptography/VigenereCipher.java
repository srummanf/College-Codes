import java.util.Scanner;

public class VigenereCipher {

    // Function to perform Vigenere Cipher encryption
    public static String encrypt(String plaintext, String key) {
        StringBuilder ciphertext = new StringBuilder();
        int keyIndex = 0;
        for (int i = 0; i < plaintext.length(); i++) {
            char plainChar = plaintext.charAt(i);
            char keyChar = key.charAt(keyIndex % key.length());
            char encryptedChar = (char) (((plainChar + keyChar) % 26) + 'A');
            ciphertext.append(encryptedChar);
            keyIndex++;
        }
        return ciphertext.toString();
    }

    // Function to perform Vigenere Cipher decryption
    public static String decrypt(String ciphertext, String key) {
        StringBuilder plaintext = new StringBuilder();
        int keyIndex = 0;
        for (int i = 0; i < ciphertext.length(); i++) {
            char cipherChar = ciphertext.charAt(i);
            char keyChar = key.charAt(keyIndex % key.length());
            char decryptedChar = (char) (((cipherChar - keyChar + 26) % 26) + 'A');
            plaintext.append(decryptedChar);
            keyIndex++;
        }
        return plaintext.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input plaintext
        System.out.print("Enter the plaintext: ");
        String plaintext = scanner.nextLine().toUpperCase();

        // Input key
        System.out.print("Enter the key: ");
        String key = scanner.nextLine().toUpperCase();

        // Encrypt the plaintext
        String encryptedText = encrypt(plaintext, key);
        System.out.println("Encrypted Text: " + encryptedText);

        // Decrypt the ciphertext
        String decryptedText = decrypt(encryptedText, key);
        System.out.println("Decrypted Text: " + decryptedText);

        scanner.close();
    }
}
