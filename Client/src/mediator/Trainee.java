package mediator;

import java.io.Serializable;

public class Trainee implements Serializable
{

  private String username,status;
  private int liftedWeight;

  public Trainee(String username){
    this.username = username;
  }
  public Trainee(String username, String status){
    this(username);
    this.status=status;
  }
  public Trainee(String username, int liftedWeight){
    this(username);
    this.liftedWeight=liftedWeight;
  }

  public String getUsername(){
    return username;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String toString(){
    return "The trainee's username: " + username;
  }

  public int getLiftedWeight()
  {
    return liftedWeight;
  }
}
