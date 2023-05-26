package mediator;

import java.io.Serializable;

/**
 * 
 * 
 * 
 * @author 
 * @version 
 */
public class Exercise implements Serializable {
    private double weight;
    private int repetitions,id;
    private String name;


    /**
     * 4-argument constructor 
     * 
     * 
     * @param id 
     *        
     * @param weight 
     *        
     * @param repetitions 
     *        
     * @param name 
     *        
     */
    public Exercise(int id,double weight, int repetitions, String name) {
        this.id=id;
        this.weight = weight;
        this.repetitions = repetitions;
        this.name = name;
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
    public double getWeight() {
        return weight;
    }

    /**
     * 
     * 
     * @param weight 
     *        
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * 
     * 
     *
     * @return 
     *        
     */
    public int getRepetitions() {
        return repetitions;
    }

    /**
     * 
     * 
     * @param repetitions 
     *        
     */
    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }

    /**
     * 
     * 
     *
     * @return 
     *        
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * 
     * @param name 
     *        
     */
    public void setName(String name) {
        this.name = name;
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
        return "Exercise{" +
                "weight=" + weight +
                ", repetitions=" + repetitions +
                ", id=" + id +
                ", name='" + name + '\'' +
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
        if(o==null || !(o instanceof Exercise)) return false;
        Exercise e = (Exercise) o;
        return e.id==id && e.name.equals(name) && e.weight==weight && e.repetitions==repetitions;
    }
}
