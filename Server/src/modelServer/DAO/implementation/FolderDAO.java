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

public class FolderDAO implements IFolderDAO
{
  private static FolderDAO instance;

  public FolderDAO(){}



  @Override
  public boolean createFolder(String username, String title) throws SQLException {
    DBConnection db = DBConnection.getInstance();
    Connection connection = db.getConnection();
    try
    {
      PreparedStatement statement = connection.prepareStatement("insert into folder2(title,trainee_username) values (?,?);");
      statement.setString(1,title);
      statement.setString(2,username);

      statement.executeUpdate();
      return true;
    }
    catch (SQLException e){
      Logger.log(e);
      return false;
    }
    finally
    {
      connection.close();
    }
  }

  @Override
  public boolean renameFolder(String username, int folderId, String newTitle) throws SQLException {
    DBConnection db = DBConnection.getInstance();
    Connection connection = db.getConnection();

    try
    {

      PreparedStatement statement = connection.prepareStatement(
              "update folder2 " +
                  "set title = '"+ newTitle +"' " +
                  "where trainee_username = '"+username+"' and id = '" + folderId +  "';"
      );

      statement.executeUpdate();


      return true;
    }
    catch (SQLException e){
      Logger.log(e);
      return false;
    }
    finally
    {
      connection.close();
    }
  }

  @Override
  public boolean removeFolder(String username, int folderId) throws SQLException {
    DBConnection db = DBConnection.getInstance();
    Connection connection = db.getConnection();

    try
    {
      String sql = "delete from user_exercise2 where folderid = ? ; "+
              "delete from folder2 where trainee_username = ? and id = ? ; ";
      sql=sql.replaceFirst("\\?",""+folderId);
      sql=sql.replaceFirst("\\?","'"+username+"'");
      sql=sql.replaceFirst("\\?",""+folderId);
      Logger.log(sql);

      PreparedStatement statement = connection.prepareStatement(sql);

      statement.executeUpdate();


      return true;
    }
    catch (SQLException e){
      Logger.log(e);
      return false;
    }
    finally
    {
      connection.close();
    }
  }

  public FolderList getFolderList(String username) throws SQLException {
    FolderList folderList = new FolderList();

    DBConnection db = DBConnection.getInstance();
    Connection connection = db.getConnection();

    try
    {

      PreparedStatement statement = connection.prepareStatement(
              "select id,title,trainee_username " +
                      "from folder2 " +
                      "where trainee_username = '"+username+"';"
      );

      ResultSet rs = statement.executeQuery();

      while(rs.next())
        folderList.add( new Folder( rs.getInt(1),rs.getString(2),rs.getString(3) ) );

      return folderList;
    }
    catch (SQLException e){
      Logger.log(e);
      return folderList;
    }
    finally
    {
      connection.close();
    }

  }
}
