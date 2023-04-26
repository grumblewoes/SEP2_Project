package modelServer;

import java.rmi.RemoteException;

public interface Model
{
    boolean createUser(String firstName, String lastName, String username, String password, int height, int weight);
    boolean login(String username, String password);
}