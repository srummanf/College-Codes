import java.io.*;
import java.math.*;
import java.net.*;
import java.util.*;

class client {

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
   
        Socket s = new Socket("localhost", 1000);
        DataInputStream dis = new DataInputStream(s.getInputStream());
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());
        Scanner sc = new Scanner(System.in);

        int r = Integer.parseInt(dis.readUTF());
        int signature = Integer.parseInt(dis.readUTF());
        int p = Integer.parseInt(dis.readUTF());
        int q = Integer.parseInt(dis.readUTF());
        int hm = Integer.parseInt(dis.readUTF());
        int g = Integer.parseInt(dis.readUTF());
        int y = Integer.parseInt(dis.readUTF());

        int w = findInverse(signature, q);
        int u2 = (r*w)%q;
        int u1 = (hm*w)%q;
        int v = (((int)Math.pow(g,u1)*(int)Math.pow(y,u2))%p)%q;

        System.out.println("w: " + w);
        System.out.println("u2: " + u2);
        System.out.println("u1: " + u1);
        System.out.println("v: " + v);

        if (v == r) {
          System.out.println("The message is verified");
        } else {
          System.out.println("The message is not verified");
        }




    } catch (Exception e) {
      // TODO: handle exception
    }
  }
}
