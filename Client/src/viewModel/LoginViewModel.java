package viewModel;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import modelClient.Model;

/**
 * 
 * ViewModel class for the LoginViewController class
 * 
 * @author Damian Trafialek
 * @version 1.0
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
   * takes the model object and the viewstate object, and initializes the different bound fields
   * in the view controller
   * 
   * @param model for the model layer which communicates with the server
   *        
   * @param viewState to store information when switching screens
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
   * method that sends a request to the server to login with the given credentials.
   * if true, then the user is logged in and the screen switches. if false, then an error
   * is displayed.
   *
   * @return boolean to indicate login success/failure
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
   * getter for the user's username typed in the username field
   *
   * @return StringProperty that represents the user's name
   *        
   */
  public StringProperty getUsernameProperty() {
    return usernameProperty;
  }
  /**
   * 
   * getter for the password string of the current user
   *
   * @return StringProperty that contains the password text
   *        
   */
  public StringProperty getPasswordProperty() {
    return passwordProperty;
  }
  /**
   * 
   * getter for the text in the error label
   *
   * @return StringProperty that contains the error text
   *        
   */
  public StringProperty getErrorLabel() {
    return errorLabel;
  }
  /**
   * 
   * getter for the boolean to check if the current user is a coach
   *
   * @return boolean to indicate if the user is a coach
   *        
   */
  public boolean isCoach() {
    return model.isCoach(usernameProperty.get());
  }
  /**
   * method that refreshes the screen upon controller initialisation and screen switch
   * 
   */
  public void clear(){
    Platform.runLater(() -> {
      errorLabel.set("");
      passwordProperty.set("");
      usernameProperty.set("");
    });
  }

}
