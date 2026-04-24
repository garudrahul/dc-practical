import HelloApp.*;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;

class HelloImpl extends HelloPOA {
    public String sayHello() {
        return "Hello from CORBA Server!";
    }
}

public class HelloServer {
    public static void main(String args[]) {
        try {
            ORB orb = ORB.init(args, null);

            POA rootpoa = POAHelper.narrow(
                orb.resolve_initial_references("RootPOA"));
            rootpoa.the_POAManager().activate();

            HelloImpl helloImpl = new HelloImpl();

            org.omg.CORBA.Object ref =
                rootpoa.servant_to_reference(helloImpl);

            Hello href = HelloHelper.narrow(ref);

            org.omg.CORBA.Object objRef =
                orb.resolve_initial_references("NameService");

            NamingContextExt ncRef =
                NamingContextExtHelper.narrow(objRef);

            String name = "Hello";
            NameComponent path[] = ncRef.to_name(name);

            ncRef.rebind(path, href);

            System.out.println("Server ready...");
            orb.run();

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
