package modelServer.DAO.interfaces;

import mediator.User;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

public interface IUserDAO
{
  boolean createTrainee(String username, String password, String firstName, String lastName, int height, int weight) throws SQLException;
//  User getUserByUserName(String username) throws SQLException;
  boolean createFolder(String username, String name)  throws SQLException;
  boolean removeFolder(String username, String name)  throws SQLException;
  boolean editFolder(String username, String oldName, String newName)  throws SQLException;

  User getTrainee(String username) throws SQLException;

  boolean updateTrainee(String u, int h, int w,boolean s) throws SQLException;

}
