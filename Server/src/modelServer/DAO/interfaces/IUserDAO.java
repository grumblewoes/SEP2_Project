package modelServer.DAO.interfaces;

import modelServer.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserDAO
{
  boolean createTrainee(String username, String password, String firstName, String lastName, int height, int weight) throws SQLException;
//  User getUserByUserName(String username) throws SQLException;

}
