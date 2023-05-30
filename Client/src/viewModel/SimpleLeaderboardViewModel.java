package viewModel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import mediator.Trainee;

/**
 * 
 * ViewModel class for SimpleLeaderboardViewModel view. This represents one row in the list on the leaderbaord.
 * 
 * @author Jakub Cerovsky
 * @version 1.0
 */
public class SimpleLeaderboardViewModel
{
  private StringProperty usernameProperty;
  private StringProperty weightProperty;

  /**
   * 1-argument constructor 
   * Takes the name of the trainee to be displayed
   * 
   * @param trainee Trainee to be displayed
   *        
   */
  public SimpleLeaderboardViewModel(Trainee trainee)
  {
    usernameProperty = new SimpleStringProperty(trainee.getUsername());
    weightProperty = new SimpleStringProperty(String.valueOf(trainee.getLiftedWeight()));
  }


  /**
   * 
   * getter for the username of the trainee
   *
   * @return StringProperty that represents the trainee's name
   *        
   */
  public StringProperty usernamePropertyProperty()
  {
    return usernameProperty;
  }


  /**
   * 
   * getter for the weight record of the trainee
   *
   * @return StringProperty that represents the value of the weight
   *        
   */
  public StringProperty weightPropertyProperty()
  {
    return weightProperty;
  }
}
