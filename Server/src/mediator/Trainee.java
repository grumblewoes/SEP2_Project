package mediator;

import java.io.Serializable;

/**
 * The Trainee class represents a trainee in the application.
 * It contains information about the trainee such as username, status, and lifted weight.
 * Trainee objects are serializable.
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
   * 1-argument constructor creates a Trainee object with the specified username.
   *
   *   @param username the trainee's username
   *
   */
  public Trainee(String username){
    this.username = username;
  }
  /**
   * 2-argument constructor creates a Trainee object with the specified username and status.
   *
   * @param username the trainee's username
   * @param status the trainee's status
   */
  public Trainee(String username, String status){
    this(username);
    this.status=status;
  }
  /**
   * 2-argument constructor creates a Trainee object with the specified username and lifted weight.
   *
   * @param username the trainee's username
   * @param liftedWeight the trainee's lifted weight
   *
   */
  public Trainee(String username, int liftedWeight){
    this(username);
    this.liftedWeight=liftedWeight;
  }
  /**
   * Returns the trainee's username.
   *
   * @return the trainee's username
   */
  public String getUsername(){
    return username;
  }

  /**
   * Returns the trainee's status.
   *
   * @return the trainee's status
   */
  public String getStatus() {
    return status;
  }


  /**
   * Sets the trainee's status.
   *
   * @param status the trainee's status to set
   */
  public void setStatus(String status) {
    this.status = status;
  }

  /**
   * Returns a string representation of the trainee object.
   *
   * @return a string representation of the trainee object
   */
  public String toString(){
    return "The trainee's username: " + username;
  }

  /**
   * Returns the trainee's lifted weight.
   *
   * @return the trainee's lifted weight
   */
  public int getLiftedWeight()
  {
    return liftedWeight;
  }
}
