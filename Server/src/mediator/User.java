package mediator;

import java.io.Serializable;

/**
 * The User class represents a user in the application.
 *
 *
 * @author Damian Trafiałek
 * @version 1.0
 */
public class User implements Serializable {
    private int height,weight, bench, deadlift, squat;
    private String firstName,lastName,username,status;
    private boolean shareProfile;

    /**
     * 7-argument constructor creating a User object with the specified details.
     *
     * @param height the user's height
     * @param weight the user's weight
     * @param firstName the user's first name
     * @param lastName the user's last name
     * @param username the user's username
     * @param status the user's status
     * @param shareProfile true if the user's profile is shared, false otherwise
     */
    public User(int height, int weight, String firstName, String lastName, String username, String status,boolean shareProfile) {
        this.height = height;
        this.weight = weight;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.status = status;
        this.shareProfile = shareProfile;
    }

    /**
     * 10-argument constructor creating a User object with the specified details including exercise statistics.
     *
     * @param height the user's height
     * @param weight the user's weight
     * @param firstName the user's first name
     * @param lastName the user's last name
     * @param username the user's username
     * @param status the user's status
     * @param shareProfile true if the user's profile is shared, false otherwise
     * @param deadlift the user's deadlift weight
     * @param squat the user's squat weight
     * @param bench the user's bench press weight
     */
    public User(int height, int weight, String firstName, String lastName, String username, String status,boolean shareProfile, int deadlift, int squat, int bench) {
        this.height = height;
        this.weight = weight;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.status = status;
        this.shareProfile = shareProfile;
        this.deadlift=deadlift;
        this.squat=squat;
        this.bench=bench;
    }

    /**
     * Returns the user's height.
     *
     * @return the user's height
     */
    public int getHeight() {
        return height;
    }
    /**
     * Sets the user's height.
     *
     * @param height the user's height to set
     */
    public void setHeight(int height) {
        this.height = height;
    }
    /**
     * Returns the user's weight.
     *
     * @return the user's weight
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Sets the user's weight.
     *
     * @param weight the user's weight to set
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }
    /**
     * Returns the user's first name.
     *
     * @return the user's first name
     */
    public String getFirstName() {
        return firstName;
    }
    /**
     * Sets the user's first name.
     *
     * @param firstName the user's first name to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns the user's last name.
     *
     * @return the user's last name
     */
    public String getLastName() {
        return lastName;
    }
    /**
     * Sets the user's last name.
     *
     * @param lastName the user's last name to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Returns the user's username.
     *
     * @return the user's username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the user's username.
     *
     * @param username the user's username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
     * Returns the user's status.
     *
     * @return the user's status
     */
    public String getStatus() {
        return status;
    }
    /**
     * Sets the user's status.
     *
     * @param status the user's status to set
     */
    public void setStatus(String status) {this.status = status;}

    /**
     * Returns whether the user's profile is shared.
     *
     * @return true if the user's profile is shared, false otherwise
     */
    public boolean isShareProfile() { return shareProfile; }
    /**
     * Sets whether the user's profile is shared.
     *
     * @param shareProfile true if the user's profile is shared, false otherwise
     */
    public void setShareProfile(boolean shareProfile) {  this.shareProfile = shareProfile;  }

    /**
     * Returns the user's bench press weight.
     *
     * @return the user's bench press weight
     */
    public int getBench()
    {
        return bench;
    }
    /**
     * Returns the user's deadlift weight.
     *
     * @return the user's deadlift weight
     */
    public int getDeadlift()
    {
        return deadlift;
    }

    /**
     * Returns the user's squat weight.
     *
     * @return the user's squat weight
     */
    public int getSquat()
    {
        return squat;
    }
}
