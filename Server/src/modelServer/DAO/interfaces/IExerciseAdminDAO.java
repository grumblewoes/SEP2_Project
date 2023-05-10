package modelServer.DAO.interfaces;

import java.sql.SQLException;

public interface IExerciseAdminDAO
{

  boolean addExercise(String exerciseName) throws SQLException;
  boolean removeExercise(String exerciseName) throws SQLException;
}
