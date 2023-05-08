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

  public boolean addCoach(String coachUsername, String coachPassword, String coachName, String coachLName, double coachHeight, double coachWeight,
      int pbBench, int pbSquat, int pbLift, boolean share) throws SQLException
  {
    DBConnection db = DBConnection.getInstance();
    Connection connection = db.getConnection();
    User coach = new User(coachHeight, coachWeight, coachName, coachLName, coachUsername, PLACEHOLDER, share);

    try
    {
        PreparedStatement statement = connection.prepareStatement(
            "insert into coach values (" + coachUsername + ", " + coachPassword + ", "
                + coachName + ", " + coachLName + ", " + coachHeight + ", " + coachWeight
                + ", " + pbBench + ", " + pbSquat + ", " + pbLift + ");"
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
    User coach;
    try
    {
      PreparedStatement statement = connection.prepareStatement(
          "select from coach "
              + "join trainee where username = " + traineeUser + ";"
      );

      ResultSet rs = statement.executeQuery();

      while(rs.next())
        coach = ;

      return coach;
    }
    catch (SQLException e){
      Logger.log(e);
      return coach;
    }
    finally
    {
      connection.close();
    }
  }
}
