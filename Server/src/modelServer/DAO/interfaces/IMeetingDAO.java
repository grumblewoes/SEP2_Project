package modelServer.DAO.interfaces;

import org.postgresql.jdbc2.ArrayAssistant;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Interface class used with methods connected only to Meeting relations
 *
 * @version Sprint 6 - 29.05.2023
 */
public interface IMeetingDAO
{
  ArrayList<String> getTraineeMeetingList(String traineeUsername)
      throws SQLException;
  ArrayList<String> getTraineeMeetingRequests(String traineeUsername)
      throws SQLException;
  boolean sendMeetingRequest(String traineeUsername, String coachUsername,
      LocalDate dateOfMeeting) throws SQLException;
  boolean removeMeeting(String traineeUsername, String coachUsername,
      LocalDate dateOfMeeting) throws SQLException;
  boolean approveMeeting(String trainee, String coach, LocalDate date)
      throws SQLException;
  boolean denyMeeting(String trainee, String coach, LocalDate date)
      throws SQLException;

  ArrayList<String> getCoachMeetingRequests(String coach) throws SQLException;
  ArrayList<String> getCoachMeetings(String coach) throws SQLException;

  ArrayList<LocalDate> getTakenDates(String coachUsername) throws SQLException;
}
