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

	public int size() {
		return users.size();
	}
}