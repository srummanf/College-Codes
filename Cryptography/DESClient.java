import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.spec.KeySpec;
import java.util.Base64;

public class DESClient {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 5555);

            // Replace the keyString with your DES key
            String keyString = "01234567";
            byte[] keyBytes = keyString.getBytes();

            // Ensure the key size is correct
            if (keyBytes.length != 8) {
                throw new InvalidKeyException("Invalid DES key size");
            }

            // Generate DES key
            KeySpec keySpec = new DESKeySpec(keyBytes);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretKey = keyFactory.generateSecret(keySpec);

            // Set up input and output streams
            BufferedReader serverIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader clientIn = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter serverOut = new PrintWriter(socket.getOutputStream(), true);

            while (true) {
                // Client receives and decrypts the message
                String serverEncryptedMessage = serverIn.readLine();
                Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
                cipher.init(Cipher.DECRYPT_MODE, secretKey);
                byte[] decryptedMessage = cipher.doFinal(Base64.getDecoder().decode(serverEncryptedMessage));

                // Print the decrypted message
                System.out.println("Server: Decrypted Message: " + new String(decryptedMessage));

                // Client sends a message
                System.out.print("Client: ");
                String clientMessage = clientIn.readLine();

                // Encrypt the message
                cipher.init(Cipher.ENCRYPT_MODE, secretKey);
                byte[] encryptedMessage = cipher.doFinal(clientMessage.getBytes());

                // Send the encrypted message to the server
                serverOut.println(Base64.getEncoder().encodeToString(encryptedMessage));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
