package mediator;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class that stores list of friends data.
 *
 *
 * @author Damian Trafiałek
 * @version 1.0
 */
public class FriendList implements Serializable {
    private ArrayList<Friend> list;
    /**
     * 0-argument constructor that initialises the list.
     * 
     * 
     */
    public FriendList(){
        list= new ArrayList<>();
    }

    /**
     * Method that adds the friend to the list.
     * 
     * @param friend Friend
     *        
     */
    public void add(Friend friend){
        list.add(friend);
    }
    /**
     * Method that returns the friend under given index in the list.
     * 
     * @param i - integer
     *        
     *
     * @return Friend
     *        
     */
    public Friend get(int i){return list.get(i);}
    /**
     * Returns the size of the list.
     * 
     *
     * @return integer
     *        
     */
    public int size(){return list.size();}

    @Override
    /**
     * Casts the object to string
     * 
     *
     * @return string value.
     *        
     */
    public String toString() {
        return "FriendList{" +
                "list=" + list +
                '}';
    }
}
