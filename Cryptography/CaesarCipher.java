import java.util.Scanner;

public class CaesarCipher {
    // Function to perform Caesar Cipher encryption
    public static String encryptCaesarCipher(String plaintext, int shift) {
        StringBuilder ciphertext = new StringBuilder();

        for (char ch : plaintext.toCharArray()) {
            if (Character.isLetter(ch)) {
                char base = Character.isUpperCase(ch) ? 'A' : 'a';
                ciphertext.append((char) ((ch - base + shift) % 26 + base));
            } else {
                ciphertext.append(ch);
            }
        }

        return ciphertext.toString();
    }

    // Function to perform Caesar Cipher decryption
    public static String decryptCaesarCipher(String ciphertext, int shift) {
        StringBuilder decryptedText = new StringBuilder();

        for (char ch : ciphertext.toCharArray()) {
            if (Character.isLetter(ch)) {
                char base = Character.isUpperCase(ch) ? 'A' : 'a';
                decryptedText.append((char) (((ch - base + 26 - shift) % 26 + 26) % 26 + base));
            } else {
                decryptedText.append(ch);
            }
        }

        return decryptedText.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String plaintext, ciphertext, decryptedText;
        int shift;

        // Get input from the user
        System.out.print("Enter the plaintext: ");
        plaintext = scanner.nextLine();

        System.out.print("Enter the shift value: ");
        shift = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        // Encrypt and display the ciphertext
        ciphertext = encryptCaesarCipher(plaintext, shift);
        System.out.println("Encrypted Text: " + ciphertext);

        // Decrypt and display the decrypted plaintext
        decryptedText = decryptCaesarCipher(ciphertext, shift);
        System.out.println("Decrypted Text: " + decryptedText);

        scanner.close();
    }
}
