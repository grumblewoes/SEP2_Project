package viewModel;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import modelClient.Model;

/**
 * The viewmodel class for the addFriendViewController.
 * 
 * @author Jakub Cerovsky
 * @version 1.0
 */
public class AddFriendViewModel extends ViewModel
{
  private StringProperty errorProperty, nameProperty, headerLabel;
  private Model model;
  private ViewState viewState;

  /**
   * 2-argument constructor 
   * initializes the StringProperties for each of the fields in the view controller
   * 
   * @param model for the model layer
   *        
   * @param viewState to store information when switching screens
   *        
   */
  public AddFriendViewModel(Model model, ViewState viewState){
    this.model=model;
    this.viewState = viewState;
    nameProperty = new SimpleStringProperty();
    headerLabel = new SimpleStringProperty("Add Friend");
    errorProperty = new SimpleStringProperty();
  }
  /**
   * 
   * sends a friend request to the specified person, delegates to model
   *
   * @return boolean for success/failure
   *        
   */
  public boolean sendRequest(){
    String username = viewState.getUsername();
    String friendUsername= nameProperty.get();

    boolean successAddition = model.sendFriendRequest(username,friendUsername);
    if(!successAddition)
      errorProperty.set("Failed to send the request! Please check if the username is correct.");

    return successAddition;
  }
  /**
   * refreshes the screen upon initial load and when the screen is accessed by the user
   * 
   */
  @Override public void clear()
  {
    nameProperty.set("");
    errorProperty.set("");
  }
  /**
   * 
   * getter for the errorLabel property
   *
   * @return StringProperty for the label text
   *        
   */
  public StringProperty getErrorProperty() {return errorProperty;}
  /**
   * 
   * getter for the header property
   *
   * @return StringProperty for the header text
   *        
   */
  public StringProperty getHeaderProperty() {return headerLabel;}
  /**
   * 
   * getter for the name property
   *
   * @return StringProperty for the name field
   *        
   */
  public StringProperty getNameProperty() {return nameProperty;}
}
