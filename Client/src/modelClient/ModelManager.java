package modelClient;

import mediator.*;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.GeneralListener;
import utility.observer.listener.LocalListener;
import utility.observer.subject.PropertyChangeHandler;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * 
 * 
 * 
 * @author 
 * @version 
 */
public class ModelManager implements Model, LocalListener<String,String>
{
  private Client client;
  //private PropertyChangeSupport property;
	private PropertyChangeHandler<String,String> property;

/**
 * 1-argument constructor 
 * 
 * 
 * @param client 
 *        
 */
	public ModelManager(Client client) {
		this.client = client;
		this.property= new PropertyChangeHandler<>(this);
		this.client.addListener(this);
		//property = new PropertyChangeSupport(this);
		//listens for new info from the server
		//client.addListener(this);
	}

/**
 * 
 * 
 * @param firstName 
 *        
 * @param lastName 
 *        
 * @param userName 
 *        
 * @param password 
 *        
 * @param height 
 *        
 * @param weight 
 *        
 *
 * @return 
 *        
 */
	public boolean createUser(String firstName, String lastName, String userName, String password, int height, int weight) {
		return client.createUser(firstName, lastName, userName, password, height, weight);
	}

/**
 * 
 * 
 * @param username 
 *        
 * @param password 
 *        
 *
 * @return 
 *        
 */
	public boolean login(String username, String password){
		return client.login(username, password);
	}

	@Override
/**
 * 
 * 
 * @param username 
 *        
 * @param name 
 *        
 *
 * @return 
 *        
 */
	public boolean createFolder(String username, String name) {
		return client.createFolder(username, name);
	}

	@Override
/**
 * 
 * 
 * @param username 
 *        
 * @param folderId 
 *        
 *
 * @return 
 *        
 */
	public boolean removeFolder(String username, int folderId) {
		return client.removeFolder(username, folderId);
	}

	@Override
/**
 * 
 * 
 * @param username 
 *        
 * @param folderId 
 *        
 * @param newName 
 *        
 *
 * @return 
 *        
 */
	public boolean editFolder(String username, int folderId, String newName) {
		return client.editFolder(username, folderId, newName);
	}

/**
 * 
 * 
 * @param username 
 *        
 *
 * @return 
 *        
 */
	@Override public FolderList getFolderList(String username)
	{
		return client.getFolderList(username);
	}

	@Override
/**
 * 
 * 
 * @param folderId 
 *        
 *
 * @return 
 *        
 */
	public ExerciseList getExerciseList( int folderId) {
		return client.getExerciseList(folderId);
	}

	@Override
/**
 * 
 * 
 * @param name 
 *        
 * @param folderId 
 *        
 *
 * @return 
 *        
 */
	public ExerciseList getExerciseListByNameAndFolderId(String name, int folderId) {
		return client.getExerciseListByNameAndFolderId(name,folderId);
	}

	@Override
/**
 * 
 * 
 * @param exerciseId 
 *        
 *
 * @return 
 *        
 */
	public boolean removeExercise(int exerciseId) {
		return client.removeExercise(exerciseId);
	}

	@Override
/**
 * 
 * 
 * @param name 
 *        
 * @param folderId 
 *        
 *
 * @return 
 *        
 */
	public boolean removeExercisesByName(String name, int folderId) {
		return client.removeExercisesByName(name,folderId);
	}

	@Override
/**
 * 
 * 
 * @param username 
 *        
 * @param exerciseName 
 *        
 * @param folderId 
 *        
 * @param weight 
 *        
 * @param repetitions 
 *        
 *
 * @return 
 *        
 */
	public boolean addExercise(String username,String exerciseName,int folderId,int weight,int repetitions) {
		return client.addExercise(username, exerciseName, folderId, weight, repetitions);
	}

	@Override
/**
 * 
 * 
 *
 * @return 
 *        
 */
	public ArrayList<String> getPossibleExercises() {
		return client.getPossibleExercises();
	}

//	@Override
/**
 * 
 * 
 * @param username 
 *        
 *
 * @return 
 *        
 */
//	public int getBestBenchPress(String username) {
//		return client.getBestBenchPress(username);
//	}
//
//	@Override
/**
 * 
 * 
 * @param username 
 *        
 *
 * @return 
 *        
 */
//	public int getBestSquat(String username) {
//		return client.getBestSquat(username);
//	}
//
//	@Override
/**
 * 
 * 
 * @param username 
 *        
 *
 * @return 
 *        
 */
//	public int getBestDeadlift(String username) {
//		return client.getBestDeadlift(username);
//	}

	@Override
/**
 * 
 * 
 * @param username 
 *        
 *
 * @return 
 *        
 */
	public User getTrainee(String username){return client.getTrainee(username);}

	@Override
/**
 * 
 * 
 * @param u 
 *        
 * @param h 
 *        
 * @param w 
 *        
 * @param s 
 *        
 * @param st 
 *        
 *
 * @return 
 *        
 */
	public boolean updateTrainee(String u, int h, int w,boolean s,String st){return client.updateTrainee(u,h,w,s,st);}

	@Override
/**
 * 
 * 
 *
 * @return 
 *        
 */
	public boolean acceptFriendRequest(String requester_username, String accepter_username) {
		return client.acceptFriendRequest(requester_username, accepter_username);
	}

	@Override
/**
 * 
 * 
 *
 * @return 
 *        
 */
	public boolean rejectFriendRequest(String requester_username, String accepter_username) {
		return client.rejectFriendRequest(requester_username,accepter_username);
	}

	@Override
/**
 * 
 * 
 * @param username 
 *        
 *
 * @return 
 *        
 */
	public FriendList getFriends(String username) {
		return client.getFriends(username);
	}

	@Override
/**
 * 
 * 
 * @param username 
 *        
 *
 * @return 
 *        
 */
	public ArrayList<String> getFriendRequests(String username) {
		return client.getFriendRequests(username);
	}
	@Override
/**
 * 
 * 
 * @param coach 
 *        
 *
 * @return 
 *        
 */
	public ArrayList<String> getMeetingRequests(String coach) {
		return client.getMeetingRequests(coach); }

	@Override
/**
 * 
 * 
 */
	public void propertyChange(ObserverEvent<String, String> event) {
		property.firePropertyChange(event);
	}



	@Override
/**
 * 
 * 
 *
 * @return 
 *        
 */
	public boolean addListener(GeneralListener<String, String> listener, String... propertyNames) {
		property.addListener(listener,propertyNames);
		return true;
	}

	@Override
/**
 * 
 * 
 *
 * @return 
 *        
 */
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
/**
 * 
 * 
 * @param requesterUsername 
 *        
 * @param accepterUsername 
 *        
 *
 * @return 
 *        
 */
	public boolean requestCoach(String requesterUsername, String accepterUsername) {
		return client.requestCoach(requesterUsername, accepterUsername);
	}

	@Override
/**
 * 
 * 
 * @param traineeUsername 
 *        
 *
 * @return 
 *        
 */
	public User getCoach(String traineeUsername) {
		return client.getCoach(traineeUsername);
	}

	@Override
/**
 * 
 * 
 * @param username 
 *        
 *
 * @return 
 *        
 */
	public boolean isCoach(String username) {
		return client.isCoach(username);
	}

	@Override
/**
 * 
 * 
 * @param traineeUsername 
 *        
 *
 * @return 
 *        
 */
	public boolean removeCoachAssignment(String traineeUsername) {
		return client.removeCoachAssignment(traineeUsername);
	}

  @Override public boolean acceptRequest(String traineeUsername,
      String coachUsername)
  {
    return client.acceptRequest(traineeUsername, coachUsername);
  }
	@Override
/**
 * 
 * 
 * @param username 
 *        
 */
	public void disconnectListener(String username){client.disconnectListener(username);}

/**
 * 
 * 
 * @param traineeUsername 
 *        
 * @param coachName 
 *        
 * @param date 
 *        
 *
 * @return 
 *        
 */
		@Override public boolean removeMeeting(String traineeUsername, String coachName,LocalDate date)
	{
		return client.removeMeeting(traineeUsername, coachName, date);
	}

/**
 * 
 * 
 * @param traineeUsername 
 *        
 *
 * @return 
 *        
 */
	@Override public boolean denyRequest(String traineeUsername)
  {
    return client.denyRequest(traineeUsername);
  }

  /**
   * 
   * 
   * @param traineeUsername 
   *        
   *
   * @return 
   *        
   */
  @Override public boolean removeTraineeFromRoster(String traineeUsername)
  {
    return client.removeTraineeFromRoster(traineeUsername);
  }

/**
 * 
 * 
 * @param username 
 *        
 *
 * @return 
 *        
 */
	@Override public TraineeList getTraineeList(String username)
	{
		return client.getTraineeList(username);
	}

/**
 * 
 * 
 * @param username 
 *        
 *
 * @return 
 *        
 */
	@Override public ArrayList<String> getTraineeRequest(String username)
	{
		return client.getTraineeRequest(username);
	}

	@Override
/**
 * 
 * 
 * @param trainee 
 *        
 * @param coach 
 *        
 * @param date 
 *        
 *
 * @return 
 *        
 */
	public boolean approveMeeting(String trainee, String coach, LocalDate date) {
		return client.approveMeeting(trainee, coach, date);
	}

	@Override
/**
 * 
 * 
 * @param trainee 
 *        
 * @param coach 
 *        
 * @param date 
 *        
 *
 * @return 
 *        
 */
	public boolean denyMeeting(String trainee, String coach, LocalDate date) {
		return client.denyMeeting(trainee, coach, date);
	}

	@Override
/**
 * 
 * 
 * @param coach 
 *        
 *
 * @return 
 *        
 */
	public ArrayList<String> getCoachMeetings(String coach) {
		return client.getCoachMeetings(coach);
	}

	//	@Override
/**
 * 
 * 
 * @param traineeUsername 
 *        
 *
 * @return 
 *        
 */
	@Override public ArrayList<String> getTraineeMeetingList(String traineeUsername)
	{
		return client.getTraineeMeetingList(traineeUsername);
	}

/**
 * 
 * 
 * @param traineeUsername 
 *        
 *
 * @return 
 *        
 */
	@Override public ArrayList<String> getTraineeMeetingRequests(String traineeUsername)
	{
		return client.getTraineeMeetingRequests(traineeUsername);
	}

	@Override public boolean sendMeetingRequest(String traineeUsername,
			String coachUsername, LocalDate dateOfMeeting)
	{
		return client.sendMeetingRequest(traineeUsername,coachUsername,dateOfMeeting);
	}

/**
 * 
 * 
 * @param coachUsername 
 *        
 *
 * @return 
 *        
 */
	@Override public ArrayList<LocalDate> getTakenDates(String coachUsername)
	{
		return client.getTakenDates(coachUsername);
	}

/**
 * 
 * 
 *
 * @return 
 *        
 */
	@Override public TraineeList getSquatLeaders()
	{
		return client.getSquatLeaders();
	}

/**
 * 
 * 
 *
 * @return 
 *        
 */
	@Override public TraineeList getDeadliftLeaders()
	{
		return client.getDeadliftLeaders();
	}

/**
 * 
 * 
 *
 * @return 
 *        
 */
	@Override public TraineeList getBenchLeaders()
	{
		return client.getBenchLeaders();
	}

}


