import mediator.Server;
import modelServer.Admin;
import modelServer.Model;
import modelServer.ModelManager;

/**
 * 
 * 
 * 
 * @author 
 * @version 
 */
public class ServerMain
{
  /**
   * 
   * 
   */
  public static void main(String[] args) {
    Model model = new ModelManager();
    Server server = new Server(model);


    Admin admin = new Admin(model);
    Thread adminThread = new Thread(admin);
    adminThread.start();

//    server.createFolder("d","nope");
//    server.editFolder("d","nope","nope2");
//    server.removeFolder("d","nope2");
//    Logger.log(server.getFriendRequests("e"));
//    server.acceptFriendRequest("e","d");

  }
}
