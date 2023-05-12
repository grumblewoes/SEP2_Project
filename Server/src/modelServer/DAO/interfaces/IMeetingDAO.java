package modelServer.DAO.interfaces;

import java.sql.Date;
import java.sql.SQLException;

public interface IMeetingDAO
{
  boolean approveMeeting(String trainee, String coach, Date date) throws
      SQLException;
  boolean denyMeeting(String trainee, String coach, Date date) throws SQLException;
}
