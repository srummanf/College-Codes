import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

public class RSA {
    private static final Random random = new Random();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("===========================================================================================================");
        System.out.println("================================== RSA Encryptor / Decrypter ==============================================");
        System.out.println();

        int p = generateRandomPrime();
        int q = generateRandomPrime();

        System.out.println(" The value of p : " + p);
        System.out.println(" The value of q : " + q);
        System.out.println();

        System.out.println(" - Generating your public / private key-pairs now . . .");
        System.out.println();

        KeyPair keyPair = generateKeyPair(p, q);

        System.out.println(" - Your PUBLIC key is " + keyPair.publicKey);
        System.out.println();
        System.out.println(" - Your PRIVATE key is " + keyPair.privateKey);
        System.out.println();

        System.out.print(" - Enter a message to encrypt with your public key: ");
        String message = scanner.nextLine();
        BigInteger[] encryptedMessage = encrypt(keyPair.publicKey, message);

        System.out.print(" - Your encrypted message is: ");
        for (BigInteger value : encryptedMessage) {
            System.out.print(value + " ");
        }
        System.out.println();
        System.out.println(" - Decrypting message with private key " + keyPair.privateKey + " . . .");
        String decryptedMessage = decrypt(keyPair.privateKey, encryptedMessage);
        System.out.println(" - Your message is: " + decryptedMessage);

        System.out.println();
        System.out.println("============================================ END ==========================================================");
        System.out.println("===========================================================================================================");

        scanner.close();
    }

    private static int generateRandomPrime() {
        while (true) {
            int num = random.nextInt(1000000) + 2; // Adjust the upper bound as needed
            if (isPrime(num)) {
                return num;
            }
        }
    }

    private static boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }
        if (num <= 3) {
            return true;
        }
        if (num % 2 == 0 || num % 3 == 0) {
            return false;
        }

        for (int i = 5; i * i <= num; i += 6) {
            if (num % i == 0 || num % (i + 2) == 0) {
                return false;
            }
        }

        return true;
    }

    private static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private static int multiplicativeInverse(int e, int phi) {
        int d = 0;
        int x1 = 0;
        int x2 = 1;
        int y1 = 1;
        int tempPhi = phi;

        while (e > 0) {
            int temp1 = tempPhi / e;
            int temp2 = tempPhi - temp1 * e;
            tempPhi = e;
            e = temp2;

            int x = x2 - temp1 * x1;
            int y = d - temp1 * y1;

            x2 = x1;
            x1 = x;
            d = y1;
            y1 = y;
        }

        if (tempPhi == 1) {
            return d + phi;
        }

        return -1; // No multiplicative inverse exists
    }

    private static KeyPair generateKeyPair(int p, int q) {
        if (!isPrime(p) || !isPrime(q)) {
            throw new IllegalArgumentException("Both numbers must be prime.");
        }
        if (p == q) {
            throw new IllegalArgumentException("p and q cannot be equal");
        }

        int n = p * q;
        int phi = (p - 1) * (q - 1);

        int e = random.nextInt(phi - 2) + 2; // Choose a random e such that 1 < e < phi
        while (gcd(e, phi) != 1) {
            e = random.nextInt(phi - 2) + 2;
        }

        int d = multiplicativeInverse(e, phi);

        return new KeyPair(new BigInteger(Integer.toString(e)), new BigInteger(Integer.toString(n)),
                new BigInteger(Integer.toString(d)), new BigInteger(Integer.toString(n)));
    }

    private static BigInteger[] encrypt(PublicKey publicKey, String plaintext) {
        BigInteger[] ciphertext = new BigInteger[plaintext.length()];
        for (int i = 0; i < plaintext.length(); i++) {
            ciphertext[i] = new BigInteger(String.valueOf((int) plaintext.charAt(i))).modPow(publicKey.e, publicKey.n);
        }
        return ciphertext;
    }

    private static String decrypt(PrivateKey privateKey, BigInteger[] ciphertext) {
        StringBuilder plaintext = new StringBuilder();
        for (BigInteger value : ciphertext) {
            int charValue = value.modPow(privateKey.d, privateKey.n).intValue();
            plaintext.append((char) charValue);
        }
        return plaintext.toString();
    }

    private static class KeyPair {
        public PublicKey publicKey;
        public PrivateKey privateKey;

        public KeyPair(BigInteger e, BigInteger n, BigInteger d, BigInteger n2) {
            publicKey = new PublicKey(e, n);
            privateKey = new PrivateKey(d, n2);
        }
    }

    private static class PublicKey {
        public BigInteger e;
        public BigInteger n;

        public PublicKey(BigInteger e, BigInteger n) {
            this.e = e;
            this.n = n;
        }

    }

    private static class PrivateKey {
        public BigInteger d;
        public BigInteger n;

        public PrivateKey(BigInteger d, BigInteger n) {
            this.d = d;
            this.n = n;
        }

    }
}