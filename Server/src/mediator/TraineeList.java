package mediator;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 
 * 
 * 
 * @author 
 * @version 
 */
public class TraineeList implements Serializable
{
  private ArrayList<Trainee> traineeList;

  /**
   * 0-argument constructor 
   * 
   * 
   */
  public TraineeList(){
    traineeList = new ArrayList<>();
  }

  /**
   * 
   * 
   * @param t 
   *        
   */
  public void addTrainee(Trainee t) {
    traineeList.add(t);
  }

  /**
   * 
   * 
   *
   * @return 
   *        
   */
  public int getSize(){
    return traineeList.size();
  }
  /**
   * 
   * 
   *
   * @return 
   *        
   */
  public ArrayList<Trainee> getList(){
    return traineeList;
  }

  /**
   * 
   * 
   * @param i 
   *        
   *
   * @return 
   *        
   */
  public Trainee getTrainee(int i)
  {
    return traineeList.get(i);
  }
  /**
   * 
   * 
   * @param i 
   *        
   *
   * @return 
   *        
   */
  public String getTraineeUsername(int i){return traineeList.get(i).getUsername();}
  /**
   * 
   * 
   * @param i 
   *        
   *
   * @return 
   *        
   */
  public String getTraineeStatus(int i){return traineeList.get(i).getStatus();}


  /**
   * 
   * 
   * @param i 
   *        
   *
   * @return 
   *        
   */
  public String getUsername(int i){
    return traineeList.get(i).getUsername();
  }

  /**
   * 
   * 
   *
   * @return 
   *        
   */
  public String toString(){
    return "The trainee's list: " + traineeList;
  }


}
