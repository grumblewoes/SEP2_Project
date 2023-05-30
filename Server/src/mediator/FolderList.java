package mediator;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The FolderList class represents a list of folders.
 * FolderList objects are serializable.
 *
 * @author Damian Trafiałek
 * @version 1.0
 */
public class FolderList implements Serializable {
    private ArrayList<Folder> list;
    /**
     * 0-argument constructor, creating a new FolderList object with an empty list of folders.
     * 
     * 
     */
    public FolderList(){
        list= new ArrayList<>();
    }

    /**
     * Adds a folder to the list.
     *
     * @param folder the folder to add
     */
    public void add(Folder folder){
        list.add(folder);
    }
    /**
     * Retrieves the folder at the specified index.
     *
     * @param i the index of the folder to retrieve
     * @return the folder at the specified index
     */
    public Folder get(int i){return list.get(i);}
    /**
     * Returns the number of folders in the list.
     *
     * @return the size of the folder list
     */
    public int size(){return list.size();}
}
