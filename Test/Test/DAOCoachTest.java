import modelServer.DAO.implementation.CoachDAO;
import modelServer.DAO.implementation.UserDAO;
import modelServer.DAO.interfaces.ICoachDAO;
import modelServer.DAO.interfaces.IUserDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DAOCoachTest
{
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
        assertDoesNotThrow(()-> cdao.getCoach("d"));
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
            assertFalse(cdao.requestCoach(null, null));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void requestCoachOne() {
        try { //change this
            assertTrue(cdao.requestCoach("d", "TheLegend27"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void requestCoachMany() {
        try { //change these plz
            assertTrue(cdao.requestCoach("a", "TheLegend28"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            assertTrue(cdao.requestCoach("j", "TheLegend28"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            assertTrue(cdao.requestCoach("e", "TheLegend28"));
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
            assertFalse(cdao.requestCoach("d", "TheLegend27"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void removeCoachAssignmentZero() {
        try
        {
            assertFalse(cdao.removeCoachAssignment(null));
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
    @Test
    void removeCoachAssignmentOne() {
        try
        {
            assertTrue(cdao.removeCoachAssignment("d"));
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
    @Test
    void removeCoachAssignmentMany() {
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
    }
    @Test
    void removeCoachAssignmentBoundary() {
        //wut
    }
    @Test
    void removeCoachAssignmentException() {
        //for trainee who does not have coach. tune this
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
    }

    @Test
    void acceptRequestZero() {
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
    }
    @Test
    void acceptRequestOne() {
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
    }
    @Test
    void acceptRequestMany() {

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
    }
    @Test
    void denyRequestOne() {

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
    }
    @Test
    void denyRequestMany() {

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
    }
    @Test
    void removeTraineeOne() {

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
    }
    @Test
    void removeTraineeMany()
    {

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
        assertDoesNotThrow(()->cdao.getTraineeList(null));
    }

    @Test void getTraineeListOne() throws SQLException {
        String username = "name"+Math.random();
        String coach = "coach"+Math.random();
        udao.createTrainee(username,"pds","sda","dasd",12,12);
        cdao.addCoach(coach,"dsad","dasda","dsada",12,12,12,12,12,"dsd",true);
        cdao.requestCoach(username,coach);
        cdao.acceptRequest(username,coach);

        ArrayList<String> list = cdao.getTraineeList(coach);
        ArrayList<String> requests = cdao.getTraineeRequest(coach);

        assertEquals(requests.size(),0);
        assertEquals(list.get(0),username);
    }
    @Test void getTraineeListMany() throws SQLException {
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

        ArrayList<String> list = cdao.getTraineeList(coach);
        ArrayList<String> requests = cdao.getTraineeRequest(coach);

        assertEquals(requests.size(),0);
        assertEquals(list.get(2),username3);
    }
    @Test void getTraineeListBoundary()  {
        //pass boundary
    }
    @Test void getTraineeListException()   {
        //pass
    }




    @Test void getTraineeRequestZero(){
        assertDoesNotThrow(()->cdao.getTraineeRequest(null));
    }

    @Test void getTraineeRequestOne() throws SQLException {
        String username = "name"+Math.random();
        String coach = "coach"+Math.random();
        udao.createTrainee(username,"pds","sda","dasd",12,12);
        cdao.addCoach(coach,"dsad","dasda","dsada",12,12,12,12,12,"dsd",true);
        cdao.requestCoach(username,coach);

        ArrayList<String> list = cdao.getTraineeRequest(coach);
        assertEquals(list.get(0),username);
    }
    @Test void getTraineeRequestMany() throws SQLException {
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
    }
    @Test void getTraineeRequestBoundary()   {
        //pass boundary
    }
    @Test void getTraineeRequestException()   {
        //pass
    }





}