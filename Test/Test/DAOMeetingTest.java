import junit.extensions.TestSetup;
import modelServer.DAO.implementation.CoachAdminDAO;
import modelServer.DAO.implementation.MeetingDAO;
import modelServer.DAO.implementation.TraineeDAO;
import modelServer.DAO.interfaces.ICoachAdminDAO;
import modelServer.DAO.interfaces.IMeetingDAO;
import modelServer.DAO.interfaces.ITraineeDAO;
import modelServer.DbContext.DBConnection;
import modelServer.DbContext.DBService;
import org.junit.jupiter.api.AfterEach;
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
  ITraineeDAO tDao;
  ICoachAdminDAO cDao;
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
    tDao=new TraineeDAO();
    cDao=new CoachAdminDAO();
    traineeD = "d";
    traineeB = "b";
    traineeA = "a";
    coach = "coach";
    date1 = LocalDate.of(2023, 5, 16);
    date2 = LocalDate.of(2023, 5, 17);
    date3 = LocalDate.of(2023, 5, 18);
    SetupTestDatabase();
    cDao.addCoach(coach,null,null,null,0,0,0,0,0,null);
    tDao.createTrainee(traineeA,null,null,null,0,0);
    tDao.createTrainee(traineeB,null,null,null,0,0);
    tDao.createTrainee(traineeD,null,null,null,0,0);

  }

  private void SetupTestDatabase(){
    service.restartDatabase();
    service.switchToTestDatabase();
  }
  @org.junit.jupiter.api.AfterEach
  void tearDown() {
    service.switchToProductionDatabase();
  }

  @Test void approveMeetingZero()
  {
    
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
  }

  @Test void approveMeetingOne() throws SQLException
  {
    
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
  }

  @Test void approveMeetingMany() throws SQLException
  {
    
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
      try
      {
        mdao.denyMeeting(null, null, null);
      }
      catch (SQLException e)
      {
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
  }

  @Test void denyMeetingMany() throws SQLException
  {
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
    
    try
    {
      Assertions.assertNotNull(mdao.getCoachMeetingRequests(null));
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
  }

  @Test void getCoachMeetingRequestsOne()
  {
    
    try
    {
      Assertions.assertNotNull(mdao.getCoachMeetingRequests(coach));
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
  }

  @Test void getCoachMeetingRequestsMany()
  {
    
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
    
    Assertions.assertDoesNotThrow(() -> mdao.getCoachMeetings(null));
  }

  @Test void getCoachMeetingsOne()
  {
    
    try
    {
      Assertions.assertNotNull(mdao.getCoachMeetings(coach));
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
  }

  @Test void getCoachMeetingsMany()
  {
    
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
  }

  @Test void sendMeetingRequestOne()
  {
    
    try
    {
      Assertions.assertTrue(mdao.sendMeetingRequest(traineeD, coach, date1));
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
  }

  @Test void sendMeetingRequestMany()
  {
    
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
  }

  @Test void removeMeetingOne()
  {
    
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
  }

  @Test void removeMeetingMany()
  {
    
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
  }

  @Test void removeMeetingBoundary()
  {
  }

  @Test void removeMeetingException()
  {
  }

  @Test void getTraineeMeetingRequestZero()
  {
    
    try
    {
      Assertions.assertNotNull(mdao.getTraineeMeetingRequests(null));
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
  }

  @Test void getTraineeMeetingRequestOne()
  {
    
    try
    {
      mdao.sendMeetingRequest(traineeD, coach, date1);
      Assertions.assertNotNull(mdao.getTraineeMeetingRequests(traineeD));
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
  }

  @Test void getTraineeMeetingRequestMany()
  {
    
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
    
    try
    {
      Assertions.assertNotNull(mdao.getTraineeMeetingList(null));
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
  }

  @Test void getTraineeMeetingListOne()
  {
    
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
  }

  @Test void getTraineeMeetingListMany()
  {
    
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