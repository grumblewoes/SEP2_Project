package modelServer.DbContext;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Singleton class registering the PostgreSQL driver needed for the project
 * Class helps simplify code and stores information needed for switching to test database
 *
 * @author Jakub Cerovsky
 * @version 1.0
 */
public class DBConnection
{

  private static final String user = "dmzntfyy";
  private static final String pw = "L-BXD-GwnqVz8TnCo_VJDuHVQ5Ic2tHL";
  private static final String testUser = "rphlerrs";
  private static final String testPw = "3abeOVjyZgM_kM_B3Z3AKsRO8USY7inc";
  private static boolean isTest = false;

  private static DBConnection instance;
  private static final Object lock = new Object();

  private DBConnection() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  /**
   * Crucial method for Singleton - getInstance
   * synchronized block is entered to ensure that only one thread can enter at a time
   *
   * @return instance of the DBConnection object, whether it was newly created or already existed
   */
  public static DBConnection getInstance() throws SQLException
  {

    if (instance == null)
    {
      synchronized (lock)
      {
        if (instance == null)
        {
          instance = new DBConnection();
        }
      }
    }

    return instance;
  }

  /**
   * Simple setter for switching database to production
   */
  public static void setToProduction()
  {
    isTest = false;
  }

  /**
   * Simple setter for switching database to test
   */
  public static void setToTest()
  {
    isTest = true;
  }

  /**
   * returned connection is dependable whether the requester is looking for a test database or not
   *
   * @throw method will throw a specific question if there was a sql problem or a general one for other cases
   * @return connection, whether it is null or a one initialized from the DriverManager
   */
  public Connection getConnection() throws SQLException
  {
    String url = "";
    if (isTest)
    {
      url = "jdbc:postgresql://balarama.db.elephantsql.com:5432/rphlerrs?currentSchema=valhalla_test";
    }
    else
    {
      url = "jdbc:postgresql://balarama.db.elephantsql.com:5432/dmzntfyy?currentSchema=valhalla_fitness";
    }
    Connection connection = null;
    try
    {
      if (isTest)
      {
        connection = DriverManager.getConnection(url, testUser, testPw);
      }
      else
      {
        connection = DriverManager.getConnection(url, user, pw);
      }

    }
    catch (SQLException e)
    {
      throw new RuntimeException("Failed to establish a database connection",
          e);
    }
    catch (Exception e)
    {
      throw new RuntimeException("Some other DB exception while connecting");
    }
    return connection;
  }

  /**
   * method restarting the test database
   * method will find a sql file that needs to be executed line by line in order to reset the database
   *
   * @throw method will throw a specific question if there was a sql problem or a general one for other cases
   */
  public static void restartTestDatabase()
  {
    String url = "jdbc:postgresql://balarama.db.elephantsql.com:5432/rphlerrs?currentSchema=valhalla_test";
    try
    {
      Connection connection = DriverManager.getConnection(url, testUser,
          testPw);
      String sqlFilePath = "../Server/src/modelServer/DbContext/database/restartTestDatabase.sql";
      File f = new File(sqlFilePath);
      System.out.println(f.getAbsolutePath());

      try (Reader reader = new BufferedReader(
          new FileReader(f.getAbsolutePath()));
          Statement statement = connection.createStatement())
      {
        statement.setEscapeProcessing(false);
        // Read the SQL file line by line and execute each statement
        try (BufferedReader bufferedReader = new BufferedReader(reader))
        {
          String line;
          StringBuilder statementBuilder = new StringBuilder();
          while ((line = bufferedReader.readLine()) != null)
          {
            line = line.trim(); // Remove leading/trailing whitespaces
            if (line.isEmpty() || line.startsWith("--"))
            {
              // Skip empty lines and comments
              continue;
            }
            statementBuilder.append(line);

            // Check if the statement is complete (ends with a semicolon)
            if (line.endsWith(";"))
            {
              String statement2 = statementBuilder.toString();
              statementBuilder.setLength(0); // Reset the StringBuilder

              // Execute the statement
              statement.execute(statement2);
            }
          }
        }

        System.out.println("Script executed successfully.");
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
}