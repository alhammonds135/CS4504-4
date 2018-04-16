import java.rmi.*;

    /*
        This is a remote interface
        @author M. L. Liu
     */

public interface HelloInterface extends java.rmi.Remote{
    String serverAdd(String name) throws RemoteException;

}