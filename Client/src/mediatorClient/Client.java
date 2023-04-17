package mediatorClient;


import modelClient.Model;
import utility.observer.event.ObserverEvent;
import utility.observer.javaobserver.NamedPropertyChangeSubject;
import utility.observer.listener.GeneralListener;
import utility.observer.listener.RemoteListener;
import utility.observer.subject.LocalSubject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;

public class Client<S,T> implements Model, LocalSubject<S,T>, RemoteListener
{

	private RemoteModel remoteModel;

	private PropertyChangeSupport property;

	public Client() {
		this.property = new PropertyChangeSupport(this);
	}


	public boolean createUser(String firstName, String lastName, String userName, String password, int height, int weight) {
		return false;
	}



	public void propertyChange(ObserverEvent evt) throws RemoteException {
		this.property.firePropertyChange(
				evt.getPropertyName(),
				evt.getValue1(),
				evt.getValue2()
		);
	}




	@Override
	public boolean addListener(GeneralListener<S, T> listener, String... propertyNames) {
		property.addPropertyChangeListener(  listener, propertyNames);
		return true;
	}

	@Override
	public boolean removeListener(GeneralListener<S, T> listener, String... propertyNames) {
		return true;
	}
}
