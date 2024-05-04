import java.io.*;
import java.util.*;

public class md5_paddmessage {

  static String padding(String message) {
    int len = message.length();
    int endlen = ((Integer.toBinaryString(len)).length()) * 8;
    int multiple = 1;
    int paddinglen = 0;
    for (int i = multiple;; i++) {
      int x = 512 * i;
      if (x >= (len + endlen)) {
        paddinglen = i;
        break;
      }
    }
    int padding = 512 * paddinglen - (len + endlen);
    String curr = "";
    for (int i = 1; i <= padding - 1; i++) {
      curr = curr + "0";
    }
    String paddedMessage = "1" + curr;
    String binaryLength = Integer.toBinaryString(len);
    while(binaryLength.length() < endlen){
      binaryLength = "0" + binaryLength;
    }
    String finalans = message + paddedMessage + binaryLength;
    System.out.println("Padding length : "+padding);
    System.out.println("No of zeroes for padding : "+curr.length());
    System.out.println("Final Padding message length: "+paddedMessage.length());
    System.out.println("Final message length: "+finalans.length());
    
    return finalans;
  }

  public static void main(String[] args) {
    String message =
      "01010100011010000110010101111001001000000110000101110010011001010100000001100100011001010111010001100101011100100110110101101001011011100110100101110011011101000110100101100011";
    System.out.println("Initial Message Length :" + message.length());
    String ans = (padding(message));
    System.out.println(ans);
    System.out.println(ans.length());
  }
}
