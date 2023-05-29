package modelServer.DAO.implementation;

import mediator.*;
import modelServer.DAO.interfaces.ICoachDAO;
import modelServer.DbContext.DBConnection;
import util.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * DAO Class accessing the database through an instance of the DBConnection class
 * CoachDAO works with the operations connected to the Coach object.
 * 
 * @author Anna Pomerantz
 * @version 1.0
 */
public class CoachDAO implements ICoachDAO
{
  @Override
  /**
   * Method gets the connection to the database and executes the sql statement
   * This method inserts both parameter into coach_request as a requester and accepter
   * 
   * @param traineeUser 
   *        
   * @param coachUser 
   *        
   *
   * @return  true or false, whether sending of the request was successful or not
   *        
   */
  public boolean requestCoach(String traineeUser, String coachUser) throws SQLException {
    DBConnection db = DBConnection.getInstance();
    Connection connection = db.getConnection();
    try
    {
      PreparedStatement statement = connection.prepareStatement(
              "insert into coach_request(trainee_username, coach_username) values (?,?);"
      );

      statement.setString(1, traineeUser);
      statement.setString(2, coachUser);
      int rs = statement.executeUpdate();

      return rs > 0;
    }
    catch (SQLException e){
      Logger.log(e);
      return false;
    }
    finally
    {
      connection.close();
    }
  }

  @Override
  /**
   * Method gets the connection to the database and executes the sql statement
   * 
   * @param username 
   *        
   *
   * @return  true or false, whether the user that matches parameter is coach or not
   *        
   */
  public boolean isCoach(String username) throws SQLException {
    DBConnection db = DBConnection.getInstance();
    Connection connection = db.getConnection();
    try
    {
      PreparedStatement statement = connection.prepareStatement(
              "select * from coach where username = ?;"
      );

      statement.setString(1, username);
      ResultSet rs = statement.executeQuery();
      boolean success = rs.next();
      return success;
    }
    catch (SQLException e){
      Logger.log(e);
      return false;
    }
    finally
    {
      connection.close();
    }
  }

  /**
   * Method gets the connection to the database and executes the sql statement
   * This method removes coach from trainees profile
   * 
   * @param traineeUsername 
   *        
   *
   * @return true or false, whether the removal was successful or not
   *        
   */
  @Override public boolean removeCoachAssignment(String traineeUsername)
      throws SQLException
  {
    DBConnection db = DBConnection.getInstance();
    Connection connection = db.getConnection();

    try
    {
      PreparedStatement updateStatement = connection.prepareStatement(
          "UPDATE trainee2 SET coach_username = NULL WHERE username = ? AND coach_username IS NOT NULL"
      );
      updateStatement.setString(1, traineeUsername);
      updateStatement.executeUpdate();

      // Second PreparedStatement for the DELETE statement
      PreparedStatement deleteStatement = connection.prepareStatement(
          "DELETE FROM meeting_request WHERE trainee_username = ?"
      );
      deleteStatement.setString(1, traineeUsername);
      deleteStatement.executeUpdate();
      PreparedStatement deleteStatement2 = connection.prepareStatement(
          "DELETE FROM meeting_list WHERE trainee_username = ?"
      );
      deleteStatement2.setString(1, traineeUsername);
      deleteStatement2.executeUpdate();

      return true;
    }
    catch (SQLException e){
      Logger.log(e);
      return false;
    }
    finally
    {
      connection.close();
    }
  }

  /**
   * Method gets the connection to the database and executes the sql statement
   * This method accepts the request. This means that request is deleted from coach_request table and coach is updated into trainees profile
   * 
   * @param traineeUsername 
   *        
   * @param coachUsername 
   *        
   *
   * @return  true or false, whether the acceptance was successful or not
   *        
   */
  @Override public boolean acceptRequest(String traineeUsername, String coachUsername)
      throws SQLException
  {
    DBConnection db = DBConnection.getInstance();
    Connection connection = db.getConnection();

    try
    {
      PreparedStatement statement = connection.prepareStatement(
          "delete from coach_request where trainee_username = ?"
      );
      statement.setString(1, traineeUsername);
      int result = statement.executeUpdate();

      PreparedStatement statement1 = connection.prepareStatement(
          "update trainee2 set coach_username = ? where username = ?");
      statement1.setString(1, coachUsername);
      statement1.setString(2, traineeUsername);
      int result2 = statement1.executeUpdate();
      return result > 0 && result2>0;

    }
    catch (SQLException e){
      Logger.log(e);
      return false;
    }
    finally
    {
      connection.close();
    }


  }

  /**
   * Method gets the connection to the database and executes the sql statement
   * this method deletes request from coach_request table since it was not accepted
   * 
   * @param traineeUsername 
   *        
   *
   * @return true or false, whether the denial was successful or not
   *        
   */
  @Override public boolean denyRequest(String traineeUsername)
      throws SQLException
  {
    DBConnection db = DBConnection.getInstance();
    Connection connection = db.getConnection();

    try
    {
      PreparedStatement statement = connection.prepareStatement(
          "delete from coach_request where trainee_username = ?"
      );
      statement.setString(1, traineeUsername);
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

  /**
   *  Method gets the connection to the database and executes the sql statement
   *  This method removes trainee whose username matches the parameter from all tables related to coach
   * 
   * @param traineeUsername 
   *        
   *
   * @return true or false, whether the removal was successful or not
   *        
   */
  @Override public boolean removeTraineeFromRoster(String traineeUsername)
      throws SQLException
  {
    DBConnection db = DBConnection.getInstance();
    Connection connection = db.getConnection();

    try
    {
      PreparedStatement statement = connection.prepareStatement(
          "update trainee2 set coach_username = null where username = ?"
      );
      statement.setString(1, traineeUsername);
      int result = statement.executeUpdate();

      PreparedStatement deleteStatement = connection.prepareStatement(
          "DELETE FROM meeting_request WHERE trainee_username = ?"
      );
      deleteStatement.setString(1, traineeUsername);
      deleteStatement.executeUpdate();
      PreparedStatement deleteStatement2 = connection.prepareStatement(
          "DELETE FROM meeting_list WHERE trainee_username = ?"
      );
      deleteStatement2.setString(1, traineeUsername);
      deleteStatement2.executeUpdate();
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

  /**
   *  Method gets the connection to the database and executes the sql statement
   *  TraineeList is in this case needed since we return username and status based on matching parameter
   * 
   * @param username 
   *        
   *
   * @return TraineeList where parameter matches the coach_username in trainee2 table
   *        
   */
  @Override public TraineeList getTraineeList(String username)
      throws SQLException
  {
    DBConnection db = DBConnection.getInstance();
    Connection connection = db.getConnection();
    TraineeList list = new TraineeList();

    try {

      PreparedStatement statement1 = connection.prepareStatement(
          "select username,status from trainee2 where coach_username = ?"
      );
      statement1.setString(1, username);
      ResultSet rs1 = statement1.executeQuery();
      while (rs1.next()) list.addTrainee( new Trainee(rs1.getString(1), rs1.getString(2)));

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
   * @param username 
   *        
   *
   * @return ArrayList of string where parameter matches the coach_username in coach_request table
   *        
   */
  @Override public ArrayList<String> getTraineeRequest(String username)
      throws SQLException
  {
    DBConnection db = DBConnection.getInstance();
    Connection connection = db.getConnection();
    ArrayList<String> list = new ArrayList<>();

    try {

      PreparedStatement statement1 = connection.prepareStatement("select trainee_username from coach_request where coach_username = ?");
      statement1.setString(1, username);
      ResultSet rs1 = statement1.executeQuery();
      while (rs1.next()) list.add(rs1.getString(1));

      return list;
    } catch (SQLException e) {
      return list;
    } finally {
      connection.close();
    }
  }
}
