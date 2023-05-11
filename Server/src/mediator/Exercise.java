package mediator;

import java.io.Serializable;

public class Exercise implements Serializable {
    private double weight;
    private int repetitions,id;
    private String name;


    public Exercise(int id,double weight, int repetitions, String name) {
        this.id=id;
        this.weight = weight;
        this.repetitions = repetitions;
        this.name = name;
    }
    public Exercise(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "weight=" + weight +
                ", repetitions=" + repetitions +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override public boolean equals(Object o){
        if(o==null || !(o instanceof Exercise)) return false;
        Exercise e = (Exercise) o;
        return e.id==id && e.name.equals(name) && e.weight==weight && e.repetitions==repetitions;
    }
}
