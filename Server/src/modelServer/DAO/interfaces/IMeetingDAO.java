package modelServer.DAO.interfaces;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface IMeetingDAO
{
  boolean removeMeeting(String coachUsername, String traineeUsername, LocalDate date) throws SQLException;

  boolean approveMeeting(String trainee, String coach, LocalDate date) throws
      SQLException;
  boolean denyMeeting(String trainee, String coach, LocalDate date) throws SQLException;

  ArrayList<String> getTraineeMeetingRequests(String coach) throws SQLException;
  ArrayList<String> getCoachMeetings(String coach) throws SQLException;
}

