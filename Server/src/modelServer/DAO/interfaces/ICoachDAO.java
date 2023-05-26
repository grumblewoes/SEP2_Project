package modelServer.DAO.interfaces;

import mediator.FriendList;
import mediator.TraineeList;
import mediator.User;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 
 * 
 * 
 * @author 
 * @version 
 */
public interface ICoachDAO
{

  public boolean addCoach(String coachUsername, String coachPassword, String coachName, String coachLName, int coachHeight, int coachWeight,
      int pbBench, int pbSquat, int pbLift, String status, boolean share) throws SQLException;

  /**
   * 
   * 
   * @param name 
   *        
   *
   * @return 
   *        
   */
  public boolean removeCoach(String name) throws SQLException;

  /**
   * 
   * 
   * @param traineeUser 
   *        
   *
   * @return 
   *        
   */
  public User getCoach(String traineeUser) throws SQLException;
  /**
   * 
   * 
   * @param traineeUser 
   *        
   * @param coachUser 
   *        
   *
   * @return 
   *        
   */
  public boolean requestCoach(String traineeUser, String coachUser) throws SQLException;
  /**
   * 
   * 
   * @param username 
   *        
   *
   * @return 
   *        
   */
  public boolean isCoach(String username) throws SQLException;
  boolean removeCoachAssignment(String traineeUsername) throws SQLException;
  boolean acceptRequest(String traineeUsername, String coachUsername) throws  SQLException;
  boolean denyRequest(String traineeUsername) throws  SQLException;
  boolean removeTraineeFromRoster(String traineeUsername) throws  SQLException;
  TraineeList getTraineeList(String username) throws SQLException;
  ArrayList<String> getTraineeRequest(String username) throws SQLException;
}
