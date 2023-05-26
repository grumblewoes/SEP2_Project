package viewModel;

import javafx.beans.property.SimpleBooleanProperty;
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
public class AddFriendViewModel extends ViewModel
{
  private StringProperty errorProperty, nameProperty, headerLabel;
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
  public AddFriendViewModel(Model model, ViewState viewState){
    this.model=model;
    this.viewState = viewState;
    nameProperty = new SimpleStringProperty();
    headerLabel = new SimpleStringProperty("Add Friend");
    errorProperty = new SimpleStringProperty();
  }
  /**
   * 
   * 
   *
   * @return 
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
   * 
   * 
   */
  @Override public void clear()
  {
    nameProperty.set("");
    errorProperty.set("");
  }
  /**
   * 
   * 
   *
   * @return 
   *        
   */
  public StringProperty getErrorProperty() {return errorProperty;}
  /**
   * 
   * 
   *
   * @return 
   *        
   */
  public StringProperty getHeaderProperty() {return headerLabel;}
  /**
   * 
   * 
   *
   * @return 
   *        
   */
  public StringProperty getNameProperty() {return nameProperty;}
}
