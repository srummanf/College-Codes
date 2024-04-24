import java.util.Scanner;

public class HillCipher {

    static float[][] en = new float[3][1];
    static float[][] de = new float[3][1];
    static float[][] a = new float[3][3];
    static float[][] b = new float[3][3];
    static float[][] msg = new float[3][1];
    static float[][] m = new float[3][3];

    static void getKeyMatrix() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter 3x3 matrix for key (should have inverse):");
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                a[i][j] = scanner.nextFloat();
                m[i][j] = a[i][j];
            }
        System.out.print("\nEnter a string of 3 letters (use A through Z): ");
        String mes = scanner.next();
        for (int i = 0; i < 3; i++)
            msg[i][0] = mes.charAt(i) - 65;
        scanner.close();
    }

    static void encrypt() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 1; j++)
                for (int k = 0; k < 3; k++)
                    en[i][j] += a[i][k] * msg[k][j];
        System.out.print("\nEncrypted string is: ");
        for (int i = 0; i < 3; i++)
            System.out.print((char) (Math.floorMod((int) en[i][0], 26) + 65));
    }

    static void inverseMatrix() {
        float p, q;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                if (i == j)
                    b[i][j] = 1;
                else
                    b[i][j] = 0;
            }
        for (int k = 0; k < 3; k++) {
            for (int i = 0; i < 3; i++) {
                p = m[i][k];
                q = m[k][k];
                for (int j = 0; j < 3; j++) {
                    if (i != k) {
                        m[i][j] = m[i][j] * q - p * m[k][j];
                        b[i][j] = b[i][j] * q - p * b[k][j];
                    }
                }
            }
        }
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                b[i][j] = b[i][j] / m[i][i];
        System.out.println("\n\nInverse Matrix is:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++)
                System.out.print(b[i][j] + " ");
            System.out.println();
        }
    }

    static void decrypt() {
        inverseMatrix();
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 1; j++)
                for (int k = 0; k < 3; k++)
                    de[i][j] += b[i][k] * en[k][j];
        System.out.print("\nDecrypted string is: ");
        for (int i = 0; i < 3; i++)
            System.out.print((char) (Math.floorMod((int) de[i][0], 26) + 65));
        System.out.println();
    }

    public static void main(String[] args) {
        getKeyMatrix();
        encrypt();
        decrypt();
    }
}
