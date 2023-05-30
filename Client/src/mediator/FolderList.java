package mediator;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class that stores the list of folders data.
 *
 *
 * @author Damian Trafiałek
 * @version 1.0
 */
public class FolderList implements Serializable {
    private ArrayList<Folder> list;
    /**
     * 0-argument constructor that initialises the list.
     * 
     * 
     */
    public FolderList(){
        list= new ArrayList<>();
    }

    /**
     * Add the folder to the list.
     * 
     * @param folder - Folder
     *        
     */
    public void add(Folder folder){
        list.add(folder);
    }
    /**
     * Retunrs the folder under given index in the list.
     * 
     * @param i - integer
     *        
     *
     * @return Folder
     *        
     */
    public Folder get(int i){return list.get(i);}
    /**
     * Returns the size of the list.
     * 
     *
     * @return integer
     *        
     */
    public int size(){return list.size();}
}
