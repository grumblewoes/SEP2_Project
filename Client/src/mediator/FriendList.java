package mediator;

import java.io.Serializable;
import java.util.ArrayList;

public class FriendList implements Serializable {
    private ArrayList<Friend> list;
    public FriendList(){
        list= new ArrayList<>();
    }

    public void add(Friend friend){
        list.add(friend);
    }
    public Friend get(int i){return list.get(i);}
    public int size(){return list.size();}

    @Override
    public String toString() {
        return "FriendList{" +
                "list=" + list +
                '}';
    }
}
