package modelServer.DAO.implementation;

import modelServer.DAO.interfaces.IMeetingDAO;
import modelServer.DbContext.DBConnection;
import util.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class MeetingDAO implements IMeetingDAO
{
  @Override public ArrayList<String> getTraineeMeetingList(String traineeUsername)
      throws SQLException
  {
    DBConnection db = DBConnection.getInstance();
    Connection connection = db.getConnection();
    ArrayList<String> list = new ArrayList<>();

    try {

      PreparedStatement statement = connection.prepareStatement("select date_of_meeting from meeting_list where trainee_username = ?");
      statement.setString(1, traineeUsername);
      ResultSet rs = statement.executeQuery();
      while (rs.next()){
        LocalDate dateOfMeeting = rs.getDate(1).toLocalDate();
        list.add(dateOfMeeting.toString());
      }

      return list;
    } catch (SQLException e) {
      return list;
    } finally {
      connection.close();
    }
  }

  @Override public ArrayList<String> getTraineeMeetingRequests(String traineeUsername)
      throws SQLException
  {
    DBConnection db = DBConnection.getInstance();
    Connection connection = db.getConnection();
    ArrayList<String> list = new ArrayList<>();

    try {

      PreparedStatement statement = connection.prepareStatement("select date_of_meeting from meeting_request where trainee_username = ?");
      statement.setString(1, traineeUsername);
      ResultSet rs = statement.executeQuery();
      while (rs.next()){
        LocalDate dateOfMeeting = rs.getDate(1).toLocalDate();
        list.add(dateOfMeeting.toString());
      }

      return list;
    } catch (SQLException e) {
      return list;
    } finally {
      connection.close();
    }
  }

  @Override public boolean sendMeetingRequest(String traineeUsername,
      String coachUsername, LocalDate dateOfMeeting) throws SQLException
  {
    DBConnection db = DBConnection.getInstance();
    Connection connection = db.getConnection();
    try {
      PreparedStatement statement = connection.prepareStatement(
          "INSERT INTO meeting_request (trainee_username, coach_username, date_of_meeting) "
              + "SELECT ?, ?, ? ");
      statement.setString(1, traineeUsername);
      statement.setString(2, coachUsername);
      statement.setDate(3, java.sql.Date.valueOf(dateOfMeeting));

      statement.executeUpdate();
      return true;
    } catch (SQLException e) {
      Logger.log(e);
      return false;
    } finally {
      connection.close();
    }
  }

  @Override public boolean removeMeeting(String traineeUsername,
      String coachUsername, LocalDate dateOfMeeting) throws SQLException
  {
    DBConnection db = DBConnection.getInstance();
    Connection connection = db.getConnection();

    try {

      PreparedStatement statement = connection.prepareStatement(
          "delete from meeting_list where trainee_username = ? and coach_username = ? and date_of_meeting=?;"
      );
      statement.setString(1, traineeUsername);
      statement.setString(2, coachUsername);
      statement.setDate(3, java.sql.Date.valueOf(dateOfMeeting));

      statement.executeUpdate();

      PreparedStatement statement1 = connection.prepareStatement(
          "delete from meeting_request where trainee_username = ? and coach_username = ? and date_of_meeting=?;"
      );
      statement1.setString(1, traineeUsername);
      statement1.setString(2, coachUsername);
      statement1.setDate(3, java.sql.Date.valueOf(dateOfMeeting));

      statement1.executeUpdate();
      return true;
    } catch (SQLException e) {
      Logger.log(e);
      return false;
    } finally {
      connection.close();
    }
  }

  @Override public boolean approveMeeting(String trainee, String coach,
      Date date) throws SQLException
  {
    DBConnection db = DBConnection.getInstance();
    Connection connection = db.getConnection();

    try {
      PreparedStatement statement = connection.prepareStatement(
          "delete from meeting_request where trainee_username = ? and coach_username = ? "
              + "and date_of_meeting = ?;"
      );
      statement.setString(1, trainee);
      statement.setString(2, coach);
      statement.setDate(3, date);
      statement.executeUpdate();

      PreparedStatement statement1 = connection.prepareStatement(
          "insert into meeting_list values(?, ?, ?);"
      );
      statement1.setString(1, trainee);
      statement1.setString(2, coach);
      statement1.setDate(3, date);
      statement1.executeUpdate();
      return true;

    } catch (SQLException e) {
      Logger.log(e);
      return false;

    } finally {
      connection.close();
    }
  }

  @Override public boolean denyMeeting(String trainee, String coach, Date date)
      throws SQLException
  {
    DBConnection db = DBConnection.getInstance();
    Connection connection = db.getConnection();

    try {
      PreparedStatement statement = connection.prepareStatement(
          "delete from meeting_request where trainee_username = ? and coach_username = ? "
              + "and date_of_meeting = ?;"
      );
      statement.setString(1, trainee);
      statement.setString(2, coach);
      statement.setDate(3, date);
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
