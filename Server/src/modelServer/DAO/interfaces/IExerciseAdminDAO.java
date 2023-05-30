package modelServer.DAO.interfaces;

import mediator.Exercise;

import java.sql.SQLException;

/**
 * Interface class used with methods connected only to Exercise object from the point of Admin
 *
 * @version Sprint 6 - 29.05.2023
 */
public interface IExerciseAdminDAO
{

  boolean addExercise(String exerciseName) throws SQLException;
  boolean removeExercise(String exerciseName) throws SQLException;
}
