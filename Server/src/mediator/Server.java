package mediator;

import modelServer.Model;
import util.Logger;
import utility.observer.listener.GeneralListener;
import utility.observer.subject.PropertyChangeHandler;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * The Server class represents the server-side implementation of the remote model.
 * It provides methods to interact with the model and handle remote method calls.
 *
 * @author Damian Trafiałek
 * @version 1.0
 */
public class Server implements RemoteModel
{
  private Model model;
  private PropertyChangeHandler<String, String> property;

  /**
   * 1-argument constructor creates a Server object with the specified model.
   *
   * @param model the model to be used by the server
   */
  public Server(Model model)
  {
    this.model = model;
    this.property = new PropertyChangeHandler<>(this);
    try
    {
      startRegistry();
    }
    catch (Exception e)
    {
      throw new RuntimeException(e);
    }
    startServer();
  }

  /**
   * Creates a new user with the specified details.
   *
   * @param firstName the first name of the user
   * @param lastName  the last name of the user
   * @param userName  the username of the user
   * @param password  the password of the user
   * @param height    the height of the user
   * @param weight    the weight of the user
   * @return true if the user was created successfully, false otherwise
   */
  public boolean createUser(String firstName, String lastName, String userName,
      String password, int height, int weight)
  {
    return model.createUser(firstName, lastName, userName, password, height,
        weight);
  }

  /**
   * Logs in a user with the specified username and password.
   *
   * @param username the username of the user
   * @param password the password of the user
   * @return true if the login was successful, false otherwise
   */
  public boolean login(String username, String password)
  {
    return model.login(username, password);

  }

  /**
   * Creates a new folder for the given user.
   *
   * @param username the username of the user
   * @param name     the name of the folder to be created
   * @return true if the folder is created successfully, false otherwise
   */
  @Override public boolean createFolder(String username, String name)
  {
    return model.createFolder(username, name);
  }

  /**
   * Removes the folder with the specified folder Id for the given user.
   *
   * @param username the username of the user
   * @param folderId the ID of the folder to be removed
   * @return true if the folder is removed successfully, false otherwise
   */
  @Override public boolean removeFolder(String username, int folderId)
  {
    return model.removeFolder(username, folderId);
  }

  /**
   * Renames the folder with the specified folder Id for the given user.
   *
   * @param username the username of the user
   * @param folderId the ID of the folder to be renamed
   * @param newName  the new name for the folder
   * @return true if the folder is renamed successfully, false otherwise
   */

  @Override public boolean editFolder(String username, int folderId,
      String newName)
  {
    return model.editFolder(username, folderId, newName);
  }

  /**
   * Retrieves the list of folders for the given user.
   *
   * @param username the username of the user
   * @return the list of folders for the user, or null if SQLException is caught
   */
  @Override public FolderList getFolderList(String username)
  {
    return model.getFolderList(username);
  }

  /**
   * Adds an exercise to the specified folder for the given user.
   *
   * @param username     the username of the user
   * @param exerciseName the name of the exercise to be added
   * @param folderId     the ID of the folder to which the exercise should be added
   * @param weight       the weight of the exercise
   * @param repetitions  the number of repetitions for the exercise
   * @return true if the exercise is added successfully, false otherwise
   * @throws RemoteException if a remote exception occurs during the method call
   */

  @Override public boolean addExercise(String username, String exerciseName,
      int folderId, int weight, int repetitions) throws RemoteException
  {
    return model.addExercise(username, exerciseName, folderId, weight,
        repetitions);
  }

  /**
   * Removes the exercise with the specified exercise Id.
   *
   * @param exerciseId the ID of the exercise to be removed
   * @return true if the exercise is removed successfully, false otherwise
   * @throws RemoteException if a remote exception occurs during the method call
   */
  @Override public boolean removeExercise(int exerciseId) throws RemoteException
  {
    return model.removeExercise(exerciseId);
  }

  @Override
  /**
   * Removes exercises with the specified name from the given folder.
   *
   * @param name     the name of the exercises to be removed
   * @param folderId the ID of the folder from which the exercises should be removed
   * @return true if the exercises are removed successfully, false otherwise
   * @throws RemoteException if a remote exception occurs during the method call
   *
   */ public boolean removeExercisesByName(String name, int folderId)
      throws RemoteException
  {
    return model.removeExercisesByName(name, folderId);
  }

  /**
   * Retrieves the list of exercises for the specified folder.
   *
   * @param folderId the ID of the folder
   * @return the list of exercises for the folder, or null if an error occurs
   * @throws RemoteException if a remote exception occurs during the method call
   */
  @Override public ExerciseList getExerciseList(int folderId)
      throws RemoteException
  {
    return model.getExerciseList(folderId);
  }

  @Override
  /**
   * Retrieves an ExerciseList by name and folder ID.
   *
   * @param name       the name of the exercise list
   * @param folderId   the ID of the folder
   * @return the ExerciseList matching the specified name and folder ID,
   *
   */

  public ExerciseList getExerciseListByNameAndFolderId(String name,
      int folderId)
  {
    return model.getExerciseListByNameAndFolderId(name, folderId);
  }

  @Override
  /**
   * Retrieves a list of possible exercises.
   *
   * @return an ArrayList of possible exercises
   *
   */ public ArrayList<String> getPossibleExercises()
  {
    return model.getPossibleExercises();
  }

  @Override
  /**
   * Retrieves a trainee by username.
   *
   * @param username   the username of the trainee
   * @return the User object representing the trainee
   * @throws RemoteException if a remote exception occurs during the method call
   */ public User getTrainee(String username) throws RemoteException
  {
    return model.getTrainee(username);
  }

  @Override
  /**
   * Updates the trainee with the specified details.
   *
   * @param u          the username of the trainee
   * @param h          the height of the trainee
   * @param w          the weight of the trainee
   * @param s          true if the user's profile is shared, false otherwise
   * @param st         the status text of the trainee
   * @return true if the trainee is updated successfully
   * @throws RemoteException if a remote exception occurs during the method call
   */ public boolean updateTrainee(String u, int h, int w, boolean s, String st)
      throws RemoteException
  {
    boolean result = model.updateTrainee(u, h, w, s, st);
    if (!result)
    {
      Logger.log("firing property to : " + u);
      property.firePropertyChange(u, null, "Failed to update profile.");
    }
    else
      property.firePropertyChange(u, null, "Successfully updated profile.");
    return result;
  }

  /**
   * Sends a friend request from the requester to the accepter.
   *
   * @param requesterUsername the username of the requester
   * @param accepterUsername  the username of the accepter
   * @return true if the friend request is sent successfully
   * @throws RemoteException if a remote exception occurs during the method call
   */

  @Override public boolean sendFriendRequest(String requesterUsername,
      String accepterUsername) throws RemoteException
  {
    return model.sendFriendRequest(requesterUsername, accepterUsername);
  }

  /**
   * Removes a friend relationship between two users.
   *
   * @param requesterUsername the username of the requester
   * @param accepterUsername  the username of the accepter
   * @return true if the friend relationship is removed successfully
   * @throws RemoteException if a remote exception occurs during the method call
   */
  @Override public boolean removeFriend(String requesterUsername,
      String accepterUsername) throws RemoteException
  {
    return model.removeFriend(requesterUsername, accepterUsername);
  }

  @Override
  /**
   * Sends a coach request from the requester to the accepter.
   *
   * @param requesterUsername   the username of the requester
   * @param accepterUsername    the username of the accepter
   * @return true if the coach request is sent successfully
   * @throws RemoteException if a remote exception occurs during the method call
   */ public boolean requestCoach(String requesterUsername,
      String accepterUsername) throws RemoteException
  {
    return model.requestCoach(requesterUsername, accepterUsername);
  }

  @Override
  /**
   * Retrieves the coach associated with a trainee.
   *
   * @param traineeUsername the username of the trainee
   * @return the coach associated with the trainee, or null if not found
   * @throws RemoteException if a remote exception occurs during the method call
   */ public User getCoach(String traineeUsername) throws RemoteException
  {
    return model.getCoach(traineeUsername);
  }

  @Override
  /**
   * Checks if a user is a coach.
   *
   * @param username   the username of the user
   * @return true if the user is a coach, false otherwise or if an SQLException occurs
   * @throws RemoteException if a remote exception occurs during the method call
   */ public boolean isCoach(String username) throws RemoteException
  {
    return model.isCoach(username);
  }

  /**
   * Removes the coach for a trainee.
   *
   * @param traineeUsername the username of the trainee
   * @return true if the coach assignment is successfully removed, false otherwise
   * @throws RemoteException if a remote exception occurs during the method call
   */
  @Override public boolean removeCoachAssignment(String traineeUsername)
      throws RemoteException
  {
    return model.removeCoachAssignment(traineeUsername);
  }

  /**
   * Accepts a coach request from a trainee.
   *
   * @param traineeUsername the username of the trainee
   * @param coachUsername   the username of the coach
   * @return true if the coach request is successfully accepted, false otherwise
   * @throws RemoteException if a remote exception occurs during the method call
   */
  @Override public boolean acceptRequest(String traineeUsername,
      String coachUsername) throws RemoteException
  {
    return model.acceptRequest(traineeUsername, coachUsername);
  }

  /**
   * Denies a coach request from a trainee.
   *
   * @param traineeUsername the username of the trainee
   * @return true if the coach request is successfully denied, false otherwise
   * @throws RemoteException if a remote exception occurs during the method call
   */
  @Override public boolean denyRequest(String traineeUsername)
      throws RemoteException
  {
    return model.denyRequest(traineeUsername);
  }

  /**
   * Removes a trainee from the coach's roster.
   *
   * @param traineeUsername the username of the trainee
   * @return true if the trainee is successfully removed, false otherwise
   * @throws RemoteException if a remote exception occurs during the method call
   */
  @Override public boolean removeTraineeFromRoster(String traineeUsername)
      throws RemoteException
  {
    return model.removeTraineeFromRoster(traineeUsername);
  }

  /**
   * Retrieves the list of trainees associated with a coach.
   *
   * @param username the username of the coach
   * @return the list of trainees associated with the coach
   * @throws RemoteException if a remote exception occurs during the method call
   */
  @Override public TraineeList getTraineeList(String username)
      throws RemoteException
  {
    return model.getTraineeList(username);
  }

  /**
   * Retrieves the list of all trainee requests received by a coach.
   *
   * @param username the username of the coach
   * @return the list of trainee requests received by the coach
   * @throws RemoteException if a remote exception occurs during the method call
   */
  @Override public ArrayList<String> getTraineeRequest(String username)
      throws RemoteException
  {
    return model.getTraineeRequest(username);
  }

  /**
   * Removes an existing meeting between a trainee and a coach for a specified date.
   *
   * @param traineeUsername the username of the trainee
   * @param coachName       the username of the coach
   * @param date            the date of the meeting
   * @return true if the meeting removal was approved successfully, false otherwise
   * @throws RemoteException if a remote exception occurs during the method call
   */
  @Override public boolean removeMeeting(String traineeUsername,
      String coachName, LocalDate date) throws RemoteException
  {
    return model.removeMeeting(traineeUsername, coachName, date);
  }

  /**
   * Retrieves a list of taken dates for a coach.
   *
   * @param coachUsername the username of the coach
   * @return the list of taken dates for the coach, or null if an error occurs
   * @throws RemoteException if a remote exception occurs during the method call
   */
  @Override public ArrayList<LocalDate> getTakenDates(String coachUsername)
      throws RemoteException
  {
    Logger.log("quering taken dates...");
    return model.getTakenDates(coachUsername);
  }

  /**
   * Retrieves the leaderboard of trainees based on their squat performance.
   *
   * @return the leaderboard of trainees based on squat performance, or null if an error occurs
   * @throws RemoteException if a remote exception occurs during the method call
   */
  @Override public TraineeList getSquatLeaders() throws RemoteException
  {
    return model.getSquatLeaders();
  }

  /**
   * Retrieves the leaderboard of trainees based on their deadlift performance.
   *
   * @return the leaderboard of trainees based on deadlift performance, or null if an error occurs
   * @throws RemoteException if a remote exception occurs during the method call
   */
  @Override public TraineeList getDeadliftLeaders() throws RemoteException
  {
    return model.getDeadliftLeaders();
  }

  /**
   * Retrieves the leaderboard of trainees based on their bench press performance.
   *
   * @return the leaderboard of trainees based on bench press performance, or null if an error occurs
   * @throws RemoteException if a remote exception occurs during the method call
   */
  @Override public TraineeList getBenchLeaders() throws RemoteException
  {
    return model.getBenchLeaders();
  }

  /**
   * Retrieves the list of meeting requests for a coach.
   *
   * @param coach the username of the coach
   * @return the list of meeting requests for the coach
   * @throws RemoteException if a remote exception occurs during the method call
   */
  @Override public ArrayList<String> getMeetingRequests(String coach)
      throws RemoteException
  {
    return model.getMeetingRequests(coach);
  }

  /**
   * Retrieves a list of meetings associated with a coach.
   *
   * @param coach the username of the coach
   * @return the list of meetings associated with the coach, or null if an error occurs
   * @throws RemoteException if a remote exception occurs during the method call
   */
  @Override public ArrayList<String> getCoachMeetings(String coach)
      throws RemoteException
  {
    return model.getCoachMeetings(coach);
  }

  /**
   * Retrieves the list of meetings for a trainee.
   *
   * @param traineeUsername the username of the trainee
   * @return the list of meetings for the trainee
   * @throws RemoteException if a remote exception occurs during the method call
   */
  @Override public ArrayList<String> getTraineeMeetingList(
      String traineeUsername) throws RemoteException
  {
    return model.getTraineeMeetingList(traineeUsername);
  }

  /**
   * Retrieves the list of meeting requests for a trainee.
   *
   * @param traineeUsername the username of the trainee
   * @return the list of meeting requests for the trainee
   * @throws RemoteException if a remote exception occurs during the method call
   */

  @Override public ArrayList<String> getTraineeMeetingRequests(
      String traineeUsername) throws RemoteException
  {
    return model.getTraineeMeetingRequests(traineeUsername);
  }

  /**
   * Sends a meeting request from a trainee to a coach for a specified date.
   *
   * @param traineeUsername the username of the trainee sending the meeting request
   * @param coachUsername   the username of the coach receiving the meeting request
   * @param dateOfMeeting   the date of the meeting
   * @return true if the meeting request was sent successfully, false otherwise
   * @throws RemoteException if a remote exception occurs during the method call
   */

  @Override public boolean sendMeetingRequest(String traineeUsername,
      String coachUsername, LocalDate dateOfMeeting) throws RemoteException
  {
    return model.sendMeetingRequest(traineeUsername, coachUsername,
        dateOfMeeting);
  }

  @Override
  /**
   * Approves a meeting request between a trainee and a coach for a specified date.
   *
   * @param trainee the username of the trainee
   * @param coach the username of the coach
   * @param date the date of the meeting
   * @return true if the meeting request was approved successfully, false otherwise
   * @throws RemoteException if a remote exception occurs during the method call
   */ public boolean approveMeeting(String trainee, String coach,
      LocalDate date) throws RemoteException
  {
    return model.approveMeeting(trainee, coach, date);
  }

  @Override
  /**
   * Denies a meeting request between a trainee and a coach for a specified date.
   *
   * @param trainee the username of the trainee
   * @param coach the username of the coach
   * @param date the date of the meeting
   * @return true if the meeting request was denied successfully, false otherwise
   * @throws RemoteException if a remote exception occurs during the method call
   */
  public boolean denyMeeting(String trainee, String coach, LocalDate date)
      throws RemoteException
  {
    return model.denyMeeting(trainee, coach, date);
  }

  private void startServer()
  {
    try
    {
      UnicastRemoteObject.exportObject(this, 0);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
    try
    {
      Naming.rebind("ValhallaServer", this);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
    catch (MalformedURLException e)
    {
      throw new RuntimeException(e);
    }
    System.out.println("Server started...");
  }

  private void startRegistry() throws Exception
  {
    try
    {
      Registry reg = LocateRegistry.createRegistry(1099);
      System.out.println("Registry started...");
    }
    catch (RemoteException e)
    {
      throw new Exception("Registry already started? " + e.getMessage());
    }
  }

  @Override
  /**
   * Accepts a friend request between two users.
   *
   * @param requester_username   the username of the requester
   * @param accepter_username    the username of the accepter
   * @return true if the friend request is accepted successfully
   */ public boolean acceptFriendRequest(String requester_username,
      String accepter_username)
  {
    return model.acceptFriendRequest(requester_username, accepter_username);

  }

  @Override
  /**
   * Rejects a friend request between two users.
   *
   * @param requester_username   the username of the requester
   * @param accepter_username    the username of the accepter
   * @return true if the friend request is rejected successfully
   */ public boolean rejectFriendRequest(String requester_username,
      String accepter_username)
  {
    return model.rejectFriendRequest(requester_username, accepter_username);
  }

  @Override
  /**
   * Retrieves the list of friends for a given username.
   *
   * @param username   the username of the user
   * @return the FriendList object containing the user's friends
   */ public FriendList getFriends(String username)
  {
    return model.getFriends(username);
  }

  @Override
  /**
   * Retrieves the list of friend requests for a given username.
   *
   * @param username   the username of the user
   * @return an ArrayList of friend requests received by the user
   */ public ArrayList<String> getFriendRequests(String username)
  {
    return model.getFriendRequests(username);
  }

  @Override
  /**
   * Adds a listener to the server's property change handler.
   * The listener will be notified when any of the specified properties change.
   *
   * @param listener       the general listener to be added
   * @param propertyNames  the names of the properties to listen for changes
   * @return true if the listener is successfully added, false otherwise
   * @throws RemoteException if a remote communication error occurs
   */ public boolean addListener(GeneralListener<String, String> listener,
      String... propertyNames) throws RemoteException
  {
    property.addListener(listener, propertyNames);
    return true;
  }

  @Override
  /**
   * Removes a listener from the server's property change handler.
   * The listener will no longer be notified of property changes.
   *
   * @param listener       the general listener to be removed
   * @param propertyNames  the names of the properties to stop listening for changes
   * @return true if the listener is successfully removed, false otherwise
   * @throws RemoteException if a remote communication error occurs
   */ public boolean removeListener(GeneralListener<String, String> listener,
      String... propertyNames) throws RemoteException
  {
    property.removeListener(listener, propertyNames);
    return true;
  }
}
