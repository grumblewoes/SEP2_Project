package modelServer.DAO.interfaces;

import mediator.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface class used with methods connected only to User object
 *
 * @version Sprint 6 - 29.05.2023
 */
public interface IUserDAO
{
  boolean login(String username, String password) throws SQLException;
}
