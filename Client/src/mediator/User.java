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
     * 7-argument constructor 
     * 
     * 
     * @param height 
     *        
     * @param weight 
     *        
     * @param firstName 
     *        
     * @param lastName 
     *        
     * @param username 
     *        
     * @param status 
     *        
     * @param shareProfile 
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
     * 10-argument constructor 
     * 
     * 
     * @param height 
     *        
     * @param weight 
     *        
     * @param firstName 
     *        
     * @param lastName 
     *        
     * @param username 
     *        
     * @param status 
     *        
     * @param shareProfile 
     *        
     * @param deadlift 
     *        
     * @param squat 
     *        
     * @param bench 
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
     * 
     * 
     *
     * @return 
     *        
     */
    public int getHeight() {
        return height;
    }

    /**
     * 
     * 
     * @param height 
     *        
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * 
     * 
     *
     * @return 
     *        
     */
    public int getWeight() {
        return weight;
    }

    /**
     * 
     * 
     * @param weight 
     *        
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     * 
     * 
     *
     * @return 
     *        
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * 
     * 
     * @param firstName 
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
