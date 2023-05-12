package modelServer.DAO.implementation;

import mediator.Exercise;
import mediator.User;
import modelServer.DAO.interfaces.ICoachDAO;
import modelServer.DbContext.DBConnection;
import util.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CoachDAO implements ICoachDAO
{
  private static CoachDAO instance;

  public boolean addCoach(String coachUsername, String coachPassword, String coachName, String coachLName, int coachHeight, int coachWeight,
      int pbBench, int pbSquat, int pbLift, String status, boolean share) throws SQLException
  {
    DBConnection db = DBConnection.getInstance();
    Connection connection = db.getConnection();

    try
    {
        PreparedStatement statement = connection.prepareStatement(
                "insert into coach(username, password, first_name, last_name, height, weight, bench_best, squat_best, deadlift_best, status, share) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);"
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
        statement.setBoolean(11, share);

      int result = statement.executeUpdate(); //number of modified rows
      return result > 0; //to hit this statement, technically would always be true? cuz otherwise it is caught?
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
      Logger.log(e);
      return false;
    }
    finally
    {
      connection.close();
    }
  }

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
        boolean shareProfile = rs.getBoolean("share");
        coach = new User(height, weight, firstName, lastName, username, status, shareProfile);
      }
      else if (rs1.next()) {
        int height = rs1.getInt("height");
        int weight = rs1.getInt("weight");
        String firstName = rs1.getString("first_name");
        String lastName = rs1.getString("last_name");
        String username = rs1.getString("username");
        String status = rs1.getString("status");
        boolean shareProfile = rs1.getBoolean("share");
        coach = new User(height, weight, firstName, lastName, username, status, shareProfile);
      }
      return coach;
    }
    catch (SQLException e){
      Logger.log(e);
      return null;
    }
    finally
    {
      connection.close();
    }
  }

  @Override
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
      return rs.next();
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

  @Override public boolean removeCoachAssignment(String traineeUsername)
      throws SQLException
  {
    DBConnection db = DBConnection.getInstance();
    Connection connection = db.getConnection();

    try
    {
      PreparedStatement statement = connection.prepareStatement(
          "update trainee2 set coach_username = null where username = ? and coach_username is not null"
      );
      statement.setString(1, traineeUsername);
      int result = statement.executeUpdate();
      return result > 0;
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
      Logger.log(e);
      return false;
    }
    finally
    {
      connection.close();
    }


  }

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

      return result > 0;

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
}
