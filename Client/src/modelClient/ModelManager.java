package modelClient;

import mediator.*;

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
	public boolean removeFolder(String username, int folderId) {
		return client.removeFolder(username, folderId);
	}

	@Override
	public boolean editFolder(String username, int folderId, String newName) {
		return client.editFolder(username, folderId, newName);
	}

	@Override public FolderList getFolderList(String username)
	{
		return client.getFolderList(username);
	}

	@Override
	public ExerciseList getExerciseList( int folderId) {
		return client.getExerciseList(folderId);
	}

	@Override
	public ExerciseList getExerciseListByNameAndFolderId(String name, int folderId) {
		return client.getExerciseListByNameAndFolderId(name,folderId);
	}

	@Override
	public boolean removeExercise(int exerciseId) {
		return client.removeExercise(exerciseId);
	}

	@Override
	public boolean removeExercisesByName(String name, int folderId) {
		return client.removeExercisesByName(name,folderId);
	}

	@Override
	public boolean addExercise(String username,String exerciseName,int folderId,int weight,int repetitions) {
		return client.addExercise(username, exerciseName, folderId, weight, repetitions);
	}

	@Override
	public ArrayList<String> getPossibleExercises() {
		return client.getPossibleExercises();
	}

	@Override
	public int getBestBenchPress(String username) {
		return client.getBestBenchPress(username);
	}

	@Override
	public int getBestSquat(String username) {
		return client.getBestSquat(username);
	}

	@Override
	public int getBestDeadlift(String username) {
		return client.getBestDeadlift(username);
	}

	@Override
	public User getTrainee(String username){return client.getTrainee(username);}

	@Override
	public boolean updateTrainee(String u, int h, int w,boolean s){return client.updateTrainee(u,h,w,s);}

	@Override public boolean isRosterUpdated()
	{
		return client.isRosterUpdated();
	}


	//	@Override
//	public boolean editHeight(int height) {
//		return client.editHeight(height);
//	}
//
//	@Override
//	public boolean editWeight(int weight) {
//		return client.editWeight(weight);
//	}
//
//	@Override
//	public boolean editDob(int dob) {
//		return client.editDob(dob);
//	}
//
//	@Override
//	public boolean editDeadlift(int weight) {
//		return client.editDeadlift(weight);
//	}
//
//	@Override
//	public boolean editBenchPress(int weight) {
//		return client.editBenchPress(weight);
//	}
//
//	@Override
//	public boolean editSquat(int weight) {
//		return client.editSquat(weight);
//	}

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


