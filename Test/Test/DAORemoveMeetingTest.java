import modelServer.DAO.implementation.MeetingDAO;
import modelServer.DAO.interfaces.IMeetingDAO;
import modelServer.DbContext.DBConnection;
import modelServer.DbContext.DBService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DAORemoveMeetingTest
{
  private IMeetingDAO mdao;
  String trainee, coach;
  LocalDate date1, date2, date3;
  PreparedStatement statement;
  DBConnection db;
  Connection connection;

  DBService service;

  @BeforeEach void setUp() throws SQLException
  {
    service=new DBService();
    mdao = new MeetingDAO();
    trainee = "d";
    coach = "coach";
    date1 = LocalDate.of(2023, 04, 20);
    date2 = LocalDate.of(2023, 04, 21);
    date3 = LocalDate.of(2023, 04, 22);

    try
    {
      db = DBConnection.getInstance();
      connection = db.getConnection();
      statement = connection.prepareStatement("delete from meeting_list;");
      statement.executeUpdate();
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
    finally
    {
  //
    }
  }

  private void SetupTestDatabase(){
    service.restartDatabase();
    service.switchToTestDatabase();
  }

  @Test public void removeMeetingZero()
  {
    Assertions.assertThrows(NullPointerException.class, () -> {
      try
      {
        mdao.removeMeeting(null, null, null);
      }
      catch (SQLException e)
      {
        throw new RuntimeException(e);
      }
    });
  }

  @Test public void removeMeetingOne() throws SQLException
  {
    try
    {
      db = DBConnection.getInstance();
      connection = db.getConnection();
      statement = connection.prepareStatement(
          "insert into meeting_list values('d', 'coach', '2023-05-20');");
      statement.executeUpdate();

      Assertions.assertTrue(() -> {
        try
        {
          return mdao.removeMeeting(trainee, coach, date1);
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

  @Test public void removeMeetingMany() throws SQLException {
    try
    {
      db = DBConnection.getInstance();
      connection = db.getConnection();
      statement = connection.prepareStatement(
          "insert into meeting_list values('d', 'coach', '2023-05-20');"
      + "insert into meeting_list values('d', 'coach', '2023-05-21');"
              + "insert into meeting_list values('d', 'coach', '2023-05-22');");
      statement.executeUpdate();

      Assertions.assertTrue(() -> {
        try
        {
          return mdao.removeMeeting(trainee, coach, date1);
        }
        catch (SQLException e)
        {
          throw new RuntimeException(e);
        }
      });

      Assertions.assertTrue(() -> {
        try
        {
          return mdao.removeMeeting(trainee, coach, date2);
        }
        catch (SQLException e)
        {
          throw new RuntimeException(e);
        }
      });

      Assertions.assertTrue(() -> {
        try
        {
          return mdao.removeMeeting(trainee, coach, date3);
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

  @Test void removeMeetingBoundary()
  {
    //No boundary
  }
  @Test void removeMeetingException()
  {
    //Tested
  }




}
