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
public class FolderList implements Serializable {
    private ArrayList<Folder> list;
    /**
     * 0-argument constructor 
     * 
     * 
     */
    public FolderList(){
        list= new ArrayList<>();
    }

    /**
     * 
     * 
     * @param folder 
     *        
     */
    public void add(Folder folder){
        list.add(folder);
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
    public Folder get(int i){return list.get(i);}
    /**
     * 
     * 
     *
     * @return 
     *        
     */
    public int size(){return list.size();}
}
