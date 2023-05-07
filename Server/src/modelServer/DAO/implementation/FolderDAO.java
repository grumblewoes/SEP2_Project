package modelServer.DAO.implementation;

import modelServer.DAO.interfaces.IFolderDAO;
import modelServer.DbContext.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class FolderDAO implements IFolderDAO
{
  private static FolderDAO instance;

  public FolderDAO()
  {

  }
  @Override public boolean addExercise(String username, String folderName,
      String exerciseName, int repetitions, int weight) throws SQLException
  {
    DBConnection db = DBConnection.getInstance();
    Connection connection = db.getConnection();
    try{
      PreparedStatement statement = connection.prepareStatement("select trainee(...)");
      statement.setString(1, exerciseName);
      statement.setInt(2, repetitions);
      statement.setInt(3, weight);

      statement.executeUpdate();
      return true;
    }catch (SQLException s){
      return false;
    }finally
    {
      connection.close();
    }
  }

  @Override public boolean removeExercise(String username, String folderName,
      String exerciseName)
  {
    return false;
  }

  @Override public ArrayList<String> getExerciseList(String username,
      String folderName)
  {
    return null;
  }

  @Override public ArrayList<String> getPossibleExercises()
  {
    return null;
  }
}
