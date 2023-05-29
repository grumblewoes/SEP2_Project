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

/**
 * DAO Class accessing the database through an instance of the DBConnection class
 * ExerciseDAO works with the operations connected to the Exercise object
 * 
 * @author Damian Trafialek
 * @version 1.0
 */
public class ExerciseDAO implements IExerciseDAO
{
  /**
   * Method gets the connection to the database and executes the sql statement
   * This method selects values from user_exercise2 table where the folderId matches parameter
   * 
   * @param folderId 
   *        
   *
   * @return ExerciseList with all exercise info for given folder
   *        
   */
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

      return exerciseList;
    }
    finally
    {
      connection.close();
    }
  }

  @Override
  /**
   * Method gets the connection to the database and executes the sql statement
   * This method removes exercise from user_exercise2 table based on its id
   * 
   * @param exerciseId 
   *        
   *
   * @return  true or false, whether the removal was successful or not
   *        
   */
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
   * This method deletes exercise from user_exercise2 table based on given parameters
   * 
   * @param name 
   *        
   * @param folderId 
   *        
   *
   * @return  true or false, whether the removal was successful or not
   *        
   */
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
   *
   * @return ArrayList of string with titles of all exercises in exercise_type2 table
   *        
   */
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
      return list;
    }
    finally
    {
      connection.close();
    }
  }

  @Override
  /**
   * Method gets the connection to the database and executes the sql statement
   * This method has a few stages in it.
   * Firstly, it initializes the columnToUpdate in case that exerciseName parameter is equal to any given options
   * Secondly, if the columnToUpdate is empty it simply insert the parameters into user_exercise2 table and returns true
   * If the columnToUpdate is not empty it compares if the weight is higher than the max weight in matching column name.
   * If so, the trainee object is updated with his new personal best, and then it continues into insertion
   * 
   * @param username 
   *        
   * @param exerciseName 
   *        
   * @param folderId 
   *        
   * @param weight 
   *        
   * @param repetition 
   *        
   *
   * @return  true or false, whether the addition was successful or not
   *        
   */
  public boolean addExercise(String username, String exerciseName, int folderId, int weight, int repetition) throws SQLException {
    DBConnection db = DBConnection.getInstance();
    Connection connection = db.getConnection();

    try {
      String columnToUpdate = "";
      if (exerciseName.equalsIgnoreCase("deadlift")) {
        columnToUpdate = "deadlift_best";
      } else if (exerciseName.equalsIgnoreCase("squat")) {
        columnToUpdate = "squat_best";
      } else if (exerciseName.equalsIgnoreCase("bench_press")) {
        columnToUpdate = "bench_best";
      }

      // Check if the inserted weight is the biggest
      if(!columnToUpdate.isEmpty()){
        PreparedStatement maxWeightStatement = connection.prepareStatement(
            "SELECT MAX(weight) FROM user_exercise2 WHERE trainee_username = ? and exercise_name=?");
        maxWeightStatement.setString(1, username);
        maxWeightStatement.setString(2, exerciseName);

        ResultSet resultSet = maxWeightStatement.executeQuery();
        if (resultSet.next())
        {
          int maxWeight = resultSet.getInt(1);
          if (weight > maxWeight)
          {
            PreparedStatement updateStatement = connection.prepareStatement("UPDATE trainee2 SET " + columnToUpdate + " = ? WHERE username = ?");
            updateStatement.setInt(1, weight);
            updateStatement.setString(2, username);
            updateStatement.executeUpdate();
          }
        }
      }

      PreparedStatement statement = connection.prepareStatement("INSERT INTO user_exercise2 (trainee_username, exercise_name, folderid, repetitions, weight) VALUES (?, ?, ?, ?, ?)");
      statement.setString(1, username);
      statement.setString(2, exerciseName);
      statement.setInt(3, folderId);
      statement.setInt(4, repetition);
      statement.setInt(5, weight);

      statement.executeUpdate();

      return true;
    } catch (SQLException e) {
      return false;
    } finally {
      connection.close();
    }
  }
}
