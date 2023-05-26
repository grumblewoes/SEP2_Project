import modelServer.DAO.implementation.ExerciseAdminDAO;
import modelServer.DAO.interfaces.IExerciseAdminDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 
 * 
 * 
 * @author 
 * @version 
 */
public class DAOExerciseAdminTest
{
  private IExerciseAdminDAO dao;

  @BeforeEach void setUp()
  {
    dao = new ExerciseAdminDAO();
  }

  @Test void addExerciseZero() throws SQLException
  {
    assertThrows(SQLException.class, () -> dao.addExercise(null));
  }

  @Test void addExerciseOne()
  {
    assertTrue(() -> {
      try
      {
        return dao.addExercise("Sq");
      }
      catch (SQLException e)
      {
        throw new RuntimeException(e);
      }
    });
  }

  @Test void addExerciseMany()
  {
    assertTrue(() -> {
      try
      {
        return dao.addExercise("E");
      }
      catch (SQLException e)
      {
        throw new RuntimeException(e);
      }
    });
    assertTrue(() -> {
      try
      {
        return dao.addExercise("Ex");
      }
      catch (SQLException e)
      {
        throw new RuntimeException(e);
      }
    });
    assertTrue(() -> {
      try
      {
        return dao.addExercise("Exe");
      }
      catch (SQLException e)
      {
        throw new RuntimeException(e);
      }
    });
  }

  @Test void addExerciseBoundary()
  {
    //already tested
  }

  @Test void addExerciseException()
  {
    //inserting same exercise twice
    try
    {
      dao.addExercise("Exercise1");
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
    try
    {
      assertFalse(dao.addExercise("Exercise1"));
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
  }

  @Test void removeExerciseZero()
  {
    try
    {
      assertFalse(dao.removeExercise(null));
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
  }

  @Test void removeExerciseOne()
  {
    try
    {
      dao.addExercise("Abs");
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
    assertDoesNotThrow(() -> {
      try
      {
        return dao.removeExercise("Abs");
      }
      catch (SQLException e)
      {
        throw new RuntimeException(e);
      }
    });
  }

  @Test void removeExerciseMany()
  {
    try
    {
      dao.addExercise("Exercise1");
      dao.addExercise("Exercise2");
      dao.addExercise("Exercise3");
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
    assertDoesNotThrow(() -> {
      try
      {
        return dao.removeExercise("Exercise1");
      }
      catch (SQLException e)
      {
        throw new RuntimeException(e);
      }
    });
    assertDoesNotThrow(() -> {
      try
      {
        return dao.removeExercise("Exercise2");
      }
      catch (SQLException e)
      {
        throw new RuntimeException(e);
      }
    });
    assertDoesNotThrow(() -> {
      try
      {
        return dao.removeExercise("Exercise3");
      }
      catch (SQLException e)
      {
        throw new RuntimeException(e);
      }
    });
  }

  @Test void removeCoachException()
  {
    //removing an exercise that was never listed
    try
    {
      assertFalse(dao.removeExercise("Exercise6"));
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
  }

}
