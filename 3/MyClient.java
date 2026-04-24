import java.rmi.*;
import java.rmi.registry.*;
import java.util.Scanner;

public class MyClient {
    public static void main(String args[]) {
        try {
            // Connect to registry
            Registry registry = LocateRegistry.getRegistry("localhost", 5000);

            // Lookup remote object
            Calculator stub = (Calculator) registry.lookup("CalcService");

            Scanner sc = new Scanner(System.in);

            System.out.println("Enter two numbers:");
            int a = sc.nextInt();
            int b = sc.nextInt();

            // Remote calls
            System.out.println("Addition Result: " + stub.add(a, b));
            System.out.println("Subtraction Result: " + stub.sub(a, b));
            System.out.println("Multiplication Result: " + stub.mul(a, b));

        } catch (Exception e) {
            System.out.println("Client exception: " + e);
        }
    }
}
