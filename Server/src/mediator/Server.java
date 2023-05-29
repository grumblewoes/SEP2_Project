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
 * Class that manages the communication between cleint and server.
 *
 *
 * @author Damian Trafiałek, Jakub Cerovsky, Anna Pomerantz, Julia Gramovicha
 * @version 1.0
 */
public class Server implements RemoteModel
{
  private Model model;
  private PropertyChangeHandler<String,String> property;
  /**
   * 1-argument constructor that takes the model and creates association with it.
   * Starts the server and registry.
   * 
   * @param model - Server Model
   *        
   */
  public Server(Model model){
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
   * Calls model to create a new user.
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
  public boolean createUser(String firstName, String lastName, String userName, String password, int height, int weight){
    return model.createUser(firstName, lastName, userName, password, height, weight);
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
  public boolean login(String username, String password) {
    return model.login(username, password);

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
  @Override public boolean createFolder(String username, String name)
  {
    return model.createFolder(username, name);
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
  @Override public boolean removeFolder(String username, int folderId)
  {
    return model.removeFolder(username, folderId);
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
  @Override public boolean editFolder(String username,int folderId,  String newName)
  {
    return model.editFolder(username, folderId, newName);
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
  @Override public FolderList getFolderList(String username)
  {
    return model.getFolderList(username);
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
  @Override public boolean addExercise(String username, String exerciseName, int folderId, int weight,int repetitions) throws RemoteException
  {
    return model.addExercise(username, exerciseName,folderId,weight,repetitions);
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
  @Override public boolean removeExercise(int exerciseId) throws RemoteException
  {
    return model.removeExercise(exerciseId);
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
  @Override
  public boolean removeExercisesByName(String name, int folderId) throws RemoteException {
    return model.removeExercisesByName(name,folderId);
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
  @Override public ExerciseList getExerciseList(int folderId)
      throws RemoteException
  {
    return model.getExerciseList(folderId);
  }

  /**
   * Gets the list of exercises by name and folder id.
   *
   * @param name - string value
   *
   * @param folderId - integer value
   * @return ExerciseList - list of exercises
   */
  @Override
  public ExerciseList getExerciseListByNameAndFolderId(String name, int folderId) {
    return model.getExerciseListByNameAndFolderId(name,folderId);
  }

  /**
   * Calls the server to get the list of possible exercises.
   *
   *
   * @return array list of possible exercises names
   *
   */
  @Override
  public ArrayList<String> getPossibleExercises() {
    return model.getPossibleExercises();
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
  @Override
  public User getTrainee(String username) throws RemoteException {
    return model.getTrainee(username);
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
  @Override
  public boolean updateTrainee(String u, int h, int w,boolean s,String st) throws RemoteException {
    boolean result = model.updateTrainee(u,h,w,s,st);
    if(!result) {
      Logger.log("firing property to : " + u );
      property.firePropertyChange(u,null,"Failed to update profile.");
    }
    else property.firePropertyChange(u,null,"Successfully updated profile.");
    return result;
  }

  /**
   * Calls the server to send the friend request.
   * @param requesterUsername
   * @param accepterUsername
   * @return boolean if everything went ok
   */
  @Override
  public boolean sendFriendRequest(String requesterUsername,
      String accepterUsername) throws RemoteException
  {
    return model.sendFriendRequest(requesterUsername,accepterUsername);
  }

  /**
   * Calls the server to remove a friend.
   * @param requesterUsername
   * @param accepterUsername
   * @return boolean if everything went ok
   */
  @Override public boolean removeFriend(String requesterUsername,
      String accepterUsername) throws RemoteException
  {
    return model.removeFriend(requesterUsername,accepterUsername);
  }

  /**
   * Calls the server to request the coach for a trainee.
   * @param requesterUsername
   * @param accepterUsername
   * @return boolean if everything went ok
   */
  @Override
  public boolean requestCoach(String requesterUsername, String accepterUsername) throws RemoteException {
    return model.requestCoach(requesterUsername, accepterUsername);
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
  @Override
  public User getCoach(String traineeUsername) throws RemoteException {
    return model.getCoach(traineeUsername);
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
  @Override
  public boolean isCoach(String username) throws RemoteException {
    return model.isCoach(username);
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
      throws RemoteException
  {
    return model.removeCoachAssignment(traineeUsername);
  }

  /**
   * Calls the server to accept trainee request.
   * @param traineeUsername - string value
   * @param coachUsername - string value
   * @return boolean if everything went ok
   */
  @Override public boolean acceptRequest(String traineeUsername,
      String coachUsername) throws RemoteException
  {
    return model.acceptRequest(traineeUsername, coachUsername);
  }

  /**
   * Calls the server to deny trainee request.
   * @param traineeUsername - string value
   * @return boolean if everything went ok
   */
  @Override public boolean denyRequest(String traineeUsername)
      throws RemoteException
  {
    return model.denyRequest(traineeUsername);
  }

  /**
   * Calls the server to remove trainee from a roaster.
   * @param traineeUsername - string value
   * @return boolean if everything went ok
   */
  @Override public boolean removeTraineeFromRoster(String traineeUsername)
      throws RemoteException
  {
    return model.removeTraineeFromRoster(traineeUsername);
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
  @Override public TraineeList getTraineeList(String username)
      throws RemoteException
  {
    return model.getTraineeList(username);
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
  @Override public ArrayList<String> getTraineeRequest(String username)
      throws RemoteException
  {
    return model.getTraineeRequest(username);
  }

  /**
   * Calls the server to remove the meeting.
   * @param traineeUsername - string value
   * @param coachName - string value
   * @param date - localDate value
   * @return boolean if everything went ok
   */
  @Override public boolean removeMeeting(String traineeUsername, String coachName, LocalDate date)
      throws RemoteException
  {
    return model.removeMeeting( traineeUsername, coachName, date);
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
  @Override public ArrayList<LocalDate> getTakenDates(String coachUsername) throws RemoteException
  {
    Logger.log("quering taken dates...");
    return model.getTakenDates(coachUsername);
  }

  /**
   * Calls the server to get the list of squat leaders.
   *
   *
   * @return TraineeList
   *
   */
  @Override public TraineeList getSquatLeaders() throws RemoteException
  {
    return model.getSquatLeaders();
  }

  /**
   * Calls the server to get the deadlift leaders
   *
   *
   * @return TraineList
   *
   */
  @Override public TraineeList getDeadliftLeaders() throws RemoteException
  {
    return model.getDeadliftLeaders();
  }

  /**
   * Calls the server to get the bench press leaders.
   *
   *
   * @return TraineeList
   *
   */
  @Override public TraineeList getBenchLeaders() throws RemoteException
  {
    return model.getBenchLeaders();
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
  @Override public ArrayList<String> getMeetingRequests(String coach)
      throws RemoteException
  {
    return model.getMeetingRequests(coach);
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
  @Override public ArrayList<String> getCoachMeetings(String coach)
      throws RemoteException
  {
    return model.getCoachMeetings(coach);
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
  @Override public ArrayList<String> getTraineeMeetingList(
      String traineeUsername) throws RemoteException
  {
    return model.getTraineeMeetingList(traineeUsername);
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
  @Override public ArrayList<String> getTraineeMeetingRequests(
      String traineeUsername) throws RemoteException
  {
    return model.getTraineeMeetingRequests(traineeUsername);
  }

  /**
   * Calls the server to add the meeting request.
   * @param traineeUsername - string value
   * @param coachUsername - string value
   * @param dateOfMeeting - localDate value
   * @return boolean if everything went ok
   */
  @Override public boolean sendMeetingRequest(String traineeUsername,
      String coachUsername, LocalDate dateOfMeeting) throws RemoteException
  {
    return model.sendMeetingRequest(traineeUsername,coachUsername,dateOfMeeting);
  }

  /**
   * Calls the server to approve the meeting.
   * @param trainee - string value
   * @param coach - string value
   * @param date - localDate value
   * @return boolean if everything went ok
   */
  public boolean approveMeeting(String trainee, String coach, LocalDate date) throws RemoteException {
    return model.approveMeeting(trainee, coach, date);
  }

  /**
   * Calls the server to deny the meeting.
   * @param trainee - string value
   * @param coach - string value
   * @param date - localDate value
   * @return boolean if everything went ok
   */
  @Override
  public boolean denyMeeting(String trainee, String coach, LocalDate date) throws RemoteException {
    return model.denyMeeting(trainee, coach, date);
  }

  private void startServer() {
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

  private void startRegistry() throws Exception {
    try {
      Registry reg = LocateRegistry.createRegistry(1099);
      System.out.println("Registry started...");
    } catch (RemoteException e) {
      throw new Exception("Registry already started? " + e.getMessage());
    }
  }

  /**
   * Calls the server to accept friend request.
   * @param requester_username - string value
   * @param accepter_username - string value
   * @return boolean if everything went ok
   */
  @Override
  public boolean acceptFriendRequest(String requester_username, String accepter_username) {
      return model.acceptFriendRequest(requester_username, accepter_username);

  }

  /**
   * Calls the server to reject friend request.
   * @param requester_username - string value
   * @param accepter_username - string value
   * @return boolean if everything went ok
   */
  @Override
  public boolean rejectFriendRequest(String requester_username, String accepter_username) {
      return model.rejectFriendRequest(requester_username, accepter_username);
  }

  /**
   * Calls the server to get the list of trainee's friends.
   *
   * @param username - string value
   *
   * @return list of friends
   *
   */
  @Override
  public FriendList getFriends(String username) {
      return model.getFriends(username);
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
  @Override
  public ArrayList<String> getFriendRequests(String username) {
      return model.getFriendRequests(username);
  }

  /**
   * Add the listener to the subject.
   * @param listener the listener to be added
   * @param propertyNames a var-args list of property names. If empty, then the
   *                      listener should be added as a lister for all events.
   * @return
   * @throws RemoteException
   */
  @Override
  public boolean addListener(GeneralListener<String, String> listener, String... propertyNames) throws RemoteException {
    property.addListener(listener,propertyNames);
    return true;
  }

  /**
   * Remove the listener to the subject.
   * @param listener the listener to be removed
   * @param propertyNames a var-args list of property names. If empty, then the
   *                      listener should be removed as a lister for all events.
   * @return
   * @throws RemoteException
   */
  @Override
  public boolean removeListener(GeneralListener<String, String> listener, String... propertyNames) throws RemoteException {
    property.removeListener(listener,propertyNames);
    return true;
  }
}
