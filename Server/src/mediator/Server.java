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
 * 
 * 
 * 
 * @author 
 * @version 
 */
public class Server implements RemoteModel
{
  private Model model;
  private PropertyChangeHandler<String,String> property;
  /**
   * 1-argument constructor 
   * 
   * 
   * @param model 
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
   * 
   * 
   * @param firstName 
   *        
   * @param lastName 
   *        
   * @param userName 
   *        
   * @param password 
   *        
   * @param height 
   *        
   * @param weight 
   *        
   *
   * @return 
   *        
   */
  public boolean createUser(String firstName, String lastName, String userName, String password, int height, int weight){
    return model.createUser(firstName, lastName, userName, password, height, weight);
  }

  /**
   * 
   * 
   * @param username 
   *        
   * @param password 
   *        
   *
   * @return 
   *        
   */
  public boolean login(String username, String password) {
    return model.login(username, password);

  }

  /**
   * 
   * 
   * @param username 
   *        
   * @param name 
   *        
   *
   * @return 
   *        
   */
  @Override public boolean createFolder(String username, String name)
  {
    return model.createFolder(username, name);
  }

  /**
   * 
   * 
   * @param username 
   *        
   * @param folderId 
   *        
   *
   * @return 
   *        
   */
  @Override public boolean removeFolder(String username, int folderId)
  {
    return model.removeFolder(username, folderId);
  }

  /**
   * 
   * 
   * @param username 
   *        
   * @param folderId 
   *        
   * @param newName 
   *        
   *
   * @return 
   *        
   */
  @Override public boolean editFolder(String username,int folderId,  String newName)
  {
    return model.editFolder(username, folderId, newName);
  }

  /**
   * 
   * 
   * @param username 
   *        
   *
   * @return 
   *        
   */
  @Override public FolderList getFolderList(String username)
  {
    return model.getFolderList(username);
  }

  /**
   * 
   * 
   * @param username 
   *        
   * @param exerciseName 
   *        
   * @param folderId 
   *        
   * @param weight 
   *        
   * @param repetitions 
   *        
   *
   * @return 
   *        
   */
  @Override public boolean addExercise(String username, String exerciseName, int folderId, int weight,int repetitions) throws RemoteException
  {
    return model.addExercise(username, exerciseName,folderId,weight,repetitions);
  }

  /**
   * 
   * 
   * @param exerciseId 
   *        
   *
   * @return 
   *        
   */
  @Override public boolean removeExercise(int exerciseId) throws RemoteException
  {
    return model.removeExercise(exerciseId);
  }

  @Override
  /**
   * 
   * 
   * @param name 
   *        
   * @param folderId 
   *        
   *
   * @return 
   *        
   */
  public boolean removeExercisesByName(String name, int folderId) throws RemoteException {
    return model.removeExercisesByName(name,folderId);
  }

  /**
   * 
   * 
   * @param folderId 
   *        
   *
   * @return 
   *        
   */
  @Override public ExerciseList getExerciseList(int folderId)
      throws RemoteException
  {
    return model.getExerciseList(folderId);
  }

  @Override
  /**
   * 
   * 
   * @param name 
   *        
   * @param folderId 
   *        
   *
   * @return 
   *        
   */
  public ExerciseList getExerciseListByNameAndFolderId(String name, int folderId) {
    return model.getExerciseListByNameAndFolderId(name,folderId);
  }

  @Override
  /**
   * 
   * 
   *
   * @return 
   *        
   */
  public ArrayList<String> getPossibleExercises() {
    return model.getPossibleExercises();
  }
//
//  @Override
/**
 * 
 * 
 * @param username 
 *        
 *
 * @return 
 *        
 */
//  public int getBestBenchPress(String username) throws RemoteException {
//    return model.getBestBenchPress(username);
//  }
//
//  @Override
/**
 * 
 * 
 * @param username 
 *        
 *
 * @return 
 *        
 */
//  public int getBestSquat(String username) throws RemoteException {
//    return model.getBestSquat(username);
//  }
//
//  @Override
/**
 * 
 * 
 * @param username 
 *        
 *
 * @return 
 *        
 */
//  public int getBestDeadlift(String username) throws RemoteException {
//    return model.getBestDeadlift(username);
//  }

  @Override
  /**
   * 
   * 
   * @param username 
   *        
   *
   * @return 
   *        
   */
  public User getTrainee(String username) throws RemoteException {
    return model.getTrainee(username);
  }

  @Override
  /**
   * 
   * 
   * @param u 
   *        
   * @param h 
   *        
   * @param w 
   *        
   * @param s 
   *        
   * @param st 
   *        
   *
   * @return 
   *        
   */
  public boolean updateTrainee(String u, int h, int w,boolean s,String st) throws RemoteException {
    boolean result = model.updateTrainee(u,h,w,s,st);
    if(!result) {
      Logger.log("firing property to : " + u );
      property.firePropertyChange(u,null,"Failed to update profile.");
    }
    else property.firePropertyChange(u,null,"Successfully updated profile.");
    return result;
  }

  @Override public boolean sendFriendRequest(String requesterUsername,
      String accepterUsername) throws RemoteException
  {
    return model.sendFriendRequest(requesterUsername,accepterUsername);
  }

  @Override public boolean removeFriend(String requesterUsername,
      String accepterUsername) throws RemoteException
  {
    return model.removeFriend(requesterUsername,accepterUsername);
  }

  @Override
  /**
   * 
   * 
   * @param requesterUsername 
   *        
   * @param accepterUsername 
   *        
   *
   * @return 
   *        
   */
  public boolean requestCoach(String requesterUsername, String accepterUsername) throws RemoteException {
    return model.requestCoach(requesterUsername, accepterUsername);
  }

  @Override
  /**
   * 
   * 
   * @param traineeUsername 
   *        
   *
   * @return 
   *        
   */
  public User getCoach(String traineeUsername) throws RemoteException {
    return model.getCoach(traineeUsername);
  }

  @Override
  /**
   * 
   * 
   * @param username 
   *        
   *
   * @return 
   *        
   */
  public boolean isCoach(String username) throws RemoteException {
    return model.isCoach(username);
  }

  /**
   * 
   * 
   * @param traineeUsername 
   *        
   *
   * @return 
   *        
   */
  @Override public boolean removeCoachAssignment(String traineeUsername)
      throws RemoteException
  {
    return model.removeCoachAssignment(traineeUsername);
  }

  @Override public boolean acceptRequest(String traineeUsername,
      String coachUsername) throws RemoteException
  {
    return model.acceptRequest(traineeUsername, coachUsername);
  }

  /**
   * 
   * 
   * @param traineeUsername 
   *        
   *
   * @return 
   *        
   */
  @Override public boolean denyRequest(String traineeUsername)
      throws RemoteException
  {
    return model.denyRequest(traineeUsername);
  }

  /**
   * 
   * 
   * @param traineeUsername 
   *        
   *
   * @return 
   *        
   */
  @Override public boolean removeTraineeFromRoster(String traineeUsername)
      throws RemoteException
  {
    return model.removeTraineeFromRoster(traineeUsername);
  }

  /**
   * 
   * 
   * @param username 
   *        
   *
   * @return 
   *        
   */
  @Override public TraineeList getTraineeList(String username)
      throws RemoteException
  {
    return model.getTraineeList(username);
  }

  /**
   * 
   * 
   * @param username 
   *        
   *
   * @return 
   *        
   */
  @Override public ArrayList<String> getTraineeRequest(String username)
      throws RemoteException
  {
    return model.getTraineeRequest(username);
  }

  /**
   * 
   * 
   * @param traineeUsername 
   *        
   * @param coachName 
   *        
   * @param date 
   *        
   *
   * @return 
   *        
   */
  @Override public boolean removeMeeting(String traineeUsername, String coachName, LocalDate date)
      throws RemoteException
  {
    return model.removeMeeting( traineeUsername, coachName, date);
  }

  /**
   * 
   * 
   * @param coachUsername 
   *        
   *
   * @return 
   *        
   */
  @Override public ArrayList<LocalDate> getTakenDates(String coachUsername) throws RemoteException
  {
    Logger.log("quering taken dates...");
    return model.getTakenDates(coachUsername);
  }

  /**
   * 
   * 
   *
   * @return 
   *        
   */
  @Override public TraineeList getSquatLeaders() throws RemoteException
  {
    return model.getSquatLeaders();
  }

  /**
   * 
   * 
   *
   * @return 
   *        
   */
  @Override public TraineeList getDeadliftLeaders() throws RemoteException
  {
    return model.getDeadliftLeaders();
  }

  /**
   * 
   * 
   *
   * @return 
   *        
   */
  @Override public TraineeList getBenchLeaders() throws RemoteException
  {
    return model.getBenchLeaders();
  }

  /**
   * 
   * 
   * @param coach 
   *        
   *
   * @return 
   *        
   */
  @Override public ArrayList<String> getMeetingRequests(String coach)
      throws RemoteException
  {
    return model.getMeetingRequests(coach);
  }

  /**
   * 
   * 
   * @param coach 
   *        
   *
   * @return 
   *        
   */
  @Override public ArrayList<String> getCoachMeetings(String coach)
      throws RemoteException
  {
    return model.getCoachMeetings(coach);
  }

  @Override public ArrayList<String> getTraineeMeetingList(
      String traineeUsername) throws RemoteException
  {
    return model.getTraineeMeetingList(traineeUsername);
  }

  @Override public ArrayList<String> getTraineeMeetingRequests(
      String traineeUsername) throws RemoteException
  {
    return model.getTraineeMeetingRequests(traineeUsername);
  }

  @Override public boolean sendMeetingRequest(String traineeUsername,
      String coachUsername, LocalDate dateOfMeeting) throws RemoteException
  {
    return model.sendMeetingRequest(traineeUsername,coachUsername,dateOfMeeting);
  }

  @Override
  /**
   * 
   * 
   * @param trainee 
   *        
   * @param coach 
   *        
   * @param date 
   *        
   *
   * @return 
   *        
   */
  public boolean approveMeeting(String trainee, String coach, LocalDate date) throws RemoteException {
    return model.approveMeeting(trainee, coach, date);
  }

  @Override
  /**
   * 
   * 
   * @param trainee 
   *        
   * @param coach 
   *        
   * @param date 
   *        
   *
   * @return 
   *        
   */
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

  @Override
  /**
   * 
   * 
   *
   * @return 
   *        
   */
  public boolean acceptFriendRequest(String requester_username, String accepter_username) {
      return model.acceptFriendRequest(requester_username, accepter_username);

  }

  @Override
  /**
   * 
   * 
   *
   * @return 
   *        
   */
  public boolean rejectFriendRequest(String requester_username, String accepter_username) {
      return model.rejectFriendRequest(requester_username, accepter_username);
  }

  @Override
  /**
   * 
   * 
   * @param username 
   *        
   *
   * @return 
   *        
   */
  public FriendList getFriends(String username) {
      return model.getFriends(username);
  }

  @Override
  /**
   * 
   * 
   * @param username 
   *        
   *
   * @return 
   *        
   */
  public ArrayList<String> getFriendRequests(String username) {
      return model.getFriendRequests(username);
  }

  @Override
  /**
   * 
   * 
   *
   * @return 
   *        
   */
  public boolean addListener(GeneralListener<String, String> listener, String... propertyNames) throws RemoteException {
    property.addListener(listener,propertyNames);
    return true;
  }

  @Override
  /**
   * 
   * 
   *
   * @return 
   *        
   */
  public boolean removeListener(GeneralListener<String, String> listener, String... propertyNames) throws RemoteException {
    property.removeListener(listener,propertyNames);
    return true;
  }
}
