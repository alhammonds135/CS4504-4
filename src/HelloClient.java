import java.io.*;
import java.rmi.*;

// Author: Alaina Hammonds

public class HelloClient implements MyRunnable{
    private HelloInterface myHello;
    public HelloClient() {
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
            myHello = (HelloInterface)Naming.lookup(registryURL);
            System.out.println("Lookup completed ");
        } catch (IOException | NotBoundException e) {
            System.out.println("Exception in HelloClient: " + e);
            e.printStackTrace();
        }
    }
    @Override
    public void run(int counter) {
        try {
            String message = new NumPairFormatter(counter).toString();
            System.out.println("\t"+message+ " ->" + myHello.serverAdd(message));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

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

            System.out.println("Enter [ProcessNumber].[Num1][Num2]");
            String message = br.readLine();
            String newMessage = h.serverAdd(message);

        } catch (IOException | NotBoundException e) {
            System.out.println("Exception in HelloClient: " + e);
            e.printStackTrace();
        }
    }
}