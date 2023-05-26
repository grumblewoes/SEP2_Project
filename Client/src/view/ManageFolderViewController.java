package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import viewModel.HomeViewModel;
import viewModel.ManageFolderViewModel;
import viewModel.ViewModel;

/**
 * 
 * 
 * 
 * @author 
 * @version 
 */
public class ManageFolderViewController extends ViewController
{

  @FXML private TextField nameField;
  @FXML private Label headerLabel;
  @FXML private Label errorLabel;
  private ManageFolderViewModel folderViewModel;


  /**
   * 
   * 
   * @param viewHandler 
   *        
   * @param viewModel 
   *        
   * @param root 
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
   * 
   * 
   */
  @Override public void reset()
  {
    folderViewModel.clear();
  }
}
