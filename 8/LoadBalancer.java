import java.util.ArrayList;
import java.util.List;

// Class representing a Server Node
class ServerNode {
    private String serverId;
    private int currentLoad;
    private int threshold;

    public ServerNode(String serverId, int threshold) {
        this.serverId = serverId;
        this.currentLoad = 0;
        this.threshold = threshold;
    }

    public String getServerId() {
        return serverId;
    }

    public int getCurrentLoad() {
        return currentLoad;
    }

    public int getThreshold() {
        return threshold;
    }

    // Check if server is full
    public boolean isFull() {
        return currentLoad >= threshold;
    }

    // Assign task
    public void assignTask() {
        currentLoad++;
    }
}

// Load Balancer Class
public class LoadBalancer {
    public static void main(String[] args) {

        int numServers = 3;
        int threshold = 10;
        int numTasks = 25;

        List<ServerNode> servers = new ArrayList<>();

        // Create servers
        for (int i = 1; i <= numServers; i++) {
            servers.add(new ServerNode("Server-" + i, threshold));
        }

        System.out.println("--- Starting Task Allocation ---");
        System.out.println("Total Tasks: " + numTasks);
        System.out.println("Threshold: " + threshold + "\n");

        int currentServerIndex = 0;

        // Allocate tasks
        for (int i = 1; i <= numTasks; i++) {

            String taskName = "Task-" + i;

            // Check if current server is full
            if (currentServerIndex < servers.size() &&
                    servers.get(currentServerIndex).isFull()) {

                System.out.println("\n" + servers.get(currentServerIndex).getServerId() +
                        " reached threshold. Switching to next server...\n");

                currentServerIndex++;
            }

            if (currentServerIndex < servers.size()) {

                ServerNode server = servers.get(currentServerIndex);
                server.assignTask();

                System.out.println("Allocated " + taskName + " to " +
                        server.getServerId() +
                        " | Load: " + server.getCurrentLoad() + "/" +
                        server.getThreshold());

            } else {
                System.out.println("All servers overloaded! Cannot allocate " + taskName);
            }
        }

        // Final status
        System.out.println("\n--- Final Server Load ---");

        for (ServerNode s : servers) {
            System.out.println(s.getServerId() +
                    " -> " + s.getCurrentLoad() + "/" + s.getThreshold());
        }
    }
}
