package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import viewModel.AddFriendViewModel;
import viewModel.HomeViewModel;
import viewModel.ManageFolderViewModel;
import viewModel.ViewModel;

public class AddFriendViewController extends ViewController
{
  @FXML private TextField nameField;
  @FXML private Label headerLabel;
  @FXML private Label errorLabel;
  private AddFriendViewModel friendViewModel;


  @Override public void  init(ViewHandler viewHandler, ViewModel viewModel, Region root)
  {
    this.viewHandler = viewHandler;
    this.friendViewModel = (AddFriendViewModel) viewModel;
    this.root= root;

    errorLabel.textProperty().bind( friendViewModel.getErrorProperty() );
    headerLabel.textProperty().bind(friendViewModel.getHeaderProperty());
    nameField.textProperty().bindBidirectional(friendViewModel.getNameProperty() );
  }


  @FXML private void submit(){
    boolean ans = friendViewModel.sendRequest();
    if(ans)
      viewHandler.openView("home");
  }
  @FXML private void cancel(){
    viewHandler.openView("home");
  }

  @Override public void reset()
  {
    friendViewModel.clear();
  }
}
