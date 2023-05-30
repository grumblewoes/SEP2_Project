package mediator;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The FriendList class represents a list of friends.
 * FriendList objects are serializable.
 *
 * @author Damian Trafiałek
 * @version 1.0
 */
public class FriendList implements Serializable {
    private ArrayList<Friend> list;
    /**
     * 0-argument constructor, creating a new FriendList object with an empty list of friends.
     *
     *
     */
    public FriendList(){
        list= new ArrayList<>();
    }

    /**
     * Adds a friend to the list.
     *
     * @param friend the friend to add
     */
    public void add(Friend friend){
        list.add(friend);
    }
    /**
     * Retrieves the friend at the specified index.
     *
     * @param i the index of the friend to retrieve
     * @return the friend at the specified index
     */
    public Friend get(int i){return list.get(i);}
    /**
     * Returns the number of friends in the list.
     *
     * @return the size of the friend list
     */
    public int size(){return list.size();}

    @Override
    /**
     * Returns a string representation of the FriendList object.
     *
     * @return a string representation of the FriendList object
     */
    public String toString() {
        return "FriendList{" +
                "list=" + list +
                '}';
    }
}
