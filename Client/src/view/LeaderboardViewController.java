package view;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import viewModel.LeaderboardViewModel;
import viewModel.SimpleLeaderboardViewModel;
import viewModel.ViewModel;


/**
 * View controller responsible for displaying the leaderboards view.
 *
 * @author Jakub Cerovsky
 * @version 1.0
 */
public class LeaderboardViewController extends ViewController
{
  @FXML
  private TableView<SimpleLeaderboardViewModel> squatTable;

  @FXML
  private TableView<SimpleLeaderboardViewModel> deadliftTable;

  @FXML
  private TableView<SimpleLeaderboardViewModel> benchTable;

  @FXML
  private TableColumn<SimpleLeaderboardViewModel, String> squatColumn;
  @FXML
  private TableColumn<SimpleLeaderboardViewModel, String> deadliftColumn;
  @FXML
  private TableColumn<SimpleLeaderboardViewModel, String> benchColumn;

  private LeaderboardViewModel leaderboardViewModel;
  private Stage stage = null;

  @Override
  /**
   * Method that initialise the controller and sets up all instance variables and bindings.
   *
   * @param viewHandler - handles changing views
   *
   * @param viewModel - view model related to the controller
   *
   * @param root - region that is being displayed
   *
   */
  public void init(ViewHandler viewHandler, ViewModel viewModel,
      Region root)
  {
    this.viewHandler=viewHandler;
    this.root=root;
    leaderboardViewModel = (LeaderboardViewModel) viewModel;


    deadliftColumn.setCellValueFactory(data -> {
      String deadliftValue = data.getValue().weightPropertyProperty().get() + "kg | " + data.getValue().usernamePropertyProperty().get();
      return new SimpleStringProperty(deadliftValue != null ? deadliftValue : "N/A");
    });
    benchColumn.setCellValueFactory(data -> {
      String benchValue = data.getValue().weightPropertyProperty().get() + "kg | " + data.getValue().usernamePropertyProperty().get();
      return new SimpleStringProperty(benchValue != null ? benchValue : "N/A");
    });
    squatColumn.setCellValueFactory(data -> {
      String squatValue = data.getValue().weightPropertyProperty().get() + "kg | " + data.getValue().usernamePropertyProperty().get();
      return new SimpleStringProperty(squatValue != null ? squatValue : "N/A");
    });
    squatTable.setItems(((LeaderboardViewModel) viewModel).getSquats());
    deadliftTable.setItems(((LeaderboardViewModel) viewModel).getDeadlifts());
    benchTable.setItems(((LeaderboardViewModel) viewModel).getBenches());
    reset();
  }

  /**
   * Reset method that calls view model to trigger the reset.
   *
   */
  @Override public void reset()
  {
    leaderboardViewModel.clear();
  }

  /**
   * Method that sets the stage of the view.
   * 
   * @param stage - view's stage
   *        
   */
  public void setStage(Stage stage) {
    this.stage = stage;
  }

  @FXML private void closeWindow()
  {
    if(stage!=null) {
      stage.close();
    }
  }
}
