package mediator;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 
 * 
 * 
 * @author 
 * @version 
 */
public class FriendList implements Serializable {
    private ArrayList<Friend> list;
    /**
     * 0-argument constructor 
     * 
     * 
     */
    public FriendList(){
        list= new ArrayList<>();
    }

    /**
     * 
     * 
     * @param friend 
     *        
     */
    public void add(Friend friend){
        list.add(friend);
    }
    /**
     * 
     * 
     * @param i 
     *        
     *
     * @return 
     *        
     */
    public Friend get(int i){return list.get(i);}
    /**
     * 
     * 
     *
     * @return 
     *        
     */
    public int size(){return list.size();}

    @Override
    /**
     * 
     * 
     *
     * @return 
     *        
     */
    public String toString() {
        return "FriendList{" +
                "list=" + list +
                '}';
    }
}
