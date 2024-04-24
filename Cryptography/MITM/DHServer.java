import java.io.*;
import java.math.BigInteger;
import java.net.*;

public class DHServer {
    private static final BigInteger p = new BigInteger("23"); // Prime number
    private static final BigInteger g = new BigInteger("5"); // Base

    public static void main(String[] args) {
        try {
            // Input Bob's secret number
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter Bob's secret number (b): ");
            BigInteger b = new BigInteger(reader.readLine());

            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Server started, waiting for connection...");

            Socket socket = serverSocket.accept();
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());

            // Step 2: Calculate public key
            BigInteger B = g.modPow(b, p);
            out.writeUTF(B.toString()); // Send B to client
            System.out.println("Bob sends his public key (B) to Alice: " + B);

            // Step 6: Receive client's public key
            BigInteger A = new BigInteger(in.readUTF());
            System.out.println("Bob receives Alice's public key (A): " + A);

            // Receive shared secret from Alice
            BigInteger s = new BigInteger(in.readUTF());
            System.out.println("Bob receives shared secret from Alice: " + s);

            in.close();
            out.close();
            socket.close();
            serverSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
