package modelServer;


public abstract class User {

	private String firstName;

	private String lastName;

	private String userName;

	private String password;

	private int weight;

	private int height;

	public User(String firstName, String lastName, String userName, String password, int weight, int height) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.weight = weight;
		this.height = height;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public int getWeight() {
		return weight;
	}

	public int getHeight() {
		return height;
	}

}
