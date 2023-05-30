package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import viewModel.LoginViewModel;
import viewModel.ViewModel;

/**
 * View controller responsible for displaying the login page.
 *
 * @author Damian Trafiałek, Anna Pomerantz
 * @version 1.0
 */
public class LoginViewController extends ViewController
{
  @FXML private TextField username;
  @FXML private PasswordField password;
  @FXML private Label errorLabel;
  private ViewHandler viewHandler;
  private LoginViewModel loginViewModel;


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
    this.viewHandler = viewHandler;
    loginViewModel = (LoginViewModel) viewModel;
    this.root = root;
    username.textProperty().bindBidirectional(loginViewModel.getUsernameProperty());
    password.textProperty().bindBidirectional(loginViewModel.getPasswordProperty());
    errorLabel.textProperty().bind(loginViewModel.getErrorLabel());

  }

  /**
   * Reset method that calls view model to trigger the reset.
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
  }

  @FXML private void goToSignUpButton() {
    viewHandler.openView("createProfile");
  }
}
