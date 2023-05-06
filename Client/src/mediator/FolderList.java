package mediator;

import java.io.Serializable;
import java.util.ArrayList;

public class FolderList implements Serializable {
    private ArrayList<Folder> list;
    public FolderList(){
        list= new ArrayList<>();
    }

    public void add(Folder folder){
        list.add(folder);
    }
    public Folder get(int i){return list.get(i);}
    public int size(){return list.size();}
}
