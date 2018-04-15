import java.io.*;
import java.rmi.*;

/*
    This class represents the object client for a distributed
    object of class Hello, which implements the remote interface
    HelloInterface.
    @author M. L. Liu
 */

public class HelloClient {
    public static void main(String[] args) {
        try {
            int RMIPort;
            String hostName;
            InputStreamReader is = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(is);
            System.out.println("Enter the RMIRegistry host name: ");
            hostName = br.readLine();
            System.out.println("Enter the RMIRegistry port number: ");
            String portNum = br.readLine();
            RMIPort = Integer.parseInt(portNum);
            String registryURL = "rmi://" + hostName + ":" + RMIPort + "/hello";
            HelloInterface h = (HelloInterface)Naming.lookup(registryURL);
            System.out.println("Lookup completed ");
            String message = h.sayHello("Donald Duck");
            System.out.println("HelloClient: " + message);
        } catch (IOException | NotBoundException e) {
            System.out.println("Exception in HelloClient: " + e);
            e.printStackTrace();
        }
    }
}
