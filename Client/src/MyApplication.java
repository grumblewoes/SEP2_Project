import javafx.application.Application;
import javafx.stage.Stage;
import mediator.Client;
import modelClient.Model;
import modelClient.ModelManager;
import view.ViewHandler;
import viewModel.ViewModelFactory;

public class MyApplication extends Application
{
  Client client;

  public void start(Stage primaryStage)
  {
    client = new Client();
    Model model = new ModelManager(client);
    ViewModelFactory viewModelFactory = new ViewModelFactory(model);
    ViewHandler view = new ViewHandler(viewModelFactory);

    view.start(primaryStage);
  }
}
