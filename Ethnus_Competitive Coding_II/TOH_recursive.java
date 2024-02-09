import java.util.*;

public class TOH_recursive {

    public static void main(String[] args) {
        int n = 3; // Number of disks
        towerOfHanoi(n, 'A', 'C', 'B');
    }

    public static void towerOfHanoi(int n, char source, char destination, char auxiliary) {
        if (n == 1) {
            System.out.println("Move disk 1 from " + source + " to " +
                    destination);
            return;
        }
        towerOfHanoi(n - 1, source, auxiliary, destination);
        System.out.println("Move disk " + n + " from " + source + " to " +
                destination);
        towerOfHanoi(n - 1, auxiliary, destination, source);
    }
}
