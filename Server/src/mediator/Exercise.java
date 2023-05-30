package mediator;

import java.io.Serializable;
/**
 * The Exercise class represents an exercise performed during a workout.
 * It contains information such as weight, repetitions, name and id of the exercise.
 *
 * The Exercise class implements the Serializable interface to support serialization.
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
     * 4-argument constructor, creating an Exercise object with the specified ID, weight, repetitions, and name.
     *
     * @param id           the ID of the exercise
     * @param weight       the weight used for the exercise
     * @param repetitions  the number of repetitions performed
     * @param name         the name of the exercise
     */
    public Exercise(int id,double weight, int repetitions, String name) {
        this.id=id;
        this.weight = weight;
        this.repetitions = repetitions;
        this.name = name;
    }
    /**
     * Retrieves the ID of the exercise.
     *
     * @return the ID of the exercise
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the exercise.
     *
     * @param id the ID of the exercise
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * Retrieves the weight used for the exercise.
     *
     * @return the weight used for the exercise
     */
    public double getWeight() {
        return weight;
    }
    /**
     * Sets the weight used for the exercise.
     *
     * @param weight the weight used for the exercise
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }
    /**
     * Retrieves the number of repetitions performed for the exercise.
     *
     * @return the number of repetitions performed
     */
    public int getRepetitions() {
        return repetitions;
    }

    /**
     * Sets the number of repetitions performed for the exercise.
     *
     * @param repetitions the number of repetitions performed
     */
    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }

    /**
     * Retrieves the name of the exercise.
     *
     * @return the name of the exercise
     */
    public String getName() {
        return name;
    }
    /**
     * Sets the name of the exercise.
     *
     * @param name the name of the exercise
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    /**
     * Returns a String representation of the Exercise object.
     *
     * @return a String representation of the Exercise object
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
     * Checks if the Exercise object is equal to another object.
     * It checks if they have the same ID, name, weight, and repetitions.
     *
     * @param o the object to compare with
     * @return true if the objects are equal, false otherwise
     */
    @Override public boolean equals(Object o){
        if(o==null || !(o instanceof Exercise)) return false;
        Exercise e = (Exercise) o;
        return e.id==id && e.name.equals(name) && e.weight==weight && e.repetitions==repetitions;
    }
}
