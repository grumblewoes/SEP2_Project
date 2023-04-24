package modelClient;

import mediator.Client;

public class ModelManager implements Model//, PropertyChangeListener
{
	private Client client;
	//private PropertyChangeSupport property;

	public ModelManager(Client client) {
		this.client = client;
		//property = new PropertyChangeSupport(this);
		//listens for new info from the server
		//client.addListener(this);
	}

	public boolean createUser(String firstName, String lastName, String userName, String password, int height, int weight) {
		return client.createUser(firstName, lastName, userName, password, height, weight);
	}

	public boolean login(String username, String password){
		return client.login(username, password);
	}



//	@Override public void addListener(PropertyChangeListener listener)
//	{
//		property.addPropertyChangeListener(listener);
//	}
//
//	@Override public void removeListener(PropertyChangeListener listener)
//	{
//		property.removePropertyChangeListener(listener);
//	}
//
//	@Override public void propertyChange(PropertyChangeEvent evt)
//	{
//		//passes events coming from the server to viewmodel
//		property.firePropertyChange(evt);
//	}
}


