package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import viewModel.LoginViewModel;
import viewModel.ViewModel;

/**
 * 
 * 
 * 
 * @author 
 * @version 
 */
public class LoginViewController extends ViewController
{
  @FXML private TextField username;
  @FXML private PasswordField password;
  @FXML private Label errorLabel;
  private ViewHandler viewHandler;
  private LoginViewModel loginViewModel;


  @Override public void init(ViewHandler viewHandler, ViewModel viewModel,
      Region root)
  {
    this.viewHandler = viewHandler;
    loginViewModel = (LoginViewModel) viewModel;
    this.root = root;
    username.textProperty().bindBidirectional(loginViewModel.getUsernameProperty());
    password.textProperty().bindBidirectional(loginViewModel.getPasswordProperty());
    errorLabel.textProperty().bind(loginViewModel.getErrorLabel());

  }

  /**
   * 
   * 
   */
  @Override public void reset()
  {
    loginViewModel.clear();
  }

  @FXML private void submitButton() {
    if( loginViewModel.login() ){
      if (!loginViewModel.isCoach())
        viewHandler.openView("home");
      else
        viewHandler.openView("manageRoster");
    }

    //if true, go to next screen
  }

  @FXML private void goToSignUpButton() {
    viewHandler.openView("createProfile");
  }
}
