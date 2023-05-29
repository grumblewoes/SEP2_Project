package modelServer.DAO.implementation;

import mediator.User;
import modelServer.DAO.interfaces.IUserDAO;
import modelServer.DbContext.DBConnection;
import util.Logger;

import java.sql.*;

/**
 * DAO Class accessing the database through an instance of the DBConnection class
 * UserDAO works with the operations connected to the User (Coach and Trainee) object
 * 
 * @author Damian Trafialek
 * @version 1.0
 */
public class UserDAO implements IUserDAO
{
  @Override
  /**
   * Method gets the connection to the database and executes the sql statement
   * This method selects a user (coach from coach table and trainee from trainee table) and compares if passwords are matching
   *
   * @param username
   *
   * @param password
   *
   *
   * @return false if username was not found or password is not matching, else returns true
   *
   */
  public boolean login(String username, String password) throws SQLException {
    DBConnection db = DBConnection.getInstance();
    Connection connection = db.getConnection();

    try
    {

      PreparedStatement statement = connection.prepareStatement(
          "select username, password " +
              "from trainee2 " +
              "where username = '"+username+"';"
      );
      PreparedStatement statementCoach = connection.prepareStatement("select username, password"
          + " from coach where username = '"+username+"';");

      ResultSet rs = statement.executeQuery();
      ResultSet rs1 = statementCoach.executeQuery();

      if( rs.next() ){
        return password.equals(rs.getString(2));
      }
      else if (rs1.next()) {
        return password.equals(rs1.getString(2));
      }
      return false;
    }
    catch (SQLException e){
      return false;
    }
    finally
    {
      connection.close();
    }
  }
}