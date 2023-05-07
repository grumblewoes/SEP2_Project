package modelServer.DAO.implementation;

import mediator.User;
import modelServer.DAO.interfaces.IUserDAO;
import modelServer.DbContext.DBConnection;
import util.Logger;

import java.sql.*;

public class UserDAO implements IUserDAO
{
  private static UserDAO instance;

  public UserDAO(){}

  @Override
  public boolean createTrainee(String username, String password, String firstName, String lastName, int height, int weight) throws SQLException {
    DBConnection db = DBConnection.getInstance();
    Connection connection = db.getConnection();
    try
    {
      PreparedStatement statement = connection.prepareStatement("insert into trainee2(username, password, first_name, last_name, weight, height,share_profile) values (?,?,?,?,?,?,?);");
      statement.setString(1, username);
      statement.setString(2, password);
      statement.setString(3, firstName);
      statement.setString(4, lastName);
      statement.setInt(5, height);
      statement.setInt(6, weight);
      statement.setBoolean(7, true);

      statement.executeUpdate();
      return true;
    }
    catch (SQLException e){
      Logger.log(e);
      return false;
    }
    finally
    {
      connection.close();
    }
  }

  @Override
  public boolean login(String username, String password) throws SQLException {
    DBConnection db = DBConnection.getInstance();
    Connection connection = db.getConnection();

    try
    {

      PreparedStatement statement = connection.prepareStatement(
        "select username, password " +
                "from trainee2 " +
                "where username = '"+username+"';"
      );

      ResultSet rs = statement.executeQuery();

      if( rs.next() ){
        if(password.equals(rs.getString(2)))
          return true;
      }

      return false;
    }
    catch (SQLException e){
      Logger.log(e);
      return false;
    }
    finally
    {
      connection.close();
    }
  }

  @Override public User getTrainee(String username) throws SQLException{
    DBConnection db = DBConnection.getInstance();
    Connection connection = db.getConnection();
    User user = null;

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
      Logger.log(e);
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
      Logger.log(e);
      return false;
    }
    finally
    {
      connection.close();
    }
  }

  public boolean deleteTrainee(String username)throws SQLException{
    DBConnection db = DBConnection.getInstance();
    Connection connection = db.getConnection();

    try
    {

      PreparedStatement statement = connection.prepareStatement(
              "delete from trainee2 where username = ?"
      );
      statement.setString(1,username);
      statement.executeUpdate();

      return false;
    }
    catch (SQLException e){
      return false;
    }
    finally
    {
      connection.close();
    }
  }
}
