import java.io.*;
import java.util.*;

public class CeaserCipher {

  static String encrypt(String message, int k) {
    int len = message.length();
    String encryptedMessage = "";
    for (int i = 0; i < len; i++) {
      char ch = message.charAt(i);
      System.out.println(ch);
      ch = (char) ((ch + k));
      if ((ch + k) > 122) {
        ch = (char) ((ch) - 25);
      }
      System.out.println(ch);
      encryptedMessage += ch;
    }
    return encryptedMessage;
  }

  static String decrypt(String message, int k) {
    int len = message.length();
    String decryptedMessage = "";
    for (int i = 0; i < len; i++) {
      char ch = message.charAt(i);
      ch = (char) ((ch - k));
      if ((ch - k) < 97) {
        ch = (char) ((ch) + 25);
      }
      decryptedMessage += ch;
    }
    return decryptedMessage;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter the message: ");
    String message = sc.nextLine();
    System.out.println("Enter the key: ");
    int key = sc.nextInt();
    String encryptedMessage = encrypt(message, key);
    System.out.println("Encrypted message: " + encryptedMessage);
    String decryptedMessage = decrypt(encryptedMessage, key);
    System.out.println("Decrypted message: " + decryptedMessage);
  }
}
