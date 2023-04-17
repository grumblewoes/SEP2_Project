package modelClient;

import utility.observer.javaobserver.UnnamedPropertyChangeSubject;

public interface Model extends UnnamedPropertyChangeSubject
{

	public abstract boolean createUser(String firstName, String lastName, String userName, String password, int height, int weight);

}
