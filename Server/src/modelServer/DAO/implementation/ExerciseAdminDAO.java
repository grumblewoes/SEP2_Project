package modelServer.DAO.implementation;

import modelServer.DAO.interfaces.IExerciseAdminDAO;
import modelServer.DbContext.DBConnection;
import util.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ExerciseAdminDAO implements IExerciseAdminDAO
{

    private static ExerciseAdminDAO instance;

    public ExerciseAdminDAO()
    {}


  @Override public boolean addExercise(String exerciseName) throws SQLException
  {
    DBConnection db = DBConnection.getInstance();
    Connection connection = db.getConnection();
    try
    {
      PreparedStatement statement = connection.prepareStatement("INSERT INTO exercise_type2(title) VALUES (?);");
      statement.setString(1,exerciseName);

      statement.executeUpdate();
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

  @Override public boolean removeExercise(String exerciseName)
      throws SQLException
  {
    DBConnection db = DBConnection.getInstance();
    Connection connection = db.getConnection();

    try
    {
      PreparedStatement statement = connection.prepareStatement(
          "delete " +
              "from exercise_type2 " +
              "where title = '"+exerciseName + "';");

          statement.executeUpdate();
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

}
