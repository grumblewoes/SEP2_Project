package mediator;

import java.io.Serializable;

/**
 * 
 * 
 * 
 * @author 
 * @version 
 */
public class Trainee implements Serializable
{

  private String username,status;
  private int liftedWeight;

  /**
   * 1-argument constructor 
   * 
   * 
   * @param username 
   *        
   */
  public Trainee(String username){
    this.username = username;
  }
  /**
   * 2-argument constructor 
   * 
   * 
   * @param username 
   *        
   * @param status 
   *        
   */
  public Trainee(String username, String status){
    this(username);
    this.status=status;
  }
  /**
   * 2-argument constructor 
   * 
   * 
   * @param username 
   *        
   * @param liftedWeight 
   *        
   */
  public Trainee(String username, int liftedWeight){
    this(username);
    this.liftedWeight=liftedWeight;
  }

  /**
   * 
   * 
   *
   * @return 
   *        
   */
  public String getUsername(){
    return username;
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

  /**
   * 
   * 
   *
   * @return 
   *        
   */
  public String toString(){
    return "The trainee's username: " + username;
  }

  /**
   * 
   * 
   *
   * @return 
   *        
   */
  public int getLiftedWeight()
  {
    return liftedWeight;
  }
}
