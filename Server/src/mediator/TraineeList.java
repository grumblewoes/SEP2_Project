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

  public Trainee getTrainee(int i)
  {
    return traineeList.get(i);
  }
  public String getTraineeUsername(int i){return traineeList.get(i).getUsername();}
  public String getTraineeStatus(int i){return traineeList.get(i).getStatus();}


  public String getUsername(int i){
    return traineeList.get(i).getUsername();
  }

  public String toString(){
    return "The trainee's list: " + traineeList;
  }


}
