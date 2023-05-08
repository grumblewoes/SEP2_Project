package mediator;

import modelClient.Model;

import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;

public class Client implements Model
{

  private RemoteModel server;

  public Client()
  {
    try
    {
      start();
      server = (RemoteModel) Naming.lookup(
          "rmi://localhost:1099/ValhallaServer");
      //remoteModel.addListener(this);
    }
    catch (Exception e)
    {
      e.printStackTrace();
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
      return server.login(username, password);
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

//
//  @Override
//  public boolean editHeight(int height) {
//    try
//    {
//      return server.editHeight(height);
//    }
//    catch (RemoteException e)
//    {
//      throw new RuntimeException(e);
//    }
//  }
//
//  @Override
//  public boolean editWeight(int weight) {
//    try
//    {
//      return server.editWeight(weight);
//    }
//    catch (RemoteException e)
//    {
//      throw new RuntimeException(e);
//    }
//  }
//
//  @Override
//  public boolean editDob(int dob) {
//    try
//    {
//      return server.editDob(dob);
//    }
//    catch (RemoteException e)
//    {
//      throw new RuntimeException(e);
//    }
//  }
//
//  @Override
//  public boolean editDeadlift(int weight) {
//    try
//    {
//      return server.editDeadLift(weight);
//    }
//    catch (RemoteException e)
//    {
//      throw new RuntimeException(e);
//    }
//  }
//
//  @Override
//  public boolean editBenchPress(int weight) {
//    try
//    {
//      return server.editBenchPress(weight);
//    }
//    catch (RemoteException e)
//    {
//      throw new RuntimeException(e);
//    }
//  }
//
//  @Override
//  public boolean editSquat(int weight) {
//    try
//    {
//      return server.editSquat(weight);
//    }
//    catch (RemoteException e)
//    {
//      throw new RuntimeException(e);
//    }
//  }


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

  @Override public boolean updateTrainee(String u, int h, int w,boolean s){
    try{
      return server.updateTrainee(u,h,w,s);
    }catch (RemoteException e){
      throw new RuntimeException(e);
    }
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

  @Override public FriendList getFriendList(String username)
  {
    try{
      return server.getFriendList(username);
    }catch(RemoteException e){
      return null;
    }
  }
}

