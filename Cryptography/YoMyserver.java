import java.io.*;
import java.net.*;

public class YoMyserver {
  public static void main(String[] args) throws IOException {
      ServerSocket ss = new ServerSocket(2004);
      System.out.println("Server started...");

      Socket s = ss.accept();
      System.out.println("Client connected!");

      BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
      PrintWriter pw = new PrintWriter(s.getOutputStream(), true);

      String message, key;
      message = br.readLine();
      key = br.readLine();

      String cipherText = encrypt(message, Integer.parseInt(key));
      String plainText = decrypt(cipherText, Integer.parseInt(key));

      pw.println(cipherText);
      pw.println(plainText);

      br.close();
      pw.close();
      s.close();
      ss.close();
  }

  static String encrypt(String text, int key) {
      StringBuilder result = new StringBuilder();
      for (int i = 0; i < text.length(); i++) {
          char ch = text.charAt(i);
          if (Character.isUpperCase(ch)) {
              result.append((char) ('A' + (ch - 'A' + key) % 26));
          } else {
              result.append((char) ('a' + (ch - 'a' + key) % 26));
          }
      }
      return result.toString();
  }

  static String decrypt(String text, int key) {
      return encrypt(text, 26 - key);
  }
}
