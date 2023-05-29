package modelServer.DAO.interfaces;

import mediator.User;

import java.sql.SQLException;

public interface ITraineeDAO
{
  boolean createTrainee(String username, String password, String firstName,
      String lastName, int height, int weight) throws SQLException;
  User getCoach(String traineeUser) throws SQLException;
  User getTrainee(String username) throws SQLException;

  boolean updateTrainee(String u, int h, int w, boolean s, String st)
      throws SQLException;

  boolean deleteTrainee(String username) throws SQLException;
}
