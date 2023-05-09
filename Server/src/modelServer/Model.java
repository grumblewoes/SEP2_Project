package modelServer;

import mediator.*;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Model
{
    boolean createUser(String firstName, String lastName, String username, String password, int height, int weight);
    boolean login(String username, String password);
    boolean createFolder(String username, String name);
    boolean removeFolder(String username, int folderId);
    boolean editFolder(String username, int folderId, String newName);
    FolderList getFolderList(String username);

    boolean addExercise(String username, String exerciseName, int folderId, int weight, int repetition);

    boolean removeExercise(int exerciseId);
    boolean removeExercisesByName(String name, int folderId);

    ExerciseList getExerciseList(int folderId);
    ExerciseList getExerciseListByNameAndFolderId(String name, int folderId);

    ArrayList<String> getPossibleExercises();
    int getBestSquat(String username);
    int getBestDeadlift(String username) ;
    int getBestBenchPress(String username);
    User getTrainee(String username);

    boolean updateTrainee(String u, int h, int w,boolean s);
  boolean addCoach(String coachUsername, String coachPassword, String coachName, String coachLName, int coachHeight, int coachWeight, int pbBench,
      int pbSquat, int pbLift, String status, boolean share);
  boolean removeCoach(String name);
  User getCoach(String traineeUsername);
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

    boolean isCoach(String username);
}