import java.io.*;
import java.net.*;
import java.security.*;
import java.util.*;

/**
 * This class represents a Digital Signature Client that connects to a Digital Signature Server (DSS)
 * to send a message and its corresponding digital signature for verification.
 */
public class DSS_Client {
    public static void main(String[] args) throws Exception {
        // Generate a new key pair for digital signature
        KeyPair keyPair = DigitalSignatureUtils.generateKeyPair();
        System.out.println(keyPair);

        // Create a scanner to read user input
        Scanner scanner = new Scanner(System.in);

        // Connect to the DSS server running on localhost and port 6868
        Socket socket = new Socket("localhost", 6868);

        // Create an object output stream to send objects to the server
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

        // Prompt the user to enter the message to be signed
        System.out.println("Enter the Message to be signed: ");
        String message = scanner.nextLine();
        scanner.close();

        // Convert the message to bytes
        byte[] data = message.getBytes("UTF-8");

        // Compute the digital signature for the message using the private key
        byte[] signature = DigitalSignatureUtils.signData(data, keyPair.getPrivate());
        System.out.println("Signature Computed.");

        // Send the public key, message, and digital signature to the server
        oos.writeObject(keyPair.getPublic());
        oos.writeObject(data);
        oos.writeObject(signature);
        oos.flush();

        System.out.println("Message and Signature sent to Server.");

        // Close the socket connection
        socket.close();
    }
}