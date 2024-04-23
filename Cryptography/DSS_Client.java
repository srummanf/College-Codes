import java.io.*;
import java.net.*;
import java.security.*;
import java.util.*;
public class DSS_Client {
    public static void main(String[] args) throws Exception {

        KeyPair keypair = DigitalSignatureUtils.generateKeyPair();
        System.out.println(keypair);

        Scanner sc=new Scanner(System.in);

        Socket socket = new Socket("localhost", 6868);

        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

        System.out.println("Enter the Message to be signed: ");
        String message = sc.nextLine();
        sc.close();

        byte[] data = message.getBytes("UTF-8");
        byte[] signature = DigitalSignatureUtils.signData(data, keypair.getPrivate());
        System.out.println("Signature Computed.");

        oos.writeObject(keypair.getPublic());
        oos.writeObject(data);
        oos.writeObject(signature);
        oos.flush();
        
        System.out.println("Message and Signature sent to Server.");
        socket.close();
        
    }
     
}
