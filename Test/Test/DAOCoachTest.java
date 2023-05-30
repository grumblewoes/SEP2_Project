import mediator.Trainee;
import mediator.TraineeList;
import modelServer.DAO.implementation.CoachAdminDAO;
import modelServer.DAO.implementation.CoachDAO;
import modelServer.DAO.implementation.TraineeDAO;
import modelServer.DAO.implementation.UserDAO;
import modelServer.DAO.interfaces.ICoachAdminDAO;
import modelServer.DAO.interfaces.ICoachDAO;
import modelServer.DAO.interfaces.ITraineeDAO;
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
    private ICoachAdminDAO caDao;
    private ITraineeDAO udao;

    @BeforeEach
    void setUp() {
        service=new DBService();
        cdao = new CoachDAO();
        caDao=new CoachAdminDAO();
        udao = new TraineeDAO();
    }

    private void SetupTestDatabase(){
        service.restartDatabase();
        service.switchToTestDatabase();
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
            caDao.addCoach("TheLegend27", "d", "d", "d", 1, 1, 1, 1, 1, "g");
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
            caDao.addCoach("TheLegend27", "d", "d", "d", 1, 1, 1, 1, 1, "g");
            caDao.addCoach("TheLegend28", "d", "d", "d", 1, 1, 1, 1, 1, "g");
            caDao.addCoach("TheLegend29", "d", "d", "d", 1, 1, 1, 1, 1, "g");
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
            caDao.addCoach("TheLegend27", "d", "d", "d", 1, 1, 1, 1, 1, "g");
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
            caDao.addCoach("TheLegend28", "d", "d", "d", 1, 1, 1, 1, 1, "g");
            caDao.addCoach("TheLegend29", "d", "d", "d", 1, 1, 1, 1, 1, "g");
            caDao.addCoach("TheLegend30", "d", "d", "d", 1, 1, 1, 1, 1, "g");
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
        caDao.addCoach(coach,"dsad","dasda","dsada",12,12,12,12,12,"dsd");
        cdao.requestCoach(username,coach);
        cdao.acceptRequest(username,coach);

        TraineeList list = cdao.getTraineeList(coach);
        ArrayList<String> requests = cdao.getTraineeRequest(coach);

        assertEquals(requests.size(),0);
        assertEquals(list.getTrainee(0).getUsername(),username);
        service.switchToProductionDatabase();
    }
    @Test void getTraineeListMany() throws SQLException {
        SetupTestDatabase();
        String coach = "coach"+Math.random();
        String username = "name"+Math.random();
        String username2 = "name"+Math.random();
        String username3 = "name"+Math.random();

        udao.createTrainee(username,"pds","sda","dasd",12,12);
        caDao.addCoach(coach,"dsad","dasda","dsada",12,12,12,12,12,"dsd");
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
        assertEquals(list.getTrainee(2).getUsername(),username3);
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
        caDao.addCoach(coach,"dsad","dasda","dsada",12,12,12,12,12,"dsd");
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
        caDao.addCoach(coach,"dsad","dasda","dsada",12,12,12,12,12,"dsd");
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