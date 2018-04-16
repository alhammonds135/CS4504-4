import java.rmi.*;
import java.rmi.server.*;

// Author: Alaina Hammonds

public class HelloImpl extends UnicastRemoteObject implements HelloInterface{
    public HelloImpl() throws RemoteException {
        super();
    }

    public String serverAdd(String message) throws RemoteException {
        String [] splitStr = message.split("\\.");
        int processNum = Integer.parseInt(splitStr[0]);
        String [] splitAgain = splitStr[1].split(",");

        int num1 = Integer.parseInt(splitAgain[0]);
        int num2 = Integer.parseInt(splitAgain[1]);

        // Converts message to uppercase
        return processNum + "." + Integer.toString(num1 + num2);
    }
}