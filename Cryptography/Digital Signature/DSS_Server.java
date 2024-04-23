import java.io.*;
import java.net.*;
import java.security.*;

/**
 * This class represents a Digital Signature Server (DSS) that listens on a specified port for incoming connections.
 * When a connection is established, it receives a public key, a message, and a digital signature from the client.
 * The server then verifies the digital signature using the provided public key and message.
 */
public class DSS_Server {
    public static void main(String[] args) throws Exception {
        // Create a server socket and bind it to port 6868
        ServerSocket serverSocket = new ServerSocket(6868);
        System.out.println("Server is listening on port 6868.");

        while (true) {
            // Wait for a client connection
            Socket socket = serverSocket.accept();
            System.out.println("Connection Found");

            // Create an object input stream to read objects from the client
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            // Read the public key, message, and digital signature from the client
            PublicKey pubKey = (PublicKey) ois.readObject();
            byte[] data = (byte[]) ois.readObject();
            byte[] signature = (byte[]) ois.readObject();

            System.out.println("The Message is: " + new String(data));
            System.out.println("The Signature is: " + new String(signature));

            // Verify the digital signature using the provided public key and message
            boolean isVerified = DigitalSignatureUtils.verifySignature(data, signature, pubKey);
            System.out.println("Signature verified: " + isVerified);

            // Close the client socket
            socket.close();

            // Exit the server after processing one connection
            break;
        }

        // Close the server socket
        serverSocket.close();
    }
}