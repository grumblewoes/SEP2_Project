package modelServer.DAO.interfaces;

import mediator.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserDAO
{
  boolean createTrainee(String username, String password, String firstName, String lastName, int height, int weight) throws SQLException;
//  User getUserByUserName(String username) throws SQLException;
  boolean login(String username,String password) throws SQLException;

  User getTrainee(String username) throws SQLException;

  boolean updateTrainee(String u, int h, int w,boolean s,String st) throws SQLException;

  boolean deleteTrainee(String username) throws SQLException;
}
