package mediator;

import java.io.Serializable;

/**
 * The Friend class represents a friend with its attributes such as username and status.
 * Friend objects are serializable.
 *
 * @author Damian Trafiałek
 * @version 1.0
 */
public class Friend implements Serializable {
    private String username,status;

    /**
     * 2-argument constructor, creating a Friend object with the specified username and status.
     *
     *
     * @param username
     *
     * @param status
     *
     */
    public Friend(String username, String status) {
        this.username = username;
        this.status = status;
    }
    /**
     * 1-argument constructor, creating a Friend object with the specified username and no status.
     *
     *
     * @param username
     *
     */
    public Friend(String username) {
        this.username = username;
        this.status = null;
    }


    /**
     * Returns the username of the friend.
     *
     * @return the friend's username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the friend.
     *
     * @param username the friend's username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the status of the friend.
     *
     * @return the friend's status
     */
    public String getStatus() {
        return status;
    }
    /**
     * Sets the status of the friend.
     *
     * @param status the friend's status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    /**
     * Returns a string representation of the Friend object.
     *
     * @return a string representation of the Friend object
     */
    public String toString() {
        return "Friend{" +
                "username='" + username + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
