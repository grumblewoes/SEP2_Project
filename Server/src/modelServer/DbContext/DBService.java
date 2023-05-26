package modelServer.DbContext;

import java.io.FileNotFoundException;
import java.sql.SQLException;

/**
 * 
 * 
 * 
 * @author 
 * @version 
 */
public class DBService
{
  /**
   * 
   * 
   */
  public void restartDatabase(){
    DBConnection.restartTestDatabase();
  }

  /**
   * 
   * 
   */
  public void switchToTestDatabase(){
    DBConnection.setToTest();
  }

  /**
   * 
   * 
   */
  public void switchToProductionDatabase(){
    DBConnection.setToProduction();
  }
}
