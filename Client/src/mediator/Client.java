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
 * Class that manages the communication between cleint and server.
 * 
 * 
 * @author Damian Trafiałek, Jakub Cerovsky, Anna Pomerantz, Julia Gramovicha
 * @version 1.0
 */
public class Client implements Model, RemoteListener<String, String>,
    LocalSubject<String, String>
{

  private RemoteModel server;
  private PropertyChangeHandler<String, String> property;

  /**
   * 0-argument constructor that initialises the object. Creates the connection with server.
   * 
   * 
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
   * Sets up the remote listener
   * 
   * @param username - string value to which it is listening
   *        
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
   * Removes the remote listener.
   * 
   * @param username - string value
   *        
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
   * Starts the client.
   * 
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
   * Calls server to create a new user.
   *
   * @param firstName - string value
   *
   * @param lastName - string value
   *
   * @param userName - string value
   *
   * @param password - string value
   *
   * @param height - integer value
   *
   * @param weight - integer value
   *
   *
   * @return boolean if everything went ok
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
   * Calls server to check if the user and password match.
   * 
   * @param username - string value
   *        
   * @param password - string value
   *        
   *
   * @return boolean if everything went ok
   *        
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
   * Calls sever to create a new folder.
   * 
   * @param username - string value
   *        
   * @param name - string value
   *        
   *
   * @return boolean if everything went ok
   *        
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
   * Calls server to remove folder.
   * 
   * @param username - string value
   *        
   * @param folderId - integer value
   *        
   *
   * @return boolean if everything went ok
   *        
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
   * Calls server to change the folder name.
   * 
   * @param username - string value
   *        
   * @param folderId - integer value
   *        
   * @param newName - string value
   *        
   *
   * @return boolean if everything went ok
   *        
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
   * Gets the list of user's folder.
   * 
   * @param username - string value
   *        
   *
   * @return list of folders
   *        
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
   * Gets the list of exercises within the folder.
   * 
   * @param folderId - integer value
   *        
   *
   * @return list of exercises
   *        
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

  @Override
  /**
   * Gets the list of exercises by name and folder id.
   *
   * @param name - string value
   *
   * @param folderId - integer value
   * @return ExerciseList - list of exercises
   */
  public ExerciseList getExerciseListByNameAndFolderId(String name,
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
   * Removes exercises from database.
   * 
   * @param exerciseId - integer value
   *        
   *
   * @return boolean if everything went ok
   *        
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
   * Removes exercises by name and folder id from database.
   * 
   * @param name - string value
   *        
   * @param folderId - integer value
   *        
   *
   * @return boolean if everything went ok
   *        
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
   * Calls server to add the exercise to the database.
   * @param username - string value
   * @param exerciseName - string value
   * @param folderId - integer value
   * @param weight - integer value
   * @param repetitions - integer value
   * @return boolean if everything went ok
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
   * Calls the server to get the list of possible exercises.
   * 
   *
   * @return array list of possible exercises names
   *        
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
   * Gets the trainee from server.
   * 
   * @param username - stirng value
   *        
   *
   * @return User
   *        
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
   * Calls server to update the trainee.
   * @param u - trainee username
   * @param h - trainee height
   * @param w - trainee weight
   * @param s - trainee wants to share their profile
   * @param st - trainee status
   * @return boolean if everything went ok
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
   * Calls the server to accept friend request.
   * @param requester_username - string value
   * @param accepter_username - string value
   * @return boolean if everything went ok
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
   * Calls the server to reject friend request.
   * @param requester_username - string value
   * @param accepter_username - string value
   * @return boolean if everything went ok
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
   * Calls the server to get the list of trainee's friends.
   * 
   * @param username - string value
   *
   * @return list of friends
   *        
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
   * Calls the server to get the list of the friend requests.
   * 
   * @param username - trainee username - string value
   *        
   *
   * @return ArrayList of friend requests usernames
   *        
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
   * Listens to remote notifications.
   * 
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
   * Add the listener to the subject.
   * @param listener the listener to be added
   * @param propertyNames a var-args list of property names. If empty, then the
   *                      listener should be added as a lister for all events.
   * @return boolean if everything went ok
   */
  @Override public boolean addListener(GeneralListener<String, String> listener,
      String... propertyNames)
  {
    property.addListener(listener, propertyNames);
    return true;
  }

  /**
   * Removes the listener from the subject.
   * @param listener the listener to be removed
   * @param propertyNames a var-args list of property names. If empty, then the
   *                      listener should be removed as a lister for all events.
   * @return boolean if everything went ok
   */
  @Override public boolean removeListener(
      GeneralListener<String, String> listener, String... propertyNames)
  {
    property.addListener(listener, propertyNames);
    return true;
  }

  /**
   * Calls the server to send the friend request.
   * @param requesterUsername
   * @param accepterUsername
   * @return boolean if everything went ok
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
   * Calls the server to remove a friend.
   * @param requesterUsername
   * @param accepterUsername
   * @return boolean if everything went ok
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
   * Calls the server to request the coach for a trainee.
   * @param requesterUsername
   * @param accepterUsername
   * @return boolean if everything went ok
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
   * Calls the server to get the coach of the trainee.
   * 
   * @param traineeUsername - string value
   *        
   *
   * @return User
   *        
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
   * Calls the server to check if the user is a coach.
   * 
   * @param username - string value
   *        
   *
   * @return boolean if user is a coach
   *        
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
   * Calls the server to remove the coach fromthe trainee
   * 
   * @param traineeUsername - string value
   *        
   *
   * @return boolean if everything went ok
   *        
   */
  @Override public boolean removeCoachAssignment(String traineeUsername)
  {
    try{
      return server.removeCoachAssignment(traineeUsername);
    }catch(RemoteException e){
      return false;
    }
  }

  /**
   * Calls the server to get the list of trainee's meetings
   * 
   * @param traineeUsername - string value
   *        
   *
   * @return arrayList of strings
   *        
   */
  @Override public ArrayList<String> getTraineeMeetingList(String traineeUsername)
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
   * Calls the server to get the list of trainee meeting requests.
   * 
   * @param traineeUsername - string value
   *        
   *
   * @return list of meetings requests
   *        
   */
  @Override public ArrayList<String> getTraineeMeetingRequests(String traineeUsername)
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
   * Calls the server to add the meeting request.
   * @param traineeUsername - string value
   * @param coachUsername - string value
   * @param dateOfMeeting - localDate value
   * @return boolean if everything went ok
   */
  @Override public boolean sendMeetingRequest(String traineeUsername,
      String coachUsername, LocalDate dateOfMeeting)
  {
    try
    {
      return server.sendMeetingRequest(traineeUsername,coachUsername,dateOfMeeting);
    }
    catch (RemoteException e)
    {
      return false;
    }
  }

  /**
   * Calls the server to remove the meeting.
   * @param traineeUsername - string value
   * @param coachUsername - string value
   * @param dateOfMeeting - localDate value
   * @return boolean if everything went ok
   */
  @Override public boolean removeMeeting(String traineeUsername,
      String coachUsername, LocalDate dateOfMeeting)
  {
    try
    {
      return server.removeMeeting(traineeUsername,coachUsername,dateOfMeeting);
    }
    catch (RemoteException e)
    {
      return false;
    }
  }

  /**
   * Calls the server to accept trainee request.
   * @param traineeUsername - string value
   * @param coachUsername - string value
   * @return boolean if everything went ok
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
   * Calls the server to deny trainee request.
   * @param traineeUsername - string value
   * @return boolean if everything went ok
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
   * Calls the server to remove trainee from a roaster.
   * @param traineeUsername - string value
   * @return boolean if everything went ok
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
   * Calls the server to get the list of trainees.
   * 
   * @param username - string value
   *        
   *
   * @return TraineeList
   *        
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
   * Calls the server to get the list of trainees requests.
   * 
   * @param username - string value
   *        
   *
   * @return arraylist of strings (trainees request usernames)
   *        
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
   * Calls the server to get the list of meeting requests.
   * 
   * @param coach - string value
   *        
   *
   * @return arrayList of meeting requests
   *        
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
   * Calls the server to get the list of coaches meetings.
   * 
   * @param coach - string value
   *        
   *
   * @return arrayList of coaches meetings
   *        
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
   * Calls the server to approve the meeting.
   * @param trainee - string value
   * @param coach - string value
   * @param date - localDate value
   * @return boolean if everything went ok
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
   * Calls the server to deny the meeting.
   * @param trainee - string value
   * @param coach - string value
   * @param date - localDate value
   * @return boolean if everything went ok
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
   * Method that gets the dates that are already taken/reserved with the coach.
   * 
   * @param coachUsername - username
   *        
   *
   * @return ArrayList of local Dates
   *        
   */
  @Override public ArrayList<LocalDate> getTakenDates(String coachUsername){
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
   * Calls the server to get the list of squat leaders.
   * 
   *
   * @return TraineeList
   *        
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
   * Calls the server to get the deadlift leaders
   * 
   *
   * @return TraineList
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
   * Calls the server to get the bench press leaders.
   * 
   *
   * @return TraineeList
   *        
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

