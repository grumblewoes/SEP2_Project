import junit.extensions.TestSetup;
import modelServer.DAO.implementation.MeetingDAO;
import modelServer.DAO.interfaces.IMeetingDAO;
import modelServer.DbContext.DBConnection;
import modelServer.DbContext.DBService;
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
  String traineeA, traineeB, traineeD, coach;
  LocalDate date1, date2, date3;
  PreparedStatement statement;
  DBConnection db;
  Connection connection;

  DBService service;

  @BeforeEach void setUp() throws SQLException
  {
    service = new DBService();
    mdao = new MeetingDAO();
    traineeD = "d";
    traineeB = "b";
    traineeA = "a";
    coach = "coach";
    date1 = LocalDate.of(2023, 5, 16);
    date2 = LocalDate.of(2023, 5, 17);
    date3 = LocalDate.of(2023, 5, 18);

//    try
//    {
//      db = DBConnection.getInstance();
//      connection = db.getConnection();
//      statement = connection.prepareStatement(
//          "delete from meeting_list;" + "delete from meeting_request;");
//      statement.executeUpdate();
//    }
//    catch (SQLException e)
//    {
//      throw new RuntimeException(e);
//    }
//    finally
//    {
//      connection.close();
//    }
  }

  private void SetupTestDatabase(){
    service.restartDatabase();
    service.switchToTestDatabase();
  }

  @Test void approveMeetingZero()
  {
    SetupTestDatabase();
    Assertions.assertThrows(NullPointerException.class, () -> {
      try
      {
        mdao.approveMeeting(null, null, null);
      }
      catch (SQLException e)
      {
        throw new RuntimeException(e);
      }
    });
    service.switchToProductionDatabase();
  }

  @Test void approveMeetingOne() throws SQLException
  {
    SetupTestDatabase();
    try
    {
      db = DBConnection.getInstance();
      connection = db.getConnection();
      statement = connection.prepareStatement(
          "insert into meeting_request values('d', 'coach', '2023-05-16');");
      statement.executeUpdate();

      Assertions.assertTrue(() -> {
        try
        {
          return mdao.approveMeeting(traineeD, coach, date1);
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
    finally
    {
      connection.close();
    }
    service.switchToProductionDatabase();
  }

  @Test void approveMeetingMany() throws SQLException
  {
    SetupTestDatabase();
    try
    {
      db = DBConnection.getInstance();
      connection = db.getConnection();
      statement = connection.prepareStatement(
          "insert into meeting_request values('d', 'coach', '2023-05-16');"
              + "insert into meeting_request values('d', 'coach', '2023-05-17');"
              + "insert into meeting_request values('d', 'coach', '2023-05-18');");
      statement.executeUpdate();

      Assertions.assertTrue(() -> {
        try
        {
          return mdao.approveMeeting(traineeD, coach, date1);

        }
        catch (SQLException e)
        {
          throw new RuntimeException(e);
        }
      });
      Assertions.assertTrue(() -> {
        try
        {
          return mdao.approveMeeting(traineeD, coach, date2);

        }
        catch (SQLException e)
        {
          throw new RuntimeException(e);
        }
      });
      Assertions.assertTrue(() -> {
        try
        {
          return mdao.approveMeeting(traineeD, coach, date3);

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
    finally
    {
      connection.close();
    }
    service.switchToProductionDatabase();
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
    SetupTestDatabase();
    Assertions.assertThrows(NullPointerException.class, () -> {
      try
      {
        mdao.denyMeeting(null, null, null);
      }
      catch (SQLException e)
      {
        throw new RuntimeException(e);
      }
    });
    service.switchToProductionDatabase();
  }

  @Test void denyMeetingOne() throws SQLException
  {
    SetupTestDatabase();
    try
    {
      db = DBConnection.getInstance();
      connection = db.getConnection();
      statement = connection.prepareStatement(
          "insert into meeting_request values('d', 'coach', '2023-05-16');");
      statement.executeUpdate();

      Assertions.assertTrue(() -> {
        try
        {
          return mdao.denyMeeting(traineeD, coach, date1);
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
    finally
    {
      connection.close();
    }
    service.switchToProductionDatabase();
  }

  @Test void denyMeetingMany() throws SQLException
  {
    SetupTestDatabase();
    try
    {
      db = DBConnection.getInstance();
      connection = db.getConnection();
      statement = connection.prepareStatement(
          "insert into meeting_request values('d', 'coach', '2023-05-16');"
              + "insert into meeting_request values('d', 'coach', '2023-05-17');"
              + "insert into meeting_request values('d', 'coach', '2023-05-18');");
      statement.executeUpdate();

      Assertions.assertTrue(() -> {
        try
        {
          return mdao.denyMeeting(traineeD, coach, date1);
        }
        catch (SQLException e)
        {
          throw new RuntimeException(e);
        }
      });
      Assertions.assertTrue(() -> {
        try
        {
          return mdao.denyMeeting(traineeD, coach, date2);
        }
        catch (SQLException e)
        {
          throw new RuntimeException(e);
        }
      });
      Assertions.assertTrue(() -> {
        try
        {
          return mdao.denyMeeting(traineeD, coach, date3);
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
    finally
    {
      connection.close();
    }
    service.switchToProductionDatabase();
  }

  @Test void denyMeetingBoundary()
  {
    //no boundary
  }

  @Test void denyMeetingException()
  {
    //already tested
  }

  @Test void getCoachMeetingRequestsZero()
  {
    SetupTestDatabase();
    try
    {
      Assertions.assertNotNull(mdao.getCoachMeetingRequests(null));
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
    service.switchToProductionDatabase();
  }

  @Test void getCoachMeetingRequestsOne()
  {
    SetupTestDatabase();
    try
    {
      Assertions.assertNotNull(mdao.getCoachMeetingRequests(coach));
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
    service.switchToProductionDatabase();
  }

  @Test void getCoachMeetingRequestsMany()
  {
    SetupTestDatabase();
    try
    {
      Assertions.assertNotNull(mdao.getCoachMeetingRequests(coach));
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
    try
    {
      Assertions.assertNotNull(mdao.getCoachMeetingRequests(coach));
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
    try
    {
      Assertions.assertNotNull(mdao.getCoachMeetingRequests(coach));
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
    service.switchToProductionDatabase();
  }

  @Test void getCoachMeetingRequestsBoundary()
  {
    //no boundary, no test
  }

  @Test void getCoachMeetingRequestsException()
  {
    //already tested
  }

  @Test void getCoachMeetingsZero()
  {
    SetupTestDatabase();
    Assertions.assertDoesNotThrow(() -> mdao.getCoachMeetings(null));
    service.switchToProductionDatabase();
  }

  @Test void getCoachMeetingsOne()
  {
    SetupTestDatabase();
    try
    {
      Assertions.assertNotNull(mdao.getCoachMeetings(coach));
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
    service.switchToProductionDatabase();
  }

  @Test void getCoachMeetingsMany()
  {
    SetupTestDatabase();
    try
    {
      Assertions.assertNotNull(mdao.getCoachMeetings(coach));
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
    try
    {
      Assertions.assertNotNull(mdao.getCoachMeetings(coach));
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
    try
    {
      Assertions.assertNotNull(mdao.getCoachMeetings(coach));
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
    service.switchToProductionDatabase();
  }

  @Test void getCoachMeetingsBoundary()
  {
    //no boundary
  }

  @Test void getCoachMeetingsException()
  {
    //tested already
  }

  @Test void sendMeetingRequestZero()
  {
    SetupTestDatabase();
    Assertions.assertThrows(Exception.class, () -> {
      try
      {
        mdao.sendMeetingRequest(null, null, null);
      }
      catch (Exception e)
      {
        throw new RuntimeException(e);
      }
    });
    service.switchToProductionDatabase();
  }

  @Test void sendMeetingRequestOne()
  {
    SetupTestDatabase();
    try
    {
      Assertions.assertTrue(mdao.sendMeetingRequest(traineeD, coach, date1));
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
    service.switchToProductionDatabase();
  }

  @Test void sendMeetingRequestMany()
  {
    SetupTestDatabase();
    try
    {
      Assertions.assertTrue(mdao.sendMeetingRequest(traineeD, coach, date1));
      Assertions.assertFalse(mdao.sendMeetingRequest(traineeD, coach, date1));
      Assertions.assertTrue(mdao.sendMeetingRequest(traineeD, coach, date2));
      Assertions.assertFalse(mdao.sendMeetingRequest(traineeD, coach, date2));
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
    service.switchToProductionDatabase();
  }

  @Test void sendMeetingRequestBoundary()
  {
  }

  @Test void sendMeetingRequestException()
  {
  }

  //returns boolean
  @Test void removeMeetingZero()
  {
    SetupTestDatabase();
    try
    {
      Assertions.assertTrue(mdao.removeMeeting(null, null, date1));
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
    Assertions.assertThrows(Exception.class, () -> {
      try
      {
        mdao.removeMeeting(null, null, null);
      }
      catch (Exception e)
      {
        throw new RuntimeException(e);
      }
    });
    service.switchToProductionDatabase();
  }

  @Test void removeMeetingOne()
  {
    SetupTestDatabase();
    try
    {
      mdao.sendMeetingRequest(traineeD, coach, date2);
      mdao.approveMeeting(traineeD, coach, date2);
      Assertions.assertTrue(mdao.removeMeeting(traineeD, coach, date2));
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
    service.switchToProductionDatabase();
  }

  @Test void removeMeetingMany()
  {
    SetupTestDatabase();
    try
    {
      mdao.sendMeetingRequest(traineeA, coach, date1);
      mdao.sendMeetingRequest(traineeD, coach, date2);
      mdao.sendMeetingRequest(traineeD, coach, date3);
      mdao.approveMeeting(traineeA, coach, date1);
      mdao.approveMeeting(traineeD, coach, date2);
      mdao.approveMeeting(traineeD, coach, date3);
      Assertions.assertTrue(mdao.removeMeeting(traineeD, coach, date3));
      Assertions.assertTrue(mdao.removeMeeting(traineeD, coach, date2));
      Assertions.assertTrue(mdao.removeMeeting(traineeA, coach, date1));
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
    service.switchToProductionDatabase();
  }

  @Test void removeMeetingBoundary()
  {
  }

  @Test void removeMeetingException()
  {
  }

  @Test void getTraineeMeetingRequestZero()
  {
    SetupTestDatabase();
    try
    {
      Assertions.assertNotNull(mdao.getTraineeMeetingRequests(null));
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
    service.switchToProductionDatabase();
  }

  @Test void getTraineeMeetingRequestOne()
  {
    SetupTestDatabase();
    try
    {
      mdao.sendMeetingRequest(traineeD, coach, date1);
      Assertions.assertNotNull(mdao.getTraineeMeetingRequests(traineeD));
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
    service.switchToProductionDatabase();
  }

  @Test void getTraineeMeetingRequestMany()
  {
    SetupTestDatabase();
    try
    {
      mdao.sendMeetingRequest(traineeA, coach, date1);
      mdao.sendMeetingRequest(traineeD, coach, date2);
      mdao.sendMeetingRequest(traineeD, coach, date3);
      Assertions.assertNotNull(mdao.getTraineeMeetingRequests(traineeD));
      Assertions.assertNotNull(mdao.getTraineeMeetingRequests(traineeA));

    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
    service.switchToProductionDatabase();
  }

  @Test void getTraineeMeetingRequestBoundary()
  {
    //no boundary
  }

  @Test void getTraineeMeetingRequestException()
  {
    //tested
  }

  @Test void getTraineeMeetingListZero()
  {
    SetupTestDatabase();
    try
    {
      Assertions.assertNotNull(mdao.getTraineeMeetingList(null));
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
    service.switchToProductionDatabase();
  }

  @Test void getTraineeMeetingListOne()
  {
    SetupTestDatabase();
    try
    {
      mdao.sendMeetingRequest(traineeD, coach, date1);
      mdao.approveMeeting(traineeD, coach, date1);
      Assertions.assertNotNull(mdao.getTraineeMeetingList(traineeD));
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
    service.switchToProductionDatabase();
  }

  @Test void getTraineeMeetingListMany()
  {
    SetupTestDatabase();
    try
    {
      mdao.sendMeetingRequest(traineeA, coach, date1);
      mdao.sendMeetingRequest(traineeD, coach, date2);
      mdao.sendMeetingRequest(traineeD, coach, date3);
      mdao.approveMeeting(traineeA, coach, date1);
      mdao.approveMeeting(traineeD, coach, date2);
      mdao.approveMeeting(traineeD, coach, date3);
      Assertions.assertNotNull(mdao.getTraineeMeetingList(traineeD));
      Assertions.assertNotNull(mdao.getTraineeMeetingList(traineeA));

    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
    service.switchToProductionDatabase();
  }

  @Test void getTraineeMeetingListBoundary()
  {
    //no boundary
  }

  @Test void getTraineeMeetingListException()
  {
    //tested
  }
}