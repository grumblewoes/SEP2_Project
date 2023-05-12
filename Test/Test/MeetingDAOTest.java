import modelServer.DAO.implementation.MeetingDAO;
import modelServer.DAO.interfaces.IMeetingDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class MeetingDAOTest
{
  IMeetingDAO mdao;
  String trainee, coach;
  Date date;

  @BeforeEach void setUp()
  {
    mdao = new MeetingDAO();
    trainee = ;
    coach = ;
    date = new Date();
  }

  @Test void approveMeetingZero()
  {
    assertFalse(()-> {
      try
      {
        return mdao.approveMeeting(null, null, null);
      }
      catch (SQLException e)
      {
        throw new RuntimeException(e);
      }
    });
  }
  @Test void approveMeetingOne()
  {
    assertTrue(()-> {
      try
      {
        return mdao.approveMeeting(trainee, coach, date);
      }
      catch (SQLException e)
      {
        throw new RuntimeException(e);
      }
    });
  }
  @Test void approveMeetingMany()
  {
    assertTrue(()-> {
      try
      {
        return mdao.approveMeeting(trainee, coach, date);
      }
      catch (SQLException e)
      {
        throw new RuntimeException(e);
      }
    });
    assertTrue(()-> {
      try
      {
        return mdao.approveMeeting(trainee, coach, date);
      }
      catch (SQLException e)
      {
        throw new RuntimeException(e);
      }
    });
    assertTrue(()-> {
      try
      {
        return mdao.approveMeeting(trainee, coach, date);
      }
      catch (SQLException e)
      {
        throw new RuntimeException(e);
      }
    });
  }
  @Test void approveMeetingBoundary()
  {
    //no boundary
  }
  @Test void approveMeetingException()
  {
    //trainee that doesn't have a coach
    assertTrue(()-> {
      try
      {
        return mdao.approveMeeting(trainee, coach, date);
      }
      catch (SQLException e)
      {
        throw new RuntimeException(e);
      }
    });
    //coach not associated with trainee
    assertTrue(()-> {
      try
      {
        return mdao.approveMeeting(trainee, coach, date);
      }
      catch (SQLException e)
      {
        throw new RuntimeException(e);
      }
    });
    //duplicate meeting
    assertTrue(()-> {
      try
      {
        return mdao.approveMeeting(trainee, coach, date);
      }
      catch (SQLException e)
      {
        throw new RuntimeException(e);
      }
    });
  }

  @Test void denyMeetingZero()
  {
    assertFalse(()-> {
      try
      {
        return mdao.denyMeeting(null, null, null);
      }
      catch (SQLException e)
      {
        throw new RuntimeException(e);
      }
    });
  }
  @Test void denyMeetingOne()
  {
    assertTrue(()-> {
      try
      {
        return mdao.denyMeeting(trainee, coach, date);
      }
      catch (SQLException e)
      {
        throw new RuntimeException(e);
      }
    });
  }
  @Test void denyMeetingMany()
  {
    assertTrue(()-> {
      try
      {
        return mdao.denyMeeting(trainee, coach, date);
      }
      catch (SQLException e)
      {
        throw new RuntimeException(e);
      }
    });
    assertTrue(()-> {
      try
      {
        return mdao.denyMeeting(trainee, coach, date);
      }
      catch (SQLException e)
      {
        throw new RuntimeException(e);
      }
    });
    assertTrue(()-> {
      try
      {
        return mdao.denyMeeting(trainee, coach, date);
      }
      catch (SQLException e)
      {
        throw new RuntimeException(e);
      }
    });
  }
  @Test void denyMeetingBoundary()
  {
    //no boundary
  }
  @Test void denyMeetingException()
  {
    //trainee w no coach
    assertTrue(()-> {
      try
      {
        return mdao.denyMeeting(trainee, coach, date);
      }
      catch (SQLException e)
      {
        throw new RuntimeException(e);
      }
    });
    //coach not associated w trainee
    assertTrue(()-> {
      try
      {
        return mdao.denyMeeting(trainee, coach, date);
      }
      catch (SQLException e)
      {
        throw new RuntimeException(e);
      }
    });
  }
}