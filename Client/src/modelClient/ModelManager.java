package modelClient;

import mediator.*;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.GeneralListener;
import utility.observer.listener.LocalListener;
import utility.observer.subject.PropertyChangeHandler;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ModelManager implements Model, LocalListener<String,String>
{
  private Client client;
  //private PropertyChangeSupport property;
	private PropertyChangeHandler<String,String> property;

	public ModelManager(Client client) {
		this.client = client;
		this.property= new PropertyChangeHandler<>(this);
		this.client.addListener(this);
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

//	@Override
//	public int getBestBenchPress(String username) {
//		return client.getBestBenchPress(username);
//	}
//
//	@Override
//	public int getBestSquat(String username) {
//		return client.getBestSquat(username);
//	}
//
//	@Override
//	public int getBestDeadlift(String username) {
//		return client.getBestDeadlift(username);
//	}

	@Override
	public User getTrainee(String username){return client.getTrainee(username);}

	@Override
	public boolean updateTrainee(String u, int h, int w,boolean s,String st){return client.updateTrainee(u,h,w,s,st);}

	@Override
	public boolean acceptFriendRequest(String requester_username, String accepter_username) {
		return client.acceptFriendRequest(requester_username, accepter_username);
	}

	@Override
	public boolean rejectFriendRequest(String requester_username, String accepter_username) {
		return client.rejectFriendRequest(requester_username,accepter_username);
	}

	@Override
	public FriendList getFriends(String username) {
		return client.getFriends(username);
	}

	@Override
	public ArrayList<String> getFriendRequests(String username) {
		return client.getFriendRequests(username);
	}
	@Override
	public ArrayList<String> getMeetingRequests(String coach) {
		return client.getMeetingRequests(coach); }

	@Override
	public void propertyChange(ObserverEvent<String, String> event) {
		property.firePropertyChange(event);
	}



	@Override
	public boolean addListener(GeneralListener<String, String> listener, String... propertyNames) {
		property.addListener(listener,propertyNames);
		return true;
	}

	@Override
	public boolean removeListener(GeneralListener<String, String> listener, String... propertyNames) {
		property.addListener(listener,propertyNames);
		return true;
	}


	@Override public boolean sendFriendRequest(String requesterUsername,
			String accepterUsername)
	{
		return client.sendFriendRequest(requesterUsername,accepterUsername);
	}

	@Override public boolean removeFriend(String requesterUsername,
			String accepterUsername)
	{
		return client.removeFriend(requesterUsername,accepterUsername);

	}

	@Override
	public boolean requestCoach(String requesterUsername, String accepterUsername) {
		return client.requestCoach(requesterUsername, accepterUsername);
	}

	@Override
	public User getCoach(String traineeUsername) {
		return client.getCoach(traineeUsername);
	}

	@Override
	public boolean isCoach(String username) {
		return client.isCoach(username);
	}

	@Override
	public boolean removeCoachAssignment(String traineeUsername) {
		return client.removeCoachAssignment(traineeUsername);
	}

  @Override public boolean acceptRequest(String traineeUsername,
      String coachUsername)
  {
    return client.acceptRequest(traineeUsername, coachUsername);
  }
	@Override
	public void disconnectListener(String username){client.disconnectListener(username);}

		@Override public boolean removeMeeting(String traineeUsername, String coachName,LocalDate date)
	{
		return client.removeMeeting(traineeUsername, coachName, date);
	}

	@Override public boolean denyRequest(String traineeUsername)
  {
    return client.denyRequest(traineeUsername);
  }

  @Override public boolean removeTraineeFromRoster(String traineeUsername)
  {
    return client.removeTraineeFromRoster(traineeUsername);
  }

	@Override public TraineeList getTraineeList(String username)
	{
		return client.getTraineeList(username);
	}

	@Override public ArrayList<String> getTraineeRequest(String username)
	{
		return client.getTraineeRequest(username);
	}

	@Override
	public boolean approveMeeting(String trainee, String coach, LocalDate date) {
		return client.approveMeeting(trainee, coach, date);
	}

	@Override
	public boolean denyMeeting(String trainee, String coach, LocalDate date) {
		return client.denyMeeting(trainee, coach, date);
	}

	@Override
	public ArrayList<String> getCoachMeetings(String coach) {
		return client.getCoachMeetings(coach);
	}

	//	@Override
	@Override public ArrayList<String> getTraineeMeetingList(String traineeUsername)
	{
		return client.getTraineeMeetingList(traineeUsername);
	}

	@Override public ArrayList<String> getTraineeMeetingRequests(String traineeUsername)
	{
		return client.getTraineeMeetingRequests(traineeUsername);
	}

	@Override public boolean sendMeetingRequest(String traineeUsername,
			String coachUsername, LocalDate dateOfMeeting)
	{
		return client.sendMeetingRequest(traineeUsername,coachUsername,dateOfMeeting);
	}

	@Override public ArrayList<LocalDate> getTakenDates(String coachUsername)
	{
		return client.getTakenDates(coachUsername);
	}

	@Override public TraineeList getSquatLeaders()
	{
		return client.getSquatLeaders();
	}

	@Override public TraineeList getDeadliftLeaders()
	{
		return client.getDeadliftLeaders();
	}

	@Override public TraineeList getBenchLeaders()
	{
		return client.getBenchLeaders();
	}

}


