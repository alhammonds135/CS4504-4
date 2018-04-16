import java.rmi.*;

// Author: Alaina Hammonds

public interface HelloInterface extends java.rmi.Remote{
    String serverAdd(String name) throws RemoteException;

}