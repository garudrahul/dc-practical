import java.util.*;

// Class representing a process with a Lamport Logical Clock
class LamportProcess {
    int pid;
    int clock;

    public LamportProcess(int pid) {
        this.pid = pid;
        this.clock = 0; // Initial clock value
    }

    // 1. Internal Event
    public void internalEvent() {
        clock++;
        System.out.println("Process P" + pid + " performs internal event. Clock updated to: " + clock);
    }

    // 2. Send Event
    public int sendEvent() {
        clock++;
        System.out.println("Process P" + pid + " sends message with Timestamp: " + clock);
        return clock;
    }

    // 3. Receive Event
    public void receiveEvent(int receivedTimestamp, int senderPid) {
        int oldClock = clock;
        clock = Math.max(clock, receivedTimestamp) + 1;

        System.out.println("Process P" + pid + " receives message from P" + senderPid +
                " (TS=" + receivedTimestamp + "). Clock updated from " + oldClock + " to " + clock);
    }
}

public class LamportDemo {
    public static void main(String[] args) {

        // Initialize processes
        LamportProcess p1 = new LamportProcess(1);
        LamportProcess p2 = new LamportProcess(2);
        LamportProcess p3 = new LamportProcess(3);

        System.out.println("--- Lamport Logical Clock Simulation ---");

        // Step-by-step execution (same as theory diagram)

        p1.internalEvent(); // 1
        p1.internalEvent(); // 2

        int m1 = p1.sendEvent(); // 3
        p2.receiveEvent(m1, 1);

        p2.internalEvent(); // 5

        int m2 = p2.sendEvent(); // 6
        p3.receiveEvent(m2, 2);

        int m3 = p3.sendEvent(); // 8
        p2.receiveEvent(m3, 3);

        // Final clocks
        System.out.println("\nFinal Logical Clock Values:");
        System.out.println("P1: " + p1.clock);
        System.out.println("P2: " + p2.clock);
        System.out.println("P3: " + p3.clock);
    }
}
