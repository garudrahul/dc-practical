import java.util.Scanner;

public class TokenRing {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        int[] request = new int[n];

        System.out.println("\nEnter request status for each process (1 = wants CS, 0 = does not want CS):");

        for (int i = 0; i < n; i++) {
            System.out.print("Process " + (i + 1) + ": ");
            request[i] = sc.nextInt();
        }

        System.out.print("\nEnter initial token holder process: ");
        int token = sc.nextInt();

        int rounds = n * 2; // simulate multiple cycles

        System.out.println("\n----- Token Ring Execution -----");

        for (int i = 0; i < rounds; i++) {

            System.out.println("\nToken arrived at Process " + token);

            if (request[token - 1] == 1) {
                System.out.println("Process " + token + " is requesting Critical Section");
                System.out.println("Process " + token + " ENTERS Critical Section");
                System.out.println("Process " + token + " EXITS Critical Section");

                request[token - 1] = 0;
            } else {
                System.out.println("Process " + token + " does not need Critical Section");
            }

            int next = (token % n) + 1;
            System.out.println("Token passed from Process " + token + " to Process " + next);

            token = next;
        }

        System.out.println("\nExecution completed.");
        sc.close();
    }
}
