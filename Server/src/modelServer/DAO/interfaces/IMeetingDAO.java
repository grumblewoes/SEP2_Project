package modelServer.DAO.interfaces;

import java.sql.SQLException;
import java.time.LocalDate;

public interface IMeetingDAO
{
  boolean approveMeeting(String trainee, String coach, LocalDate date) throws
      SQLException;
  boolean denyMeeting(String trainee, String coach, LocalDate date) throws SQLException;
}
