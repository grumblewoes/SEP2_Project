package modelServer.DAO.interfaces;

import mediator.FriendList;
import mediator.TraineeList;
import mediator.User;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * Interface class used with methods connected only to Coach object
 * @version Sprint 6 - 29.05.2023
 *
 */
public interface ICoachDAO
{
  boolean requestCoach(String traineeUser, String coachUser)
      throws SQLException;
  boolean isCoach(String username) throws SQLException;
  boolean removeCoachAssignment(String traineeUsername) throws SQLException;
  boolean acceptRequest(String traineeUsername, String coachUsername)
      throws SQLException;
  boolean denyRequest(String traineeUsername) throws SQLException;
  boolean removeTraineeFromRoster(String traineeUsername) throws SQLException;
  TraineeList getTraineeList(String username) throws SQLException;
  ArrayList<String> getTraineeRequest(String username) throws SQLException;
}
