import javax.crypto.*;
import java.io.*;
import java.net.Socket;
import java.util.Base64;
import javax.crypto.spec.SecretKeySpec;

public class DESClient {

    public static void main(String[] args) throws IOException {
        Socket s = new Socket("localhost", 2004);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(s.getOutputStream(), true);

        System.out.print("Enter the message: ");
        String message = br.readLine();

        String keyString = "0F1571C947D9E859"; // Replace with your DES key
        SecretKey secretKey = new SecretKeySpec(hexStringToByteArray(keyString), "DES");

        // Encrypt the message
        String encryptedMessage = encrypt(message, secretKey);
        pw.println(encryptedMessage);

        br.close();
        pw.close();
        s.close();
    }

    private static String encrypt(String input, SecretKey secretKey) {
        try {
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedBytes = cipher.doFinal(input.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return "Error encrypting message";
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
