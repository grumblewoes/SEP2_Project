import modelServer.DAO.implementation.MeetingDAO;
import modelServer.DAO.interfaces.IMeetingDAO;
import modelServer.DbContext.DBConnection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

class DAOMeetingTest
{
  IMeetingDAO mdao;
  String trainee, coach;
  LocalDate date1, date2, date3;
  PreparedStatement statement;
  DBConnection db;
  Connection connection;

    @BeforeEach void setUp() throws SQLException
    {
        mdao = new MeetingDAO();
        trainee = "d";
        coach = "coach";
        date1 = LocalDate.of(2023, 5, 16);
        date2 = LocalDate.of(2023, 5, 17);
        date3 = LocalDate.of(2023, 5, 18);

        try
        {
            db = DBConnection.getInstance();
            connection = db.getConnection();
            statement = connection.prepareStatement("delete from meeting_list;"
                + "delete from meeting_request;");
            statement.executeUpdate();
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        finally {
            connection.close();
        }
    }

    @Test void approveMeetingZero()
    {
        Assertions.assertThrows(NullPointerException.class, () -> {
            try {
                mdao.approveMeeting(null, null, null);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }
    @Test void approveMeetingOne() throws SQLException
    {
        try
        {
            db = DBConnection.getInstance();
            connection = db.getConnection();
            statement = connection.prepareStatement("insert into meeting_request values('d', 'coach', '2023-05-16');");
            statement.executeUpdate();

            Assertions.assertTrue(()-> {
                try
                {
                    return mdao.approveMeeting(trainee, coach,
                        date1);
                }
                catch (SQLException e)
                {
                    throw new RuntimeException(e);
                }
            });

            statement = connection.prepareStatement("delete from meeting_list;");
            statement.executeUpdate();
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        finally {
            connection.close();
        }

    }
    @Test void approveMeetingMany() throws SQLException
    {
        try
        {
            db = DBConnection.getInstance();
            connection = db.getConnection();
            statement = connection.prepareStatement("insert into meeting_request values('d', 'coach', '2023-05-16');"
                + "insert into meeting_request values('d', 'coach', '2023-05-17');"
                + "insert into meeting_request values('d', 'coach', '2023-05-18');");
            statement.executeUpdate();

            Assertions.assertTrue(()-> {
                try
                {
                    return mdao.approveMeeting(trainee, coach, date1);

                }
                catch (SQLException e)
                {
                    throw new RuntimeException(e);
                }
            });
            Assertions.assertTrue(()-> {
                try
                {
                    return mdao.approveMeeting(trainee, coach, date2);

                }
                catch (SQLException e)
                {
                    throw new RuntimeException(e);
                }
            });
            Assertions.assertTrue(()-> {
                try
                {
                    return mdao.approveMeeting(trainee, coach, date3);

        }
        catch (SQLException e)
        {
          throw new RuntimeException(e);
        }
      });
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
    finally {
      connection.close();
    }
  }
  @Test void approveMeetingBoundary()
  {
    //no boundary
  }
  @Test void approveMeetingException()
  {
    //trainee cannot request from coach that is not assigned to them, so no
  }

  @Test void denyMeetingZero()
  {
    Assertions.assertThrows(NullPointerException.class, () -> {
      try {
        mdao.denyMeeting(null, null, null);
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    });
  }
  @Test void denyMeetingOne() throws SQLException
  {
    try
    {
      db = DBConnection.getInstance();
      connection = db.getConnection();
      statement = connection.prepareStatement("insert into meeting_request values('d', 'coach', '2023-05-16');");
      statement.executeUpdate();

      Assertions.assertTrue(()-> {
        try
        {
          return mdao.denyMeeting(trainee, coach,
              date1);
        }
        catch (SQLException e)
        {
          throw new RuntimeException(e);
        }
      });

      statement = connection.prepareStatement("delete from meeting_list;");
      statement.executeUpdate();
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
    finally {
      connection.close();
    }
  }
  @Test void denyMeetingMany() throws SQLException
  {
    try
    {
      db = DBConnection.getInstance();
      connection = db.getConnection();
      statement = connection.prepareStatement("insert into meeting_request values('d', 'coach', '2023-05-16');"
          + "insert into meeting_request values('d', 'coach', '2023-05-17');"
          + "insert into meeting_request values('d', 'coach', '2023-05-18');");
      statement.executeUpdate();

      Assertions.assertTrue(()-> {
        try
        {
          return mdao.denyMeeting(trainee, coach,
              date1);
        }
        catch (SQLException e)
        {
          throw new RuntimeException(e);
        }
      });
      Assertions.assertTrue(()-> {
        try
        {
          return mdao.denyMeeting(trainee, coach,
              date2);
        }
        catch (SQLException e)
        {
          throw new RuntimeException(e);
        }
      });
      Assertions.assertTrue(()-> {
        try
        {
          return mdao.denyMeeting(trainee, coach,
              date3);
        }
        catch (SQLException e)
        {
          throw new RuntimeException(e);
        }
      });

      statement = connection.prepareStatement("delete from meeting_list;");
      statement.executeUpdate();
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
    finally {
      connection.close();
    }
  }
    @Test void denyMeetingBoundary()
    {
        //no boundary
    }
    @Test void denyMeetingException()
    {
        //already tested
    }

    @Test void getTraineeMeetingRequestsZero() {
        try {
            Assertions.assertNotNull(mdao.getTraineeMeetingRequests(null));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Test void getTraineeMeetingRequestsOne() {
        try {
            Assertions.assertNotNull(mdao.getTraineeMeetingRequests(coach));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
  @Test void getTraineeMeetingRequestsMany() {
    try {
      Assertions.assertNotNull(mdao.getTraineeMeetingRequests(coach));
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    try {
      Assertions.assertNotNull(mdao.getTraineeMeetingRequests(coach));
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    try {
      Assertions.assertNotNull(mdao.getTraineeMeetingRequests(coach));
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
  @Test void getTraineeMeetingRequestsBoundary() {
    //no boundary, no test
  }
  @Test void getTraineeMeetingRequestsException() {
    //already tested
  }

  @Test void getCoachMeetingsZero() {

    Assertions.assertDoesNotThrow(() -> mdao.getCoachMeetings(null));
  }
  @Test void getCoachMeetingsOne() {
    try {
      Assertions.assertNotNull(mdao.getCoachMeetings(coach));
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
  @Test void getCoachMeetingsMany() {
    try {
      Assertions.assertNotNull(mdao.getCoachMeetings(coach));
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    try {
      Assertions.assertNotNull(mdao.getCoachMeetings(coach));
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    try {
      Assertions.assertNotNull(mdao.getCoachMeetings(coach));
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
  @Test void getCoachMeetingsBoundary() {
    //no boundary
  }
  @Test void getCoachMeetingsException() {
    //tested already
  }

  @Test void getTraineeMeetingRequestZero() {
    //when merging, might have issues. two methods with similar name
  }
  @Test void getTraineeMeetingRequestOne() {

  }
  @Test void getTraineeMeetingRequestMany() {}
  @Test void getTraineeMeetingRequestBoundary() {}
  @Test void getTraineeMeetingRequestException() {}

  //returns boolean
  @Test void sendMeetingRequestZero() {}
  @Test void sendMeetingRequestOne() {}
  @Test void sendMeetingRequestMany() {}
  @Test void sendMeetingRequestBoundary() {}
  @Test void sendMeetingRequestException() {}

  //returns boolean
  @Test void removeMeetingZero() {}
  @Test void removeMeetingOne() {}
  @Test void removeMeetingMany() {}
  @Test void removeMeetingBoundary() {}
  @Test void removeMeetingException() {}
}