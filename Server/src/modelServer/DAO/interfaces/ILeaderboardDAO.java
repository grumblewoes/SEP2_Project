package modelServer.DAO.interfaces;

import mediator.TraineeList;

import java.sql.SQLException;

/**
 * Interface class used with methods connected only to Leaderboard function
 *
 * @version Sprint 6 - 29.05.2023
 */
public interface ILeaderboardDAO
{
  TraineeList getSquatLeaders() throws SQLException;
  TraineeList getDeadliftLeaders() throws SQLException;
  TraineeList getBenchLeaders() throws SQLException;
}
