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

  @Override
  public boolean sendMeetingRequest(String traineeUsername, String coachUsername, LocalDate dateOfMeeting) throws SQLException {
    DBConnection db = DBConnection.getInstance();
    Connection connection = db.getConnection();

    try {
      // Check if the same values already exist in the meeting_list table
      PreparedStatement checkStatement = connection.prepareStatement(
          "SELECT * FROM meeting_list WHERE trainee_username = ? AND coach_username = ? AND date_of_meeting = ?");
      checkStatement.setString(1, traineeUsername);
      checkStatement.setString(2, coachUsername);
      checkStatement.setDate(3, java.sql.Date.valueOf(dateOfMeeting));

      ResultSet resultSet = checkStatement.executeQuery();
      if (resultSet.next()) {
        throw new SQLException("Duplicate values found in meeting_list table");
      }

      // Insert the meeting request into the meeting_request table
      PreparedStatement insertStatement = connection.prepareStatement(
          "INSERT INTO meeting_request (trainee_username, coach_username, date_of_meeting) VALUES (?, ?, ?)");
      insertStatement.setString(1, traineeUsername);
      insertStatement.setString(2, coachUsername);
      insertStatement.setDate(3, java.sql.Date.valueOf(dateOfMeeting));

      insertStatement.executeUpdate();
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
      LocalDate date) throws SQLException
  {
    DBConnection db = DBConnection.getInstance();
    Connection connection = db.getConnection();

    try
    {
      PreparedStatement statement = connection.prepareStatement(
          "delete from meeting_request where trainee_username = ? and coach_username = ? "
              + "and date_of_meeting = ?;");
      statement.setString(1, trainee);
      statement.setString(2, coach);
      statement.setDate(3, Date.valueOf(date));
      statement.executeUpdate();

      PreparedStatement statement1 = connection.prepareStatement(
          "insert into meeting_list values(?, ?, ?);");
      statement1.setString(1, trainee);
      statement1.setString(2, coach);
      statement1.setDate(3, Date.valueOf(date));
      statement1.executeUpdate();
      return true;

    }
    catch (SQLException e)
    {
      Logger.log(e);
      return false;

    }
    finally
    {
      connection.close();
    }
  }

  @Override public boolean denyMeeting(String trainee, String coach,
      LocalDate date) throws SQLException
  {
    DBConnection db = DBConnection.getInstance();
    Connection connection = db.getConnection();

    try
    {
      PreparedStatement statement = connection.prepareStatement(
          "delete from meeting_request where trainee_username = ? and coach_username = ? "
              + "and date_of_meeting = ?;");
      statement.setString(1, trainee);
      statement.setString(2, coach);
      statement.setDate(3, Date.valueOf(date));
      statement.executeUpdate();

      return true;

    }
    catch (SQLException e)
    {
      Logger.log(e);
      return false;

    }
    finally
    {
      connection.close();
    }
  }



  //is arraylist instead of meetinglist because STUPID gson won't convert LocalDate to json format
  @Override public ArrayList<String> getCoachMeetingRequests(String coach)
      throws SQLException
  {
    DBConnection db = DBConnection.getInstance();
    Connection connection = db.getConnection();
    ArrayList<String> list = new ArrayList<>();

    try
    {
      PreparedStatement statement = connection.prepareStatement(
          "select * from meeting_request where coach_username = ?");
      statement.setString(1, coach);
      ResultSet rs = statement.executeQuery();
      while (rs.next())
      {
        String traineeUser = rs.getString(1);
        LocalDate dateOfMeeting = rs.getDate(3).toLocalDate();
        list.add(dateOfMeeting + "," + traineeUser);
      }
      return list;

    }
    catch (SQLException e)
    {
      return list;
    }
    finally
    {
      connection.close();
    }
  }

  @Override public ArrayList<String> getCoachMeetings(String coach)
      throws SQLException
  {
    DBConnection db = DBConnection.getInstance();
    Connection connection = db.getConnection();
    ArrayList<String> list = new ArrayList<>();

    try
    {
      PreparedStatement statement = connection.prepareStatement(
          "select * from meeting_list where coach_username = ?");
      statement.setString(1, coach);
      ResultSet rs = statement.executeQuery();
      while (rs.next())
      {
        String traineeUser = rs.getString(1);
        LocalDate dateOfMeeting = rs.getDate(3).toLocalDate();
        list.add(dateOfMeeting + "," + traineeUser);
      }
      return list;

    }
    catch (SQLException e)
    {
      return list;
    }
    finally
    {
      connection.close();
    }
  }

  @Override public ArrayList<LocalDate> getTakenDates(String coachUsername)
      throws SQLException
  {
    DBConnection db = DBConnection.getInstance();
    Connection connection = db.getConnection();
    ArrayList<LocalDate> list = new ArrayList<>();

    try
    {
      LocalDate currentDate = LocalDate.now();
      LocalDate endDate = currentDate.plusDays(30);

      PreparedStatement statement = connection.prepareStatement(
          "SELECT date_of_meeting FROM meeting_list WHERE coach_username = ? " +
              "AND date_of_meeting >= ? AND date_of_meeting <= ?");
      statement.setString(1, coachUsername);
      statement.setDate(2, java.sql.Date.valueOf(currentDate));
      statement.setDate(3, java.sql.Date.valueOf(endDate));

      ResultSet rs = statement.executeQuery();
      ArrayList<LocalDate> tempList = new ArrayList<>();
      while (rs.next()) {
        LocalDate dateOfMeeting = rs.getDate(1).toLocalDate();
        tempList.add(dateOfMeeting);

        // Process a batch of rows
        if (tempList.size() >= 6) {
          list.addAll(tempList);
          tempList.clear();
        }
      }

      // Add any remaining rows in the tempList
      list.addAll(tempList);
      return list;

    }
    catch (SQLException e)
    {
      return list;
    }
    finally
    {
      connection.close();
    }
  }

}

