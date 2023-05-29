package modelServer.DAO.implementation;

import mediator.User;
import modelServer.DAO.interfaces.ITraineeDAO;
import modelServer.DbContext.DBConnection;
import util.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * DAO Class accessing the database through an instance of the DBConnection class
 * TraineeDAO works with the operations connected to the Trainee object.
 *
 * @author Jakub Cerovsky
 * @version 1.0
 */
public class TraineeDAO implements ITraineeDAO
{

  @Override
  /**
   * Method gets the connection to the database and executes the sql statement
   * This method simply inserts all parameters into a trainee2 table
   *
   * @param username
   *
   * @param password
   *
   * @param firstName
   *
   * @param lastName
   *
   * @param height
   *
   * @param weight
   *
   *
   * @return true or false, whether the creation was a successful or not
   *
   */
  public boolean createTrainee(String username, String password, String firstName, String lastName, int height, int weight) throws
      SQLException
  {
    DBConnection db = DBConnection.getInstance();
    Connection connection = db.getConnection();
    try
    {
      PreparedStatement statement = connection.prepareStatement("insert into trainee2(username, password, first_name, last_name, weight, height,share_profile) values (?,?,?,?,?,?,?);");
      statement.setString(1, username);
      statement.setString(2, password);
      statement.setString(3, firstName);
      statement.setString(4, lastName);
      statement.setInt(5, height);
      statement.setInt(6, weight);
      statement.setBoolean(7, true);

      statement.executeUpdate();
      return true;
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
   * This method selects an object from coach table where either coach is assigned to a trainee or is a coach which matches the parametes
   *
   * @param traineeUser
   *
   *
   * @return User(Coach) object which meets the parameter
   *
   */
  @Override public User getCoach(String traineeUser) throws SQLException
  {
    //has two ways of working. checks trainee table first, and if that doesn't work, it knows to check coach table
    DBConnection db = DBConnection.getInstance();
    Connection connection = db.getConnection();
    User coach = null;
    try
    {
      PreparedStatement statement = connection.prepareStatement(
          "select * from coach c "
              + "join trainee2 t on c.username = t.coach_username " +
              "where t.username = '" + traineeUser + "';"
      );
      PreparedStatement statementCoach = connection.prepareStatement("select * from coach "
          + "where username = '" + traineeUser + "';");

      ResultSet rs = statement.executeQuery();
      ResultSet rs1 = statementCoach.executeQuery();
      if(rs.next()) {
        int height = rs.getInt("height");
        int weight = rs.getInt("weight");
        String firstName = rs.getString("first_name");
        String lastName = rs.getString("last_name");
        String username = rs.getString("username");
        String status = rs.getString("status");
        coach = new User(height, weight, firstName, lastName, username, status, true);
      }
      else if (rs1.next()) {
        int height = rs1.getInt("height");
        int weight = rs1.getInt("weight");
        String firstName = rs1.getString("first_name");
        String lastName = rs1.getString("last_name");
        String username = rs1.getString("username");
        String status = rs1.getString("status");
        coach = new User(height, weight, firstName, lastName, username, status, true);
      }
      return coach;
    }
    catch (SQLException e){
      return null;
    }
    finally
    {
      connection.close();
    }
  }


  /**
   * Method gets the connection to the database and executes the sql statement
   * This method simply selects a trainee and sets it into a new User object based on given parameter.
   *
   * @param username
   *
   *
   * @return User(Trainee) object which meets the parameter
   *
   */
  @Override public User getTrainee(String username) throws SQLException{
    DBConnection db = DBConnection.getInstance();
    Connection connection = db.getConnection();
    User user = null;

    try
    {

      PreparedStatement statement = connection.prepareStatement(
          "select height,weight,first_name,last_name,username,share_profile,status, deadlift_best, squat_best, bench_best " +
              "from trainee2 " +
              "where username = '"+username+"';"
      );

      ResultSet rs = statement.executeQuery();
      if(rs.next()){
        //        int height, int weight, String firstName, String lastName, String username, String gender
        int height = rs.getInt(1);
        int weight = rs.getInt(2);
        String firstName = rs.getString(3);
        String lastName = rs.getString(4);
        boolean shareProfile = rs.getBoolean(6);
        String status = rs.getString(7);
        int deadlift = rs.getInt(8);
        int squat = rs.getInt(9);
        int bench = rs.getInt(10);
        user= new User(height,weight,firstName,lastName,username,status,shareProfile,deadlift,squat,bench);
      }

      return user;
    }
    catch (SQLException e){
      return null;
    }
    finally
    {
      connection.close();
    }
  }

  @Override
  /**
   * Method gets the connection to the database and executes the sql statement
   * This method updates a trainee from trainee table by given parameters
   *
   * @param u
   *
   * @param h
   *
   * @param w
   *
   * @param s
   *
   * @param st
   *
   *
   * @return true or false, whether the update was a successful or not
   *
   */
  public boolean updateTrainee(String u, int h, int w,boolean s,String st) throws SQLException {
    DBConnection db = DBConnection.getInstance();
    Connection connection = db.getConnection();
    if( (st!=null && st.length()>10) || h==0 || w==0){
      return false;
    }
    try
    {

      PreparedStatement statement = connection.prepareStatement(
          "update trainee2 " +
              "set height = "+ h +" " +
              ", weight = "+ w +" " +
              ", share_profile = "+ s +" " +
              ", status = '"+ st +"' " +
              "where username = '"+u+"' ;"
      );

      statement.executeUpdate();
      return true;
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
   * This method deletes a trainee from trainee table by given parameter
   *
   * @param username
   *
   *
   * @return true or false, whether the removal was a successful or not
   *
   */
  public boolean deleteTrainee(String username)throws SQLException{
    DBConnection db = DBConnection.getInstance();
    Connection connection = db.getConnection();

    try
    {

      PreparedStatement statement = connection.prepareStatement(
          "delete from trainee2 where username = ?"
      );
      statement.setString(1,username);
      statement.executeUpdate();

      return false;
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
