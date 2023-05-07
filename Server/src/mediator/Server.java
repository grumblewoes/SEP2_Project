package mediator;

import modelServer.Model;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Server implements RemoteModel
{
  private Model model;

  public Server(Model model){
    this.model = model;
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
  public boolean updateTrainee(String u, int h, int w,boolean s) throws RemoteException {
    return model.updateTrainee(u,h,w,s);
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
}
