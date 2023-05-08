package mediator;

import java.io.Serializable;

public class Friend implements Serializable
{
  private String username;

  public Friend (String username){
    setUsername(username);
  }

  public String getUsername()
  {
    return username;
  }

  public void setUsername(String username)
  {
    this.username = username;
  }
}
