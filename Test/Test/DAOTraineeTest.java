import mediator.User;
import modelServer.DAO.implementation.CoachAdminDAO;
import modelServer.DAO.implementation.CoachDAO;
import modelServer.DAO.implementation.TraineeDAO;
import modelServer.DAO.implementation.UserDAO;
import modelServer.DAO.interfaces.ICoachAdminDAO;
import modelServer.DAO.interfaces.ICoachDAO;
import modelServer.DAO.interfaces.ITraineeDAO;
import modelServer.DAO.interfaces.IUserDAO;
import modelServer.DbContext.DBService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class DAOTraineeTest
{
  private DBService service;
  private ICoachAdminDAO cdao;
  private ITraineeDAO dao;
  private String username1, username2, username3, password, firstName, lastName;
  private int height, weight;

  @BeforeEach void setUp()
  {
    cdao = new CoachAdminDAO();
    dao = new TraineeDAO();
    service = new DBService();
    username1 = "username" + Math.random();
    username2 = "username" + Math.random();
    username3 = "username" + Math.random();
    password = "password" + Math.random();
    firstName = "firstName" + Math.random();
    lastName = "lastName" + Math.random();
    SetupTestDatabase();
  }

  private void SetupTestDatabase()
  {
    service.restartDatabase();
    service.switchToTestDatabase();
  }

  @org.junit.jupiter.api.AfterEach
  void tearDown() {
    
  }
  @Test public void getCoachZero()
  {
    
    try
    {
      assertNull(dao.getCoach("noobMan27"));
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
    
  }

  @Test public void getCoachOne()
  {
    
    assertDoesNotThrow(() -> dao.getCoach("d"));
    
  }

  @Test public void getCoachMany()
  {
    
    try
    {
      cdao.addCoach("noobMan27", "d", "d", "d", 1, 1, 1, 1, 1, "g");
      cdao.addCoach("noobMan28", "d", "d", "d", 1, 1, 1, 1, 1, "g");
      cdao.addCoach("noobMan29", "d", "d", "d", 1, 1, 1, 1, 1, "g");
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
    assertDoesNotThrow(() -> dao.getCoach("noobMan27"));
    assertDoesNotThrow(() -> dao.getCoach("noobMan28"));
    assertDoesNotThrow(() -> dao.getCoach("noobMan29"));
    
  }

  @Test public void getCoachBoundary()
  {
    // wut
  }

  @Test public void getCoachException()
  {
    //getting a coach that doesn't exist, either cuz user doesn't exist or user has no coach
    
    try
    {
      assertNull(dao.getCoach("noobMan69")); //user doesn't exist
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
    
  }

  @Test public void createZero()
  {
    
    assertDoesNotThrow(() -> dao.createTrainee(null, null, null, null, 0, 0));
    
  }

  @Test public void createOne()
  {
    
    assertDoesNotThrow(
        () -> dao.createTrainee(username1, password, firstName, lastName,
            height, weight));
    
  }

  @Test public void createMany()
  {
    
    assertDoesNotThrow(
        () -> dao.createTrainee(username2, password, firstName, lastName,
            height, weight));
    assertDoesNotThrow(
        () -> dao.createTrainee(username3, password, firstName, lastName,
            height, weight));
    
  }

  @Test public void createBoundary()
  {
    //pass
  }

  @Test public void createExceptions()
  {
    //tested in Z
  }


  /**
   *
   */
  @org.junit.jupiter.api.Test public void getTraineeZero()
  {
    
    assertDoesNotThrow(() -> dao.getTrainee(null));
    
  }

  /**
   *
   */
  @org.junit.jupiter.api.Test public void getTraineeOne() throws SQLException
  {
    
    dao.createTrainee("a", "a", "a", "a", 100, 100);
    User user = dao.getTrainee("a");

    assertEquals("a", user.getUsername());
    
  }

  /**
   *
   */
  @org.junit.jupiter.api.Test public void getTraineeMany()
  {
    
    assertDoesNotThrow(() -> dao.getTrainee(username1));
    assertDoesNotThrow(() -> dao.getTrainee(username1));
    assertDoesNotThrow(() -> dao.getTrainee(username1));
    try
    {
      assert dao.getTrainee("dasdadsadasdadsadasdsad") == null;
    }
    catch (SQLException e)
    {
      //pass
    }
    
  }

  /**
   *
   */
  @org.junit.jupiter.api.Test public void getTraineeBoundaryException()
  {
    //pass
  }

  /**
   *
   */
  @org.junit.jupiter.api.Test public void updateZero()
  {
    
    assertDoesNotThrow(() -> {
      dao.updateTrainee(null, 0, 0, false, "");
    });
    
  }

  /**
   *
   */
  @org.junit.jupiter.api.Test public void updateOne()
  {
    
    assertDoesNotThrow(() -> {
      dao.updateTrainee(null, 0, 0, false, "");
    });
    
  }

  /**
   *
   */
  @org.junit.jupiter.api.Test public void updateMany()
  {
    
    assertDoesNotThrow(() -> {
      dao.updateTrainee(username1, 212, 50, false, "");
    });
    assertDoesNotThrow(() -> {
      dao.updateTrainee(username1, 132, 60, false, "");
    });
    assertDoesNotThrow(() -> {
      dao.updateTrainee(username2, 200, 70, true, "");
    });
    
  }

  @org.junit.jupiter.api.Test
  /**
   *
   *
   */ public void Zdelete()
  {
    assertDoesNotThrow(() -> dao.deleteTrainee("a"));
    assertDoesNotThrow(() -> dao.deleteTrainee(username1));
    assertDoesNotThrow(() -> dao.deleteTrainee(username2));
    assertDoesNotThrow(() -> dao.deleteTrainee(username3));
  }
}

