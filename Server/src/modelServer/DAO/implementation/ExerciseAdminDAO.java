package modelServer.DAO.implementation;

import mediator.Exercise;
import modelServer.DAO.interfaces.IExerciseAdminDAO;
import modelServer.DbContext.DBConnection;
import util.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * DAO Class accessing the database through an instance of the DBConnection class
 * ExerciseAdminDAO works with the operations connected to the relation between admin and exercise
 *
 * @author Julija Gramovicha
 * @version 1.0
 */
public class ExerciseAdminDAO implements IExerciseAdminDAO
{
  /**
   * Method gets the connection to the database and executes the sql statement
   * This method first check if the exercise with the same title already exists in the database and if not then inserts in into exercise_type table
   *
   * @param exerciseName
   * @return true or false, whether the addition was a successful or not
   */
  @Override public boolean addExercise(String exerciseName) throws SQLException
  {
    DBConnection db = DBConnection.getInstance();
    Connection connection = db.getConnection();
    try
    {
      // checks, if the same exercise is in the table
      PreparedStatement statement = connection.prepareStatement(
          "SELECT title FROM exercise_type2 WHERE title = ?");
      statement.setString(1, exerciseName);
      ResultSet resultSet = statement.executeQuery();

      if (resultSet.next()) // if so, you cannot add it
      {
        System.out.println("Exercise with the same name already exists");
      }
      else
      {

        PreparedStatement statement3 = connection.prepareStatement(
            "INSERT INTO exercise_type2(title) VALUES (?);");
        statement3.setString(1, exerciseName);

        statement3.executeUpdate();
        System.out.println("The exercise was added");

        return true;

      }

    }
    catch (SQLException e)
    {
      System.out.println(
          "Couldn't add the exercise. It is already in the table");
      return false;
    }
    finally
    {
      connection.close();
    }
    return false;
  }

  /**
   * Method gets the connection to the database and executes the sql statement
   * This method first check if the exercise with the given title is in a table and if so then it deletes it from the exercise_type table
   *
   * @param exerciseName
   * @return true or false, whether the removal was a successful or not
   */
  @Override public boolean removeExercise(String exerciseName)
      throws SQLException
  {
    DBConnection db = DBConnection.getInstance();
    Connection connection = db.getConnection();

    try
    {
      // checks, if the exercise is in the table
      PreparedStatement statement = connection.prepareStatement(
          "SELECT title FROM exercise_type2 WHERE title = ?");
      statement.setString(1, exerciseName);
      ResultSet resultSet = statement.executeQuery();

      if (resultSet.next()) // if so, it removes it
      {
        PreparedStatement statement2 = connection.prepareStatement(
            "delete " + "from exercise_type2 " + "where title = '"
                + exerciseName + "';");

        statement2.executeUpdate();
        System.out.println("The exercise was removed");

      }
      else // exercise is not in the table
      {
        System.out.println("There is no such exercise in the system");
      }
    }
    catch (SQLException e)
    {
      System.out.println(
          "Couldn't remove the exercise. This exercise is used somewhere else. ");
    }
    finally
    {
      connection.close();
    }
    return false;
  }

}
