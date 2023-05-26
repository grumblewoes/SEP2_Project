import mediator.Trainee;
import mediator.TraineeList;
import modelServer.DAO.implementation.CoachDAO;
import modelServer.DAO.implementation.UserDAO;
import modelServer.DAO.interfaces.ICoachDAO;
import modelServer.DAO.interfaces.IUserDAO;
import modelServer.DbContext.DBService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DAOCoachTest
{
    DBService service;
    private ICoachDAO cdao;
    private IUserDAO udao;

    @BeforeEach
    void setUp() {
        service=new DBService();
        cdao = new CoachDAO();
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
                return cdao.addCoach(null, null, null, null, 0, 0, 0, 0, 0, null, false);
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
                return cdao.addCoach("TheLegend27", "LegendThe27", "Alpha", "Male", 180, 80, 220, 220, 220, "On that grind", true);
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
            cdao.addCoach("TheLegend42", "g", "Alpha1", "Male1", 180, 80, 220, 220, 220, "On that grind", true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            assertFalse(cdao.addCoach("TheLegend42", "g", "Alpha1", "Male1", 180, 80, 220, 220, 220, "On that grind", true));
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
        service.switchToProductionDatabase();
    }
    @Test
    void removeCoachMany() {
        SetupTestDatabase();
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
        service.switchToProductionDatabase();
    }
    @Test
    void removeCoachBoundary() {
        SetupTestDatabase();
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

    @Test
    void getCoachZero() {
        SetupTestDatabase();
        try {
            assertNull(cdao.getCoach("noobMan27"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        service.switchToProductionDatabase();
    }
    @Test
    void getCoachOne() {
        SetupTestDatabase();
        assertDoesNotThrow(()-> cdao.getCoach("d"));
        service.switchToProductionDatabase();
    }
    @Test
    void getCoachMany() {
        SetupTestDatabase();
        try {
            cdao.addCoach("noobMan27", "d", "d", "d", 1, 1, 1, 1, 1, "g", true);
            cdao.addCoach("noobMan28", "d", "d", "d", 1, 1, 1, 1, 1, "g", true);
            cdao.addCoach("noobMan29", "d", "d", "d", 1, 1, 1, 1, 1, "g", true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        assertDoesNotThrow(()-> cdao.getCoach("noobMan27"));
        assertDoesNotThrow(()-> cdao.getCoach("noobMan28"));
        assertDoesNotThrow(()-> cdao.getCoach("noobMan29"));
        service.switchToProductionDatabase();
    }
    @Test
    void getCoachBoundary() {
        // wut
    }
    @Test
    void getCoachException() {
        //getting a coach that doesn't exist, either cuz user doesn't exist or user has no coach
        SetupTestDatabase();
        try {
            assertNull(cdao.getCoach("noobMan69")); //user doesn't exist
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        service.switchToProductionDatabase();
    }

    @Test
    void isCoachZero() {
        SetupTestDatabase();
        try {
            assertFalse(cdao.isCoach(null));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        service.switchToProductionDatabase();
    }
    @Test
    void isCoachOne() {
        SetupTestDatabase();
        try {
            cdao.addCoach("TheLegend27", "d", "d", "d", 1, 1, 1, 1, 1, "g", true);
            assertTrue(cdao.isCoach("TheLegend27"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        service.switchToProductionDatabase();
    }
    @Test
    void isCoachMany() {
        SetupTestDatabase();
        try {
            cdao.addCoach("TheLegend27", "d", "d", "d", 1, 1, 1, 1, 1, "g", true);
            cdao.addCoach("TheLegend28", "d", "d", "d", 1, 1, 1, 1, 1, "g", true);
            cdao.addCoach("TheLegend29", "d", "d", "d", 1, 1, 1, 1, 1, "g", true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
        service.switchToProductionDatabase();
    }
    @Test
    void isCoachBoundary() {
        //no boundary
    }
    @Test
    void isCoachException() {
        //coach that does not exist in the system, aka random or trainee
        SetupTestDatabase();
        try {
            assertFalse(cdao.isCoach("coolguy67"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        service.switchToProductionDatabase();
    }

    @Test
    void requestCoachZero() {
        SetupTestDatabase();
        try {
            assertFalse(cdao.requestCoach(null, null));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        service.switchToProductionDatabase();
    }
    @Test
    void requestCoachOne() {
        SetupTestDatabase();
        try {
            cdao.addCoach("TheLegend27", "d", "d", "d", 1, 1, 1, 1, 1, "g", true);
            assertTrue(cdao.requestCoach("d", "TheLegend27"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        service.switchToProductionDatabase();
    }
    @Test
    void requestCoachMany() {
        SetupTestDatabase();
        try {
            udao.createTrainee("e", "e", "e", "e", 100, 100);
            udao.createTrainee("f", "e", "e", "e", 100, 100);
            cdao.addCoach("TheLegend28", "d", "d", "d", 1, 1, 1, 1, 1, "g", true);
            cdao.addCoach("TheLegend29", "d", "d", "d", 1, 1, 1, 1, 1, "g", true);
            cdao.addCoach("TheLegend30", "d", "d", "d", 1, 1, 1, 1, 1, "g", true);
            assertTrue(cdao.requestCoach("d", "TheLegend28"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            assertTrue(cdao.requestCoach("e", "TheLegend29"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            assertTrue(cdao.requestCoach("f", "TheLegend28"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        service.switchToProductionDatabase();
    }
    @Test
    void requestCoachBoundary() {
        //?? no boundary
    }
    @Test
    void requestCoachException() {
        //user already requested a coach
        SetupTestDatabase();
        try {
            assertFalse(cdao.requestCoach("d", "TheLegend27"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        service.switchToProductionDatabase();
    }

    @Test
    void removeCoachAssignmentZero() {
        SetupTestDatabase();
        try
        {
            assertFalse(cdao.removeCoachAssignment(null));
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        service.switchToProductionDatabase();
    }
    @Test
    void removeCoachAssignmentOne() {
        SetupTestDatabase();
        try
        {
            assertTrue(cdao.removeCoachAssignment("d"));
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        service.switchToProductionDatabase();
    }
    @Test
    void removeCoachAssignmentMany() {
        SetupTestDatabase();
        try
        {
            assertTrue(cdao.removeCoachAssignment("d"));
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        try
        {
            assertTrue(cdao.removeCoachAssignment("d"));
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        try
        {
            assertTrue(cdao.removeCoachAssignment("d"));
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        service.switchToProductionDatabase();
    }
    @Test
    void removeCoachAssignmentBoundary() {
        //wut
    }
    @Test
    void removeCoachAssignmentException() {
        //for trainee who does not have coach. tune this
        SetupTestDatabase();
        try {
            cdao.removeCoachAssignment("d");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        assertFalse(()-> {
            try
            {
                return cdao.removeCoachAssignment("d");
            }
            catch (SQLException e)
            {
                throw new RuntimeException(e);
            }
        });
        service.switchToProductionDatabase();
    }

    @Test
    void acceptRequestZero() {
        SetupTestDatabase();
       assertFalse(()-> {
           try
           {
               return cdao.acceptRequest(null, null);
           }
           catch (SQLException e)
           {
               throw new RuntimeException(e);
           }
       });
        service.switchToProductionDatabase();
    }
    @Test
    void acceptRequestOne() {
        SetupTestDatabase();
        assertTrue(()-> {
            try
            {
                return cdao.acceptRequest("a", "TheLegend28");
            }
            catch (SQLException e)
            {
                throw new RuntimeException(e);
            }
        });
        service.switchToProductionDatabase();
    }
    @Test
    void acceptRequestMany() {
        SetupTestDatabase();
        assertTrue(()-> {
            try
            {
                return cdao.acceptRequest("j", "TheLegend28");
            }
            catch (SQLException e)
            {
                throw new RuntimeException(e);
            }
        });
        assertTrue(()-> {
            try
            {
                return cdao.acceptRequest("e", "TheLegend28");
            }
            catch (SQLException e)
            {
                throw new RuntimeException(e);
            }
        });
        assertTrue(()-> {
            try
            {
                return cdao.acceptRequest("d", "TheLegend28");
            }
            catch (SQLException e)
            {
                throw new RuntimeException(e);
            }
        });
        service.switchToProductionDatabase();
    }
    @Test
    void acceptRequestBoundary() {
        //no boundary, no test
    }
    @Test
    void acceptRequestException() {
        //already tested
    }
    @Test
    void denyRequestZero() {
        SetupTestDatabase();
        assertFalse(()-> {
            try
            {
                return cdao.denyRequest(null);
            }
            catch (SQLException e)
            {
                throw new RuntimeException(e);
            }
        });
        service.switchToProductionDatabase();
    }
    @Test
    void denyRequestOne() {
        SetupTestDatabase();
        assertTrue(()-> {
            try
            {
                return cdao.denyRequest("username0.11755701897640713");
            }
            catch (SQLException e)
            {
                throw new RuntimeException(e);
            }
        });
        service.switchToProductionDatabase();
    }
    @Test
    void denyRequestMany() {
        SetupTestDatabase();
        assertTrue(()-> {
            try
            {
                return cdao.denyRequest("username0.8860545108206384");
            }
            catch (SQLException e)
            {
                throw new RuntimeException(e);
            }
        });
        assertTrue(()-> {
            try
            {
                return cdao.denyRequest("username0.9951104767484433");
            }
            catch (SQLException e)
            {
                throw new RuntimeException(e);
            }
        });
        assertTrue(()-> {
            try
            {
                return cdao.denyRequest("username0.353358245436485");
            }
            catch (SQLException e)
            {
                throw new RuntimeException(e);
            }
        });
        service.switchToProductionDatabase();
    }
    @Test
    void denyRequestBoundary(String traineeUsername) {
        //no boundary, no test
    }
    @Test
    void denyRequestException(String traineeUsername) {
        //already tested
    }
    @Test
    void removeTraineeZero() {
        SetupTestDatabase();
        assertFalse(()-> {
            try
            {
                return cdao.removeTraineeFromRoster(null);
            }
            catch (SQLException e)
            {
                throw new RuntimeException(e);
            }
        });
        service.switchToProductionDatabase();
    }
    @Test
    void removeTraineeOne() {
        SetupTestDatabase();
        assertTrue(()-> {
            try
            {
                return cdao.removeTraineeFromRoster("d");
            }
            catch (SQLException e)
            {
                throw new RuntimeException(e);
            }
        });
        service.switchToProductionDatabase();
    }
    @Test
    void removeTraineeMany()
    {
        SetupTestDatabase();
        assertTrue(() -> {
            try
            {
                return cdao.removeTraineeFromRoster("a");
            }
            catch (SQLException e)
            {
                throw new RuntimeException(e);
            }
        });
        assertTrue(()-> {
            try
            {
                return cdao.removeTraineeFromRoster("b");
            }
            catch (SQLException e)
            {
                throw new RuntimeException(e);
            }
        });

        assertTrue(()-> {
            try
            {
                return cdao.removeTraineeFromRoster("d");
            }
            catch (SQLException e)
            {
                throw new RuntimeException(e);
            }
        });
        service.switchToProductionDatabase();
    }
    @Test
    void removeTraineeBoundary(String traineeUsername) {
        //no boundary, no test
    }
    @Test
    void removeTraineeException(String traineeUsername) {
        //trainee is not associated with coach
    }



//    ArrayList<String> getTraineeList(String username) throws SQLException;
//    ArrayList<String> getTraineeRequest(String username) throws SQLException;

    @Test void getTraineeListZero(){
        SetupTestDatabase();
        assertDoesNotThrow(()->cdao.getTraineeList(null));
        service.switchToProductionDatabase();
    }

    @Test void getTraineeListOne() throws SQLException {
        SetupTestDatabase();
        String username = "name"+Math.random();
        String coach = "coach"+Math.random();
        udao.createTrainee(username,"pds","sda","dasd",12,12);
        cdao.addCoach(coach,"dsad","dasda","dsada",12,12,12,12,12,"dsd",true);
        cdao.requestCoach(username,coach);
        cdao.acceptRequest(username,coach);

        TraineeList list = cdao.getTraineeList(coach);
        ArrayList<String> requests = cdao.getTraineeRequest(coach);

        assertEquals(requests.size(),0);
        assertEquals(list.getTrainee(0),username);
        service.switchToProductionDatabase();
    }
    @Test void getTraineeListMany() throws SQLException {
        SetupTestDatabase();
        String coach = "coach"+Math.random();
        String username = "name"+Math.random();
        String username2 = "name"+Math.random();
        String username3 = "name"+Math.random();

        udao.createTrainee(username,"pds","sda","dasd",12,12);
        cdao.addCoach(coach,"dsad","dasda","dsada",12,12,12,12,12,"dsd",true);
        udao.createTrainee(username2,"pds","sda","dasd",12,12);
        udao.createTrainee(username3,"pds","sda","dasd",12,12);

        cdao.requestCoach(username,coach);
        cdao.acceptRequest(username,coach);
        cdao.requestCoach(username2,coach);
        cdao.acceptRequest(username2,coach);
        cdao.requestCoach(username3,coach);
        cdao.acceptRequest(username3,coach);

        TraineeList list = cdao.getTraineeList(coach);
        ArrayList<String> requests = cdao.getTraineeRequest(coach);

        assertEquals(requests.size(),0);
        assertEquals(list.getTrainee(2),username3);
        service.switchToProductionDatabase();
    }
    @Test void getTraineeListBoundary()  {
        //pass boundary
    }
    @Test void getTraineeListException()   {
        //pass
    }




    @Test void getTraineeRequestZero(){
        SetupTestDatabase();
        assertDoesNotThrow(()->cdao.getTraineeRequest(null));
        service.switchToProductionDatabase();
    }

    @Test void getTraineeRequestOne() throws SQLException {
        SetupTestDatabase();
        String username = "name"+Math.random();
        String coach = "coach"+Math.random();
        udao.createTrainee(username,"pds","sda","dasd",12,12);
        cdao.addCoach(coach,"dsad","dasda","dsada",12,12,12,12,12,"dsd",true);
        cdao.requestCoach(username,coach);

        ArrayList<String> list = cdao.getTraineeRequest(coach);
        assertEquals(list.get(0),username);
        service.switchToProductionDatabase();
    }
    @Test void getTraineeRequestMany() throws SQLException {
        SetupTestDatabase();
        String coach = "coach"+Math.random();
        String username = "name"+Math.random();
        String username2 = "name"+Math.random();
        String username3 = "name"+Math.random();

        udao.createTrainee(username,"pds","sda","dasd",12,12);
        cdao.addCoach(coach,"dsad","dasda","dsada",12,12,12,12,12,"dsd",true);
        udao.createTrainee(username2,"pds","sda","dasd",12,12);
        udao.createTrainee(username3,"pds","sda","dasd",12,12);

        cdao.requestCoach(username,coach);
        cdao.requestCoach(username2,coach);
        cdao.requestCoach(username3,coach);

        ArrayList<String> list = cdao.getTraineeRequest(coach);
        assertEquals(list.get(2),username3);
        service.switchToProductionDatabase();
    }
    @Test void getTraineeRequestBoundary()   {
        //pass boundary
    }
    @Test void getTraineeRequestException()   {
        //pass
    }





}