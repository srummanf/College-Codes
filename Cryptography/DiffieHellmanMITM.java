import java.util.Random;
import java.util.Scanner;


class DiffieHellmanMITM {
    static int p, g;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Generate a prime number
        p = generatePrimeNumber();
        System.out.println("Generated prime number: " + p);

        System.out.print("Enter a number: ");
        g = scanner.nextInt();

        A alice = new A();
        A bob = new A();
        B eve = new B();

        // Printing out the private selected number by Alice and Bob
        System.out.println("Alice selected (a) : " + alice.n);
        System.out.println("Bob selected (b) : " + bob.n);
        System.out.println("Eve selected private number for Alice (c) : " + eve.a);
        System.out.println("Eve selected private number for Bob (d) : " + eve.b);

        // Generating public values
        int ga = alice.publish();
        int gb = bob.publish();
        int gea = eve.publish(0);
        int geb = eve.publish(1);
        System.out.println("Alice published (ga): " + ga);
        System.out.println("Bob published (gb): " + gb);
        System.out.println("Eve published value for Alice (gc): " + gea);
        System.out.println("Eve published value for Bob (gd): " + geb);

        // Computing the secret key
        int sa = alice.computeSecret(gea);
        int sea = eve.computeSecret(ga, 0);
        int sb = bob.computeSecret(geb);
        int seb = eve.computeSecret(gb, 1);
        System.out.println("Alice computed (S1) : " + sa);
        System.out.println("Eve computed key for Alice (S1) : " + sea);
        System.out.println("Bob computed (S2) : " + sb);
        System.out.println("Eve computed key for Bob (S2) : " + seb);
    }

    static class A {
        int n;

        A() {
            // Generating a random private number selected by Alice
            Random random = new Random();
            n = random.nextInt(p - 1) + 1;
        }

        // generating public values
        int publish() {
            return (int) (Math.pow(g, n) % p);
        }

        // computing secret key
        int computeSecret(int gb) {
            return (int) (Math.pow(gb, n) % p);
        }
    }

    static class B {
        int a, b;

        B() {
            // Generating a random private number selected for alice
            // Generating a random private number selected for bob
            Random random = new Random();
            a = random.nextInt(p - 1) + 1;
            b = random.nextInt(p - 1) + 1;
        }

        // generating public values
        int publish(int i) {
            return (int) (Math.pow(g, (i == 0 ? a : b)) % p);
        }

        // computing secret key
        int computeSecret(int ga, int i) {
            return (int) (Math.pow(ga, (i == 0 ? a : b)) % p);
        }
    }

    // Method to generate a prime number
    public static int generatePrimeNumber() {
        Random rand = new Random();
        int num;
        do {
            num = rand.nextInt(1000) + 100; // Generate a random number between 100 and 1000
        } while (!isPrime(num));
        return num;
    }

    // Method to check if a number is prime
    public static boolean isPrime(int num) {
        if (num == 2) {
            return true;
        }
        if (num < 2 || num % 2 == 0) {
            return false;
        }
        for (int n = 3; n <= Math.sqrt(num); n += 2) {
            if (num % n == 0) {
                return false;
            }
        }
        return true;
    }
}
