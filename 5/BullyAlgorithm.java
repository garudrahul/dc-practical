import java.util.Scanner;

// Process class
class Process {
    int id;
    boolean active;

    Process(int id) {
        this.id = id;
        this.active = true;
    }
}

public class BullyAlgorithm {
    int totalProcesses;
    Process[] processes;
    int coordinator;

    public static void main(String[] args) {
        BullyAlgorithm bully = new BullyAlgorithm();
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter total number of processes: ");
        int n = sc.nextInt();

        bully.initialize(n);
        bully.displayStatus();
        bully.electionDemo(sc);

        sc.close();
    }

    // Initialize processes
    void initialize(int n) {
        totalProcesses = n;
        processes = new Process[n];

        for (int i = 0; i < n; i++) {
            processes[i] = new Process(i + 1);
        }

        coordinator = n; // Highest ID is coordinator
        System.out.println("\n=== Processes initialized (IDs 1 to " + n + ") ===\n");
    }

    // Display current status
    void displayStatus() {
        System.out.println("Current Status:");
        System.out.print("Process: ");
        for (int i = 0; i < totalProcesses; i++) {
            System.out.print("P" + processes[i].id + "\t");
        }

        System.out.println("\nActive:");
        for (int i = 0; i < totalProcesses; i++) {
            System.out.print(processes[i].active ? "YES\t" : "NO\t");
        }

        System.out.println("\nCoordinator: P" + coordinator + "\n");
    }

    // Election process
    void electionDemo(Scanner sc) {
        System.out.print("Enter process to crash (0-" + (totalProcesses - 1) + "): ");
        int failed = sc.nextInt();

        if (failed >= 0 && failed < totalProcesses) {
            processes[failed].active = false;
            System.out.println("Process P" + (failed + 1) + " has FAILED\n");
        }

        System.out.print("Enter process initiating election: ");
        int initiator = sc.nextInt() - 1;

        if (processes[initiator].active) {
            System.out.println("\n=== ELECTION STARTED by P" + (initiator + 1) + " ===\n");
            performElection(initiator);
        } else {
            System.out.println("Initiator is crashed!");
        }

        displayStatus();
    }

    void performElection(int initiator) {
        boolean receivedOK = false;

        System.out.println("P" + (initiator + 1) + " sends ELECTION to higher processes...");

        for (int i = initiator + 1; i < totalProcesses; i++) {
            if (processes[i].active) {
                System.out.println("ELECTION -> P" + (i + 1));

                if (i == findHighestActive()) {
                    System.out.println("P" + (i + 1) + " becomes coordinator!");
                    coordinator = i + 1;
                    announceCoordinator(i);
                    return;
                } else {
                    System.out.println("OK <- P" + (i + 1));
                    receivedOK = true;
                }
            }
        }

        if (!receivedOK) {
            coordinator = initiator + 1;
            System.out.println("P" + (initiator + 1) + " becomes COORDINATOR");
            announceCoordinator(initiator);
        }
    }

    int findHighestActive() {
        for (int i = totalProcesses - 1; i >= 0; i--) {
            if (processes[i].active) return i;
        }
        return -1;
    }

    void announceCoordinator(int winner) {
        System.out.println("\nP" + (winner + 1) + " sends COORDINATOR message to all:");

        for (int i = 0; i < totalProcesses; i++) {
            if (i != winner && processes[i].active) {
                System.out.println("COORDINATOR -> P" + (i + 1));
            }
        }

        System.out.println("=== NEW COORDINATOR: P" + (winner + 1) + " ===\n");
    }
}
