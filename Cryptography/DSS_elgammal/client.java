import java.io.*;
import java.math.*;
import java.net.*;
import java.util.*;

public class client {

  public static void main(String[] args) {
    try {
      Socket s = new Socket("localhost", 1000);
      DataInputStream dis = new DataInputStream(s.getInputStream());
      Scanner sc = new Scanner(System.in);

      int Signature1 = Integer.parseInt(dis.readUTF());
      int Signature2 = Integer.parseInt(dis.readUTF());
      int S1S2 = (int)Math.pow(Signature1, Signature2);
      int Ya = Integer.parseInt(dis.readUTF());
      int q = Integer.parseInt(dis.readUTF());
      int alpha = Integer.parseInt(dis.readUTF());
      int message = Integer.parseInt(dis.readUTF());

      BigInteger Balpha = BigInteger.valueOf(alpha);
      BigInteger Bq = BigInteger.valueOf(q);
      BigInteger Bm = BigInteger.valueOf(message);
      BigInteger BYa = BigInteger.valueOf(Ya);
      BigInteger BS1 = BigInteger.valueOf(Signature1);
      BigInteger BS2 = BigInteger.valueOf(Signature2);
      BigInteger BS1S2 = BigInteger.valueOf(S1S2);
      BigInteger one = BigInteger.valueOf(1);

      BigInteger Verification1 = Balpha.modPow(Bm, Bq);


    //   BigInteger Verification2 = BS1S2.multiply(BYa.modPow(BS1, Bq));
    int Verification2 = (int)((int)Math.pow(Ya, Signature1) * (int)Math.pow(Signature1, Signature2))%q;


      System.out.println("Signature1: " + Signature1);
      System.out.println("Signature2: " + Signature2);
      System.out.println("Verification1: " + Verification1);
      System.out.println("Verification2: " + Verification2);
      System.out.println("S1S2: " + S1S2);


      if (Verification2 == Verification1.intValue()) {
        System.out.println("The message is verified");
      } else {
        System.out.println("The message is not verified");
      }
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
  }
}
