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
public class ExerciseList implements Serializable {
    private ArrayList<Exercise> list;
    /**
     * 0-argument constructor 
     * 
     * 
     */
    public ExerciseList(){
        list= new ArrayList<>();
    }

    /**
     * 
     * 
     * @param exercise 
     *        
     */
    public void add(Exercise exercise){
        list.add(exercise);
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
    public Exercise get(int i){return list.get(i);}
    /**
     * 
     * 
     *
     * @return 
     *        
     */
    public int size(){return list.size();}

    /**
     * 
     * 
     * @param name 
     *        
     *
     * @return 
     *        
     */
    public ExerciseList filterByName(String name){
        ExerciseList filteredList = new ExerciseList();
        if(name==null) return filteredList;

        for(Exercise ex : list)
            if(name.equals(ex.getName()))
                filteredList.add(ex);

        return filteredList;
    }

    @Override
    /**
     * 
     * 
     *
     * @return 
     *        
     */
    public String toString() {
        return "ExerciseList{" +
                "list=" + list +
                '}';
    }

    /**
     * 
     * 
     * @param o 
     *        
     *
     * @return 
     *        
     */
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
