package modelServer;


public abstract class User {

	private String firstName;

	private String lastName;

	private String userName;

	private String password;

	private int weight;

	private int height;

	public User(String firstName, String lastName, String userName, String password, int weight, int height) {
		setFirstName(firstName);
		setLastName(lastName);
		setUserName(userName);
		setHeight(height);
		setWeight(weight);
		setPassword(password);

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

	public void setFirstName(String firstName){
		if(firstName==null || firstName.length() == 0) throw new IllegalArgumentException("Invalid firstName");
		this.firstName=firstName;
	}
	public void setLastName(String lastName){
		if(lastName==null || lastName.length() == 0) throw new IllegalArgumentException("Invalid lastName");
		this.lastName=lastName;
	}
	public void setUserName(String userName){
		if(userName==null || userName.length() == 0) throw new IllegalArgumentException("Invalid userName");
		this.userName=userName;
	}
	public void setPassword(String password){
		if(password==null || password.length() == 0) throw new IllegalArgumentException("Invalid password");
		this.password=password;
	}
	public void setHeight(int height){
		if(height<=0) throw new IllegalArgumentException("Invalid weight");
		this.height=height;
	}
	public void setWeight(int weight){
		if(weight<=0) throw new IllegalArgumentException("Invalid weight");
		this.weight=weight;
	}

	@Override public String toString(){
		return getFirstName()+"-"+getLastName()+"-"+getUserName();
	}

}
