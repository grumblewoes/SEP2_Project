package modelServer.DAO.interfaces;

import java.sql.SQLException;

/**
 * Interface class used with methods connected only to Coach object from the point of Admin
 *
 * @version Sprint 6 - 29.05.2023
 */
public interface ICoachAdminDAO
{
  boolean addCoach(String coachUsername, String coachPassword, String coachName,
      String coachLName, int coachHeight, int coachWeight, int pbBench,
      int pbSquat, int pbLift, String status) throws SQLException;
  boolean removeCoach(String name) throws SQLException;
}
