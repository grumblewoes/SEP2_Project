package viewModel;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import modelClient.Model;

public class LoginViewModel extends ViewModel
{
  private StringProperty usernameProperty;
  private StringProperty passwordProperty;
  private StringProperty errorLabel;
  private Model model;

  public LoginViewModel(Model model) {
    this.model = model;
    usernameProperty = new SimpleStringProperty();
    passwordProperty = new SimpleStringProperty();
    errorLabel = new SimpleStringProperty();
  }

  public boolean login() {
    if (model.login(usernameProperty.get(), passwordProperty.get()))
    {
      clear();
      return true;
    }
    errorLabel.set("An error occurred during login.");
    return false;
  }

  public StringProperty getUsernameProperty() {
    return usernameProperty;
  }
  public StringProperty getPasswordProperty() {
    return passwordProperty;
  }
  public StringProperty getErrorLabel() {
    return errorLabel;
  }
  public void clear(){
    Platform.runLater(() -> {
      errorLabel.set("");
      passwordProperty.set("");
      usernameProperty.set("");
    });
  }

}
