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
 * 
 * 
 * @author 
 * @version 
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
   * 
   * 
   * @param model 
   *        
   * @param viewState 
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
   * 
   *
   * @return 
   *        
   */
  public StringProperty getErrorProperty()
  {
    return errorProperty;
  }

  /**
   * 
   * 
   *
   * @return 
   *        
   */
  public StringProperty getTraineeRequestList()
  {
    return traineeRequestList;
  }

  /**
   * 
   * 
   *
   * @return 
   *        
   */
  public StringProperty getMeetingRequestList()
  {
    return meetingRequestList;
  }

  /**
   * 
   * 
   *
   * @return 
   *        
   */
  public StringProperty getMeetingList()
  {
    return meetingList;
  }

  /**
   * 
   * 
   *
   * @return 
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
   * 
   * 
   * @param username 
   *        
   *
   * @return 
   *        
   */
  public boolean acceptRequest(String username)
  {
    return model.acceptRequest(username, viewState.getUsername());
  }

  /**
   * 
   * 
   * @param username 
   *        
   *
   * @return 
   *        
   */
  public boolean denyRequest(String username)
  {
    return model.denyRequest(username);
  }

  /**
   * 
   * 
   *
   * @return 
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
   * 
   *
   * @return 
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
   * 
   *
   * @return 
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
   * 
   *
   * @return 
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
   * 
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
   * 
   *
   * @return 
   *        
   */
  public StringProperty getUsernameProperty()
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
  public StringProperty getSelectedTraineeName()
  {
    return selectedTraineeName;
  }

  /**
   * 
   * 
   * @param selectedTraineeName 
   *        
   */
  public void setSelectedTraineeName(String selectedTraineeName)
  {
    System.out.println("Clicked" + selectedTraineeName);
    this.selectedTraineeName = new SimpleStringProperty(selectedTraineeName);
  }

  /**
   * 
   * 
   * @param mtgString 
   *        
   */
  public void setSelectedMeeting(String mtgString)
  {
    System.out.println("Clicked" + " " + mtgString);
    this.selectedMtg = new SimpleStringProperty(mtgString);
  }

  /**
   * 
   * 
   *
   * @return 
   *        
   */
  public StringProperty getSelectedMeeting()
  {
    return selectedMtg;
  }

  /**
   * 
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
   * 
   * 
   * @param username 
   *        
   */
  public void manageTraineeOpen(String username) {
    viewState.setProfileUsername(username);
    viewState.setGoBack("manageRoster");
  }

  /**
   * 
   * 
   */
  public void setupOpenProfile() {
    viewState.setProfileUsername(viewState.getUsername() );
    viewState.setGoBack("manageRoster");
  }
}
