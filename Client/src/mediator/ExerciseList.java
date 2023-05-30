package mediator;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class that stores the list of exercises data.
 *
 *
 * @author Damian Trafiałek
 * @version 1.0
 */
public class ExerciseList implements Serializable {
    private ArrayList<Exercise> list;
    /**
     * 0-argument constructor that initialises the list.
     * 
     * 
     */
    public ExerciseList(){
        list= new ArrayList<>();
    }

    /**
     * Method that adds the exercises to the list.
     * 
     * @param exercise - exercise class
     *        
     */
    public void add(Exercise exercise){
        list.add(exercise);
    }
    /**
     * Method that returns the exercises under given index in the list.
     * 
     * @param i - integer
     *        
     *
     * @return exercise
     *        
     */
    public Exercise get(int i){return list.get(i);}
    /**
     * Method that returns the size of the list.
     * 
     *
     * @return integer
     *        
     */
    public int size(){return list.size();}

    /**
     * Method that filters the exercises in the list and returns the exerciseList of those that match the name.
     * 
     * @param name - string value
     *        
     *
     * @return ExercsieList
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
     * Method that casts the objects to string and returns it.
     *
     *
     * @return String - object casted to String
     *
     */
    public String toString() {
        return "ExerciseList{" +
                "list=" + list +
                '}';
    }

    /**
     * Method that compares the objects if they are the same.
     *
     * @param o - some object
     *
     *
     * @return boolean value
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
