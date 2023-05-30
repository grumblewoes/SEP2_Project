import modelServer.DAO.implementation.MeetingDAO;
import modelServer.DbContext.DBService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class DAOGreyboxMeetingTest {
  private MeetingDAO meetingDAO;
  private DBService service;

  @BeforeEach
  void setUp() {
    meetingDAO = new MeetingDAO();
    service=new DBService();
    SetupTestDatabase();
  }

  @org.junit.jupiter.api.AfterEach
  void tearDown() {
    service.switchToProductionDatabase();
  }

  private void SetupTestDatabase(){
    service.restartDatabase();
    service.switchToTestDatabase();
  }

  @Test
  void testGetTraineeMeetingList() throws SQLException {
    String traineeUsername = "d";
    ArrayList<String> meetingList = meetingDAO.getTraineeMeetingList(traineeUsername);
    assertNotNull(meetingList);

  }

  @Test
  void testGetTraineeMeetingRequests() throws SQLException {
    String traineeUsername = "d";
    ArrayList<String> meetingRequests = meetingDAO.getTraineeMeetingRequests(traineeUsername);
    assertNotNull(meetingRequests);

  }

  @Test
  void testSendMeetingRequest() throws SQLException {
    String traineeUsername = "d";
    String coachUsername = "coach";
    LocalDate dateOfMeeting = LocalDate.now();
    boolean result = meetingDAO.sendMeetingRequest(traineeUsername, coachUsername, dateOfMeeting);
    assertTrue(result);

  }

  @Test
  void testRemoveMeeting() throws SQLException {
    String traineeUsername = "d";
    String coachUsername = "coach";
    LocalDate dateOfMeeting = LocalDate.now();
    boolean result = meetingDAO.removeMeeting(traineeUsername, coachUsername, dateOfMeeting);
    assertTrue(result);

  }

  @Test
  void testApproveMeeting() throws SQLException {
    String traineeUsername = "d";
    String coachUsername = "coach";
    LocalDate dateOfMeeting = LocalDate.now();
    boolean result = meetingDAO.approveMeeting(traineeUsername, coachUsername, dateOfMeeting);
    assertTrue(result);

  }

  @Test
  void testDenyMeeting() throws SQLException {
    String traineeUsername = "d";
    String coachUsername = "coach";
    LocalDate dateOfMeeting = LocalDate.now();
    boolean result = meetingDAO.denyMeeting(traineeUsername, coachUsername, dateOfMeeting);
    assertTrue(result);

  }

  @Test
  void testGetCoachMeetingRequests() throws SQLException {
    String coachUsername = "coach";
    ArrayList<String> meetingRequests = meetingDAO.getCoachMeetingRequests(coachUsername);
    assertNotNull(meetingRequests);

  }

  @Test
  void testGetCoachMeetings() throws SQLException {
    String coachUsername = "coach";
    ArrayList<String> coachMeetings = meetingDAO.getCoachMeetings(coachUsername);
    assertNotNull(coachMeetings);

  }

  @Test
  void testGetTakenDates() throws SQLException {
    String coachUsername = "coach";
    ArrayList<LocalDate> takenDates = meetingDAO.getTakenDates(coachUsername);
    assertNotNull(takenDates);

  }
}
