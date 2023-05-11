package mediator;

import java.io.Serializable;
import java.util.ArrayList;

public class TraineeList implements Serializable
{
  private ArrayList<Trainee> traineeList;

  public TraineeList(){
    traineeList = new ArrayList<>();
  }

  public void addTrainee(Trainee t) {
    traineeList.add(t);
  }

  public int getSize(){
    return traineeList.size();
  }

  public String getTrainee(int i)
  {
    return traineeList.get(i).getUsername();
  }


  public String getUsername(int i){
    return traineeList.get(i).getUsername();
  }

  public String toString(){
    return "The trainee's list: " + traineeList;
  }


}
