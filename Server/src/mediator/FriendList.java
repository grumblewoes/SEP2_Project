package mediator;

import java.io.Serializable;
import java.util.ArrayList;

public class FriendList implements Serializable
{
  private ArrayList<Friend> list;

  public FriendList(){
    this.list=new ArrayList<>();
  }

  public void add(Friend friend){
    list.add(friend);
  }
}
