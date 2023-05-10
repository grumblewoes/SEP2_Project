//import mediator.Server;
//import modelServer.AdminTask;
//import modelServer.Model;
//import modelServer.ModelManager;
//import util.Logger;
//
//public class ServerMain
//{
//  public static void main(String[] args)
//  {
//    Model model = new ModelManager();
//    Server server = new Server(model);
//
//    AdminTask task = new AdminTask(model);
//    Thread t1 = new Thread(task);
//    t1.setDaemon(true);
//    t1.start();
//
////    server.createFolder("d","nope");
////    server.editFolder("d","nope","nope2");
////    server.removeFolder("d","nope2");
////    Logger.log(server.getFriendRequests("e"));
////    server.acceptFriendRequest("e","d");
//  }
//}
