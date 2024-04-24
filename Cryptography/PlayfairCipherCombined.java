import java.util.Scanner;

public class PlayfairCipherCombined {

    // Function to convert string to lowercase
    public static void toLowerCase(char[] plain, int ps) {
        for (int i = 0; i < ps; i++) {
            if (plain[i] > 64 && plain[i] < 91)
                plain[i] += 32;
        }
    }

    // Function to remove spaces from a string
    public static int removeSpaces(char[] plain, int ps) {
        int count = 0;
        for (int i = 0; i < ps; i++) {
            if (plain[i] != ' ')
                plain[count++] = plain[i];
        }
        return count;
    }

    // Function to generate the Playfair Cipher matrix
    public static void generateKeyTable(char[] key, int ks, char[][] keyT) {
        int[] dicty = new int[26];
        int i = 0, j = 0;

        for (int k = 0; k < ks; k++) {
            if (key[k] != 'j')
                dicty[key[k] - 97] = 2;
        }
        dicty['j' - 97] = 1;

        for (int k = 0; k < ks; k++) {
            if (dicty[key[k] - 97] == 2) {
                dicty[key[k] - 97] -= 1;
                keyT[i][j] = key[k];
                j++;
                if (j == 5) {
                    i++;
                    j = 0;
                }
            }
        }

        for (int k = 0; k < 26; k++) {
            if (dicty[k] == 0) {
                keyT[i][j] = (char) (k + 97);
                j++;
                if (j == 5) {
                    i++;
                    j = 0;
                }
            }
        }

        // Print the matrix
        System.out.println("Playfair Cipher Matrix:");
        for (i = 0; i < 5; i++) {
            for (j = 0; j < 5; j++) {
                System.out.print(keyT[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Function to search for characters in the Playfair Cipher matrix
    public static void search(char[][] keyT, char a, char b, int[] arr) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (keyT[i][j] == a) {
                    arr[0] = i;
                    arr[1] = j;
                } else if (keyT[i][j] == b) {
                    arr[2] = i;
                    arr[3] = j;
                }
            }
        }
    }

    // Function to handle negative modulo for Playfair Cipher
    public static int mod5(int a) {
        if (a < 0)
            a += 5;
        return (a % 5);
    }

    // Function to encrypt using Playfair Cipher
    public static void encrypt(char[] str, char[][] keyT, int ps) {
        int[] a = new int[4];
        for (int i = 0; i < ps; i += 2) {
            search(keyT, str[i], str[i + 1], a);
            if (a[0] == a[2]) {
                str[i] = keyT[a[0]][mod5(a[1] + 1)];
                str[i + 1] = keyT[a[0]][mod5(a[3] + 1)];
            } else if (a[1] == a[3]) {
                str[i] = keyT[mod5(a[0] + 1)][a[1]];
                str[i + 1] = keyT[mod5(a[2] + 1)][a[1]];
            } else {
                str[i] = keyT[a[0]][a[3]];
                str[i + 1] = keyT[a[2]][a[1]];
            }
        }
    }

    // Function to decrypt using Playfair Cipher
    public static void decrypt(char[] str, char[][] keyT, int ps) {
        int[] a = new int[4];
        for (int i = 0; i < ps; i += 2) {
            search(keyT, str[i], str[i + 1], a);
            if (a[0] == a[2]) {
                str[i] = keyT[a[0]][mod5(a[1] - 1)];
                str[i + 1] = keyT[a[0]][mod5(a[3] - 1)];
            } else if (a[1] == a[3]) {
                str[i] = keyT[mod5(a[0] - 1)][a[1]];
                str[i + 1] = keyT[mod5(a[2] - 1)][a[1]];
            } else {
                str[i] = keyT[a[0]][a[3]];
                str[i + 1] = keyT[a[2]][a[1]];
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        char[] str = new char[SIZE];
        char[] key = new char[SIZE];

        System.out.print("Enter the key text: ");
        String keyString = scanner.nextLine();
        key = keyString.toCharArray();

        System.out.print("Enter the plaintext/ciphertext: ");
        String strString = scanner.nextLine();
        str = strString.toCharArray();

        int ks = removeSpaces(key, key.length);
        toLowerCase(key, ks);

        int ps = removeSpaces(str, str.length);
        toLowerCase(str, ps);

        char[][] keyT = new char[5][5];
        generateKeyTable(key, ks, keyT);

        // Encrypt the message
        encrypt(str, keyT, ps);
        System.out.println("Encrypted Text: " + String.valueOf(str));

        // Decrypt the message
        decrypt(str, keyT, ps);
        System.out.println("Decrypted Text: " + String.valueOf(str));

        scanner.close();
    }

    // Constants
    private static final int SIZE = 30;
}
