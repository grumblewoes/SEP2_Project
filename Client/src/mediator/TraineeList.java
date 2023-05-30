package mediator;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * The TraineeList class represents a list of trainees.
 * TraineeList objects are serializable.
 *
 * @author Damian Trafiałek
 * @version 1.0
 */
public class TraineeList implements Serializable
{
  private ArrayList<Trainee> traineeList;

  /**
   * 0-argument constructor creates an empty TraineeList object.
   *
   *
   */
  public TraineeList(){
    traineeList = new ArrayList<>();
  }

  /**
   * Adds a trainee to the list.
   *
   * @param t the trainee to add
   */
  public void addTrainee(Trainee t) {
    traineeList.add(t);
  }


  /**
   * Returns the number of trainees in the list.
   *
   * @return the size of the trainee list
   */
  public int getSize(){
    return traineeList.size();
  }
  /**
   * Returns the list of trainees.
   *
   * @return the list of trainees
   */
  public ArrayList<Trainee> getList(){
    return traineeList;
  }

  /**
   * Returns the trainee at the specified index in the list.
   *
   * @param i the index of the trainee to retrieve
   * @return the trainee at the specified index
   */
  public Trainee getTrainee(int i)
  {
    return traineeList.get(i);
  }
  /**
   * Returns the username of the trainee at the specified index in the list.
   *
   * @param i the index of the trainee
   * @return the username of the trainee at the specified index
   */
  public String getTraineeUsername(int i){return traineeList.get(i).getUsername();}
  /**
   * Returns the status of the trainee at the specified index in the list.
   *
   * @param i the index of the trainee
   * @return the status of the trainee at the specified index
   */
  public String getTraineeStatus(int i){return traineeList.get(i).getStatus();}

  /**
   * Returns the username of the trainee at the specified index in the list.
   * (Equivalent to getTraineeUsername(int i))
   *
   * @param i the index of the trainee
   * @return the username of the trainee at the specified index
   */
  public String getUsername(int i){
    return traineeList.get(i).getUsername();
  }
  /**
   * Returns a string representation of the trainee list.
   *
   * @return a string representation of the trainee list
   */
  public String toString(){
    return "The trainee's list: " + traineeList;
  }


}
