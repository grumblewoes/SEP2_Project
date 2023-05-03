package modelClient;

import mediator.Client;

import java.util.ArrayList;

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

	@Override
	public boolean createFolder(String username, String name) {
		return client.createFolder(username, name);
	}

	@Override
	public boolean removeFolder(String username, String name) {
		return client.removeFolder(username, name);
	}

	@Override
	public boolean editFolder(String username, String oldName, String newName) {
		return client.editFolder(username, oldName, newName);
	}

	@Override public ArrayList<String> getFolderList(String username)
	{
		return client.getFolderList(username);
	}

	@Override
	public boolean editHeight(int height) {
		return client.editHeight(height);
	}

	@Override
	public boolean editWeight(int weight) {
		return client.editWeight(weight);
	}

	@Override
	public boolean editDob(int dob) {
		return client.editDob(dob);
	}

	@Override
	public boolean editDeadlift(int weight) {
		return client.editDeadlift(weight);
	}

	@Override
	public boolean editBenchPress(int weight) {
		return client.editBenchPress(weight);
	}

	@Override
	public boolean editSquat(int weight) {
		return client.editSquat(weight);
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


