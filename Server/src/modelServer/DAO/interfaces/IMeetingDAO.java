package modelServer.DAO.interfaces;

import mediator.FriendList;
import mediator.MeetingList;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface IMeetingDAO
{
  MeetingList getCoachMeetingList(String coachUsername) throws SQLException;

  boolean removeMeeting(String coachUsername, String traineeUsername, LocalDate date) throws SQLException;

}