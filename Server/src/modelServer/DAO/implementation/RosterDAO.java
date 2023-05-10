package modelServer.DAO.implementation;

import mediator.User;
import modelServer.DAO.interfaces.IRosterDAO;
import modelServer.DbContext.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RosterDAO implements IRosterDAO
{

  private static RosterDAO instance;

  public RosterDAO(){}

  @Override public boolean acceptTrainee()
  {
    try{
    DBConnection db = DBConnection.getInstance();
    Connection connection = db.getConnection();

      PreparedStatement statement = connection.prepareStatement("insert into table  ")
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }

    return false;
  }

  @Override public boolean denyTrainee()
  {

    return false;
  }

  @Override public User removeTrainee()
  {
    return null;
  }

  @Override public boolean isRosterUpdated()
  {
    return false;
  }

}
