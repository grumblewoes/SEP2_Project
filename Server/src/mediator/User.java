package mediator;

import java.io.Serializable;

/**
 * 
 * 
 * 
 * @author 
 * @version 
 */
public class User implements Serializable {
    private int height,weight, bench, deadlift, squat;
    private String firstName,lastName,username,status;
    private boolean shareProfile;

    /**
     * 7-argument constructor initialising instance variables without the bench,deadlift and squat.
     * 
     * 
     * @param height - integer value
     *        
     * @param weight - integer value
     *        
     * @param firstName - string value
     *        
     * @param lastName - string value
     *        
     * @param username - string value
     *        
     * @param status - string value
     *        
     * @param shareProfile - boolean value
     *        
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
     * 10-argument constructor initialising all instance variables.
     * 
     *
     * @param height - integer value
     *
     * @param weight - integer value
     *
     * @param firstName - string value
     *
     * @param lastName - string value
     *
     * @param username - string value
     *
     * @param status - string value
     *
     * @param shareProfile - boolean value
     *        
     * @param deadlift - integer value
     *        
     * @param squat - integer value
     *        
     * @param bench - integer value
     *        
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
     * Returns the height.
     * 
     *
     * @return height integer
     *        
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets the height.
     * 
     * @param height - integer value
     *        
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Returns weight.
     * 
     *
     * @return weight integer
     *        
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Sets the weight.
     * 
     * @param weight - integer value
     *        
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     * Returns the first name.
     * 
     *
     * @return first name string
     *        
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name.
     * 
     * @param firstName - string value
     *        
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * 
     * 
     *
     * @return 
     *        
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * 
     * 
     * @param lastName 
     *        
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * 
     * 
     *
     * @return 
     *        
     */
    public String getUsername() {
        return username;
    }

    /**
     * 
     * 
     * @param username 
     *        
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 
     * 
     *
     * @return 
     *        
     */
    public String getStatus() {
        return status;
    }

    /**
     * 
     * 
     * @param status 
     *        
     */
    public void setStatus(String status) {this.status = status;  }

    /**
     * 
     * 
     *
     * @return 
     *        
     */
    public boolean isShareProfile() { return shareProfile; }

    /**
     * 
     * 
     * @param shareProfile 
     *        
     */
    public void setShareProfile(boolean shareProfile) {  this.shareProfile = shareProfile;  }

    /**
     * 
     * 
     *
     * @return 
     *        
     */
    public int getBench()
    {
        return bench;
    }

    /**
     * 
     * 
     *
     * @return 
     *        
     */
    public int getDeadlift()
    {
        return deadlift;
    }

    /**
     * 
     * 
     *
     * @return 
     *        
     */
    public int getSquat()
    {
        return squat;
    }
}
