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

public class Server implements RemoteModel
{
  private Model model;
  private PropertyChangeHandler<String,String> property;
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

  public boolean createUser(String firstName, String lastName, String userName, String password, int height, int weight){
    return model.createUser(firstName, lastName, userName, password, height, weight);
  }

  public boolean login(String username, String password) {
    return model.login(username, password);

  }

  @Override public boolean createFolder(String username, String name)
  {
    return model.createFolder(username, name);
  }

  @Override public boolean removeFolder(String username, int folderId)
  {
    return model.removeFolder(username, folderId);
  }

  @Override public boolean editFolder(String username,int folderId,  String newName)
  {
    return model.editFolder(username, folderId, newName);
  }

  @Override public FolderList getFolderList(String username)
  {
    return model.getFolderList(username);
  }

  @Override public boolean addExercise(String username, String exerciseName, int folderId, int weight,int repetitions) throws RemoteException
  {
    return model.addExercise(username, exerciseName,folderId,weight,repetitions);
  }

  @Override public boolean removeExercise(int exerciseId) throws RemoteException
  {
    return model.removeExercise(exerciseId);
  }

  @Override
  public boolean removeExercisesByName(String name, int folderId) throws RemoteException {
    return model.removeExercisesByName(name,folderId);
  }

  @Override public ExerciseList getExerciseList(int folderId)
      throws RemoteException
  {
    return model.getExerciseList(folderId);
  }

  @Override
  public ExerciseList getExerciseListByNameAndFolderId(String name, int folderId) {
    return model.getExerciseListByNameAndFolderId(name,folderId);
  }

  @Override
  public ArrayList<String> getPossibleExercises() {
    return model.getPossibleExercises();
  }

  @Override
  public int getBestBenchPress(String username) throws RemoteException {
    return model.getBestBenchPress(username);
  }

  @Override
  public int getBestSquat(String username) throws RemoteException {
    return model.getBestSquat(username);
  }

  @Override
  public int getBestDeadlift(String username) throws RemoteException {
    return model.getBestDeadlift(username);
  }

  @Override
  public User getTrainee(String username) throws RemoteException {
    return model.getTrainee(username);
  }

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
  public boolean requestCoach(String requesterUsername, String accepterUsername) throws RemoteException {
    return model.requestCoach(requesterUsername, accepterUsername);
  }

  @Override
  public User getCoach(String traineeUsername) throws RemoteException {
    return model.getCoach(traineeUsername);
  }

  @Override
  public boolean isCoach(String username) throws RemoteException {
    return model.isCoach(username);
  }

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

  @Override public boolean denyRequest(String traineeUsername)
      throws RemoteException
  {
    return model.denyRequest(traineeUsername);
  }

  @Override public boolean removeTraineeFromRoster(String traineeUsername)
      throws RemoteException
  {
    return model.removeTraineeFromRoster(traineeUsername);
  }

  @Override public ArrayList<String> getTraineeList(String username)
      throws RemoteException
  {
    return model.getTraineeList(username);
  }

  @Override public ArrayList<String> getTraineeRequest(String username)
      throws RemoteException
  {
    return model.getTraineeRequest(username);
  }

  @Override public boolean removeMeeting(String traineeUsername, String coachName, LocalDate date)
      throws RemoteException
  {
    return model.removeMeeting( traineeUsername, coachName, date);
  }

  @Override public ArrayList<LocalDate> getTakenDates(String coachUsername) throws RemoteException
  {
    return model.getTakenDates(coachUsername);
  }

  @Override public ArrayList<String> getMeetingRequests(String coach)
      throws RemoteException
  {
    return model.getMeetingRequests(coach);
  }

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
  public boolean approveMeeting(String trainee, String coach, LocalDate date) throws RemoteException {
    return model.approveMeeting(trainee, coach, date);
  }

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

  @Override
  public boolean acceptFriendRequest(String requester_username, String accepter_username) {
      return model.acceptFriendRequest(requester_username, accepter_username);

  }

  @Override
  public boolean rejectFriendRequest(String requester_username, String accepter_username) {
      return model.rejectFriendRequest(requester_username, accepter_username);
  }

  @Override
  public FriendList getFriends(String username) {
      return model.getFriends(username);
  }

  @Override
  public ArrayList<String> getFriendRequests(String username) {
      return model.getFriendRequests(username);
  }

  @Override
  public boolean addListener(GeneralListener<String, String> listener, String... propertyNames) throws RemoteException {
    property.addListener(listener,propertyNames);
    return true;
  }

  @Override
  public boolean removeListener(GeneralListener<String, String> listener, String... propertyNames) throws RemoteException {
    property.removeListener(listener,propertyNames);
    return true;
  }
}
