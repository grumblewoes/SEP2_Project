package modelServer.DAO.implementation;

import mediator.User;
import modelServer.DAO.interfaces.IUserDAO;
import modelServer.DbContext.DBConnection;
import modelServer.Trainee;

import java.sql.*;

public class UserDAO implements IUserDAO
{
  private static UserDAO instance;

  public UserDAO()
  {
  }

  @Override public boolean createTrainee(String username, String password,
      String firstName, String lastName, int height, int weight)
      throws SQLException
  {
    DBConnection db = DBConnection.getInstance();
    Connection connection = db.getConnection();
    try
    {
      PreparedStatement statement = connection.prepareStatement(
          "insert into Trainee(username, password, first_name, last_name, weight, height) values (?,?,?,?,?,?);");
      statement.setString(1, username);
      statement.setString(2, password);
      statement.setString(3, firstName);
      statement.setString(4, lastName);
      statement.setInt(5, height);
      statement.setInt(6, weight);

      statement.executeUpdate();
      return true;
    }
    catch (SQLException e)
    {
      return false;
    }
    finally
    {
      connection.close();
    }
  }

  @Override public boolean createFolder(String username, String name)
      throws SQLException
  {
    DBConnection db = DBConnection.getInstance();
    Connection connection = db.getConnection();

    try
    {
      PreparedStatement statement = connection.prepareStatement(
          "INSERT INTO WorkoutFloder(title, trainee_username) VALUES(?,?);");
      statement.setString(1, name);
      statement.setString(2, username);

      statement.executeUpdate();
      return true;
    }
    catch (SQLException e)
    {
      return false;
    }
    finally
    {
      connection.close();
    }
  }

  @Override public boolean removeFolder(String username, String name)
      throws SQLException
  {
    DBConnection db = DBConnection.getInstance();
    Connection connection = db.getConnection();

    try
    {
      PreparedStatement statement = connection.prepareStatement(
          "DELETE FROM workoutfolder WHERE username = ? AND name= ?");
      statement.setString(1, username);
      statement.setString(2, name);

      statement.executeUpdate();
      return true;
    }
    catch (SQLException e)
    {
      return false;
    }
    finally
    {
      connection.close();
    }
  }

  @Override public boolean editFolder(String username, String oldName, String newName) throws SQLException
  {
    DBConnection db = DBConnection.getInstance();
    Connection connection = db.getConnection();

    try
    {
      PreparedStatement statement = connection.prepareStatement(
          "UPDATE workoutfolder SET name = ? WHERE name =? AND username=?");
      statement.setString(1, newName);
      statement.setString(2, oldName);
      statement.setString(3, username);

      statement.executeUpdate();
      return true;
    }
    catch (SQLException e)
    {
      return false;
    }
    finally
    {
      connection.close();
    }
  }


  @Override public mediator.User getTrainee(String username) throws SQLException{
    DBConnection db = DBConnection.getInstance();
    Connection connection = db.getConnection();
    mediator.User user = null;

    try
    {

      PreparedStatement statement = connection.prepareStatement(
              "select height,weight,first_name,last_name,username,share_profile " +
                      "from trainee2 " +
                      "where username = '"+username+"';"
      );

      ResultSet rs = statement.executeQuery();
      if(rs.next()){
//        int height, int weight, String firstName, String lastName, String username, String gender
        int height = rs.getInt(1);
        int weight = rs.getInt(2);
        String firstName = rs.getString(3);
        String lastName = rs.getString(4);
        String gender = "idk";
        boolean shareProfile = rs.getBoolean(6);
        user= new User(height,weight,firstName,lastName,username,gender,shareProfile);
      }


      return user;
    }
    catch (SQLException e){
//      Logger.log(e);
      return null;
    }
    finally
    {
      connection.close();
    }
  }

  @Override
  public boolean updateTrainee(String u, int h, int w,boolean s) throws SQLException {
    DBConnection db = DBConnection.getInstance();
    Connection connection = db.getConnection();

    try
    {

      PreparedStatement statement = connection.prepareStatement(
              "update trainee2 " +
                      "set height = "+ h +" " +
                      ", weight = "+ w +" " +
                      ", share_profile = "+ s +" " +
                      "where username = '"+u+"' ;"
      );

      statement.executeUpdate();


      return true;
    }
    catch (SQLException e){
//      Logger.log(e);
      return false;
    }
    finally
    {
      connection.close();
    }
  }
}