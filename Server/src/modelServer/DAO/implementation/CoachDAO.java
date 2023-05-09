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
        //calculating bmi here??


        PreparedStatement statement = connection.prepareStatement(
            "insert into coach(username, password, first_name, last_name, height, weight, bmi, bench_best, squat_best, deadlift_best, status, share) values (" + coachUsername + ", " + coachPassword + ", "
                + coachName + ", " + coachLName + ", " + coachHeight + ", " + coachWeight
                + ", " + + pbBench + ", " + pbSquat + ", " + pbLift + ", " + status + ", true);"
      );

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
                    + "where coachName = " + name + ";"
      );

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
    DBConnection db = DBConnection.getInstance();
    Connection connection = db.getConnection();
    User coach = null;
    try
    {
      PreparedStatement statement = connection.prepareStatement(
          "select * from coach c "
              + "join trainee2 t on c.username = t.username " +
                  "where t.username = " + traineeUser + ";"
      );

      ResultSet rs = statement.executeQuery();
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
}
