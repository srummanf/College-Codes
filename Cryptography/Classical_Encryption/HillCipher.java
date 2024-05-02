import java.io.*;
import java.util.*;

public class HillCipher {

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
    Scanner sc = new Scanner(System.in);
    System.out.println("The size of matrix for Hill Cipher is : 3 ");
    int[][] key = new int[3][3];
    int[][] adj = new int[3][3];

    

    int determinant =
      key[0][0] *
      (key[1][1] * key[2][2] - key[1][2] * key[2][1]) -
      key[0][1] *
      (key[1][0] * key[2][2] - key[1][2] * key[2][0]) +
      key[0][2] *
      (key[1][0] * key[2][1] - key[1][1] * key[2][0]);

    adj[0][0] = key[1][1] * key[2][2] - key[1][2] * key[2][1];
    adj[0][1] = key[2][1] * key[0][2] - key[0][1] * key[2][2];
    adj[0][2] = key[0][1] * key[1][2] - key[1][1] * key[0][2];
    adj[1][0] = key[1][2] * key[2][0] - key[1][0] * key[2][2];
    adj[1][1] = key[0][0] * key[2][2] - key[0][2] * key[2][0];
    adj[1][2] = key[1][0] * key[0][2] - key[0][0] * key[1][2];
    adj[2][0] = key[1][0] * key[2][1] - key[2][0] * key[1][1];
    adj[2][1] = key[2][0] * key[0][1] - key[0][0] * key[2][1];
    adj[2][2] = key[0][0] * key[1][1] - key[1][0] * key[0][1];

    System.out.print("Enter message size: ");
    int len = sc.nextInt();
    int arrlen = len + (3-len%3);
    System.out.println("The size of the array is : " + arrlen);
    int arr[] = new int[arrlen];

    for (int i = 0; i < len; i++) {
      System.out.print("Enter the message char : ");
      String s = sc.next();
      arr[i] = s.charAt(0) - 97;
      System.out.println(arr[i]);
    }

    for (int i = len; i <arrlen; i++) {
      arr[i] = -9999;
    }

    for (int i = 0; i <arrlen; i++) {
      System.out.println(arr[i]);
    }

    

  }
}
