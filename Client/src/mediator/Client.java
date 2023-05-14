package mediator;

import modelClient.Model;
import util.Logger;
import utility.observer.event.ObserverEvent;
import utility.observer.javaobserver.NamedPropertyChangeSubject;
import utility.observer.listener.GeneralListener;
import utility.observer.listener.RemoteListener;
import utility.observer.subject.LocalSubject;
import utility.observer.subject.PropertyChangeHandler;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Client implements Model, RemoteListener<String, String>, LocalSubject<String,String> {

  private RemoteModel server;
  private PropertyChangeHandler<String,String> property;
  public Client()
  {
    this.property= new PropertyChangeHandler<>(this);
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
  public void connectListener(String username)  {
    try{
      server.addListener(this,username);
    }catch (RemoteException e){
      Logger.log("Unable to connect listener");
    }
  }
  public void disconnectListener(String username)  {
    try{
      server.removeListener(this,username);
    }catch (RemoteException e){
      Logger.log("Unable to disconnect with listener");
    }
  }

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

  public boolean login(String username, String password)
  {
    try
    {
      boolean logged = server.login(username, password);
      if(logged) connectListener(username);
      return logged;
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
  }

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



  public ExerciseList getExerciseList(int folderId) {
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
  public ExerciseList getExerciseListByNameAndFolderId(String name, int folderId) {
    try{
      return server.getExerciseListByNameAndFolderId(name,folderId);
    }catch (RemoteException e){
      throw new RuntimeException(e);
    }
  }

  @Override
  public boolean removeExercise(int exerciseId) {
    try{
      return server.removeExercise(exerciseId);
    }catch (RemoteException e){
      throw new RuntimeException(e);
    }
  }

  @Override
  public boolean removeExercisesByName(String name, int folderId) {
    try{

      return server.removeExercisesByName(name,folderId);
    }catch (RemoteException e){
      throw  new RuntimeException(e);
    }
  }

  @Override
  public boolean addExercise(String username,String exerciseName,int folderId,int weight,int repetitions) {
    try{
      return server.addExercise(username,exerciseName,folderId,weight,repetitions);
    }catch (RemoteException e){
      throw new RuntimeException(e);
    }
  }

  @Override
  public ArrayList<String> getPossibleExercises() {
    try{
      return server.getPossibleExercises();
    }catch (RemoteException e){
      throw  new RuntimeException(e);
    }
  }

  @Override
  public int getBestBenchPress(String username) {
    try{
      return server.getBestBenchPress(username);
    }catch (RemoteException e){
      throw  new RuntimeException(e);
    }
  }

  @Override
  public int getBestSquat(String username) {
    try{
      return server.getBestSquat(username);
    }catch (RemoteException e){
      throw  new RuntimeException(e);
    }
  }

  @Override
  public int getBestDeadlift(String username) {
    try{
      return server.getBestDeadlift(username);
    }catch (RemoteException e){
      throw  new RuntimeException(e);
    }
  }

  @Override
  public User getTrainee(String username) {
    try{
      return server.getTrainee(username);
    }catch (RemoteException e){
      throw  new RuntimeException(e);
    }
  }

  @Override public boolean updateTrainee(String u, int h, int w,boolean s,String st){
    try{
      return server.updateTrainee(u,h,w,s,st);
    }catch (RemoteException e){
      throw new RuntimeException(e);
    }
  }

  @Override
  public boolean acceptFriendRequest(String requester_username, String accepter_username) {
    try{
      return server.acceptFriendRequest(requester_username, accepter_username);
    }catch (RemoteException e){
      throw new RuntimeException(e);
    }
  }

  @Override
  public boolean rejectFriendRequest(String requester_username, String accepter_username) {
    try{
      return server.rejectFriendRequest(requester_username, accepter_username);
    }catch (RemoteException e){
      throw new RuntimeException(e);
    }
  }

  @Override
  public FriendList getFriends(String username) {
    try{
      return server.getFriends(username);
    }catch (RemoteException e){
      throw new RuntimeException(e);
    }
  }

  @Override
  public ArrayList<String> getFriendRequests(String username) {
    try{
      return server.getFriendRequests(username);
    }catch (RemoteException e){
      throw new RuntimeException(e);
    }
  }

  @Override
  public void propertyChange(ObserverEvent<String, String> event) {
    String name = event.getPropertyName();
    String val1 = event.getValue1();
    String val2 = event.getValue2();
    Logger.log("Received notification: "+name+", "+val1+", "+val2);
    property.firePropertyChange(name,val1,val2);
  }



  @Override
  public boolean addListener(GeneralListener<String, String> listener, String... propertyNames) {
    property.addListener(listener,propertyNames);
    return true;
  }

  @Override
  public boolean removeListener(GeneralListener<String, String> listener, String... propertyNames) {
    property.addListener(listener,propertyNames);
    return true;
  }
  @Override public boolean sendFriendRequest(String requesterUsername,
      String accepterUsername)
  {
    try{
      return server.sendFriendRequest(requesterUsername,accepterUsername);
    }catch(RemoteException e){
      return false;
    }
  }

  @Override public boolean removeFriend(String requesterUsername,
      String accepterUsername)
  {
    try{
      return server.removeFriend(requesterUsername,accepterUsername);
    }catch(RemoteException e){
      return false;
    }
  }

  @Override
  public boolean requestCoach(String requesterUsername, String accepterUsername) {
    try{
      return server.requestCoach(requesterUsername, accepterUsername);
    }catch(RemoteException e){
      return false;
    }
  }

  @Override
  public User getCoach(String traineeUsername) {
    try{
      return server.getCoach(traineeUsername);
    }catch(RemoteException e){
      return null;
    }

  }

  @Override
  public boolean isCoach(String username) {
    try{
      return server.isCoach(username);
    }catch(RemoteException e){
      return false;
    }
  }

  @Override public boolean removeCoachAssignment(String traineeUsername)
  {
    try{
      return server.removeCoachAssignment(traineeUsername);
    }catch(RemoteException e){
      return false;
    }
  }

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

   public ArrayList<String> getTraineeList(String username){
     try
     {
       return server.getTraineeList(username);
     }
     catch (RemoteException e)
     {
       throw new RuntimeException(e);
     }
   }
  public ArrayList<String> getTraineeRequest(String username) {
    try
    {
      return server.getTraineeRequest(username);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
  }

  @Override public MeetingList getCoachMeetingList(String coachUsername)
  {
    try
    {
      return server.getCoachMeetingList(coachUsername);
    }
    catch (RemoteException e)
    {
      return null;
    }
  }

  @Override public boolean removeMeeting(String coachName, LocalDate date)
  {
    try
    {
      return server.removeMeeting(coachName, date);
    }
    catch (RemoteException e)
    {
      return false;
    }
  }


}

