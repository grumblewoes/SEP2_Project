package modelServer.DAO.interfaces;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface IMeetingDAO
{
  ArrayList<String> getTraineeMeetingList(String traineeUsername) throws SQLException;
  ArrayList<String> getTraineeMeetingRequests(String traineeUsername) throws SQLException;
  boolean sendMeetingRequest(String traineeUsername, String coachUsername, LocalDate dateOfMeeting) throws
      SQLException;
  boolean removeMeeting(String traineeUsername, String coachUsername, LocalDate dateOfMeeting) throws SQLException;
  boolean approveMeeting(String trainee, String coach, Date date) throws
      SQLException;
  boolean denyMeeting(String trainee, String coach, Date date) throws SQLException;
}
