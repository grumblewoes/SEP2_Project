package modelServer.DAO.interfaces;

import mediator.TraineeList;

import java.sql.SQLException;

/**
 * 
 * 
 * 
 * @author 
 * @version 
 */
public interface ILeaderboardDAO
{
  TraineeList getSquatLeaders() throws SQLException;
  TraineeList getDeadliftLeaders() throws SQLException;
  TraineeList getBenchLeaders() throws SQLException;
}
