import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.net.*;
import java.io.*;

    /*
        This class represents the object server for a distributed
        object of class Hello, which implements the remote interface
        HelloInterface.
        @author M. L. Liu
     */

public class HelloServer {
    public static void main(String[] args) {
        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(is);
        String portNum, registryUrl;
        try {
            System.out.println("Enter the RMIregistry port number: ");
            portNum = (br.readLine().trim());
            int RMIPortNum = Integer.parseInt(portNum);
            startRegistry(RMIPortNum);
            HelloImpl exportedObj = new HelloImpl();
            registryUrl = "rmi://localhost:" + portNum + "/hello";
            Naming.rebind(registryUrl, exportedObj);
            System.out.println("Server registered. Registry currently contains:");
            listRegistry(registryUrl);
            System.out.println("Hello Server ready.");
        } catch (IOException e) {
            System.out.println("Exception in HelloServer.main: " + e);
        }
    }

    private static void startRegistry(int rmiPortNum) throws RemoteException {
        try {
            Registry registry = LocateRegistry.getRegistry(rmiPortNum);
            registry.list(); // Will throw an exception if registry does not already exist
        } catch (RemoteException e) {
            System.out.println("RMI registry cannot be located at port " + rmiPortNum);
            Registry registry = LocateRegistry.createRegistry(rmiPortNum);
            System.out.println("RMI registry created at port " + rmiPortNum);
        }
    }

    private static void listRegistry(String registryUrl) throws RemoteException, MalformedURLException {
        System.out.println("Registry " + registryUrl + " contains: ");
        String [] names = Naming.list(registryUrl);
        for(int i = 0; i < names.length; i++) {
            System.out.println(names[i]);
        }
    }
}