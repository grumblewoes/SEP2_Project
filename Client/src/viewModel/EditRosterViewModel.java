package viewModel;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import javafx.scene.control.Button;
import modelClient.Model;

public class EditRosterViewModel
{
  private Button acceptButton;
  private Button denyButton;
  private Button removeButton;

  private StringProperty errorProperty;

  private Model model;
  private ViewState viewState;

  public EditRosterViewModel(Model model, ViewState viewState){
    this.model =model;
    this.viewState = viewState;
    acceptButton = new Button();
    denyButton = new Button();
    removeButton = new Button();
    errorProperty = new SimpleStringProperty();

  }

  public Button getAcceptButton(){
    return acceptButton;
  }

  public Button getDenyButton(){
    return denyButton;
  }

  public Button getRemoveButton(){
    return removeButton;
  }

  public StringProperty getErrorProperty(){
    return errorProperty;
  }
}
