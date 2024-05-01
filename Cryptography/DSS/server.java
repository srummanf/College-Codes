import java.io.*;
import java.math.*;
import java.net.*;
import java.util.*;

class server {

  static int findInverse(int num, int n) {
    for (int i = 2; i < Integer.MAX_VALUE; ++i) {
      if ((num * i) % n == 1) {
        return i;
      }
    }
    return -1;
  }

  static int negativeMod(int num, int n) {
    int num2 = Math.abs(num);
    for (int i = 2; i < Integer.MAX_VALUE; ++i) {
      if ((n * i) > num2) {
        return ((n * i) - num2);
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

        int p = 5;
        int q = 7;
        int g = 6;
        int hm = 4;
        int x = 2;
        int k = 5;
        

        int y = (int)Math.pow(q,x)%p;
        int r = ((int)Math.pow(g,x)%p)%q;
        int signature = (hm + x*r)*(findInverse(k, r));

        System.out.println("r: " + r);
        System.out.println("signature: " + signature);

        System.out.println("p: " + p);
        System.out.println("q: " + q);
        System.out.println("hm: " + hm);
        System.out.println("g: " + g);
        System.out.println("y: " + y);

        dos.writeUTF(Integer.toString(r));
        dos.writeUTF(Integer.toString(signature));
        dos.writeUTF(Integer.toString(p));
        dos.writeUTF(Integer.toString(q));
        dos.writeUTF(Integer.toString(hm));
        dos.writeUTF(Integer.toString(g));
        dos.writeUTF(Integer.toString(y));


    } catch (Exception e) {
      // TODO: handle exception
    }
  }
}
