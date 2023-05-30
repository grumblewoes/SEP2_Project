package viewModel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.util.StringConverter;
import javafx.util.converter.LocalDateStringConverter;
import mediator.User;
import modelClient.Model;
import util.Logger;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

/**
 * 
 * The viewmodel for the AddMeetingViewController class.
 * 
 * @author Jakub Cerovsky
 * @version 1.0
 */
public class AddMeetingViewModel extends ViewModel
{
  private StringProperty errorProperty, coachProperty;
  private ObjectProperty<LocalDate> dateOfMeeting;
  private Model model;
  private ViewState viewState;

  /**
   * 2-argument constructor 
   * initializes each of the StringProperties for the different fields in the controller class
   * 
   * @param model for the model layer to pass information to the server
   *        
   * @param viewState to store information when switching screens
   *        
   */
  public AddMeetingViewModel(Model model, ViewState viewState){
    this.model=model;
    this.viewState = viewState;
    coachProperty=new SimpleStringProperty();
    errorProperty = new SimpleStringProperty();
    dateOfMeeting = new SimpleObjectProperty<>();
  }
  /**
   * 
   * sends a meeting request from the trainee to the specified coach
   *
   * @return boolean for success/failure to send the request
   *        
   */
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
  /**
   * refreshes the screen upon initial load and when the screen is accessed by the user
   * 
   */
  @Override public void clear()
  {
    dateOfMeeting.set(null);
    errorProperty.set("");
    Logger.log(viewState.getProfileUsername());
    coachProperty.set(model.getCoach(viewState.getProfileUsername()).getUsername());
  }
  /**
   * 
   * getter for the errorLabel text
   *
   * @return StringProperty for the error label text
   *        
   */
  public StringProperty getErrorProperty() {return errorProperty;}
  /**
   * 
   * getter for the meeting date chosen by the trainee
   *
   * @return ObjectProperty<LocalDate> for the meeting date
   *        
   */
  public ObjectProperty<LocalDate> getDateOfMeeting(){return dateOfMeeting;}

  /**
   * getter for the trainee's coach
   * 
   *
   * @return StringProperty for the name of the coach
   *        
   */
  public StringProperty getCoachProperty()
  {
    return coachProperty;
  }

  /**
   * 
   * getter for the dates where the coach is unavailable to schedule a meeting
   *
   * @return ArrayList of LocalDates for taken days
   *        
   */
  public ArrayList<LocalDate> getTakenDates(){
    String coachUsername = null;
    User coach = model.getCoach(viewState.getUsername());
    if (coach != null) {
      coachUsername = coach.getUsername();
    }

    if (coachUsername == null) {
      errorProperty.set("You do not have a coach.");
      return null;
    }
    return model.getTakenDates(coachUsername);
  }
}
