package mediator;

import java.io.Serializable;

/**
 * 
 * 
 * 
 * @author 
 * @version 
 */
public class Friend implements Serializable {
    private String username,status;

    /**
     * 2-argument constructor 
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
     * 1-argument constructor 
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
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    /**
     * 
     * 
     *
     * @return 
     *        
     */
    public String toString() {
        return "Friend{" +
                "username='" + username + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
