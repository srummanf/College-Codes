import java.io.*;
import java.net.*;
import java.util.*;

public class server {

  public static void main(String[] args) {
    // ServerSocket serverSocket = new ServerSocket(1000);
    // Socket socket = serverSocket.accept();
    // DataInputStream dis = new DataInputStream(socket.getInputStream());
    // DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
    Scanner sc = new Scanner(System.in);

    System.out.println("Enter the key: ");
    String key = sc.nextLine();
    int keyLength = key.length();

    System.out.println("Enter the message: ");
    String message = sc.nextLine();

    int rows = (int) Math.ceil((double) message.length() / keyLength);
    int cols = keyLength;

    char[][] matrix = new char[rows][cols];

    int index = 0;
    

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (index < message.length()) {
          matrix[i][j] = message.charAt(index);
          index++;
        } else {
          matrix[i][j] = 'X';
        }
      }
    }

    System.out.println("Matrix: ");
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        System.out.print(matrix[i][j] + " ");
      }
      System.out.println();
    }

    int index2 = 0;
    String encryptedMessage = "";

    

    for(int i = 0; i<cols; i++){
        int col = (key.charAt(index2) - '0');
        System.out.println("Column: " + col);
        for(int j = 0; j<rows; j++){
            System.out.println("Row - "+j+" Col - "+col+" Val - "+matrix[j][col]);
            encryptedMessage += matrix[j][col];
        }
        index2++;
    }

    System.out.println("Encrypted message: " + encryptedMessage);
  }
}
