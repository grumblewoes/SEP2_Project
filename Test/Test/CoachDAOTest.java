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
                return cdao.addCoach("TheLegend27", "LegendThe27", "Alpha", "Male", 180, 80, 220, 220, 220, "On that grind", true);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }
    @Test
    void addCoachMany() {
        assertTrue(()-> {
            try {
                return cdao.addCoach("TheLegend28", "g", "Alpha1", "Male1", 180, 80, 220, 220, 220, "On that grind", true);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        assertTrue(()-> {
            try {
                return cdao.addCoach("TheLegend29", "g", "Alpha2", "Male2", 180, 80, 220, 220, 220, "On that grind", true);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        assertTrue(()-> {
            try {
                return cdao.addCoach("TheLegend30", "g", "Alpha3", "Male3", 180, 80, 220, 220, 220, "On that grind", true);
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
            cdao.addCoach("TheLegend42", "g", "Alpha1", "Male1", 180, 80, 220, 220, 220, "On that grind", true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            assertFalse(cdao.addCoach("TheLegend42", "g", "Alpha1", "Male1", 180, 80, 220, 220, 220, "On that grind", true));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void removeCoachZero() {
        try {
            assertFalse(cdao.removeCoach(null));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void removeCoachOne() {
        try {
            cdao.addCoach("TheLegend31", "g", "Alpha", "Male", 180, 80, 220, 220, 220, "On that grind", true);
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
            cdao.addCoach("TheLegend32", "g", "Alpha", "Male", 180, 80, 220, 220, 220, "On that grind", true);
            cdao.addCoach("TheLegend33", "g", "Alpha", "Male", 180, 80, 220, 220, 220, "On that grind", true);
            cdao.addCoach("TheLegend34", "g", "Alpha", "Male", 180, 80, 220, 220, 220, "On that grind", true);
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
    }
    @Test
    void removeCoachException() {
        //removing a coach that was never listed
        try {
            assertFalse(cdao.removeCoach("TheLegend69"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getCoachZero() {
        try {
            assertNull(cdao.getCoach("noobMan27"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void getCoachOne() {
        assertDoesNotThrow(()-> cdao.getCoach("noobMan27"));
    }
    @Test
    void getCoachMany() {
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
        try {
            assertNull(cdao.getCoach("noobMan69")); //user doesn't exist
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            assertNull(cdao.getCoach("noobMan420")); //user has no coach
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void isCoachZero() {
        try {
            assertFalse(cdao.isCoach(null));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void isCoachOne() {
        try {
            assertTrue(cdao.isCoach("TheLegend27"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void isCoachMany() {
        try {
            assertTrue(cdao.isCoach("TheLegend27"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            assertTrue(cdao.isCoach("TheLegend28"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            assertTrue(cdao.isCoach("TheLegend29"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void isCoachBoundary() {
        //no boundary
    }
    @Test
    void isCoachException() {
        //coach that does not exist in the system, aka random or trainee
        try {
            assertFalse(cdao.isCoach("coolguy67"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void requestCoachZero() {
        try {
            assertNull(cdao.requestCoach(null, null));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void requestCoachOne() {
        try { //change this
            assertNotNull(cdao.requestCoach("noobMan67", "TheLegend27"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void requestCoachMany() {
        try { //change these plz
            assertNotNull(cdao.requestCoach("noobMan67", "TheLegend27"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            assertNotNull(cdao.requestCoach("noobMan67", "TheLegend27"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            assertNotNull(cdao.requestCoach("noobMan67", "TheLegend27"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void requestCoachBoundary() {
        //?? no boundary
    }
    @Test
    void requestCoachException() {
        //user already requested a coach. change this plz
        try {
            assertNull(cdao.requestCoach("noobMan68", "TheLegend69"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}