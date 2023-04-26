package mediator;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteModel extends Remote
{

	boolean createUser(String firstName, String lastName, String userName, String password, int height, int weight) throws
			RemoteException;
	boolean login(String username, String password) throws RemoteException;
	boolean createFolder(String username, String name) throws RemoteException;
	boolean removeFolder(String username, String name) throws RemoteException;
	boolean editFolder(String username, String oldName, String newName) throws RemoteException;

}
