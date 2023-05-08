package modelServer.DAO.interfaces;

import mediator.User;

import java.sql.SQLException;

public interface ICoachDAO
{

  public boolean addCoach(String coachUsername, String coachPassword, String coachName, String coachLName, double coachHeight, double coachWeight,
      int pbBench, int pbSquat, int pbLift, boolean share) throws SQLException;

  public boolean removeCoach(String name) throws SQLException;

  public User getCoach(String traineeUser) throws SQLException;
}
