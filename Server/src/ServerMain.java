import mediator.Server;
import modelServer.Model;
import modelServer.ModelManager;
import util.Logger;

public class ServerMain
{
  public static void main(String[] args)
  {
    Model model = new ModelManager();
    Server server = new Server(model);

//    server.createFolder("d","nope");
//    server.editFolder("d","nope","nope2");
//    server.removeFolder("d","nope2");
//    Logger.log(server.getFolderList("d"));
  }
}
