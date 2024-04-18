import javax.net.ssl.SSLSocketFactory; 
import javax.net.ssl.SSLSocket; 
import java.io.BufferedReader; 
import java.io.InputStreamReader; 
import java.io.PrintWriter; 
 
public class SSLClient { 
    public static void main(String[] args) { 
        try { 
            System.setProperty("javax.net.ssl.trustStore", "truststore.jks"); 
            System.setProperty("javax.net.ssl.trustStorePassword", "changeit"); 
 
            SSLSocketFactory ssf = (SSLSocketFactory) 
SSLSocketFactory.getDefault(); 
            SSLSocket sslSocket = (SSLSocket) ssf.createSocket("localhost", 
9999); 
 
            PrintWriter out = new PrintWriter(sslSocket.getOutputStream(), true); 
            BufferedReader in = new BufferedReader(new 
InputStreamReader(sslSocket.getInputStream())); 
 
            out.println("Hello SSL Server"); 
            String line; 
            while ((line = in.readLine()) != null) { 
                System.out.println("Received: " + line); 
            } 
        } catch (Exception e) { 
            e.printStackTrace(); 
        } 
    } 
} 