package modelServer.DAO.implementation;

import mediator.Folder;
import mediator.FolderList;
import mediator.Friend;
import mediator.FriendList;
import modelServer.DAO.interfaces.IFriendDAO;
import modelServer.DbContext.DBConnection;
import util.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FriendDAO implements IFriendDAO
{
  @Override public boolean sendFriendRequest(String requesterUsername,
      String accepterUsername) throws SQLException
  {
    DBConnection db = DBConnection.getInstance();
    Connection connection = db.getConnection();
    try
    {
      PreparedStatement statement = connection.prepareStatement("insert into friendship_request(requester_username, accepter_username) values (?,?);");
      statement.setString(1, requesterUsername);
      statement.setString(2, accepterUsername);

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

  @Override public boolean removeFriend(String requesterUsername,
      String accepterUsername) throws SQLException
  {
    DBConnection db = DBConnection.getInstance();
    Connection connection = db.getConnection();

    try
    {

      PreparedStatement statement = connection.prepareStatement(
          "delete from friendship_list where requester_username = ? and accepter_username = ?;"
      );
      statement.setString(1, requesterUsername);
      statement.setString(2, accepterUsername);

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

  @Override public FriendList getFriendList(String username) throws SQLException
  {
    FriendList friendList = new FriendList();
    String friendUsername = null;

    DBConnection db = DBConnection.getInstance();
    Connection connection = db.getConnection();

    try
    {

      PreparedStatement statement = connection.prepareStatement(
          "select requester_username, accepter_username from friendship_list where requester_username = ? or accepter_username = ?;"
      );
      statement.setString(1, username);
      statement.setString(2, username);
      ResultSet rs = statement.executeQuery();

      while(rs.next()){
        if(username.equals(rs.getString(1))){
          friendUsername=rs.getString(2);
        }
        else {
          friendUsername=rs.getString(1);
        }
        friendList.add( new Friend( friendUsername) );
      }
      return friendList;
    }
    catch (SQLException e){
      Logger.log(e);
      return friendList;
    }
    finally
    {
      connection.close();
    }

  }
}
