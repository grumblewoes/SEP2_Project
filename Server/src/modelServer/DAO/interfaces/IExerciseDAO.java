package modelServer.DAO.interfaces;

import mediator.ExerciseList;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IExerciseDAO
{
  ExerciseList getExerciseList(int folderId) throws  SQLException;

    boolean removeExercise(int exerciseId) throws SQLException;

    boolean removeExerciseByName(String name, int folderId) throws SQLException;

    ArrayList<String> getPossibleExercises() throws SQLException;

  boolean addExercise(String username, String exerciseName, int folderId, int weight, int repetition) throws SQLException;
  int getBestWeightFromExerciseByName(String username, String name) throws SQLException;
  int getBestSquat(String username) throws SQLException;
  int getBestDeadlift(String username) throws SQLException;
  int getBestBenchPress(String username) throws SQLException;
}
