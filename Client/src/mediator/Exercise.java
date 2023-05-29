package mediator;

import java.io.Serializable;

/**
 * Class that stores exercise data.
 * 
 * 
 * @author Damian Trafiałek
 * @version 1.0
 */
public class Exercise implements Serializable {
    private double weight;
    private int repetitions,id;
    private String name;


    /**
     * 4-argument constructor that creates the exercise.
     * 
     * 
     * @param id - id of the exercises in the database
     *        
     * @param weight - weight of the exercise performed
     *        
     * @param repetitions - number of repetitions performed
     *        
     * @param name - the name of the exercise
     *        
     */
    public Exercise(int id,double weight, int repetitions, String name) {
        this.id=id;
        this.weight = weight;
        this.repetitions = repetitions;
        this.name = name;
    }

    /**
     * Returns the exercise's id.
     * 
     *
     * @return id
     *        
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id of the exercise.
     * 
     * @param id - integer
     *        
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the exercise's weight.
     * 
     *
     * @return weight
     *        
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Sets the exercise's weight.
     * 
     * @param weight - integer
     *        
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * Returns the exercise's repetitions.
     * 
     *
     * @return repetitions
     *        
     */
    public int getRepetitions() {
        return repetitions;
    }

    /**
     * Sets the exercise's repetitions.
     * 
     * @param repetitions - inteher
     *        
     */
    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }

    /**
     * Returns the exercise's name.
     * 
     *
     * @return name - string value
     *        
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the exercise's name.
     * 
     * @param name - string value
     *        
     */
    public void setName(String name) {
        this.name = name;
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
        return "Exercise{" +
                "weight=" + weight +
                ", repetitions=" + repetitions +
                ", id=" + id +
                ", name='" + name + '\'' +
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
        if(o==null || !(o instanceof Exercise)) return false;
        Exercise e = (Exercise) o;
        return e.id==id && e.name.equals(name) && e.weight==weight && e.repetitions==repetitions;
    }
}
