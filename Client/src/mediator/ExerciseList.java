package mediator;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The ExerciseList class represents a list of exercises.
 * ExerciseList objects are serializable.
 *
 * @author Damian Trafiałek
 * @version 1.0
 */
public class ExerciseList implements Serializable {
    private ArrayList<Exercise> list;
    /**
     * 0-argument constructor initializes the Array List of exercises.
     */
    public ExerciseList(){
        list= new ArrayList<>();
    }

    /**
     * Adds an exercise to the list.
     *
     * @param exercise the exercise to add
     */
    public void add(Exercise exercise){
        list.add(exercise);
    }

    /**
     * Retrieves the exercise at the specified index.
     *
     * @param i the index of the exercise to retrieve
     * @return the exercise at the specified index
     */
    public Exercise get(int i){return list.get(i);}

    /**
     * Returns the number of exercises in the list.
     *
     * @return the size of the exercise list
     */
    public int size(){return list.size();}

    /**
     * Filters the exercises in the list by the given name.
     *
     * @param name the name to filter by
     * @return a new ExerciseList object containing the filtered exercises
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
     * Returns a string representation of the ExerciseList object.
     *
     * @return a string representation of the ExerciseList object
     */
    public String toString() {
        return "ExerciseList{" +
                "list=" + list +
                '}';
    }

    /**
     * Checks if the specified object is equal to this ExerciseList.
     * Checks if they have the same size and contain the same exercises in the same order.
     *
     * @param o the object to compare
     * @return true if the specified object is equal to this ExerciseList, false otherwise
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
