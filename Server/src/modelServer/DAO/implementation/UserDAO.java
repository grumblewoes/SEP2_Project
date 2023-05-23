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
      PreparedStatement statementCoach = connection.prepareStatement("select username, password"
          + " from coach where username = '"+username+"';");

      ResultSet rs = statement.executeQuery();
      ResultSet rs1 = statementCoach.executeQuery();

      if( rs.next() ){
        return password.equals(rs.getString(2));
      }
      else if (rs1.next()) {
        return password.equals(rs1.getString(2));
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
          "select height,weight,first_name,last_name,username,share_profile,status, deadlift_best, squat_best, bench_best " +
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
        boolean shareProfile = rs.getBoolean(6);
        String status = rs.getString(7);
        int deadlift = rs.getInt(8);
        int squat = rs.getInt(9);
        int bench = rs.getInt(10);
        user= new User(height,weight,firstName,lastName,username,status,shareProfile,deadlift,squat,bench);
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
  public boolean updateTrainee(String u, int h, int w,boolean s,String st) throws SQLException {
    DBConnection db = DBConnection.getInstance();
    Connection connection = db.getConnection();
    if( (st!=null && st.length()>10) || h==0 || w==0){
      Logger.log("Unable to update trainee");
      return false;
    }
    try
    {

      PreparedStatement statement = connection.prepareStatement(
      "update trainee2 " +
              "set height = "+ h +" " +
              ", weight = "+ w +" " +
              ", share_profile = "+ s +" " +
              ", status = '"+ st +"' " +
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
