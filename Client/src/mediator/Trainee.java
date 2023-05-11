package mediator;

public class Trainee
{

  private String username;
  private int id;

  public Trainee(String username, int id){
    this.username = username;
  }

  public String getUsername(){
    return username;
  }

  public int getId(){
    return id;
  }

  public String toString(){
    return "The trainee's username: " + username;
  }



}
