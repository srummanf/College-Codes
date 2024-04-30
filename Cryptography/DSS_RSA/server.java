import java.io.*;
import java.net.*;
import java.util.*;
import java.math.*;

class server {

  public static void main(String[] args) {
    try {
        ServerSocket ss = new ServerSocket(3000);
        System.out.println("Waiting for connection...");
        Socket s = ss.accept();
        DataInputStream dis = new DataInputStream(s.getInputStream());
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the message: ");
        int message = sc.nextInt();
        int p =7;
        int q =17;
        int n = p*q;
        int phi = (p-1)*(q-1);
        int e = 11;


        BigInteger bE = BigInteger.valueOf(e);
        BigInteger bPhi = BigInteger.valueOf(phi);

        BigInteger bD = bE.modInverse(bPhi);

        BigInteger bMessage = BigInteger.valueOf(message);
        BigInteger bN = BigInteger.valueOf(n);

        BigInteger signature = bMessage.modPow(bD, bN);

        System.out.println("Signature: " + signature);
        System.out.println("Public Key: " + e + " , " +n);
        System.out.println("Private Key: " + bD + " , " +n);
        System.out.println("Message: " + message);


        dos.writeUTF(String.valueOf(signature));
        dos.writeUTF(String.valueOf(n));
        dos.writeUTF(String.valueOf(e));
        dos.writeUTF(String.valueOf(message));  



    } catch (Exception e) {
        e.printStackTrace();
    }
  }
}
