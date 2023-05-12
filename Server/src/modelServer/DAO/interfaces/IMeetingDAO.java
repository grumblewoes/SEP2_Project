package modelServer.DAO.interfaces;

import mediator.FriendList;
import mediator.MeetingList;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface IMeetingDAO
{
  MeetingList getTraineeMeetingList(String traineeUsername) throws SQLException;
  MeetingList getTraineeMeetingRequests(String traineeUsername) throws SQLException;
  boolean sendMeetingRequest(String traineeUsername, String coachUsername, LocalDate dateOfMeeting) throws
      SQLException;
  boolean removeMeeting(String traineeUsername, String coachUsername, LocalDate dateOfMeeting) throws SQLException;
  boolean approveMeeting(String trainee, String coach, Date date) throws
      SQLException;
  boolean denyMeeting(String trainee, String coach, Date date) throws SQLException;
}
