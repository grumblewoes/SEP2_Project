package viewModel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import mediator.Trainee;

/**
 * 
 * 
 * 
 * @author 
 * @version 
 */
public class SimpleLeaderboardViewModel
{
  private StringProperty usernameProperty;
  private StringProperty weightProperty;

  /**
   * 1-argument constructor 
   * 
   * 
   * @param trainee 
   *        
   */
  public SimpleLeaderboardViewModel(Trainee trainee)
  {
    usernameProperty = new SimpleStringProperty(trainee.getUsername());
    weightProperty = new SimpleStringProperty(String.valueOf(trainee.getLiftedWeight()));
  }


  /**
   * 
   * 
   *
   * @return 
   *        
   */
  public StringProperty usernamePropertyProperty()
  {
    return usernameProperty;
  }


  /**
   * 
   * 
   *
   * @return 
   *        
   */
  public StringProperty weightPropertyProperty()
  {
    return weightProperty;
  }
}
