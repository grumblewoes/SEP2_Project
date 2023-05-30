package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import viewModel.HomeViewModel;
import viewModel.ManageFolderViewModel;
import viewModel.ViewModel;

/**
 * View controller responsible for displaying the add, rename folder view.
 *
 * @author Damian Trafiałek
 * @version 1.0
 */
public class ManageFolderViewController extends ViewController
{

  @FXML private TextField nameField;
  @FXML private Label headerLabel;
  @FXML private Label errorLabel;
  private ManageFolderViewModel folderViewModel;


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
  @Override public void  init(ViewHandler viewHandler, ViewModel viewModel, Region root)
  {
    this.viewHandler = viewHandler;
    this.folderViewModel = (ManageFolderViewModel) viewModel;
    this.root= root;

    errorLabel.textProperty().bind( folderViewModel.getErrorProperty() );
    headerLabel.textProperty().bind(folderViewModel.getHeaderProperty());
    nameField.textProperty().bindBidirectional(folderViewModel.getNameProperty() );
  }


  @FXML private void submit(){
    boolean ans = folderViewModel.submit();
    if(ans)
      viewHandler.openView("home");
  }
  @FXML private void cancel(){
    viewHandler.openView("home");
  }

  /**
   * Reset method that calls view model to trigger the reset.
   *
   */
  @Override public void reset()
  {
    folderViewModel.clear();
  }

}
