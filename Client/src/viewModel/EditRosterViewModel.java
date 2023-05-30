package viewModel;

import com.google.gson.Gson;
import com.google.gson.stream.JsonToken;
import javafx.beans.Observable;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.Button;
import mediator.FriendList;
import mediator.Trainee;
import mediator.TraineeList;
import modelClient.Model;
import util.Logger;
import view.ViewController;

import java.lang.reflect.Array;
import java.rmi.RemoteException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * 
 * ViewModel for the EditRosterViewController class
 * 
 * @author Catarina Jesus
 * @version 1.0
 */
public class EditRosterViewModel extends ViewModel
{

  private StringProperty errorProperty, traineeList, usernameProperty, selectedTraineeName, selectedMtg;

  private Model model;
  private ViewState viewState;

  private StringProperty traineeRequestList, meetingRequestList, meetingList;

  private Gson gson;
  private DateTimeFormatter formatter;

  /**
   * 2-argument constructor 
   * accepts a model and viewstate object and initialises the rest of the bound properties,
   * along with a Gson interpreter and a date formatter
   * 
   * @param model for the model layer of MVVM, which communicates with the server
   *        
   * @param viewState for storing information when switching screens
   *        
   */
  public EditRosterViewModel(Model model, ViewState viewState)
  {
    this.model = model;
    this.viewState = viewState;
    usernameProperty = new SimpleStringProperty();
    errorProperty = new SimpleStringProperty();
    traineeRequestList = new SimpleStringProperty();
    meetingRequestList = new SimpleStringProperty();
    traineeList = new SimpleStringProperty();
    selectedTraineeName = new SimpleStringProperty();
    selectedMtg = new SimpleStringProperty();
    meetingList = new SimpleStringProperty();
    gson = new Gson();
    formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
  }

  /**
   * 
   * getter for the error property
   *
   * @return StringProperty that contains the error text
   *        
   */
  public StringProperty getErrorProperty()
  {
    return errorProperty;
  }

  /**
   * 
   * getter for the JSON string of trainee requests for the current coach
   *
   * @return StringProperty that contains the list of trainee requests
   *        
   */
  public StringProperty getTraineeRequestList()
  {
    return traineeRequestList;
  }

  /**
   * 
   * getter for the JSON string of meeting requests from the current coach's trainees
   *
   * @return StringProperty that contains the list of meeting requests
   *        
   */
  public StringProperty getMeetingRequestList()
  {
    return meetingRequestList;
  }

  /**
   * 
   * getter for the JSON string of meeting's on the current coach's roster
   *
   * @return StringProperty that contains the list of meetings
   *        
   */
  public StringProperty getMeetingList()
  {
    return meetingList;
  }

  /**
   * 
   * getter for the JSON string of trainees on the current coach's roster
   *
   * @return StringProperty that contains the list of trainees
   *        
   */
  public StringProperty getTraineeList()
  {
    return traineeList;
  }

  private void loadTrainee()
  {
    TraineeList trainees = model.getTraineeList(viewState.getUsername());
    String coachUsername = viewState.getUsername();
    ArrayList<String> requestArrayList = model.getTraineeRequest(coachUsername);
    TraineeList requests = new TraineeList();
    for (String s : requestArrayList)
    {
      requests.addTrainee(new Trainee(s));
    }
    Logger.log(coachUsername + " " + requests);

    traineeRequestList.set(gson.toJson(requests));
    traineeList.set(gson.toJson(trainees));
  }

  private void loadMtgRequests()
  {
    ArrayList<String> mtgList = model.getMeetingRequests(
        viewState.getUsername());
    meetingRequestList.set("");
    meetingRequestList.set(gson.toJson(mtgList));
  }

  private void loadMtgs()
  {
    ArrayList<String> mtgList = model.getCoachMeetings(viewState.getUsername());
    meetingList.set("");
    meetingList.set(gson.toJson(mtgList));
  }

  /**
   * method that requests to the server to accept the trainee request from the specified trainee
   * 
   * @param username String that contains the trainee's username
   *        
   *
   * @return boolean that contains the success/failure of the request
   *        
   */
  public boolean acceptRequest(String username)
  {
    return model.acceptRequest(username, viewState.getUsername());
  }

  /**
   method that requests to the server to reject the trainee request from the specified trainee
   *
   * @param username String that contains the trainee's username
   *
   *
   * @return boolean that contains the success/failure of the request
   *        
   */
  public boolean denyRequest(String username)
  {
    return model.denyRequest(username);
  }

  /**
   * method that requests to the server to remove the selected trainee from the coach's roster
   *
   * @return boolean that contains the success/failure of the request
   *        
   */
  public boolean removeTrainee()
  {
    if (model.removeTraineeFromRoster(selectedTraineeName.get()))
      return true;
    else
      errorProperty.set("An error occurred while trying to remove trainee.");
    return false;
  }

  /**
   * 
   * method that requests to the server to cancel a meeting that was previously accepted by the coach
   *
   * @return boolean that signifies the success/failure of the request
   *        
   */
  public boolean removeMeeting()
  {
    try
    {
      String[] s = selectedMtg.get().split(",");
      LocalDate date = LocalDate.parse(s[0], formatter);

      if (model.removeMeeting(s[1], usernameProperty.get(), date))
        return true;
    }
    catch (Exception e)
    {
      System.out.println("No meeting was selected");
      errorProperty.set("PLease select a meeting to remove");
    }
    return false;
  }


  /**
   *
   * method that requests to the server to approve a meeting request
   *
   * @return boolean that signifies the success/failure of the request
   *        
   */
  public boolean approveMeeting()
  {
      String[] s = selectedMtg.get().split(",");
    System.out.println(selectedMtg.get());
    LocalDate date = LocalDate.parse(s[0], formatter);

    if (model.approveMeeting(s[1], usernameProperty.get(), date))
      return true;
    else
      errorProperty.set(
          "An error occurred while trying to approve the meeting request.");
    return false;
  }

  /**
   *
   * method that requests to the server to reject a meeting request
   *
   * @return boolean that signifies the success/failure of the request
   *
   */
  public boolean denyMeeting()
  {
    String[] s = selectedMtg.get().split(",");
    LocalDate date = LocalDate.parse(s[0], formatter);

    if (model.denyMeeting(s[1], usernameProperty.get(), date))
      return true;
    else
      errorProperty.set(
          "An error occurred while trying to deny the meeting request.");
    return false;
  }

  /**
   * refreshes the screen upon controller initialisation and screen switch, and requests
   * updated information from the server for the trainee roster, meeting requests, and trainee
   * requests
   * 
   */
  @Override public void clear()
  {
    //listeners in controller see this as a change when refreshing the screen, so it updates every time
    //instead of only when there's updates
    loadTrainee();
    loadMtgRequests();
    loadMtgs();
    selectedMtg.set("");
    errorProperty.set("");
    usernameProperty.set(viewState.getUsername());
    Logger.log("is a coach: "+viewState.isCoach());
  }

  /**
   * 
   * getter for the username property
   *
   * @return StringProperty that contains the username of the coach
   *        
   */
  public StringProperty getUsernameProperty()
  {
    return usernameProperty;
  }

  /**
   * 
   * getter for the selected trainee property
   *
   * @return StringProperty that contains the name of the selected trainee
   *        
   */
  public StringProperty getSelectedTraineeName()
  {
    return selectedTraineeName;
  }

  /**
   * setter for the selected trainee's name property
   * 
   * @param selectedTraineeName String that contains the selected trainee's name
   *        
   */
  public void setSelectedTraineeName(String selectedTraineeName)
  {
    System.out.println("Clicked" + selectedTraineeName);
    this.selectedTraineeName = new SimpleStringProperty(selectedTraineeName);
  }

  /**
   * setter for the selected meeting property
   * 
   * @param mtgString String that contains the selected meeting text
   *        
   */
  public void setSelectedMeeting(String mtgString)
  {
    //System.out.println("Clicked" + " " + mtgString);
    this.selectedMtg = new SimpleStringProperty(mtgString);
  }

//  /**
//   *
//   *
//   *
//   * @return
//   *
//   */
//  public StringProperty getSelectedMeeting()
//  {
//    return selectedMtg;
//  }

  /**
   * resets all viewstate variables to their defaults, and disconnects the listener for the current trainee
   *
   */
  public void logout()
  {
    model.disconnectListener(viewState.getUsername());
    viewState.setIsCoach(false);
    viewState.setUsername(null);
    viewState.setProfileUsername(null);
    viewState.setGoBack(null);
    viewState.setExerciseName(null);
    viewState.setFolderName(null);
    viewState.setFolderId(0);
    viewState.setNewFolder(false);
    viewState.setManageFolderEditable(false);
  }
  /**
   * method that prepares the viewstate to view the selected trainee's profile
   * 
   * @param username String that contains the name of the selected trainee
   *        
   */
  public void manageTraineeOpen(String username) {
    viewState.setProfileUsername(username);
    viewState.setGoBack("manageRoster");
  }

  /**
   * method that prepares the viewstate to view the coach's profile
   *
   */
  public void setupOpenProfile() {
    viewState.setProfileUsername(viewState.getUsername() );
    viewState.setGoBack("manageRoster");
  }
}
