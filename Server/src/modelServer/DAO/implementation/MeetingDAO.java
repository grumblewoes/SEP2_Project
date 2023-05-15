package modelServer.DAO.implementation;

import mediator.Meeting;
import mediator.MeetingList;
import modelServer.DAO.interfaces.IMeetingDAO;
import modelServer.DbContext.DBConnection;
import util.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class MeetingDAO implements IMeetingDAO
{
  @Override public boolean approveMeeting(String trainee, String coach,
      LocalDate date) throws SQLException
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
      statement.setDate(3, Date.valueOf(date));
      statement.executeUpdate();

      PreparedStatement statement1 = connection.prepareStatement(
          "insert into meeting_list values(?, ?, ?);"
      );
      statement1.setString(1, trainee);
      statement1.setString(2, coach);
      statement1.setDate(3, Date.valueOf(date));
      statement1.executeUpdate();
      return true;

    } catch (SQLException e) {
      Logger.log(e);
      return false;

    } finally {
      connection.close();
    }
  }

  @Override public boolean denyMeeting(String trainee, String coach, LocalDate date)
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
      statement.setDate(3, Date.valueOf(date));
      statement.executeUpdate();

      return true;

    } catch (SQLException e) {
      Logger.log(e);
      return false;

    } finally {
      connection.close();
    }
  }

  //is arraylist instead of meetinglist because STUPID gson won't convert LocalDate to json format
  @Override public ArrayList<String> getTraineeMeetingRequests(String coach)
      throws SQLException
  {
    DBConnection db = DBConnection.getInstance();
    Connection connection = db.getConnection();
    ArrayList<String> list = new ArrayList<>();

    try {
      PreparedStatement statement = connection.prepareStatement("select * from meeting_request where coach_username = ?");
      statement.setString(1, coach);
      ResultSet rs = statement.executeQuery();
      while (rs.next()){
        String traineeUser = rs.getString(1);
        LocalDate dateOfMeeting = rs.getDate(3).toLocalDate();
        list.add(dateOfMeeting + "," + traineeUser);
      }
      return list;

    } catch (SQLException e) {
      return list;
    } finally {
      connection.close();
    }
  }

  @Override public ArrayList<String> getCoachMeetings(String coach)
      throws SQLException
  {
    DBConnection db = DBConnection.getInstance();
    Connection connection = db.getConnection();
    ArrayList<String> list = new ArrayList<>();

    try {
      PreparedStatement statement = connection.prepareStatement("select * from meeting_list where coach_username = ?");
      statement.setString(1, coach);
      ResultSet rs = statement.executeQuery();
      while (rs.next()){
        String traineeUser = rs.getString(1);
        LocalDate dateOfMeeting = rs.getDate(3).toLocalDate();
        list.add(dateOfMeeting + "," + traineeUser);
      }
      return list;

    } catch (SQLException e) {
      return list;
    } finally {
      connection.close();
    }
  }
}

  @Override public boolean removeMeeting(String coachUsername, String traineeUsername, LocalDate date)
      throws SQLException
  {
    {
      DBConnection db = DBConnection.getInstance();
      Connection connection = db.getConnection();

      try
      {

        PreparedStatement statement = connection.prepareStatement(
            "delete from meeting_list where coach_username = ? and and date_of_meeting=?;");
        statement.setString(1, coachUsername);
        statement.setString(2, traineeUsername);
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
  }
}

