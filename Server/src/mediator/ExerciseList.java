package mediator;

import java.io.Serializable;
import java.util.ArrayList;

public class ExerciseList implements Serializable {
    private ArrayList<Exercise> list;
    public ExerciseList(){
        list= new ArrayList<>();
    }

    public void add(Exercise exercise){
        list.add(exercise);
    }

    public Exercise get(int i){return list.get(i);}
    public int size(){return list.size();}

    public ExerciseList filterByName(String name){
        ExerciseList filteredList = new ExerciseList();
        if(name==null) return filteredList;

        for(Exercise ex : list)
            if(name.equals(ex.getName()))
                filteredList.add(ex);

        return filteredList;
    }

    @Override
    public String toString() {
        return "ExerciseList{" +
                "list=" + list +
                '}';
    }

    @Override public boolean equals(Object o){
        if( o == null || !(o instanceof ExerciseList)) return false;
        ExerciseList l = (ExerciseList) o;
        if(l.size()!=this.size()) return false;
        for(int i=0;i<this.size();++i)
            if( !l.get(i).equals(this.get(i)))
                return false;
        return true;

    }
}
