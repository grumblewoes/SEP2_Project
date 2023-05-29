package modelServer.DAO.implementation;

import mediator.FolderList;
import modelServer.DAO.interfaces.IFolderDAO;
import modelServer.DbContext.DBConnection;
import mediator.Folder;
import util.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * DAO Class accessing the database through an instance of the DBConnection class
 * FolderDAO works with the operations connected to the Folder object
 *
 * @author Damian Trafialek
 * @version 1.0
 */
public class FolderDAO implements IFolderDAO
{
  /**
   * Method gets the connection to the database and executes the sql statement
   * Method insert the parameters into a folder table
   *
   * @param username
   * @param title
   *
   * @return true or false, whether the folder was or was not created
   */
  @Override public boolean createFolder(String username, String title)
      throws SQLException
  {
    DBConnection db = DBConnection.getInstance();
    Connection connection = db.getConnection();
    try
    {
      PreparedStatement statement = connection.prepareStatement(
          "insert into folder2(title,trainee_username) values (?,?);");
      statement.setString(1, title);
      statement.setString(2, username);

      statement.executeUpdate();
      return true;
    }
    catch (SQLException e)
    {
      return false;
    }
    finally
    {
      connection.close();
    }
  }

  /**
   * Method gets the connection to the database and executes the sql statement
   * This method update the folder name from folder table based on given parameters
   *
   * @param username
   * @param folderId
   * @param newTitle
   *
   * @return true or false, whether the renaming was successful or not
   */
  @Override public boolean renameFolder(String username, int folderId,
      String newTitle) throws SQLException
  {
    DBConnection db = DBConnection.getInstance();
    Connection connection = db.getConnection();

    try
    {

      PreparedStatement statement = connection.prepareStatement(
          "update folder2 " + "set title = '" + newTitle + "' "
              + "where trainee_username = '" + username + "' and id = '"
              + folderId + "';");

      statement.executeUpdate();

      return true;
    }
    catch (SQLException e)
    {
      return false;
    }
    finally
    {
      connection.close();
    }
  }

  /**
   * Method gets the connection to the database and executes the sql statement
   * This method deletes the folder from user_exercise table and folder table based on given parameters
   *
   * @param username
   * @param folderId
   *
   * @return true or false, whether the removal was successful or not
   */
  @Override public boolean removeFolder(String username, int folderId)
      throws SQLException
  {
    DBConnection db = DBConnection.getInstance();
    Connection connection = db.getConnection();

    try
    {
      String sql = "delete from user_exercise2 where folderid = ? ; "
          + "delete from folder2 where trainee_username = ? and id = ? ; ";
      sql = sql.replaceFirst("\\?", "" + folderId);
      sql = sql.replaceFirst("\\?", "'" + username + "'");
      sql = sql.replaceFirst("\\?", "" + folderId);
      PreparedStatement statement = connection.prepareStatement(sql);

      statement.executeUpdate();

      return true;
    }
    catch (SQLException e)
    {
      return false;
    }
    finally
    {
      connection.close();
    }
  }

  /**
   * Method gets the connection to the database and executes the sql statement
   * This method selects attributes from folder table based on the username given as parameter
   *
   * @param username
   * @return FolderList of given username
   */
  public FolderList getFolderList(String username) throws SQLException
  {
    FolderList folderList = new FolderList();

    DBConnection db = DBConnection.getInstance();
    Connection connection = db.getConnection();

    try
    {

      PreparedStatement statement = connection.prepareStatement(
          "select id,title,trainee_username " + "from folder2 "
              + "where trainee_username = '" + username + "';");

      ResultSet rs = statement.executeQuery();

      while (rs.next())
        folderList.add(
            new Folder(rs.getInt(1), rs.getString(2), rs.getString(3)));

      return folderList;
    }
    catch (SQLException e)
    {
      return folderList;
    }
    finally
    {
      connection.close();
    }

  }
}
