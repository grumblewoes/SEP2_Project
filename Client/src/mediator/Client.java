package mediator;

import modelClient.Model;

import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
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

  public boolean removeFolder(String username, String name)
  {
    try
    {
      return server.removeFolder(username, name);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
  }

  public boolean editFolder(String username, String oldName, String newName)
  {
    try
    {
      return server.editFolder(username, oldName, newName);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }

  }

  public ArrayList<String> getFolderList(String username)
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

  @Override public boolean editHeight(int height)
  {
    try
    {
      return server.editHeight(height);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
  }

  @Override public boolean editWeight(int weight)
  {
    try
    {
      return server.editWeight(weight);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
  }

  @Override public boolean editDob(int dob)
  {
    try
    {
      return server.editDob(dob);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
  }

  @Override public boolean editDeadlift(int weight)
  {
    try
    {
      return server.editDeadLift(weight);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
  }

  @Override public boolean editBenchPress(int weight)
  {
    try
    {
      return server.editBenchPress(weight);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
  }

  @Override public boolean editSquat(int weight)
  {
    try
    {
      return server.editSquat(weight);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
  }

  @Override public boolean addExercise(String username, String folderName,
      String exerciseName, int repetitions, int weight)
  {
    try
    {
      return server.addExercise(username, folderName, exerciseName, repetitions,
          weight);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
  }

  @Override public boolean removeExercise(String username, String folderName,
      String exerciseName)
  {
    try
    {
      return server.removeExercise(username, folderName, exerciseName);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
  }

  @Override public ArrayList<String> getExerciseList(String username,
      String folderName)
  {

    try
    {
      return server.getExerciseList(username, folderName);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
  }

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

}

