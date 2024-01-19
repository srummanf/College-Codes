import javax.crypto.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.spec.KeySpec;
import java.util.Base64;

public class CipherServer
{
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5555);
            System.out.println("Server waiting for connection...");

            Socket socket = serverSocket.accept();
            System.out.println("Client connected");

            // Generate DES key
            KeyGenerator keyGen = KeyGenerator.getInstance("DES");
            SecretKey secretKey = keyGen.generateKey();

            // Send the key to the client
            ObjectOutputStream outKey = new ObjectOutputStream(socket.getOutputStream());
            outKey.writeObject(secretKey);

            // Set up input and output streams
            BufferedReader serverIn = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader clientIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter clientOut = new PrintWriter(socket.getOutputStream(), true);

            while (true) {
                // Server sends a message
                System.out.print("Server: ");
                String serverMessage = serverIn.readLine();

                // Encrypt the message
                Cipher cipher = Cipher.getInstance("DES");
                cipher.init(Cipher.ENCRYPT_MODE, secretKey);
                byte[] encryptedMessage = cipher.doFinal(serverMessage.getBytes());

                // Print the encrypted message
                System.out.println("Encrypted Message: " + Base64.getEncoder().encodeToString(encryptedMessage));

                // Send the encrypted message to the client
                clientOut.println(Base64.getEncoder().encodeToString(encryptedMessage));

                // Client receives and decrypts the message
                String clientEncryptedMessage = clientIn.readLine();
                byte[] decryptedMessage = cipher.doFinal(Base64.getDecoder().decode(clientEncryptedMessage));

                // Print the decrypted message
                System.out.println("Client: Decrypted Message: " + new String(decryptedMessage));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
