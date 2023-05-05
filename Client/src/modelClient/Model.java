package modelClient;

import java.util.ArrayList;

public interface Model
{

	public abstract boolean createUser(String firstName, String lastName, String userName, String password, int height, int weight);
	public boolean login(String username, String password);
	boolean createFolder(String username, String name);
	boolean removeFolder(String username, String name);
	boolean editFolder(String username, String oldName, String newName);
	ArrayList<String> getFolderList(String username);
	boolean editHeight(int height);
	boolean editWeight(int weight);
	boolean editDob(int dob);
	boolean editDeadlift(int weight);
	boolean editBenchPress(int weight);
	boolean editSquat(int weight);

	boolean addExercise(String username, String folderName, String exerciseName, int repetitions, int weight);
	boolean removeExercise(String username, String folderName, String exerciseName);
	ArrayList<String> getExerciseList(String username, String folderName);
	ArrayList<String> getPossibleExercises();
}
