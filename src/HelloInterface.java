import java.rmi.*;

    /*
        This is a remote interface
        @author M. L. Liu
     */

public interface HelloInterface {
    String sayHello(String name) throws RemoteException;

}
