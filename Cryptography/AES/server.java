import java.io.*;
import java.net.*;
import java.security.*;
import java.util.Base64;
import java.util.Scanner;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

public class server {

  public static void main(String[] args) {
    try {
      ServerSocket serverSocket = new ServerSocket(5555);
      System.out.println("Server waiting for connection...");

      Socket socket = serverSocket.accept();
      System.out.println("Client connected");

      // Replace the keyString with your AES key
      String keyString = "0123456789012345";
      byte[] keyBytes = keyString.getBytes();

      // Generate AES key
      SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");

      // Set up input and output streams
      DataInputStream serverIn = new DataInputStream(socket.getInputStream());
      DataOutputStream serverOut = new DataOutputStream(
        socket.getOutputStream()
      );
      Scanner scanner = new Scanner(System.in);

      int end = 0;
      while (true) {
        if (end == 1) break;
        // Server sends a message
        System.out.print("Server: ");
        String serverMessage = scanner.nextLine();

        // Encrypt the message
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        byte[] encryptedMessage = cipher.doFinal(serverMessage.getBytes());

        // Print the encrypted message
        System.out.println(
          "Encrypted Message: " +
          Base64.getEncoder().encodeToString(encryptedMessage)
        );

        // Send the encrypted message to the client
        serverOut.writeUTF(
          Base64.getEncoder().encodeToString(encryptedMessage)
        );

        // Client receives and decrypts the message
        String clientEncryptedMessage = serverIn.readUTF();
        byte[] decodedMessage = Base64
          .getDecoder()
          .decode(clientEncryptedMessage);
        byte[] decryptedMessage = cipher.doFinal(decodedMessage);

        // Print the decrypted message
        System.out.println(
          "Client: Decrypted Message: " + new String(decryptedMessage)
        );
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
