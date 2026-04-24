import java.util.*;

public class ChandyMisraHaas {

    static int n;
    static int[][] graph;
    static boolean[] visited;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        n = sc.nextInt();

        graph = new int[n][n];

        System.out.println("Enter dependency matrix (1 if Pi depends on Pj):");

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = sc.nextInt();
            }
        }

        System.out.print("Enter initiator process: ");
        int initiator = sc.nextInt() - 1;

        visited = new boolean[n];

        System.out.println("\n--- Probe Execution ---");

        if (detectDeadlock(initiator, initiator)) {
            System.out.println("\nDeadlock detected involving Process " + (initiator + 1));
        } else {
            System.out.println("\nNo Deadlock detected");
        }

        sc.close();
    }

    static boolean detectDeadlock(int initiator, int current) {

        visited[current] = true;

        for (int i = 0; i < n; i++) {

            if (graph[current][i] == 1) {

                System.out.println("Probe sent: (" + (initiator + 1) + ", " +
                        (current + 1) + ", " + (i + 1) + ")");

                if (i == initiator) {
                    return true; // cycle found
                }

                if (!visited[i]) {
                    if (detectDeadlock(initiator, i))
                        return true;
                }
            }
        }

        return false;
    }
}
