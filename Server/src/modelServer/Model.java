package modelServer;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Model
{
    boolean createUser(String firstName, String lastName, String username, String password, int height, int weight);
    boolean login(String username, String password);
    boolean createFolder(String username, String name);
    boolean removeFolder(String username, String name);
    boolean editFolder(String username, String oldName, String newName);
    ArrayList<String> getFolderList(String username);
    boolean addExercise(String username, String folderName, int repetitions, int weight);
    boolean removeExercise(String username, String folderName, String exerciseName);
    ArrayList<String> getExercise(String username, String folderName);
    ArrayList<String> getPossibleExercises();
}