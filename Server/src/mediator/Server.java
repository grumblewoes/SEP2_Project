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

  @Override public boolean removeFolder(String username, String name)
  {
    return model.removeFolder(username, name);
  }

  @Override public boolean editFolder(String username, String oldName,
      String newName)
  {
    return model.editFolder(username, oldName, newName);
  }

  @Override public ArrayList<String> getFolderList(String username)
  {
    return model.getFolderList(username);
  }

  @Override public boolean addExercise(String username, String name) throws RemoteException
  {
    return model.addExercise(username, name);
  }

  @Override public boolean removeExercise(String username, String name) throws RemoteException
  {
    return model.removeExercise(username, name);
  }

  @Override public ArrayList<String> getExerciseList(String username)
      throws RemoteException
  {
    return model.getExercise(username, getFolderList(username).)
  }

  @Override public double editHeight(double height)
  {
    return model.editHeight(height);
  }

  @Override public double editWeight(double weight)
  {
    return model.editWeight(weight);
  }

  @Override public String editDob(String dob)
  {
    return model.editDob(dob);
  }

  @Override public boolean editDeadlift(int weight)
  {
    return model.editDeadlift(weight);
  }

  @Override public boolean editBenchPress(int weight)
  {
    return model.editBenchPress(weight);
  }

  @Override public boolean editSquat(int weight)
  {
    return model.editSquat(weight);
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
