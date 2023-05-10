package modelServer.DAO.interfaces;

import mediator.User;

public interface IRosterDAO
{
  boolean acceptTrainee();
  boolean denyTrainee();
  User removeTrainee();

  boolean isRosterUpdated();

}
