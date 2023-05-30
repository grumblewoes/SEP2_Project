package mediator;

import java.io.Serializable;

/**
 * The Folder class represents a folder with its attributes such as id, title, and owner.
 * Folder objects are serializable.
 *
 *
 * @author Damian Trafiałek
 * @version 1.0
 */
public class Folder implements Serializable {
    private int id;
    private String title,owner;

    /**
     * 3-argument constructor, creating a Folder object with the specified id, title, and owner.
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
     * Returns the id of the folder.
     *
     * @return the folder id
     */
    public int getId() {
        return id;
    }
    /**
     * Sets the id of the folder.
     *
     * @param id the folder id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the title of the folder.
     *
     * @return the folder title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the folder.
     *
     * @param title the folder title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns the owner of the folder.
     *
     * @return the folder owner
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Sets the owner of the folder.
     *
     * @param owner the folder owner to set
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }
}
