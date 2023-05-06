package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import viewModel.FolderViewModel;
import viewModel.HomeViewModel;
import viewModel.ViewModel;

import java.awt.event.ActionEvent;

public class  FolderViewController extends ViewController
{

  @FXML private Label folderName;
  @FXML private VBox exerciseBox;

  @FXML private Label errorLabel;
  private ViewHandler viewHandler;
  private FolderViewModel folderViewModel;

  @Override public void init(ViewHandler viewHandler, ViewModel viewModel,
      Region root)
  {
    this.viewHandler = viewHandler;
    folderViewModel = (FolderViewModel) viewModel;
    this.root = root;

    errorLabel.textProperty().bind(folderViewModel.getErrorProperty());

  }

  @Override public void reset()
  {
    folderViewModel.clear();
  }

  @FXML private void addExercise()
  {
    viewHandler.openView("addExercise");
  }

  @FXML boolean removeExercise(ActionEvent evt, String name)
  {
    String buttonLabel = ((Button) evt.getSource()).getText();
    if (buttonLabel.equals("Remove"))
    {
      folderViewModel.removeExercise(name);
    }
    return false;
  }
  private void populateWorkout(String exerciseListString) {
    folderViewModel.
  }
}
