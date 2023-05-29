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

/**
 * DAO Class accessing the database through an instance of the DBConnection class
 * FriendDAO works with the operations connected to the relations between Trainee objects.
 * 
 * @author Damian Trafialek
 * @version 1.0
 */
public class FriendDAO implements IFriendDAO {

    @Override
    /**
     * Method gets the connection to the database and executes the sql statement
     * Since a friendship was accepted it is deleted from request table and inserted into friendship_list table with given parameters
     *
     * @param requesterUsername
     *
     * @param accepterUsername
     *
     *
     * @return  true or false, whether the acceptance was successful or not
     *
     */
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
            return false;
        } finally {
            connection.close();
        }
    }

    @Override
    /**
     * Method gets the connection to the database and executes the sql statement
     * Since a friendship was rejected it is deleted from request table based on given parameters
     * @param requesterUsername
     *
     * @param accepterUsername
     *
     *
     * @return  true or false, whether the rejection was successful or not
     *
     */
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
            return false;
        } finally {
            connection.close();
        }
    }

    @Override
    /**
     * Method gets the connection to the database and executes the sql statement
     * This method selects usernames of users and their statuses who are in friendship with trainee based on given parameter
     *
     * @param username
     *
     *
     * @return FriendList needs to be returned since we are working with two attributes
     *
     */
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
    /**
     * Method gets the connection to the database and executes the sql statement
     * This method selects usernames of users requesting a friendship from friendship_request table for trainee based on given parameter
     *
     * @param username
     *
     *
     * @return ArrayList of strings with usernames of requester
     *
     */
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
    /**
     * Method gets the connection to the database and executes the sql statement
     * This method inserts a friendship request to a friendship_request table if the parameters do not already matches the values one way or the other in both friendship tables
     *
     * @param requesterUsername
     *
     * @param accepterUsername
     *
     * @return true or false, whether sending of the request was successful or not
     */
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

            return false;
        } finally {
            connection.close();
        }
    }

    @Override
    /**
     * Method gets the connection to the database and executes the sql statement
     * This method deletes a friendship from a friendship_list table if the parameters matches the values one way or the other
     *
     * @param requesterUsername
     *
     * @param accepterUsername
     *
     *
     * @return true or false, whether the removal was successful or not
     */
    public boolean removeFriend(String requesterUsername,
                                String accepterUsername) throws SQLException {
        DBConnection db = DBConnection.getInstance();
        Connection connection = db.getConnection();

        try {

            PreparedStatement statement = connection.prepareStatement(
                    "delete from friendship_list where requester_username = ? and accepter_username = ? or requester_username = ? and accepter_username = ?;"
            );
            statement.setString(1, requesterUsername);
            statement.setString(2, accepterUsername);
            statement.setString(3, accepterUsername);
            statement.setString(4, requesterUsername);

            statement.executeUpdate();
            return true;
        } catch (SQLException e) {

            return false;
        } finally {
            connection.close();
        }
    }
}

