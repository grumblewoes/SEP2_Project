package viewModel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import mediator.Trainee;

public class SimpleLeaderboardViewModel
{
  private StringProperty usernameProperty;
  private StringProperty weightProperty;

  public SimpleLeaderboardViewModel(Trainee trainee)
  {
    usernameProperty = new SimpleStringProperty(trainee.getUsername());
    weightProperty = new SimpleStringProperty(String.valueOf(trainee.getLiftedWeight()));
  }


  public StringProperty usernamePropertyProperty()
  {
    return usernameProperty;
  }


  public StringProperty weightPropertyProperty()
  {
    return weightProperty;
  }
}