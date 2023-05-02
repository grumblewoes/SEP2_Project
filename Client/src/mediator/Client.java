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

  public boolean addExercise(String username, String name) {
    try {
      return server.addExercise(username, name);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
  }

  public boolean removeExercise(String username, String name) {
    try
    {
      return server.removeExercise(username, name);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
  }

  public ArrayList<String> getExerciseList(String username) {
    try
    {
      return server.getExerciseList(username);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
  }

}

