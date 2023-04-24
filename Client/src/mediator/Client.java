package mediator;


import modelClient.Model;

import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Client implements Model, Remote
{

	private RemoteModel server;


	public Client()
	{
		try
		{
			start();
			server = (RemoteModel) Naming.lookup("rmi://localhost:1099/ValhallaServer");
			//remoteModel.addListener(this);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void start() {
		try {
			UnicastRemoteObject.exportObject(this, 0);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}


	public boolean createUser(String firstName, String lastName, String userName, String password, int height, int weight) {
		try
		{
			return server.createUser(firstName, lastName, userName, password, height, weight);
		}
		catch (RemoteException e)
		{
			throw new RuntimeException(e);
		}
	}


	public boolean login(String username, String password){
		try
		{
			return server.login(username, password);
		}
		catch (RemoteException e)
		{
			throw new RuntimeException(e);
		}
	}

}

