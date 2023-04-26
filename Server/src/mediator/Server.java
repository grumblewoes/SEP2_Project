package mediator;

import modelServer.Model;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

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
