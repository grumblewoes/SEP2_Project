package modelClient;

import mediator.*;
import utility.observer.subject.LocalSubject;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface Model extends LocalSubject<String,String>
{

	public abstract boolean createUser(String firstName, String lastName, String userName, String password, int height, int weight);
	public boolean login(String username, String password);
	boolean createFolder(String username, String name);
	boolean removeFolder(String username, int folderId);
	boolean editFolder(String username, int folderId, String newName);
	FolderList getFolderList(String username);

	ExerciseList getExerciseList( int folderId);
	ExerciseList getExerciseListByNameAndFolderId( String name,int folderId);

	boolean removeExercise(int exerciseId);
	boolean removeExercisesByName(String name, int folderId);

	boolean addExercise(String username,String exerciseName,int folderId,int weight,int repetitions);

	ArrayList<String> getPossibleExercises();

	int getBestBenchPress(String username) ;
	int getBestSquat(String username) ;
	int getBestDeadlift(String username) ;

	User getTrainee(String username);

    boolean updateTrainee(String u, int h, int w,boolean s,String st);

	boolean acceptFriendRequest(String requester_username,String accepter_username)  ;
	boolean rejectFriendRequest(String requester_username,String accepter_username)  ;

	FriendList getFriends(String username) ;
	ArrayList<String> getFriendRequests(String username) ;
	boolean sendFriendRequest(String requesterUsername,
			String accepterUsername);
	boolean removeFriend(String requesterUsername,
			String accepterUsername);
	boolean requestCoach(String requesterUsername, String accepterUsername);
	User getCoach(String traineeUsername);
	boolean isCoach(String username);
  	boolean removeCoachAssignment(String traineeUsername);

	boolean acceptRequest(String traineeUsername, String coachUsername);
	boolean denyRequest(String traineeUsername);
	boolean removeTraineeFromRoster(String traineeUsername);

	TraineeList getTraineeList(String username);
	ArrayList<String> getTraineeRequest(String username);
	ArrayList<String> getMeetingRequests(String coach);
	boolean approveMeeting(String trainee, String coach, LocalDate date);
	boolean denyMeeting(String trainee, String coach, LocalDate date);

	ArrayList<String> getCoachMeetings(String coach);
	void disconnectListener(String username);

	boolean removeMeeting(String traineeUsername, String coachUsername, LocalDate dateOfMeeting);


	ArrayList<String> getTraineeMeetingList(String traineeUsername);
	ArrayList<String> getTraineeMeetingRequests(String traineeUsername);
	boolean sendMeetingRequest(String traineeUsername, String coachUsername, LocalDate dateOfMeeting);
	ArrayList<LocalDate> getTakenDates(String coachUsername);

}
