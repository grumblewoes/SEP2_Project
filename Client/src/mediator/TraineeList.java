package mediator;

import java.util.ArrayList;

public class TraineeList
{
  private ArrayList<Trainee> traineeList;

  private int id;

  public TraineeList(int id){
    traineeList = new ArrayList<>();
    this.id = id;
  }

  public int getSize(){
    return traineeList.size();
  }

  public String getTrainee(int i)
  {
    return traineeList.get(i).getUsername();
  }

  public int getTraineeId(int i){
    return traineeList.get(i).getId();
  }

  public String getUsername(int i){
    return traineeList.get(i).getUsername();
  }

  public String toString(){
    return "The trainee's list: " + traineeList;
  }


}
