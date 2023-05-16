package viewModel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.util.StringConverter;
import javafx.util.converter.LocalDateStringConverter;
import modelClient.Model;
import util.Logger;

import java.time.LocalDate;
import java.util.Date;

public class AddMeetingViewModel extends ViewModel
{
  private StringProperty errorProperty, coachProperty;
  private ObjectProperty<LocalDate> dateOfMeeting;
  private Model model;
  private ViewState viewState;

  public AddMeetingViewModel(Model model, ViewState viewState){
    this.model=model;
    this.viewState = viewState;
    coachProperty=new SimpleStringProperty();
    errorProperty = new SimpleStringProperty();
    dateOfMeeting = new SimpleObjectProperty<>();
  }
  public boolean sendMeetingRequest(){
    String traineeUsername = viewState.getUsername();
    String coachUsername = model.getCoach(viewState.getProfileUsername()).getUsername();
    LocalDate date= dateOfMeeting.get();
    Logger.log(traineeUsername);
    Logger.log(coachUsername);
    Logger.log(date);

    boolean successAddition = model.sendMeetingRequest(traineeUsername,coachUsername, date);
    if(!successAddition)
      errorProperty.set("Failed to send the request! Please check if the date is correct.");

    return successAddition;
  }
  @Override public void clear()
  {
    dateOfMeeting.set(null);
    errorProperty.set("");
    Logger.log(viewState.getProfileUsername());
    coachProperty.set(model.getCoach(viewState.getProfileUsername()).getUsername());
  }
  public StringProperty getErrorProperty() {return errorProperty;}
  public ObjectProperty<LocalDate> getDateOfMeeting(){return dateOfMeeting;}

  public StringProperty getCoachProperty()
  {
    return coachProperty;
  }
}
