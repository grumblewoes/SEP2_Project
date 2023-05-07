package modelClient;

import mediator.ExerciseList;
import mediator.Folder;
import mediator.FolderList;
import mediator.User;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Model
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

    boolean updateTrainee(String u, int h, int w,boolean s);

//	boolean editHeight(int height);
//	boolean editWeight(int weight);
//	boolean editDob(int dob);
//	boolean editDeadlift(int weight);
//	boolean editBenchPress(int weight);
//	boolean editSquat(int weight);
}
