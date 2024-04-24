import java.io.*;
import java.math.BigInteger;
import java.net.*;

public class DHClient {
    private static final BigInteger p = new BigInteger("23"); // Prime number
    private static final BigInteger g = new BigInteger("5"); // Base

    public static void main(String[] args) {
        try {
            // Input Alice's secret number
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter Alice's secret number (a): ");
            BigInteger a = new BigInteger(reader.readLine());

            Socket socket = new Socket("localhost", 12345);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());

            // Step 2: Calculate public key
            BigInteger A = g.modPow(a, p);
            out.writeUTF(A.toString()); // Send A to server
            System.out.println("Alice sends her public key (A) to Bob: " + A);

            // Step 5: Receive server's public key
            BigInteger B = new BigInteger(in.readUTF());
            System.out.println("Alice receives Bob's public key (B): " + B);

            // Input shared secret
            System.out.print("Enter shared secret: ");
            BigInteger s = new BigInteger(reader.readLine());
            out.writeUTF(s.toString()); // Send shared secret to server
            System.out.println("Alice sends shared secret to Bob: " + s);

            in.close();
            out.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
