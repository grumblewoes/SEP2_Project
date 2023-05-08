package modelServer.DAO.implementation;

import mediator.User;
import modelServer.DAO.interfaces.ICoachDAO;
import modelServer.DAO.interfaces.IUserDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class CoachDAOTest {
    private ICoachDAO cdao;
    private IUserDAO udao;

    @BeforeEach
    void setUp() {
        cdao = new CoachDAO();
        udao = new UserDAO();
    }

    @Test
    void addCoachZero() {
        assertThrows(SQLException.class, ()->cdao.addCoach(null, null, null, null, 0, 0, 0, 0, 0, null, false));
    }
    @Test
    void addCoachOne() {
        assertDoesNotThrow(()->cdao.addCoach("TheLegend27", "27LegendThe", "Alpha", "Male", 180, 80, 220, 220, 220, "On that grind", true));
    }
    @Test
    void addCoachMany() {
        assertDoesNotThrow(()->cdao.addCoach("TheLegend28", "28LegendThe", "Alpha1", "Male1", 180, 80, 220, 220, 220, "On that grind", true));
        assertDoesNotThrow(()->cdao.addCoach("TheLegend29", "29LegendThe", "Alpha2", "Male2", 180, 80, 220, 220, 220, "On that grind", true));
        assertDoesNotThrow(()->cdao.addCoach("TheLegend30", "30LegendThe", "Alpha3", "Male3", 180, 80, 220, 220, 220, "On that grind", true));
    }
    @Test
    void addCoachBoundary() {
    //??
    }
    @Test
    void addCoachException() {
        //??
    }

    @Test
    void removeCoachZero() {
        assertThrows(SQLException.class, ()->cdao.removeCoach(null));
    }
    @Test
    void removeCoachOne() {
        try {
            cdao.addCoach("TheLegend31", "31LegendThe", "Alpha", "Male", 180, 80, 220, 220, 220, "On that grind", true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        assertDoesNotThrow(()->cdao.removeCoach("TheLegend31"));
    }
    @Test
    void removeCoachMany() {
        try {
            cdao.addCoach("TheLegend32", "32LegendThe", "Alpha", "Male", 180, 80, 220, 220, 220, "On that grind", true);
            cdao.addCoach("TheLegend33", "33LegendThe", "Alpha", "Male", 180, 80, 220, 220, 220, "On that grind", true);
            cdao.addCoach("TheLegend34", "34LegendThe", "Alpha", "Male", 180, 80, 220, 220, 220, "On that grind", true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        assertDoesNotThrow(()->cdao.removeCoach("TheLegend32"));
        assertDoesNotThrow(()->cdao.removeCoach("TheLegend33"));
        assertDoesNotThrow(()->cdao.removeCoach("TheLegend34"));
    }
    @Test
    void removeCoachBoundary() {
        //??
    }
    @Test
    void removeCoachException() {
        //??
    }

    @Test
    void getCoachZero() {
        cdao.getCoach()
    }
    @Test
    void getCoachOne() {
    }
    @Test
    void getCoachMany() {
        try {
            cdao.addCoach("TheLegend35", "35LegendThe", "Alpha", "Male", 180, 80, 220, 220, 220, "On that grind", true);
            cdao.addCoach("TheLegend36", "36LegendThe", "Alpha", "Male", 180, 80, 220, 220, 220, "On that grind", true);
            cdao.addCoach("TheLegend37", "37LegendThe", "Alpha", "Male", 180, 80, 220, 220, 220, "On that grind", true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    @Test
    void getCoachBoundary() {
        //??
    }
    @Test
    void getCoachException() {
        //??
    }
}