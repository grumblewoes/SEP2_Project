package mediator;

import modelClient.Model;
import util.Logger;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.GeneralListener;
import utility.observer.listener.RemoteListener;
import utility.observer.subject.LocalSubject;
import utility.observer.subject.PropertyChangeHandler;

import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * The Client class represents a client that implements the Model interface and acts as a RemoteListener and LocalSubject.
 *
 * @author Damian Trafiałek
 * @version 1.0
 */
public class Client implements Model, RemoteListener<String, String>,
    LocalSubject<String, String>
{

  private RemoteModel server;
  private PropertyChangeHandler<String, String> property;

  /**
   * 0-argument constructor creating a Client object with no arguments.
   * Initializes the property change handler and establishes a connection with the remote server.
   */
  public Client()
  {
    this.property = new PropertyChangeHandler<>(this);
    try
    {
      start();
      server = (RemoteModel) Naming.lookup(
          "rmi://localhost:1099/ValhallaServer");

    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  /**
   * Connects the listener to the server using the specified username.
   *
   * @param username the username to connect the listener with
   */
  public void connectListener(String username)
  {
    try
    {
      server.addListener(this, username);
    }
    catch (RemoteException e)
    {
      Logger.log("Unable to connect listener");
    }
  }

  /**
   * Disconnects the listener from the server using the specified username.
   *
   * @param username the username to disconnect the listener from
   */
  public void disconnectListener(String username)
  {
    try
    {
      server.removeListener(this, username);
    }
    catch (RemoteException e)
    {
      Logger.log("Unable to disconnect with listener");
    }
  }

  /**
   * Starts the client by exporting itself as a remote object.
   */
  public void start()
  {
    try
    {
      UnicastRemoteObject.exportObject((Remote) this, 0);
    }
    catch (Exception e)
    {
      System.out.println(e.getMessage());
    }
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
   * @throws RuntimeException if a runtime exception occurs during the method call
   */
  public boolean createUser(String firstName, String lastName, String userName,
      String password, int height, int weight)
  {
    try
    {
      return server.createUser(firstName, lastName, userName, password, height,
          weight);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
  }

  /**
   * Logs in a user with the specified username and password.
   *
   * @param username the username of the user
   * @param password the password of the user
   * @return true if the login was successful, false otherwise
   * @throws RuntimeException if a runtime exception occurs during the method call
   */
  public boolean login(String username, String password)
  {
    try
    {
      boolean logged = server.login(username, password);
      if (logged)
        connectListener(username);
      return logged;
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
  }

  /**
   * Creates a new folder for the given user.
   *
   * @param username the username of the user
   * @param name     the name of the folder to be created
   * @return true if the folder is created successfully, false otherwise
   * @throws RuntimeException if a runtime exception occurs during the method call
   */
  public boolean createFolder(String username, String name)
  {
    try
    {
      return server.createFolder(username, name);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
  }

  /**
   * Removes the folder with the specified folder Id for the given user.
   *
   * @param username the username of the user
   * @param folderId the ID of the folder to be removed
   * @return true if the folder is removed successfully, false otherwise
   * @throws RuntimeException if a runtime exception occurs during the method call
   */
  public boolean removeFolder(String username, int folderId)
  {
    try
    {
      return server.removeFolder(username, folderId);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
  }

  /**
   * Renames the folder with the specified folder Id for the given user.
   *
   * @param username the username of the user
   * @param folderId the ID of the folder to be renamed
   * @param newName  the new name for the folder
   * @return true if the folder is renamed successfully, false otherwise
   * @throws RuntimeException if a runtime exception occurs during the method call
   */
  public boolean editFolder(String username, int folderId, String newName)
  {
    try
    {
      return server.editFolder(username, folderId, newName);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }

  }

  /**
   * Retrieves the list of folders for the given user.
   *
   * @param username the username of the user
   * @return the list of folders for the user, or null if SQLException is caught
   * @throws RuntimeException if a runtime exception occurs during the method call
   */
  public FolderList getFolderList(String username)
  {
    try
    {
      return server.getFolderList(username);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }

  }

  /**
   * Retrieves the list of exercises for the specified folder.
   *
   * @param folderId the ID of the folder
   * @return the list of exercises for the folder, or null if an error occurs
   * @throws RuntimeException if a runtime exception occurs during the method call
   */
  public ExerciseList getExerciseList(int folderId)
  {
    try
    {
      return server.getExerciseList(folderId);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
  }

  /**
   * Retrieves an ExerciseList by name and folder ID.
   *
   * @param name     the name of the exercise list
   * @param folderId the ID of the folder
   * @return the ExerciseList matching the specified name and folder ID,
   * @throws RuntimeException if a runtime exception occurs during the method call
   */
  @Override public ExerciseList getExerciseListByNameAndFolderId(String name,
      int folderId)
  {
    try
    {
      return server.getExerciseListByNameAndFolderId(name, folderId);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
  }

  /**
   * Removes the exercise with the specified exercise Id.
   *
   * @param exerciseId the ID of the exercise to be removed
   * @return true if the exercise is removed successfully, false otherwise
   * @throws RuntimeException if a runtime exception occurs during the method call
   */
  @Override public boolean removeExercise(int exerciseId)
  {
    try
    {
      return server.removeExercise(exerciseId);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
  }

  /**
   * Removes exercises with the specified name from the given folder.
   *
   * @param name     the name of the exercises to be removed
   * @param folderId the ID of the folder from which the exercises should be removed
   * @return true if the exercises are removed successfully, false otherwise
   * @throws RuntimeException if a runtime exception occurs during the method call
   */
  @Override public boolean removeExercisesByName(String name, int folderId)
  {
    try
    {

      return server.removeExercisesByName(name, folderId);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
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
   * @throws RuntimeException if a runtime exception occurs during the method call
   */
  @Override public boolean addExercise(String username, String exerciseName,
      int folderId, int weight, int repetitions)
  {
    try
    {
      return server.addExercise(username, exerciseName, folderId, weight,
          repetitions);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
  }

  /**
   * Retrieves a list of possible exercises.
   *
   * @return an ArrayList of possible exercises
   * @throws RuntimeException if a runtime exception occurs during the method call
   */
  @Override public ArrayList<String> getPossibleExercises()
  {
    try
    {
      return server.getPossibleExercises();
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
  }

  /**
   * Retrieves a trainee by username.
   *
   * @param username the username of the trainee
   * @return the User object representing the trainee
   * @throws RuntimeException if a runtime exception occurs during the method call
   */
  @Override public User getTrainee(String username)
  {
    try
    {
      return server.getTrainee(username);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
  }

  /**
   * Updates the trainee with the specified details.
   *
   * @param u  the username of the trainee
   * @param h  the height of the trainee
   * @param w  the weight of the trainee
   * @param s  true if the user's profile is shared, false otherwise
   * @param st the status text of the trainee
   * @return true if the trainee is updated successfully
   * @throws RuntimeException if a runtime exception occurs during the method call
   */

  @Override public boolean updateTrainee(String u, int h, int w, boolean s,
      String st)
  {
    try
    {
      return server.updateTrainee(u, h, w, s, st);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
  }

  /**
   * Accepts a friend request between two users.
   *
   * @param requester_username the username of the requester
   * @param accepter_username  the username of the accepter
   * @return true if the friend request is accepted successfully
   * @throws RuntimeException if a runtime exception occurs during the method call
   */
  @Override public boolean acceptFriendRequest(String requester_username,
      String accepter_username)
  {
    try
    {
      return server.acceptFriendRequest(requester_username, accepter_username);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
  }

  /**
   * Rejects a friend request between two users.
   *
   * @param requester_username the username of the requester
   * @param accepter_username  the username of the accepter
   * @return true if the friend request is rejected successfully
   * @throws RuntimeException if a runtime exception occurs during the method call
   */

  @Override public boolean rejectFriendRequest(String requester_username,
      String accepter_username)
  {
    try
    {
      return server.rejectFriendRequest(requester_username, accepter_username);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
  }

  /**
   * Retrieves the list of friends for a given username.
   *
   * @param username the username of the user
   * @return the FriendList object containing the user's friends
   * @throws RuntimeException if a runtime exception occurs during the method call
   */
  @Override public FriendList getFriends(String username)
  {
    try
    {
      return server.getFriends(username);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
  }

  /**
   * Retrieves the list of friend requests for a given username.
   *
   * @param username the username of the user
   * @return an ArrayList of friend requests received by the user
   * @throws RuntimeException if a runtime exception occurs during the method call
   */
  @Override public ArrayList<String> getFriendRequests(String username)
  {
    try
    {
      return server.getFriendRequests(username);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
  }

  /**
   * This method is invoked when a property change event occurs.
   *
   * @param event the ObserverEvent representing the property change event
   */
  @Override public void propertyChange(ObserverEvent<String, String> event)
  {
    String name = event.getPropertyName();
    String val1 = event.getValue1();
    String val2 = event.getValue2();
    Logger.log("Received notification: " + name + ", " + val1 + ", " + val2);
    property.firePropertyChange(name, val1, val2);
  }

  /**
   * Adds a listener to the property change handler for the specified property names.
   *
   * @param listener      the listener to add
   * @param propertyNames the names of the properties to listen for
   * @return true if the listener was added successfully, false otherwise
   */
  @Override public boolean addListener(GeneralListener<String, String> listener,
      String... propertyNames)
  {
    property.addListener(listener, propertyNames);
    return true;
  }

  /**
   * Removes a listener from the property change handler for the specified property names.
   *
   * @param listener      the listener to remove
   * @param propertyNames the names of the properties to stop listening for
   * @return true if the listener was removed successfully, false otherwise
   */

  @Override public boolean removeListener(
      GeneralListener<String, String> listener, String... propertyNames)
  {
    property.addListener(listener, propertyNames);
    return true;
  }

  /**
   * Sends a friend request from the requester to the accepter.
   *
   * @param requesterUsername the username of the requester
   * @param accepterUsername  the username of the accepter
   * @return true if the friend request is sent successfully, false otherwise
   */
  @Override public boolean sendFriendRequest(String requesterUsername,
      String accepterUsername)
  {
    try
    {
      return server.sendFriendRequest(requesterUsername, accepterUsername);
    }
    catch (RemoteException e)
    {
      return false;
    }
  }

  /**
   * Removes a friend relationship between two users.
   *
   * @param requesterUsername the username of the requester
   * @param accepterUsername  the username of the accepter
   * @return true if the friend relationship is removed successfully, false otherwise
   */

  @Override public boolean removeFriend(String requesterUsername,
      String accepterUsername)
  {
    try
    {
      return server.removeFriend(requesterUsername, accepterUsername);
    }
    catch (RemoteException e)
    {
      return false;
    }
  }

  /**
   * Sends a coach request from the requester to the accepter.
   *
   * @param requesterUsername the username of the requester
   * @param accepterUsername  the username of the accepter
   * @return true if the coach request is sent successfully, false otherwise
   */

  @Override public boolean requestCoach(String requesterUsername,
      String accepterUsername)
  {
    try
    {
      return server.requestCoach(requesterUsername, accepterUsername);
    }
    catch (RemoteException e)
    {
      return false;
    }
  }

  /**
   * Retrieves the coach associated with a trainee.
   *
   * @param traineeUsername the username of the trainee
   * @return the coach associated with the trainee, or null if not found
   */
  @Override public User getCoach(String traineeUsername)
  {
    try
    {
      return server.getCoach(traineeUsername);
    }
    catch (RemoteException e)
    {
      return null;
    }

  }

  /**
   * Checks if a user is a coach.
   *
   * @param username the username of the user
   * @return true if the user is a coach, false otherwise
   */
  @Override public boolean isCoach(String username)
  {
    try
    {
      return server.isCoach(username);
    }
    catch (RemoteException e)
    {
      return false;
    }
  }

  /**
   * Removes the coach for a trainee.
   *
   * @param traineeUsername the username of the trainee
   * @return true if the coach assignment is successfully removed, false otherwise
   */
  @Override public boolean removeCoachAssignment(String traineeUsername)
  {
    try
    {
      return server.removeCoachAssignment(traineeUsername);
    }
    catch (RemoteException e)
    {
      return false;
    }
  }
  /**
   * Retrieves the list of meetings for a trainee.
   *
   * @param traineeUsername the username of the trainee
   * @return the list of meetings for the trainee, null otherwise
   */
  @Override public ArrayList<String> getTraineeMeetingList(
      String traineeUsername)
  {
    try
    {
      return server.getTraineeMeetingList(traineeUsername);
    }
    catch (RemoteException e)
    {
      return null;
    }
  }


  /**
   * Retrieves the list of meeting requests for a trainee.
   *
   * @param traineeUsername the username of the trainee
   * @return the list of meeting requests for the trainee, null otherwise
   */
  @Override public ArrayList<String> getTraineeMeetingRequests(
      String traineeUsername)
  {
    try
    {
      return server.getTraineeMeetingRequests(traineeUsername);
    }
    catch (RemoteException e)
    {
      return null;
    }
  }

  /**
   * Sends a meeting request from a trainee to a coach for a specified date.
   *
   * @param traineeUsername the username of the trainee sending the meeting request
   * @param coachUsername the username of the coach receiving the meeting request
   * @param dateOfMeeting the date of the meeting
   * @return true if the meeting request was sent successfully, false otherwise
   */
  @Override public boolean sendMeetingRequest(String traineeUsername,
      String coachUsername, LocalDate dateOfMeeting)
  {
    try
    {
      return server.sendMeetingRequest(traineeUsername, coachUsername,
          dateOfMeeting);
    }
    catch (RemoteException e)
    {
      return false;
    }
  }
  /**
   * Removes an existing meeting between a trainee and a coach for a specified date.
   *
   * @param traineeUsername the username of the trainee
   * @param coachUsername the username of the coach
   * @param dateOfMeeting the date of the meeting
   * @return true if the meeting removal was approved successfully, false otherwise
   */

  @Override public boolean removeMeeting(String traineeUsername,
      String coachUsername, LocalDate dateOfMeeting)
  {
    try
    {
      return server.removeMeeting(traineeUsername, coachUsername,
          dateOfMeeting);
    }
    catch (RemoteException e)
    {
      return false;
    }
  }

  /**
   * Accepts a coach request from a trainee.
   *
   * @param traineeUsername the username of the trainee
   * @param coachUsername the username of the coach
   * @return true if the coach request is successfully accepted, false otherwise
   * @throws RuntimeException if a runtime exception occurs during the method call
   */

  @Override public boolean acceptRequest(String traineeUsername,
      String coachUsername)
  {
    try
    {
      return server.acceptRequest(traineeUsername, coachUsername);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
  }


  /**
   * Denies a coach request from a trainee.
   *
   * @param traineeUsername the username of the trainee
   * @return true if the coach request is successfully denied, false otherwise
   * @throws RuntimeException if a runtime exception occurs during the method call
   */
  @Override public boolean denyRequest(String traineeUsername)
  {
    try
    {
      return server.denyRequest(traineeUsername);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
  }


  /**
   * Removes a trainee from the coach's roster.
   *
   * @param traineeUsername the username of the trainee
   * @return true if the trainee is successfully removed, false otherwise
   * @throws RuntimeException if a runtime exception occurs during the method call
   */
  @Override public boolean removeTraineeFromRoster(String traineeUsername)
  {
    try
    {
      return server.removeTraineeFromRoster(traineeUsername);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
  }

  /**
   * Retrieves the list of trainees associated with a coach.
   *
   * @param username the username of the coach
   * @return the list of trainees associated with the coach
   * @throws RuntimeException if a runtime exception occurs during the method call
   */
  public TraineeList getTraineeList(String username)
  {
    try
    {
      return server.getTraineeList(username);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
  }
  /**
   * Retrieves the list of all trainee requests received by a coach.
   *
   * @param username the username of the coach
   * @return the list of trainee requests received by the coach
   * @throws RuntimeException if a runtime exception occurs during the method call
   */
  public ArrayList<String> getTraineeRequest(String username)
  {
    try
    {
      return server.getTraineeRequest(username);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
  }

  /**
   * Retrieves the list of meeting requests for a coach.
   *
   * @param coach the username of the coach
   * @return the list of meeting requests for the coach
   * @throws RuntimeException if a runtime exception occurs during the method call
   */
  public ArrayList<String> getMeetingRequests(String coach)
  {
    try
    {
      return server.getMeetingRequests(coach);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
  }

  /**
   * Retrieves a list of meetings associated with a coach.
   *
   * @param coach the username of the coach
   * @return the list of meetings associated with the coach, or null if an error occurs
   * @throws RuntimeException if a runtime exception occurs during the method call
   */
  public ArrayList<String> getCoachMeetings(String coach)
  {
    try
    {
      return server.getCoachMeetings(coach);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
  }
  /**
   * Approves a meeting request between a trainee and a coach for a specified date.
   *
   * @param trainee the username of the trainee
   * @param coach the username of the coach
   * @param date the date of the meeting
   * @return true if the meeting request was approved successfully, false otherwise
   * @throws RuntimeException if a runtime exception occurs during the method call
   */
  @Override public boolean approveMeeting(String trainee, String coach,
      LocalDate date)
  {
    try
    {
      return server.approveMeeting(trainee, coach, date);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
  }
  /**
   * Denies a meeting request between a trainee and a coach for a specified date.
   *
   * @param trainee the username of the trainee
   * @param coach the username of the coach
   * @param date the date of the meeting
   * @return true if the meeting request was denied successfully, false otherwise
   * @throws RuntimeException if a runtime exception occurs during the method call
   */
  @Override public boolean denyMeeting(String trainee, String coach,
      LocalDate date)
  {
    try
    {
      return server.denyMeeting(trainee, coach, date);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
  }

  /**
   * Retrieves a list of taken dates for a coach.
   *
   * @param coachUsername the username of the coach
   * @return the list of taken dates for the coach, or null if an error occurs
   * @throws RuntimeException if a runtime exception occurs during the method call
   */
  @Override public ArrayList<LocalDate> getTakenDates(String coachUsername)
  {
    try
    {
      return server.getTakenDates(coachUsername);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
  }
  /**
   * Retrieves the leaderboard of trainees based on their squat performance.
   *
   * @return the leaderboard of trainees based on squat performance, or null if an error occurs
   * @throws RuntimeException if a runtime exception occurs during the method call
   */
  @Override public TraineeList getSquatLeaders()
  {
    try
    {
      return server.getSquatLeaders();
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
  }

  /**
   * Retrieves the leaderboard of trainees based on their deadlift performance.
   *
   * @return the leaderboard of trainees based on deadlift performance, or null if an error occurs
   * @throws RuntimeException if a runtime exception occurs during the method call
   *
   */
  @Override public TraineeList getDeadliftLeaders()
  {
    try
    {
      return server.getDeadliftLeaders();
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
  }

  /**
   * Retrieves the leaderboard of trainees based on their bench press performance.
   *
   * @return the leaderboard of trainees based on bench press performance, or null if an error occurs
   * @throws RuntimeException if a runtime exception occurs during the method call
   */
  @Override public TraineeList getBenchLeaders()
  {
    try
    {
      return server.getBenchLeaders();
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
  }

}

