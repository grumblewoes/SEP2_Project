package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import viewModel.LoginViewModel;
import viewModel.ViewModel;

public class LoginViewController extends ViewController
{
  @FXML private TextField username, password;
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

  @Override public void reset()
  {
    loginViewModel.clear();
  }

  @FXML private void submitButton() {
    loginViewModel.login();
    //if true, go to next screen
  }

  @FXML private void goToSignUpButton() {
    viewHandler.openView("createProfile");
  }
}