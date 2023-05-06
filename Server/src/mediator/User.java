package mediator;

import java.io.Serializable;

public class User implements Serializable {
    private int height,weight;
    private String firstName,lastName,username,gender;
    private boolean shareProfile;

    public User(int height, int weight, String firstName, String lastName, String username, String gender,boolean shareProfile) {
        this.height = height;
        this.weight = weight;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.gender = gender;
        this.shareProfile = shareProfile;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {this.gender = gender;  }

    public boolean isShareProfile() { return shareProfile; }

    public void setShareProfile(boolean shareProfile) {  this.shareProfile = shareProfile;  }
}
