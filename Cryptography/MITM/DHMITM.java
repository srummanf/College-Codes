import java.io.*;
import java.math.BigInteger;
import java.net.*;

public class DHMITM {
    private static final BigInteger p = new BigInteger("23"); // Prime number
    private static final BigInteger g = new BigInteger("5"); // Base

    public static void main(String[] args) {
        try {
            // Input Eve's secret number
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter Eve's secret number (e): ");
            BigInteger e = new BigInteger(reader.readLine());

            ServerSocket serverSocket = new ServerSocket(12346); // Eve's port
            System.out.println("MITM server started, waiting for connection...");

            // Connect to Alice
            Socket aliceSocket = new Socket("localhost", 12345); // Alice's port
            DataOutputStream aliceOut = new DataOutputStream(aliceSocket.getOutputStream());
            DataInputStream aliceIn = new DataInputStream(aliceSocket.getInputStream());

            // Connect to Bob
            Socket bobSocket = new Socket("localhost", 12345); // Bob's port
            DataOutputStream bobOut = new DataOutputStream(bobSocket.getOutputStream());
            DataInputStream bobIn = new DataInputStream(bobSocket.getInputStream());

            // Step 1: Receive Alice's public key and send Eve's modified public key
            BigInteger A = new BigInteger(aliceIn.readUTF());
            System.out.println("Eve intercepts Alice's public key (A): " + A);
            BigInteger EveA = g.modPow(e, p); // Eve's modified public key
            bobOut.writeUTF(EveA.toString()); // Forward Eve's public key to Bob
            System.out.println("Eve forwards her modified public key (EveA) to Bob: " + EveA);

            // Step 2: Receive Bob's public key and send Eve's modified public key
            BigInteger B = new BigInteger(bobIn.readUTF());
            System.out.println("Eve intercepts Bob's public key (B): " + B);
            BigInteger EveB = g.modPow(e, p); // Eve's modified public key
            aliceOut.writeUTF(EveB.toString()); // Forward Eve's public key to Alice
            System.out.println("Eve forwards her modified public key (EveB) to Alice: " + EveB);

            // Close connections
            aliceIn.close();
            aliceOut.close();
            aliceSocket.close();
            bobIn.close();
            bobOut.close();
            bobSocket.close();
            serverSocket.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

