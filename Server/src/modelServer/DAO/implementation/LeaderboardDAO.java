package modelServer.DAO.implementation;

import mediator.Trainee;
import mediator.TraineeList;
import modelServer.DAO.interfaces.ILeaderboardDAO;
import modelServer.DbContext.DBConnection;
import util.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * DAO Class accessing the database through an instance of the DBConnection class
 * LeaderboardDAO works with the operations connected to the displaying the Leaders of the system for specific exercises
 * 
 * @author Jakub Cerovsky
 * @version 1.0
 */
public class LeaderboardDAO implements ILeaderboardDAO
{
  /**
   * Method gets the connection to the database and executes the sql statement
   *
   * @return TraineeList of top 10 usernames and their squat_best from trainee table only if the trainee wants to share the profile
   */
  @Override public TraineeList getSquatLeaders() throws SQLException
  {
    DBConnection db = DBConnection.getInstance();
    Connection connection = db.getConnection();
    TraineeList list = new TraineeList();

    try {

      PreparedStatement statement1 = connection.prepareStatement(
          "SELECT username, squat_best "
              + "FROM trainee2 WHERE share_profile = true and squat_best is not null "
              + "ORDER BY squat_best DESC LIMIT 10;"
      );
      ResultSet rs1 = statement1.executeQuery();
      while (rs1.next()) list.addTrainee( new Trainee(rs1.getString(1), rs1.getInt(2)));

      return list;
    } catch (SQLException e) {
      return list;
    } finally {
      connection.close();
    }
  }

  /**
   * Method gets the connection to the database and executes the sql statement
   *
   * @return TraineeList of top 10 usernames and their deadlift_best from trainee table only if the trainee wants to share the profile
   */
  @Override public TraineeList getDeadliftLeaders() throws SQLException
  {
    DBConnection db = DBConnection.getInstance();
    Connection connection = db.getConnection();
    TraineeList list = new TraineeList();

    try {

      PreparedStatement statement1 = connection.prepareStatement(
          "SELECT username, deadlift_best "
              + "FROM trainee2 WHERE share_profile = true and deadlift_best is not null "
              + "ORDER BY deadlift_best DESC LIMIT 10;"
      );
      ResultSet rs1 = statement1.executeQuery();
      while (rs1.next()) list.addTrainee( new Trainee(rs1.getString(1), rs1.getInt(2)));

      return list;
    } catch (SQLException e) {
      return list;
    } finally {
      connection.close();
    }
  }

  /**
   * Method gets the connection to the database and executes the sql statement
   *
   * @return TraineeList of top 10 usernames and their bench_best from trainee table only if the trainee wants to share the profile
   */
  @Override public TraineeList getBenchLeaders() throws SQLException
  {
    DBConnection db = DBConnection.getInstance();
    Connection connection = db.getConnection();
    TraineeList list = new TraineeList();

    try {

      PreparedStatement statement1 = connection.prepareStatement(
          "SELECT username, bench_best " +
              "FROM trainee2 WHERE share_profile = true and bench_best is not null " +
              "ORDER BY bench_best DESC LIMIT 10;"
      );
      ResultSet rs1 = statement1.executeQuery();
      while (rs1.next()) list.addTrainee( new Trainee(rs1.getString(1), rs1.getInt(2)));

      return list;
    } catch (SQLException e) {
      return list;
    } finally {
      connection.close();
    }
  }
}
