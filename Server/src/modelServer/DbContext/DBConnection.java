package modelServer.DbContext;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

  private static final String user = "dmzntfyy";
  private static final String pw = "L-BXD-GwnqVz8TnCo_VJDuHVQ5Ic2tHL";
  private static DBConnection instance;
  private static final Object lock = new Object();

  private DBConnection() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  public static DBConnection getInstance() throws SQLException {

    if (instance == null) {
      synchronized(lock) {
        if (instance == null) {
          instance = new DBConnection();
        }
      }
    }

    return instance;
  }

  public Connection getConnection() throws SQLException {
    String url = "";
    url="jdbc:postgresql://balarama.db.elephantsql.com:5432/dmzntfyy?currentSchema=valhalla_fitness";
    Connection connection = null;
    connection = DriverManager.getConnection(url, user, pw);

    return connection;
  }
}