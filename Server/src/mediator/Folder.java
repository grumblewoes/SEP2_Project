package mediator;

import java.io.Serializable;

/**
 * Class that stores folder data.
 *
 *
 * @author Damian Trafiałek
 * @version 1.0
 */
public class Folder implements Serializable {
    private int id;
    private String title,owner;

    /**
     * 3-argument constructor that creates all the instace variables.
     * 
     * 
     * @param id - integer
     *        
     * @param title - string
     *        
     * @param owner - string
     *        
     */
    public Folder(int id, String title, String owner) {
        this.id = id;
        this.title = title;
        this.owner = owner;
    }

    /**
     * Returns the folder's id.
     *
     *
     * @return id
     *
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id of the folder.
     *
     * @param id - integer
     *
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the folder's title.
     *
     *
     * @return title
     *
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the folder.
     *
     * @param title - integer
     *
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns folder's owner.
     * 
     *
     * @return owner string value
     *        
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Sets folder's owner.
     * 
     * @param owner 
     *        
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }
}
