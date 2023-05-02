package modelClient;

import java.util.ArrayList;

public interface Model
{

	public abstract boolean createUser(String firstName, String lastName, String userName, String password, int height, int weight);
	public boolean login(String username, String password);
	boolean createFolder(String username, String name);
	boolean removeFolder(String username, String name);
	boolean editFolder(String username, String oldName, String newName);
	ArrayList<String> getFolderList(String username);
}
