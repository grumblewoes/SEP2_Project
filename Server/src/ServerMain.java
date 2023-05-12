import mediator.Server;
import modelServer.AdminTask;
import modelServer.Admin;
import modelServer.Model;
import modelServer.ModelManager;
import util.Logger;

public class ServerMain
{
  public static void main(String[] args)
  {
    Model model = new ModelManager();
    Server server = new Server(model);
    Admin admin = new Admin(model);
    admin.run();

//    server.createFolder("d","nope");
//    server.editFolder("d","nope","nope2");
//    server.removeFolder("d","nope2");
//    Logger.log(server.getFriendRequests("e"));
//    server.acceptFriendRequest("e","d");
  }
}

