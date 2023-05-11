package mediator;

import java.util.ArrayList;

public class TraineeList
{

  private ArrayList<String> traineeList;

  public TraineeList(){
    traineeList = new ArrayList<>();
  }

  public int getSize(){
    return traineeList.size();
  }

  public String getTrainee(int i)
  {
    return traineeList.get(i);
  }
}
