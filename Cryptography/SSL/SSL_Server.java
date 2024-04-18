import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

public class SSL_Server {

  public static void main(String[] args) {
    try {
      System.setProperty("javax.net.ssl.keyStore", "keystore.jks");
      System.setProperty("javax.net.ssl.keyStorePassword", "changeit");

      SSLServerSocketFactory ssf = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
      SSLServerSocket serverSocket = (SSLServerSocket) ssf.createServerSocket(
        9999
      );

      System.out.println("SSL ServerSocket started");
      SSLSocket sslSocket = (SSLSocket) serverSocket.accept();
      System.out.println("Server socket accepted");

      PrintWriter out = new PrintWriter(sslSocket.getOutputStream(), true);
      BufferedReader in = new BufferedReader(
        new InputStreamReader(sslSocket.getInputStream())
      );

      String line;
      while ((line = in.readLine()) != null) {
        System.out.println("Received: " + line);
        out.println("Echo: " + line);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
