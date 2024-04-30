import java.util.*;
import java.io.*;

/**
 * DESKeyGen
 */
public class DESKeyGen {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // System.out.println("Enter the P10: ");
        // String P10 = sc.nextLine();

        // if(P10.length() != 10) {
        //     System.out.println("Invalid P10 length");
        //     System.exit(0);
        // }

        // System.out.println("Enter the P8: ");
        // String P8 = sc.nextLine();

        // if(P8.length() != 8) {
        //     System.out.println("Invalid P8 length");
        //     System.exit(0);
        // }

        // System.out.println("Enter the key: ");
        // String key = sc.nextLine();

        // if(key.length() != 10) {
        //     System.out.println("Invalid Key length");
        //     System.exit(0);
        // }

        String P10="2416390875";
        String P8="52637498";
        String key="1100011110";

        String LSl1="",LSr1="", LSl2="", LSr2="";
        
        for(int i = 0; i<P10.length(); i++) {
            int pos = (P10.charAt(i)) - '0';
            if(i<P10.length()/2){
                LSl1 += key.charAt(pos);
            } else {
                LSr1 += key.charAt(pos);
            }
        }

        LSl1 = LSl1.substring(1)+LSl1.charAt(0);
        LSr1 = LSr1.substring(1)+LSr1.charAt(0);

        String LSl1_copy = LSl1;
        String LSr1_copy = LSr1;

        String K1 = LSl1.concat(LSr1);

        String a="", b="";
        for(int i = 0; i<P8.length(); i++) {
            int pos = (P8.charAt(i)) - '0';
            if(i<P8.length()/2){
                a += K1.charAt(pos);
            } else {
                b += K1.charAt(pos);
            }
        }

        String key1 = a.concat(b);

        LSl2 = LSl1.substring(2)+LSl1.substring(0,2);
        LSr2 = LSr1.substring(2)+LSr1.substring(0,2);


        String K2 = LSl2.concat(LSr2);

        a=""; 
        b="";
        for(int i = 0; i<P8.length(); i++) {
            int pos = (P8.charAt(i)) - '0';
            if(i<P8.length()/2){
                a += K2.charAt(pos);
            } else {
                b += K2.charAt(pos);
            }
        }
         String key2 = a.concat(b);


        System.out.println("LSl1: "+ LSl1);
        System.out.println("LSr1: "+ LSr1);
        System.out.println("K1: "+ K1);
        System.out.println("key1: "+ key1);
        System.out.println("LSl2: "+ LSl2);
        System.out.println("LSr2: "+ LSr2);
        System.out.println("key2: "+ key2);
    }
}