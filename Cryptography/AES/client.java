import java.io.*;
import java.net.Socket;
import java.security.*;
import java.util.Base64;
import java.util.Scanner;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

public class client {

  public static void main(String[] args) {
    try {
      Socket socket = new Socket("localhost", 5555);

      // Replace the keyString with your AES key
      String keyString = "0123456789012345";
      byte[] keyBytes = keyString.getBytes();

      // Generate AES key
      SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");

      // Set up input and output streams
      DataInputStream clientIn = new DataInputStream(socket.getInputStream());
      DataOutputStream clientOut = new DataOutputStream(
        socket.getOutputStream()
      );
      Scanner scanner = new Scanner(System.in);

      int end = 0;
      while (true) {
        if (end == 1) break;
        // Client sends a message
        System.out.print("Client: ");
        String clientMessage = scanner.nextLine();

        // Encrypt the message
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        byte[] encryptedMessage = cipher.doFinal(clientMessage.getBytes());

        // Print the encrypted message
        System.out.println(
          "Encrypted Message: " +
          Base64.getEncoder().encodeToString(encryptedMessage)
        );

        // Send the encrypted message to the server
        clientOut.writeUTF(
          Base64.getEncoder().encodeToString(encryptedMessage)
        );

        // Server receives and decrypts the message
        String serverEncryptedMessage = clientIn.readUTF();
        byte[] decodedMessage = Base64
          .getDecoder()
          .decode(serverEncryptedMessage);
        byte[] decryptedMessage = cipher.doFinal(decodedMessage);

        // Print the decrypted message
        System.out.println(
          "Server: Decrypted Message: " + new String(decryptedMessage)
        );
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
