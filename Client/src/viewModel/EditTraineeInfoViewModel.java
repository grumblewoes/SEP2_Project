package viewModel;

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
public class EditTraineeInfoViewModel extends ViewModel
{
  private Model model;
  private ViewState viewState;

  private StringProperty errorProperty, bmiProperty, heightProperty, weightProperty, dobProperty, deadliftProperty, squatProperty;

  /**
   * 2-argument constructor 
   * 
   * 
   * @param model 
   *        
   * @param viewState 
   *        
   */
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

  /**
   * 
   * 
   *
   * @return 
   *        
   */
  public StringProperty getErrorProperty() { return errorProperty;}
  /**
   * 
   * 
   *
   * @return 
   *        
   */
  public StringProperty getBmiProperty() { return bmiProperty;}
  /**
   * 
   * 
   *
   * @return 
   *        
   */
  public StringProperty getHeightProperty() { return heightProperty;}
  /**
   * 
   * 
   *
   * @return 
   *        
   */
  public StringProperty getWeightProperty() { return weightProperty;}
  /**
   * 
   * 
   *
   * @return 
   *        
   */
  public StringProperty getDobProperty() { return dobProperty;}
  /**
   * 
   * 
   *
   * @return 
   *        
   */
  public StringProperty getDeadliftProperty() { return deadliftProperty;}
  /**
   * 
   * 
   *
   * @return 
   *        
   */
  public StringProperty getSquatProperty() { return squatProperty;}

  /**
   * 
   * 
   */
  @Override public void clear()
  {

  }
}
