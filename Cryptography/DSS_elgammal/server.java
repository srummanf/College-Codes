import java.io.*;
import java.math.*;
import java.net.*;
import java.util.*;

public class server {

  static int findInverse(int num, int n) {
    for (int i = 2; i < Integer.MAX_VALUE; ++i) {
      if ((num * i) % n == 1) {
        return i;
      }
    }
    return -1;
  }

  static int negativeMod(int num, int n){
    int num2 = Math.abs(num);
    for (int i = 2; i < Integer.MAX_VALUE; ++i) {
      if ((n * i) > num2) {
        return ((n*i) - num2);
      }
    }
    return -1;
  }

  public static void main(String[] args) {
    try {
      ServerSocket ss = new ServerSocket(1000);
      System.out.println("Waiting for connection...");

      Socket s = ss.accept();
      DataInputStream dis = new DataInputStream(s.getInputStream());
      DataOutputStream dos = new DataOutputStream(s.getOutputStream());
      Scanner sc = new Scanner(System.in);

      int q = 19;
      int alpha = 10;
      System.out.println("Enter the message: ");
      int message = sc.nextInt();
      int Xa = 16;

      BigInteger Balpha = BigInteger.valueOf(alpha);
      BigInteger BXa = BigInteger.valueOf(Xa);
      BigInteger Bq = BigInteger.valueOf(q);
      BigInteger BMessage = BigInteger.valueOf(message);

      BigInteger BYa = Balpha.modPow(BXa, Bq);

      int k = 5;
      BigInteger Bk = BigInteger.valueOf(k);

      BigInteger Signature1 = Balpha.modPow(Bk, Bq);
    //   BigInteger Signature2 =
    //     (BMessage.subtract(BXa.multiply(Signature1))).multiply(
    //         Bk.modInverse(BigInteger.valueOf(q - 1))
    //       );

    int a = findInverse(k, q-1);
    int b = message - (Xa * Signature1.intValue());
    int c = negativeMod(b, q-1);
    int Signature2 = (a*c) % (q-1);

      System.out.println(
        "Sign1 " + Signature1
      );
      System.out.println(
        "Sign2: " + Signature2
      );

      dos.writeUTF(String.valueOf(Signature1));
      dos.writeUTF(String.valueOf(Signature2));
      dos.writeUTF(String.valueOf(BYa));
      dos.writeUTF(String.valueOf(q));
      dos.writeUTF(String.valueOf(alpha));
      dos.writeUTF(String.valueOf(message));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
