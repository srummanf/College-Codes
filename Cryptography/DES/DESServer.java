import java.util.*;
import java.io.*;
import java.net.*;
import java.security.*;
import java.security.spec.*;
import javax.crypto.*;
import javax.crypto.spec.*;

public class DESServer {

  public static void main(String[] args) {
    try {
      ServerSocket serverSocket = new ServerSocket(5555);
      System.out.println("Server waiting for connection...");

      Socket socket = serverSocket.accept();
      System.out.println("Client connected");

      // Replace the keyString with your DES key and it has to be 8 digits
      String keyString = "01234567";
      byte[] keyBytes = keyString.getBytes();

      // Ensure the key size is correct
      // if (keyBytes.length != 8) {
      //   throw new InvalidKeyException("Invalid DES key size");
      // }

      // Generate DES key
      KeySpec keySpec = new DESKeySpec(keyBytes);
      SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
      SecretKey secretKey = keyFactory.generateSecret(keySpec);

      // Set up input and output streams
      DataInputStream dis = new DataInputStream(socket.getInputStream());
      DataOutputStream dos = new DataOutputStream(
        socket.getOutputStream()
      );
      Scanner scanner = new Scanner(System.in);
      int end = 1;
      while (true) {
        if(end ==0) break;
        // Server sends a message
        System.out.print("Server: ");
        String serverMessage = scanner.nextLine();

        // Encrypt the message
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedMessage = cipher.doFinal(serverMessage.getBytes());

        // Print the encrypted message
        System.out.println(
          "Encrypted Message: " +
          Base64.getEncoder().encodeToString(encryptedMessage)
        );

        // Send the encrypted message to the client
        dos.writeUTF(
          Base64.getEncoder().encodeToString(encryptedMessage)
        );

        // Client receives and decrypts the message
        String clientEncryptedMessage = dis.readUTF();
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedMessage = cipher.doFinal(
          Base64.getDecoder().decode(clientEncryptedMessage)
        );

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
