/**
 * 
 * Here's the step-by-step algorithm for the provided Java code:

### Algorithm:

1. Import necessary packages:
   - `java.io.*` for input/output operations.
   - `java.net.*` for networking functionalities.
   - `java.security.MessageDigest` for generating SHA-512 hash.
   - `java.security.NoSuchAlgorithmException` for handling exceptions related to missing algorithms.
   - `java.util.Scanner` for reading user input.

2. Define the `SHAServer` class.
   - Declare the `main` method.

3. Inside the `main` method:
   - Create a `ServerSocket` object `serverSocket` and initialize it to `null`.
   - Create a `Socket` object `socket` and initialize it to `null`.
   - Try block starts to handle potential IOException and NoSuchAlgorithmException.

4. Try block:
   - Initialize `serverSocket` with a new `ServerSocket` instance on port `12345`.
   - Print "Server started, waiting for client..." to the console.
   - Accept a client connection and assign it to the `socket` object.
   - Create `DataInputStream` and `DataOutputStream` objects (`dis` and `dos`) to handle input and output streams from/to the client.
   - Create a `Scanner` object (`scanner`) to read input from the console.

5. While loop:
   - Continuously loop until a termination command ("exit") is received from the client.
   - Read a message from the client (`received`) using `dis.readUTF()`.
   - If the received message is "exit", break the loop.
   - Decrypt the received message using the `getDecryptedSHA512` method and print it to the console.
   - Prompt the user to enter a message to send to the client.
   - Read the message from the console using `scanner.nextLine()`.
   - Send the SHA-512 hash of the message to the client using `dos.writeUTF(getSHA512(message))`.

6. Close resources:
   - Close the `Scanner`, `DataInputStream`, `DataOutputStream`, `Socket`, and `ServerSocket`.
   - Handle any potential IOException or NoSuchAlgorithmException.



 */
/**
 * A simple server program that listens for client connections and performs SHA-512 hashing on received messages.
 */
import java.io.*;
import java.net.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class SHAServer {
    
    /**
     * Main method to start the server.
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket socket = null;
        try {
            // Create a server socket on port 12345
            serverSocket = new ServerSocket(12345);
            System.out.println("Server started, waiting for client...");

            // Accept client connection
            socket = serverSocket.accept();

            // Initialize input and output streams
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            // Scanner for user input
            Scanner scanner = new Scanner(System.in);

            // Main loop to interact with the client
            while (true) {
                // Receive message from client
                String received = dis.readUTF();
                if (received.equalsIgnoreCase("exit")) {
                    break;
                }
                // Decrypt and print received message
                String decryptedMessage = getSHA512(received);
                System.out.println("Client: " + decryptedMessage);
                System.out.print("Server: ");
                // Prompt for message to send to client
                String message = scanner.nextLine();
                // Send SHA-512 hash of the message to client
                dos.writeUTF(getSHA512(message));
            }

            // Close resources
            scanner.close();
            dis.close();
            dos.close();
            socket.close();
            serverSocket.close();
        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    // Note : getSHA512 and getDecryptedSHA512 methods are same

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
