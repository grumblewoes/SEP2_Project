package modelServer.DbContext;


/**
 * Class created in order to delegate methods from DBConnection class and reduce access to it
 *
 * @author Jakub Cerovsky
 * @version 1.0
 */
public class DBService
{
  /**
   * method accessing the restartTestDatabase method from DBConnection
   */
  public void restartDatabase(){
    DBConnection.restartTestDatabase();
  }

  /**
   * method accessing the setToTest method from DBConnection
   */
  public void switchToTestDatabase(){
    DBConnection.setToTest();
  }

  /**
   * method accessing the setToProduction method from DBConnection
   */
  public void switchToProductionDatabase(){
    DBConnection.setToProduction();
  }
}
