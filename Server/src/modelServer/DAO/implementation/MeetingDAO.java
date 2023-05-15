package modelServer.DAO.implementation;

import mediator.Meeting;
import mediator.MeetingList;
import modelServer.DAO.interfaces.IMeetingDAO;
import modelServer.DbContext.DBConnection;
import util.Logger;

import java.sql.*;
import java.time.LocalDate;

public class MeetingDAO implements IMeetingDAO
{
  @Override public MeetingList getCoachMeetingList(String coachUsername)
      throws SQLException
  {
    DBConnection db = DBConnection.getInstance();
    Connection connection = db.getConnection();
    MeetingList list = new MeetingList();

    try
    {

      PreparedStatement statement = connection.prepareStatement(
          "select date_of_meeting and trainee_username from meeting_list where coach_username = ?");
      statement.setString(1, coachUsername);
      ResultSet rs = statement.executeQuery();
      while (rs.next())
      {
        LocalDate dateOfMeeting = rs.getDate(1).toLocalDate();

        list.add(new Meeting(dateOfMeeting));
      }
      return list;
    }
    catch (SQLException e)
    {
      Logger.log(e);
      return list;
    }
    finally
    {
      connection.close();
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

