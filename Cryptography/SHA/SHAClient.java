/** 
### Algorithm:

1. Import necessary packages:
   - `java.io.*` for input/output operations.
   - `java.net.*` for networking functionalities.
   - `java.security.MessageDigest` for generating SHA-512 hash.
   - `java.security.NoSuchAlgorithmException` for handling exceptions related to missing algorithms.
   - `java.util.Scanner` for reading user input.

2. Define the `SHAClient` class.

3. Inside the `main` method:
   - Create a `Socket` object `socket` and initialize it to `null`.
   - Try block starts to handle potential IOException and NoSuchAlgorithmException.

4. Try block:
   - Connect to the server running on localhost at port 12345 using the `Socket` constructor.
   - Initialize `DataInputStream` and `DataOutputStream` objects (`dis` and `dos`) to handle input and output streams to/from the server.
   - Create a `Scanner` object (`scanner`) to read input from the console.

5. While loop:
   - Continuously loop until a termination command ("exit") is entered by the user.
   - Prompt the user to enter a message to send to the server.
   - Read the message from the console using `scanner.nextLine()`.
   - If the entered message is "exit", break the loop.
   - Send the SHA-512 hash of the message to the server using `dos.writeUTF(getSHA512(message))`.
   - Receive a response from the server using `dis.readUTF()` and print it to the console.

6. Close resources:
   - Close the `Scanner`, `DataInputStream`, `DataOutputStream`, and `Socket`.
   - Handle any potential IOException or NoSuchAlgorithmException.

 */

import java.io.*;
import java.net.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class SHAClient {

  /**
   * Main method to start the client.
   * @param args Command-line arguments (not used).
   */
  public static void main(String[] args) {
    Socket socket = null;
    try {
      // Connect to the server running on localhost at port 12345
      socket = new Socket("localhost", 12345);
      // Initialize input and output streams
      DataInputStream dis = new DataInputStream(socket.getInputStream());
      DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
      // Scanner for user input
      Scanner scanner = new Scanner(System.in);

      // Main loop to interact with the server
      while (true) {
        // Prompt for message to send to server
        System.out.print("Client: ");
        String message = scanner.nextLine();
        if (message.equalsIgnoreCase("exit")) {
          break;
        }
        // Send SHA-512 hash of the message to server
        dos.writeUTF(getSHA512(message));
        // Receive and print server's response
        String received = dis.readUTF();
        System.out.println("Server: " + received);
      }

      // Close resources
      scanner.close();
      dis.close();
      dos.close();
      socket.close();
    } catch (IOException | NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
  }

  /**
   * Generates the SHA-512 hash of the input string.
   * @param input The string to be hashed.
   * @return The SHA-512 hash of the input string.
   * @throws NoSuchAlgorithmException If SHA-512 algorithm is not available.
   */
  public static String getSHA512(String input) throws NoSuchAlgorithmException {
    MessageDigest md = MessageDigest.getInstance("SHA-512");
    byte[] messageDigest = md.digest(input.getBytes());
    StringBuilder sb = new StringBuilder();
    for (byte b : messageDigest) {
      sb.append(String.format("%02x", b));
    }
    return sb.toString();
  }
}
