package mediator;

import java.io.Serializable;

/**
 * 
 * 
 * 
 * @author 
 * @version 
 */
public class Folder implements Serializable {
    private int id;
    private String title,owner;

    /**
     * 3-argument constructor 
     * 
     * 
     * @param id 
     *        
     * @param title 
     *        
     * @param owner 
     *        
     */
    public Folder(int id, String title, String owner) {
        this.id = id;
        this.title = title;
        this.owner = owner;
    }

    /**
     * 
     * 
     *
     * @return 
     *        
     */
    public int getId() {
        return id;
    }

    /**
     * 
     * 
     * @param id 
     *        
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 
     * 
     *
     * @return 
     *        
     */
    public String getTitle() {
        return title;
    }

    /**
     * 
     * 
     * @param title 
     *        
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 
     * 
     *
     * @return 
     *        
     */
    public String getOwner() {
        return owner;
    }

    /**
     * 
     * 
     * @param owner 
     *        
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }
}
