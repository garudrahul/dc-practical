import java.rmi.*;
import java.rmi.registry.*;

public class MyServer {
    public static void main(String args[]) {
        try {
            // Create remote object
            Calculator stub = new CalculatorImpl();

            // Start registry on port 5000
            Registry registry = LocateRegistry.createRegistry(5000);

            // Bind object
            registry.rebind("CalcService", stub);

            System.out.println("Server is ready and running...");
        } catch (Exception e) {
            System.out.println("Server failed: " + e);
        }
    }
}
