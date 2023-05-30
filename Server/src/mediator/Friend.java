package mediator;

import java.io.Serializable;

/**
 * Class that stores friend data.
 * 
 * 
 * @author Damian Trafiałek
 * @version 1.0
 */
public class Friend implements Serializable {
    private String username,status;

    /**
     * 2-argument constructor that initialises the object.
     * 
     * 
     * @param username -string value
     *        
     * @param status -string value
     *        
     */
    public Friend(String username, String status) {
        this.username = username;
        this.status = status;
    }
    /**
     * 1-argument constructor that initialises the object with null status.
     * 
     * 
     * @param username - string value
     *        
     */
    public Friend(String username) {
        this.username = username;
        this.status = null;
    }

    /**
     * Returns username of the friend.
     * 
     *
     * @return string
     *        
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of teh friend.
     * 
     * @param username - string value
     *        
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the status of the friend.
     * 
     *
     * @return string
     *        
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status of the friend.
     * 
     * @param status 
     *        
     */
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    /**
     * Casts object to the string
     * 
     *
     * @return string value
     *        
     */
    public String toString() {
        return "Friend{" +
                "username='" + username + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
