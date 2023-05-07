package modelServer.DAO.interfaces;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IFolderDAO
{
  boolean addExercise(String username, String folderName, String exerciseName, int repetitions, int weight)
      throws SQLException;
  boolean removeExercise(String username, String folderName, String exerciseName);
  ArrayList<String> getExerciseList(String username, String folderName);
  ArrayList<String> getPossibleExercises();
}
