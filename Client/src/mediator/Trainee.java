package mediator;

import java.io.Serializable;

/**
 * Class that stores data of trainee.
 * 
 * 
 * @author Damian Trafiałek
 * @version 1.0
 */
public class Trainee implements Serializable
{

  private String username,status;
  private int liftedWeight;

  /**
   * 1-argument constructor that initialises username leaving status and liftedWeight with default value.
   * 
   * 
   * @param username - string value
   *        
   */
  public Trainee(String username){
    this.username = username;
  }
  /**
   * 2-argument constructor that initialises username and status leaving and liftedWeight with default value
   * 
   * 
   * @param username -string value
   *        
   * @param status -string value
   *        
   */
  public Trainee(String username, String status){
    this(username);
    this.status=status;
  }
  /**
   * 2-argument constructor that initialises username and liftedWeight leaving status with default value
   * 
   * 
   * @param username - string value
   *        
   * @param liftedWeight - integer value
   *        
   */
  public Trainee(String username, int liftedWeight){
    this(username);
    this.liftedWeight=liftedWeight;
  }

  /**
   * Method that get the username.
   * 
   *
   * @return string username
   *        
   */
  public String getUsername(){
    return username;
  }

  /**
   * Method that getsw the status string.
   * 
   *
   * @return status string
   *        
   */
  public String getStatus() {
    return status;
  }

  /**
   * Method that sets the string
   * 
   * @param status -string
   *        
   */
  public void setStatus(String status) {
    this.status = status;
  }

  /**
   * Casts the object to string.
   * 
   *
   * @return string
   *        
   */
  public String toString(){
    return "The trainee's username: " + username;
  }

  /**
   * Returns the lifted weight of the user.
   * 
   *
   * @return lifted weight integer
   *        
   */
  public int getLiftedWeight()
  {
    return liftedWeight;
  }
}
