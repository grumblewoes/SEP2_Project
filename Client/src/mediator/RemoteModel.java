package mediator;

import utility.observer.subject.RemoteSubject;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface RemoteModel extends RemoteSubject<String,String>
{

	boolean createUser(String firstName, String lastName, String userName, String password, int height, int weight) throws
			RemoteException;
	boolean login(String username, String password) throws RemoteException;
	boolean createFolder(String username, String name)  throws RemoteException;
	boolean removeFolder(String username, int folderId)  throws RemoteException;
	boolean editFolder(String username, int folderId, String newName)  throws RemoteException;
	FolderList getFolderList(String username)  throws RemoteException;
	boolean addExercise(String username, String exerciseName, int folderId, int weight, int repetitions) throws RemoteException;
	boolean removeExercise(int exerciseId) throws RemoteException;
	boolean removeExercisesByName(String name, int folderId) throws RemoteException;
	ExerciseList getExerciseList(int folderId) throws RemoteException;
	ExerciseList getExerciseListByNameAndFolderId(String name, int folderId) throws  RemoteException;
	ArrayList<String> getPossibleExercises() throws RemoteException;

	int getBestBenchPress(String username) throws RemoteException;
	int getBestSquat(String username) throws RemoteException;
	int getBestDeadlift(String username) throws RemoteException;

	User getTrainee(String username) throws RemoteException;

	boolean updateTrainee(String u, int h, int w,boolean s,String st) throws RemoteException;

	boolean acceptFriendRequest(String requester_username,String accepter_username)  throws RemoteException;
	boolean rejectFriendRequest(String requester_username,String accepter_username)  throws RemoteException;

	FriendList getFriends(String username) throws RemoteException;
	ArrayList<String> getFriendRequests(String username) throws RemoteException;

	boolean sendFriendRequest(String requesterUsername,
			String accepterUsername) throws RemoteException;
	boolean removeFriend(String requesterUsername,
			String accepterUsername) throws RemoteException;

    boolean requestCoach(String requesterUsername, String accepterUsername) throws RemoteException;

	User getCoach(String traineeUsername) throws RemoteException;

	boolean isCoach(String username) throws RemoteException;
  boolean removeCoachAssignment(String traineeUsername) throws RemoteException;

	boolean acceptRequest(String traineeUsername, String coachUsername) throws RemoteException;
	boolean denyRequest(String traineeUsername) throws RemoteException;
	boolean removeTraineeFromRoster(String traineeUsername) throws RemoteException;
	ArrayList<String> getTraineeList(String username) throws RemoteException;
	ArrayList<String> getTraineeRequest(String username) throws RemoteException;
	boolean removeMeeting(String coachName, LocalDate date) throws RemoteException;
	MeetingList getCoachMeetingList(String coachUsername) throws RemoteException;
}
