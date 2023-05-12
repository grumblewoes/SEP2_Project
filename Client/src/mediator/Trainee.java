package mediator;

public class Trainee
{

  private String username;

  public Trainee(String username){
    this.username = username;
  }

  public String getUsername(){
    return username;
  }

  public String toString(){
    return "The trainee's username: " + username;
  }



}
