package mediator;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 
 * 
 * 
 * @author Damian Trafiałek
 * @version 1.0
 */
public class TraineeList implements Serializable
{
  private ArrayList<Trainee> traineeList;

  /**
   * 0-argument constructor initialises a new array list.
   * 
   * 
   */
  public TraineeList(){
    traineeList = new ArrayList<>();
  }

  /**
   * Add the trainee to the list.
   * 
   * @param t - Trainee
   *        
   */
  public void addTrainee(Trainee t) {
    traineeList.add(t);
  }

  /**
   * Returns the size of the list.
   * 
   *
   * @return integer size
   *        
   */
  public int getSize(){
    return traineeList.size();
  }
  /**
   * Returns the arrayList of trainees.
   * 
   *
   * @return ArrayList of trainees
   *        
   */
  public ArrayList<Trainee> getList(){
    return traineeList;
  }

  /**
   * Gets the trainee under given index in the list.
   * 
   * @param i - integer index
   *        
   *
   * @return Trainee
   *        
   */
  public Trainee getTrainee(int i)
  {
    return traineeList.get(i);
  }
  /**
   * Gets the username of the trainee under given index in the list.
   * 
   * @param i - integer index
   *        
   *
   * @return String username
   *        
   */
  public String getTraineeUsername(int i){return traineeList.get(i).getUsername();}
  /**
   * Gets the staus of the trainee under given index in the list.
   *
   * @param i - integer index
   *
   *
   * @return string status
   *
   */
  public String getTraineeStatus(int i){return traineeList.get(i).getStatus();}


  /**
   * Gets the staus of the trainee under given index in the list.
   * 
   * @param i - integer index
   *        
   *
   * @return string status
   *        
   */
  public String getUsername(int i){
    return traineeList.get(i).getUsername();
  }

  /**
   * Casts object to string.
   * 
   *
   * @return string
   *        
   */
  public String toString(){
    return "The trainee's list: " + traineeList;
  }


}
