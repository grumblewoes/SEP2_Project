package mediator;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface RemoteModel extends Remote
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

    boolean updateTrainee(String u, int h, int w,boolean s) throws RemoteException;
}
