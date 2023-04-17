package modelServer;

import java.util.ArrayList;

public class TraineeList {


	private ArrayList<User> users;

	public TraineeList() {
		users = new ArrayList<>();
	}

	public void addUser(User user) {
		users.add(user);
	}
	public User getUserByUsername (String username) {

		if(username == null) return null;

		for(User u: users)
			if(username.equals(u.getUserName()))
				return u;
		return null;
	}

	public int size() {
		return users.size();
	}
}