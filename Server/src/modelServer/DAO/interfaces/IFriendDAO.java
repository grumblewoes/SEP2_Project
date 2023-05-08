package modelServer.DAO.interfaces;

import mediator.FriendList;

import java.sql.SQLException;

public interface IFriendDAO
{
  boolean sendFriendRequest(String requesterUsername, String accepterUsername) throws
      SQLException;
  boolean removeFriend(String requesterUsername, String accepterUsername) throws SQLException;

  FriendList getFriendList(String username) throws SQLException;
}
