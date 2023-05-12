package modelServer.DAO.implementation;

import modelServer.DAO.interfaces.IMeetingDAO;
import modelServer.DbContext.DBConnection;
import util.Logger;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MeetingDAO implements IMeetingDAO
{
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
