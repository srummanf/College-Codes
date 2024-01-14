import java.io.*;
import java.net.*;

public class YoMyclient {
  public static void main(String[] args) throws IOException {
      Socket s = new Socket("localhost", 2004);

      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter pw = new PrintWriter(s.getOutputStream(), true);

      System.out.print("Enter the message: ");
      String message = br.readLine();
      System.out.print("Enter the key: ");
      String key = br.readLine();

      pw.println(message);
      pw.println(key);

      BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
      System.out.println("Encrypted message: " + reader.readLine());
      System.out.println("Decrypted message: " + reader.readLine());

      br.close();
      pw.close();
      s.close();
  }
}
