package modelServer.DAO.interfaces;

import mediator.ExerciseList;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Interface class used with methods connected only to Exercise object
 *
 * @version Sprint 6 - 29.05.2023
 */
public interface IExerciseDAO
{
  ExerciseList getExerciseList(int folderId) throws SQLException;

  boolean removeExercise(int exerciseId) throws SQLException;

  boolean removeExerciseByName(String name, int folderId) throws SQLException;

  ArrayList<String> getPossibleExercises() throws SQLException;

  boolean addExercise(String username, String exerciseName, int folderId,
      int weight, int repetition) throws SQLException;

}
