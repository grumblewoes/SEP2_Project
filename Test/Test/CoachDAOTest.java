import mediator.User;
import modelServer.DAO.implementation.CoachDAO;
import modelServer.DAO.implementation.UserDAO;
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
        assertTrue(()-> {
            try {
                return cdao.addCoach("TheLegend27", "27LegendThe", "Alpha", "Male", 180, 80, 220, 220, 220, "On that grind", true);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }
    @Test
    void addCoachMany() {
        assertTrue(()-> {
            try {
                return cdao.addCoach("TheLegend28", "28LegendThe", "Alpha1", "Male1", 180, 80, 220, 220, 220, "On that grind", true);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        assertTrue(()-> {
            try {
                return cdao.addCoach("TheLegend29", "29LegendThe", "Alpha2", "Male2", 180, 80, 220, 220, 220, "On that grind", true);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        assertTrue(()-> {
            try {
                return cdao.addCoach("TheLegend30", "30LegendThe", "Alpha3", "Male3", 180, 80, 220, 220, 220, "On that grind", true);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }
    @Test
    void addCoachBoundary() {
        //already tested
    }
    @Test
    void addCoachException() {
        //inserting same coach twice (i.e coach w same username. are we making check constraints for first/last name?)
        try {
            cdao.addCoach("TheLegend42", "28LegendThe", "Alpha1", "Male1", 180, 80, 220, 220, 220, "On that grind", true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        assertThrows(SQLException.class,()->cdao.addCoach("TheLegend42", "28LegendThe", "Alpha1", "Male1", 180, 80, 220, 220, 220, "On that grind", true));
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
        assertTrue(()-> {
            try {
                return cdao.removeCoach("TheLegend31");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
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
    }
    @Test
    void removeCoachBoundary() {
        try {
            cdao.addCoach("TheLegend32", "32LegendThe", "Alpha", "Male", 180, 80, 220, 220, 220, "On that grind", true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        assertDoesNotThrow(()->cdao.removeCoach("TheLegend32"));
        assertThrows(SQLException.class, ()->cdao.removeCoach("TheLegend32"));
    }
    @Test
    void removeCoachException() {
        //removing a coach that was never listed
        assertThrows(SQLException.class, ()->cdao.removeCoach("TheLegend69"));
    }

    @Test
    void getCoachZero() {
        assertThrows(SQLException.class, ()-> cdao.getCoach("noobMan27"));
    }
    @Test
    void getCoachOne() {
        assertDoesNotThrow(()-> cdao.getCoach("noobMan27"));
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
        assertDoesNotThrow(()-> cdao.getCoach("noobMan27"));
        assertDoesNotThrow(()-> cdao.getCoach("noobMan28"));
        assertDoesNotThrow(()-> cdao.getCoach("noobMan29"));
    }
    @Test
    void getCoachBoundary() {
        // wut
    }
    @Test
    void getCoachException() {
        //getting a coach that doesn't exist, either cuz user doesn't exist or user has no coach
        assertThrows(SQLException.class, ()-> cdao.getCoach("noobMan69")); //user doesn't exist
        assertThrows(SQLException.class, ()-> cdao.getCoach("noobMan420")); //user has no coach
    }
}