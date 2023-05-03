package viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import modelClient.Model;

public class EditTraineeInfoViewModel extends ViewModel
{
  private Model model;
  private ViewState viewState;

  private StringProperty errorProperty, bmiProperty, heightProperty, weightProperty, dobProperty, deadliftProperty, squatProperty;

  public EditTraineeInfoViewModel(Model model, ViewState viewState) {
    this.model = model;
    this.viewState = viewState;
    errorProperty = new SimpleStringProperty();
    bmiProperty = new SimpleStringProperty();
    heightProperty = new SimpleStringProperty();
    weightProperty = new SimpleStringProperty();
    dobProperty = new SimpleStringProperty();
    deadliftProperty = new SimpleStringProperty();
    squatProperty = new SimpleStringProperty();
  }

  public StringProperty getErrorProperty() { return errorProperty;}
  public StringProperty getBmiProperty() { return bmiProperty;}
  public StringProperty getHeightProperty() { return heightProperty;}
  public StringProperty getWeightProperty() { return weightProperty;}
  public StringProperty getDobProperty() { return dobProperty;}
  public StringProperty getDeadliftProperty() { return deadliftProperty;}
  public StringProperty getSquatProperty() { return squatProperty;}

  @Override public void clear()
  {

  }
}
