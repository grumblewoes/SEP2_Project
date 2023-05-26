package viewModel;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import modelClient.Model;

/**
 * 
 * 
 * 
 * @author 
 * @version 
 */
public class LoginViewModel extends ViewModel
{
  private StringProperty usernameProperty;
  private StringProperty passwordProperty;
  private StringProperty errorLabel;
  private Model model;
  private ViewState viewState;

  /**
   * 2-argument constructor 
   * 
   * 
   * @param model 
   *        
   * @param viewState 
   *        
   */
  public LoginViewModel(Model model, ViewState viewState) {
    this.model = model;
    usernameProperty = new SimpleStringProperty();
    passwordProperty = new SimpleStringProperty();
    errorLabel = new SimpleStringProperty();
    this.viewState = viewState;
  }

  /**
   * 
   * 
   *
   * @return 
   *        
   */
  public boolean login() {
    if (model.login(usernameProperty.get(), passwordProperty.get()))
    {
      viewState.setUsername(usernameProperty.get());
      viewState.setIsCoach(model.isCoach(usernameProperty.get()));
      return true;
    }else{
      errorLabel.set("An error occurred during login.");
      return false;
    }
  }

  /**
   * 
   * 
   *
   * @return 
   *        
   */
  public StringProperty getUsernameProperty() {
    return usernameProperty;
  }
  /**
   * 
   * 
   *
   * @return 
   *        
   */
  public StringProperty getPasswordProperty() {
    return passwordProperty;
  }
  /**
   * 
   * 
   *
   * @return 
   *        
   */
  public StringProperty getErrorLabel() {
    return errorLabel;
  }
  /**
   * 
   * 
   *
   * @return 
   *        
   */
  public boolean isCoach() {
    return model.isCoach(usernameProperty.get());
  }
  /**
   * 
   * 
   */
  public void clear(){
    Platform.runLater(() -> {
      errorLabel.set("");
      passwordProperty.set("d");
      usernameProperty.set("d");
    });
  }

}
