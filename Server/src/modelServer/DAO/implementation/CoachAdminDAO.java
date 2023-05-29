package modelServer.DAO.implementation;

import modelServer.DAO.interfaces.ICoachAdminDAO;
import modelServer.DbContext.DBConnection;
import util.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * DAO Class accessing the database through an instance of the DBConnection class
 * CoachAdminDAO works with the operations connected to the relation between Admin and Coach object.
 *
 * @author Julija Gramovicha
 * @version 1.0
 */
public class CoachAdminDAO implements ICoachAdminDAO
{
  @Override
  /**
   * Method gets the connection to the database and executes the sql statement
   * This method simply inserts all parameters into a coach table
   *
   * @param coachUsername
   *
   * @param coachPassword
   *
   * @param coachName
   *
   * @param coachLName
   *
   * @param coachHeight
   *
   * @param coachWeight
   *
   * @param pbBench
   *
   * @param pbSquat
   *
   * @param pbLift
   *
   * @param status
   *
   * @return true or false, whether the creation was a successful or not
   *
   */
  public boolean addCoach(String coachUsername, String coachPassword, String coachName, String coachLName, int coachHeight, int coachWeight,
      int pbBench, int pbSquat, int pbLift, String status) throws SQLException
  {
    DBConnection db = DBConnection.getInstance();
    Connection connection = db.getConnection();

    try
    {
      PreparedStatement statement = connection.prepareStatement(
          "insert into coach(username, password, first_name, last_name, height, weight, bench_best, squat_best, deadlift_best, status) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);"
      );
      statement.setString(1, coachUsername);
      statement.setString(2, coachPassword);
      statement.setString(3, coachName);
      statement.setString(4, coachLName);
      statement.setInt(5, coachHeight);
      statement.setInt(6, coachWeight);
      statement.setInt(7, pbBench);
      statement.setInt(8, pbSquat);
      statement.setInt(9, pbLift);
      statement.setString(10, status);

      int result = statement.executeUpdate(); //number of modified rows
      return result > 0; //to hit this statement, technically would always be true? cuz otherwise it is caught?
    }
    catch (SQLException e){
      return false;
    }
    finally
    {
      connection.close();
    }
  }

  /**
   * Method gets the connection to the database and executes the sql statement
   * This method deletes the coach from coach table if the parameter matches the value
   *
   * @param name
   *
   *
   * @return  true or false, whether the removal was a successful or not
   *
   */
  public boolean removeCoach(String name) throws SQLException
  {
    DBConnection db = DBConnection.getInstance();
    Connection connection = db.getConnection();

    try
    {
      PreparedStatement statement = connection.prepareStatement(
          "delete from coach "
              + "where username = ?;"
      );
      statement.setString(1, name);
      int result = statement.executeUpdate();
      return result > 0;
    }
    catch (SQLException e){
      return false;
    }
    finally
    {
      connection.close();
    }
  }
}
