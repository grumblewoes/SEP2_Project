import modelServer.DAO.implementation.CoachAdminDAO;
import modelServer.DAO.implementation.CoachDAO;
import modelServer.DAO.implementation.UserDAO;
import modelServer.DAO.interfaces.ICoachAdminDAO;
import modelServer.DAO.interfaces.ICoachDAO;
import modelServer.DAO.interfaces.IUserDAO;
import modelServer.DbContext.DBService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DAOCoachAdminTest
{
  DBService service;
  private ICoachAdminDAO cdao;
  private IUserDAO udao;

  @BeforeEach
  void setUp() {
    service=new DBService();
    cdao = new CoachAdminDAO();
    udao = new UserDAO();
  }

  private void SetupTestDatabase(){
    service.restartDatabase();
    service.switchToTestDatabase();
  }

  @Test
  void addCoachZero() {
    SetupTestDatabase();
    assertFalse(()-> {
      try {
        return cdao.addCoach(null, null, null, null, 0, 0, 0, 0, 0, null);
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    });
    service.switchToProductionDatabase();
  }
  @Test
  void addCoachOne() {
    SetupTestDatabase();
    assertTrue(()-> {
      try {
        return cdao.addCoach("TheLegend27", "LegendThe27", "Alpha", "Male", 180, 80, 220, 220, 220, "On that grind");
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    });
    service.switchToProductionDatabase();
  }
  @Test
  void addCoachMany() {
    SetupTestDatabase();
    assertTrue(()-> {
      try {
        return cdao.addCoach("TheLegend28", "g", "Alpha1", "Male1", 180, 80, 220, 220, 220, "On that grind");
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    });
    assertTrue(()-> {
      try {
        return cdao.addCoach("TheLegend29", "g", "Alpha2", "Male2", 180, 80, 220, 220, 220, "On that grind");
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    });
    assertTrue(()-> {
      try {
        return cdao.addCoach("TheLegend30", "g", "Alpha3", "Male3", 180, 80, 220, 220, 220, "On that grind");
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    });
    service.switchToProductionDatabase();
  }
  @Test
  void addCoachBoundary() {
    //already tested
  }
  @Test
  void addCoachException() {
    //inserting same coach twice (i.e coach w same username. are we making check constraints for first/last name?)
    SetupTestDatabase();
    try {
      cdao.addCoach("TheLegend42", "g", "Alpha1", "Male1", 180, 80, 220, 220, 220, "On that grind");
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    try {
      assertFalse(cdao.addCoach("TheLegend42", "g", "Alpha1", "Male1", 180, 80, 220, 220, 220, "On that grind"));
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    service.switchToProductionDatabase();
  }

  @Test
  void removeCoachZero() {
    SetupTestDatabase();
    try {
      assertFalse(cdao.removeCoach(null));
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    service.switchToProductionDatabase();
  }
  @Test
  void removeCoachOne() {
    SetupTestDatabase();
    try {
      cdao.addCoach("TheLegend31", "g", "Alpha", "Male", 180, 80, 220, 220, 220, "On that grind");
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    assertTrue(()-> {
      try {
        return cdao.removeCoach("TheLegend31");
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    });
    service.switchToProductionDatabase();
  }
  @Test
  void removeCoachMany() {
    SetupTestDatabase();
    try {
      cdao.addCoach("TheLegend32", "g", "Alpha", "Male", 180, 80, 220, 220, 220, "On that grind");
      cdao.addCoach("TheLegend33", "g", "Alpha", "Male", 180, 80, 220, 220, 220, "On that grind");
      cdao.addCoach("TheLegend34", "g", "Alpha", "Male", 180, 80, 220, 220, 220, "On that grind");
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    assertTrue(()-> {
      try {
        return cdao.removeCoach("TheLegend32");
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    });
    assertTrue(()-> {
      try {
        return cdao.removeCoach("TheLegend33");
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    });
    assertTrue(()-> {
      try {
        return cdao.removeCoach("TheLegend34");
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    });
    service.switchToProductionDatabase();
  }
  @Test
  void removeCoachBoundary() {
    SetupTestDatabase();
    try {
      cdao.addCoach("TheLegend32", "32LegendThe", "Alpha", "Male", 180, 80, 220, 220, 220, "On that grind");
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    try {
      assertTrue(cdao.removeCoach("TheLegend32"));
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    try {
      assertFalse(cdao.removeCoach("TheLegend32"));
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    service.switchToProductionDatabase();
  }
  @Test
  void removeCoachException() {
    //removing a coach that was never listed
    SetupTestDatabase();
    try {
      assertFalse(cdao.removeCoach("TheLegend69"));
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    service.switchToProductionDatabase();
  }
}
