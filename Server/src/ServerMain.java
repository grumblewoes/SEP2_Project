import mediator.Server;
import modelServer.Model;
import modelServer.ModelManager;

public class ServerMain
{
  public static void main(String[] args)
  {
    Model model = new ModelManager();
    Server server = new Server(model);
  }
}
