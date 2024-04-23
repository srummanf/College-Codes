import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Base64;

public class AESSServer {

    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(2004);
        System.out.println("Server started...");

        Socket s = ss.accept();
        System.out.println("Client connected!");

        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        PrintWriter pw = new PrintWriter(s.getOutputStream(), true);

        String encryptedMessage = br.readLine();
        String keyString = "0123456789abcdef"; // Replace with your AES key

        // Decode the key
        byte[] keyBytes = hexStringToByteArray(keyString);
        SecretKey secretKey = new SecretKeySpec(keyBytes, "AES");

        // Decrypt the message
        String decryptedMessage = decrypt(encryptedMessage, secretKey);
        pw.println(decryptedMessage);

        br.close();
        pw.close();
        s.close();
        ss.close();
    }

    private static String decrypt(String input, SecretKey secretKey) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decodedBytes = Base64.getDecoder().decode(input);
            byte[] decryptedBytes = cipher.doFinal(decodedBytes);
            return new String(decryptedBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return "Error decrypting message";
        }
    }

    private static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                                 + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }
}
