package modelServer.DAO.implementation;

import mediator.Exercise;
import mediator.ExerciseList;
import mediator.Folder;
import mediator.FolderList;
import modelServer.DAO.interfaces.IExerciseDAO;
import modelServer.DAO.interfaces.IFolderDAO;
import modelServer.DbContext.DBConnection;
import util.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ExerciseDAO implements IExerciseDAO
{
  private static ExerciseDAO instance;

  public ExerciseDAO(){}



  public ExerciseList getExerciseList(int folderId) throws SQLException {
    ExerciseList exerciseList = new ExerciseList();

    DBConnection db = DBConnection.getInstance();
    Connection connection = db.getConnection();

    try
    {

      PreparedStatement statement = connection.prepareStatement(
              "select exercise_name, repetitions,weight,id " +
                      "from user_exercise2 " +
                      "where folderId = '" + folderId+"';"
      );
      ResultSet rs = statement.executeQuery();

      while(rs.next())
        exerciseList.add( new Exercise( rs.getInt(4),rs.getDouble(3), rs.getInt(2),rs.getString(1) ) );

      return exerciseList;
    }
    catch (SQLException e){
      Logger.log(e);
      return exerciseList;
    }
    finally
    {
      connection.close();
    }
  }

  @Override
  public boolean removeExercise(int exerciseId) throws SQLException {
    DBConnection db = DBConnection.getInstance();
    Connection connection = db.getConnection();

    try
    {

      PreparedStatement statement = connection.prepareStatement(
              "delete " +
                      "from user_exercise2 " +
                      "where id = '"+exerciseId + "';"
      );

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

  @Override
  public boolean removeExerciseByName(String name, int folderId) throws SQLException {
    DBConnection db = DBConnection.getInstance();
    Connection connection = db.getConnection();

    try
    {

      PreparedStatement statement = connection.prepareStatement(
              "delete " +
                      "from user_exercise2 " +
                      "where exercise_name = '" +name + "' and folderid = '" + folderId + "';"
      );

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

  @Override
  public ArrayList<String> getPossibleExercises() throws SQLException {
    ArrayList<String> list = new ArrayList<>();
    DBConnection db = DBConnection.getInstance();
    Connection connection = db.getConnection();

    try
    {

      PreparedStatement statement = connection.prepareStatement(
              "select title " +
                      "from exercise_type2 ;"
      );

      ResultSet rs = statement.executeQuery();

      while(rs.next())
        list.add( rs.getString(1) );

      return list;
    }
    catch (SQLException e){
      Logger.log(e);
      return list;
    }
    finally
    {
      connection.close();
    }
  }

  @Override
  public boolean addExercise(String username, String exerciseName, int folderId, int weight, int repetition) throws SQLException {
    DBConnection db = DBConnection.getInstance();
    Connection connection = db.getConnection();
    try
    {
      PreparedStatement statement = connection.prepareStatement("insert into user_exercise2(trainee_username,exercise_name,folderid,repetitions,weight) values (?,?,?,?,?);");
      statement.setString(1,username);
      statement.setString(2,exerciseName);
      statement.setInt(3, folderId);
      statement.setInt(4, repetition);
      statement.setInt(5, weight);


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

  @Override
  public int getBestWeightFromExerciseByName(String username,String name) throws SQLException {
    DBConnection db = DBConnection.getInstance();
    Connection connection = db.getConnection();

    try
    {

      PreparedStatement statement = connection.prepareStatement(
              "select weight " +
                      "from user_exercise2 " +
                      "where trainee_username = '"+ username +"' and exercise_name = '" + name + "'"+
                      "order by weight desc " +
                      "limit 1"
      );

      ResultSet rs = statement.executeQuery();

      int ans = 0;
      if( rs.next() )
        ans = rs.getInt(1);
      return ans;

    }
    catch (SQLException e){
      Logger.log(e);
      return 0;
    }
    finally
    {
      connection.close();
    }
  }

  @Override
  public int getBestSquat(String username) throws SQLException {
    return getBestWeightFromExerciseByName(username,"squat");
  }

  @Override
  public int getBestDeadlift(String username) throws SQLException {
    return getBestWeightFromExerciseByName(username,"deadlift");
  }

  @Override
  public int getBestBenchPress(String username) throws SQLException {
    return getBestWeightFromExerciseByName(username,"bench_press");
  }
}
