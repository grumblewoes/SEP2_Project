package modelServer.DAO.interfaces;

import mediator.Friend;
import mediator.FriendList;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IFriendDAO
{
    boolean acceptFriendRequest(String requester_username,String accepter_username) throws SQLException;
    boolean rejectFriendRequest(String requester_username,String accepter_username) throws SQLException;

    FriendList getFriends(String username) throws SQLException;
    ArrayList<String> getFriendRequests(String username) throws SQLException;
}