package viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import modelClient.Model;

/**
 * 
 * ViewModel for the EditTraineeInfoViewController class.
 * 
 * @author Damian Trafialek
 * @version 1.0
 */
public class EditTraineeInfoViewModel extends ViewModel
{
  private Model model;
  private ViewState viewState;

  private StringProperty errorProperty, bmiProperty, heightProperty, weightProperty, dobProperty, deadliftProperty, squatProperty;

  /**
   * 2-argument constructor 
   * accepts a model and viewstate object as arguments, and initialises the rest of the bound properties
   * 
   * @param model for the model layer of MVVM, which communicates with the server
   *        
   * @param viewState to store information when switching screens
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
   * getter for the error label text
   *
   * @return StringProperty that contains the text
   *        
   */
  public StringProperty getErrorProperty() { return errorProperty;}
  /**
   * 
   * getter for the BMI property
   *
   * @return StringProperty that contains the BMI text
   *        
   */
  public StringProperty getBmiProperty() { return bmiProperty;}
  /**
   * 
   * getter for the height property
   *
   * @return StringProperty that contains the height number
   *        
   */
  public StringProperty getHeightProperty() { return heightProperty;}
  /**
   * 
   * getter for the weight property
   *
   * @return StringProperty that contains the weight number
   *        
   */
  public StringProperty getWeightProperty() { return weightProperty;}
  /**
   * 
   * getter for the DOB property
   *
   * @return StringProperty that contains the DOB
   *        
   */
  public StringProperty getDobProperty() { return dobProperty;}
  /**
   * 
   * getter for the deadlift property
   *
   * @return StringProperty for the deadlift weight text
   *        
   */
  public StringProperty getDeadliftProperty() { return deadliftProperty;}
  /**
   *
   * getter for the squat property
   *
   * @return StringProperty for the squat weight text
   *        
   */
  public StringProperty getSquatProperty() { return squatProperty;}

  /**
   * used upon screen refresh
   * 
   */
  @Override public void clear()
  {

  }
}
