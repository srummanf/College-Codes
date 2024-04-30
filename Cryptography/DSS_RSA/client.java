import java.io.*;
import java.math.BigInteger;
import java.net.*;
import java.util.*;

class client {

  public static void main(String[] args) {
    try {
        Socket s = new Socket("localhost", 3000);
        DataInputStream dis = new DataInputStream(s.getInputStream());
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());

        int signature = Integer.parseInt(dis.readUTF());
        int n = Integer.parseInt(dis.readUTF());
        int e = Integer.parseInt(dis.readUTF());
        int message = Integer.parseInt(dis.readUTF());

        System.out.println("Signature: " + signature);
        System.out.println("Public Key: " + e + " , " +n);
        System.out.println("Message: " + message);

        BigInteger bE = BigInteger.valueOf(e);
        BigInteger bSignature = BigInteger.valueOf(signature);
        BigInteger bN = BigInteger.valueOf(n);

        BigInteger verification = bSignature.modPow(bE, bN);
        System.out.println("Verification: " + verification);

        if(verification.intValue() == message) {
            System.out.println("The message is verified");
        } else {
            System.out.println("The message is not verified");
        }
        



    } catch (Exception e) {
        e.printStackTrace();
    }
  }
}
