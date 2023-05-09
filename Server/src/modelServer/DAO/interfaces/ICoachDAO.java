package modelServer.DAO.interfaces;

import mediator.User;

import java.sql.SQLException;

public interface ICoachDAO
{

  public boolean addCoach(String coachUsername, String coachPassword, String coachName, String coachLName, int coachHeight, int coachWeight,
      int pbBench, int pbSquat, int pbLift, String status, boolean share) throws SQLException;

  public boolean removeCoach(String name) throws SQLException;

  public User getCoach(String traineeUser) throws SQLException;
}
