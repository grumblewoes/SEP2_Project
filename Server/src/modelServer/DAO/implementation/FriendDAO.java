package modelServer.DAO.implementation;


import mediator.Friend;
import mediator.FriendList;
import modelServer.DAO.interfaces.IFriendDAO;
import modelServer.DbContext.DBConnection;
import util.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FriendDAO implements IFriendDAO {

    @Override
    public boolean acceptFriendRequest(String requester_username, String accepter_username) throws SQLException {
        DBConnection db = DBConnection.getInstance();
        Connection connection = db.getConnection();

        try {

            PreparedStatement statement = connection.prepareStatement(
                    "delete from friendship_request where requester_username = '" + requester_username + "' and accepter_username = '" + accepter_username + "'; " +
                            "insert into friendship_list(requester_username, accepter_username) values (?,?);"
            );
            statement.setString(1, requester_username);
            statement.setString(2, accepter_username);
            statement.executeUpdate();


            return true;
        } catch (SQLException e) {
            Logger.log(e);
            Logger.log("failed to accept");
            return false;
        } finally {
            connection.close();
        }
    }

    @Override
    public boolean rejectFriendRequest(String requester_username, String accepter_username) throws SQLException {
        DBConnection db = DBConnection.getInstance();
        Connection connection = db.getConnection();

        try {

            PreparedStatement statement = connection.prepareStatement(
                    "delete from friendship_request where requester_username = ? and accepter_username = ?"
            );
            statement.setString(1, requester_username);
            statement.setString(2, accepter_username);
            statement.executeUpdate();


            return true;
        } catch (SQLException e) {
            Logger.log(e);
            Logger.log("failed to reject");
            return false;
        } finally {
            connection.close();
        }
    }

    @Override
    public FriendList getFriends(String username) throws SQLException {
        DBConnection db = DBConnection.getInstance();
        Connection connection = db.getConnection();
        FriendList list = new FriendList();

        try {

            PreparedStatement statement1 = connection.prepareStatement(
                    "select username, status from trainee2 where username in (select accepter_username from friendship_list where requester_username = ?)"
            );
            statement1.setString(1, username);
            ResultSet rs1 = statement1.executeQuery();
            while (rs1.next()) {
                String u = rs1.getString(1);
                String st = rs1.getString(2);
                list.add(new Friend(u, st));
            }

            PreparedStatement statement2 = connection.prepareStatement(
                    "select username, status from trainee2 where username in (select requester_username from friendship_list where accepter_username = ?) "
            );
            statement2.setString(1, username);
            ResultSet rs2 = statement2.executeQuery();
            while (rs2.next()) {
                String u = rs2.getString(1);
                String st = rs2.getString(2);
                list.add(new Friend(u, st));
            }

            return list;
        } catch (SQLException e) {
            return list;
        } finally {
            connection.close();
        }
    }

    @Override
    public ArrayList<String> getFriendRequests(String username) throws SQLException {
        DBConnection db = DBConnection.getInstance();
        Connection connection = db.getConnection();
        ArrayList<String> list = new ArrayList<>();

        try {

            PreparedStatement statement1 = connection.prepareStatement("select requester_username from friendship_request where accepter_username = ?");
            statement1.setString(1, username);
            ResultSet rs1 = statement1.executeQuery();
            while (rs1.next()) list.add(rs1.getString(1));

            return list;
        } catch (SQLException e) {
            return list;
        } finally {
            connection.close();
        }
    }

    @Override
    public boolean sendFriendRequest(String requesterUsername,
                                     String accepterUsername) throws SQLException {
        DBConnection db = DBConnection.getInstance();
        Connection connection = db.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO friendship_request(requester_username, accepter_username)"
                    + "SELECT ?, ?"
                    + "WHERE NOT EXISTS ("
                    + "    SELECT 1 FROM friendship_request"
                    + "    WHERE (requester_username = ? AND accepter_username = ?) OR (requester_username = ? AND accepter_username = ?)"
                    + ") AND NOT EXISTS ("
                    + "    SELECT 1 FROM friendship_list"
                    + "    WHERE (requester_username = ? AND accepter_username = ?) OR (requester_username = ? AND accepter_username = ?)"
                    + ") AND ? <> ?;");
            statement.setString(1, requesterUsername);
            statement.setString(2, accepterUsername);
            statement.setString(3, requesterUsername);
            statement.setString(4, accepterUsername);
            statement.setString(5, accepterUsername);
            statement.setString(6, requesterUsername);
            statement.setString(7, requesterUsername);
            statement.setString(8, accepterUsername);
            statement.setString(9, accepterUsername);
            statement.setString(10, requesterUsername);
            statement.setString(11, accepterUsername);
            statement.setString(12, requesterUsername);

            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            Logger.log(e);
            return false;
        } finally {
            connection.close();
        }
    }

    @Override
    public boolean removeFriend(String requesterUsername,
                                String accepterUsername) throws SQLException {
        DBConnection db = DBConnection.getInstance();
        Connection connection = db.getConnection();

        try {

            PreparedStatement statement = connection.prepareStatement(
                    "delete from friendship_list where requester_username = ? and accepter_username = ?;"
            );
            statement.setString(1, requesterUsername);
            statement.setString(2, accepterUsername);

            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            Logger.log(e);
            return false;
        } finally {
            connection.close();
        }
    }
}

