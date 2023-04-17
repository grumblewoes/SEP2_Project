package mediatorClient;

.String;

public class Client implements Model, RemoteListener
{

	private RemoteModel remoteModel;

	public Client() {

	}


	/**
	 * @see Model#createUser(String, String, String, String, int, int)
	 *  
	 */
	public boolean createUser(String firstName, String lastName, String userName, String password, int height, int weight) {
		return false;
	}


	/**
	 * @see RemoteListener#propertyChange()
	 */
	public void propertyChange(ObserverEvent<S, T> event) {

	}

}
