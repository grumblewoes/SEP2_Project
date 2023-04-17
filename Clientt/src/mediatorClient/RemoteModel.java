package mediatorClient;

import java.rmi.Remote;

public interface RemoteModel extends Remote
{

	boolean createUser(String firstName, String lastName, String userName, String password, int height, int weight);

}
