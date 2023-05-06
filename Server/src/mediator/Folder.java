package mediator;

import java.io.Serializable;

public class Folder implements Serializable {
    private int id;
    private String title,owner;

    public Folder(int id, String title, String owner) {
        this.id = id;
        this.title = title;
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
