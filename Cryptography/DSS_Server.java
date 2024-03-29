import java.io.*;
import java.net.*;
import java.security.*;

public class DSS_Server {

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(6868);
        System.out.println("Server is listening on port 6868.");
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("Connection Found");
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            PublicKey pubKey=(PublicKey) ois.readObject();
            byte[] data = (byte[]) ois.readObject();
            byte[] signature = (byte[]) ois.readObject();
            System.out.println("The Message is: "+data);
            System.out.println("The Signature is: "+signature);
            boolean isVerified = DigitalSignatureUtils.verifySignature(data, signature, pubKey);
            System.out.println("Signature verified: " + isVerified);
            socket.close();
            break;
        }
        serverSocket.close();
    }
    
}
