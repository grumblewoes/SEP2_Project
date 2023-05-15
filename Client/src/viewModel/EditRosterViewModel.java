package viewModel;

import com.google.gson.Gson;
import javafx.beans.Observable;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.Button;
import mediator.FriendList;
import mediator.MeetingList;
import mediator.Trainee;
import mediator.TraineeList;
import modelClient.Model;
import util.Logger;
import view.ViewController;

import java.lang.reflect.Array;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class EditRosterViewModel extends ViewModel
{

  private StringProperty errorProperty, traineeList, usernameProperty, selectedTraineeName, selectedMtg;

  private Model model;
  private ViewState viewState;

  private StringProperty traineeRequestList, meetingRequestList, meetingList;

  private Gson gson;
  private DateTimeFormatter formatter;

  public EditRosterViewModel(Model model, ViewState viewState){
    this.model =model;
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

  public StringProperty getErrorProperty(){
    return errorProperty;
  }

  public StringProperty getTraineeRequestList (){
    return traineeRequestList;
  }

  public StringProperty getMeetingRequestList()
  { return meetingRequestList; }
  public StringProperty getMeetingList()
  { return meetingList; }

  public StringProperty getTraineeList(){
    return traineeList;
  }


  private void loadTrainee()
  {
      ArrayList<String> traineesArrayList = model.getTraineeList(viewState.getUsername());
      TraineeList trainees = new TraineeList();
      for (String s : traineesArrayList) {
        trainees.addTrainee(new Trainee(s));
      }
      String coachUsername = viewState.getUsername();
      ArrayList<String> requestArrayList = model.getTraineeRequest( coachUsername);
      TraineeList requests = new TraineeList();
      for (String s : requestArrayList) {
        requests.addTrainee(new Trainee(s));
      }
      Logger.log(coachUsername+" "+requests);

      traineeRequestList.set(gson.toJson(requests));
      traineeList.set(gson.toJson(trainees));
  }

  private void loadMtgRequests()
  {
    ArrayList<String> mtgList = model.getMeetingRequests(viewState.getUsername());
    meetingRequestList.set("");
    meetingRequestList.set(gson.toJson(mtgList));
  }

  private void loadMtgs()
  {
    ArrayList<String> mtgList = model.getCoachMeetings(viewState.getUsername());
    meetingList.set("");
    meetingList.set(gson.toJson(mtgList));
  }

  private void loadMeetings()
  {

    //get list of folders from the database

    MeetingList meetingList = model.getCoachMeetingList(usernameProperty.get());
    //    meetingsListProperty.set("");
    meetingsListProperty.set(gson.toJson(meetingList));

  }

  public boolean removeMeeting(String traineeName, LocalDate date)

      //selectedMeeting.get()))
  {
    if (model.removeMeeting(viewState.getUsername(), traineeName, date))
    {
      return true;
    }
    else
    {
      errorProperty.set(
          "An error occurred while trying to remove the meeting.");
      return false;
    }
  }
  public boolean acceptRequest(String username)
  {
    return model.acceptRequest(username, viewState.getUsername());
  }

  public boolean denyRequest(String username)
  {
    return model.denyRequest(username);
  }

  public boolean removeTrainee()
  {
    if (model.removeTraineeFromRoster(selectedTraineeName.get()))
      return true;
    else
      errorProperty.set("An error occurred while trying to remove trainee.");
    return false;
  }

  public boolean approveMeeting() {
    String[] s = selectedMtg.get().split(",");
    System.out.println(selectedMtg.get());
    LocalDate date = LocalDate.parse(s[0], formatter);

    if (model.approveMeeting(s[1], usernameProperty.get(), date))
      return true;
    else
      errorProperty.set("An error occurred while trying to approve the meeting request.");
    return false;
  }

  public boolean denyMeeting() {
    String[] s = selectedMtg.get().split(",");
    LocalDate date = LocalDate.parse(s[0], formatter);

    if (model.denyMeeting(s[1], usernameProperty.get(), date))
      return true;
    else
      errorProperty.set("An error occurred while trying to deny the meeting request.");
    return false;
  }

  @Override public void clear()
  {
    //listeners in controller see this as a change when refreshing the screen, so it updates every time
    //instead of only when there's updates
    loadTrainee();
    loadMtgRequests();
    loadMtgs();
    errorProperty.set("");
    usernameProperty.set(viewState.getUsername());
  }



  public StringProperty getUsernameProperty() {
  public StringProperty getUsernameProperty()
  {
    return usernameProperty;
  }

  public void setSelectedTraineeName(String selectedTraineeName) {
  public StringProperty getSelectedTraineeName()
  {
    return selectedTraineeName;
  }

  public void setSelectedTraineeName(String selectedTraineeName)
  {
    System.out.println("Clicked");
    this.selectedTraineeName = new SimpleStringProperty(selectedTraineeName);
  }
  public void setSelectedMeeting(String mtgString) {
    this.selectedMtg = new SimpleStringProperty(mtgString);
  }
  public StringProperty getSelectedMeeting() {
    return selectedMtg;
  }

  public StringProperty getSelectedMeeting()
  {
    return selectedMeeting;
  }

  public void setSelectedMeeting(String selectedMeeting, String trainee)
  {
    System.out.println("Clicked");
    this.selectedMeeting = new SimpleStringProperty(selectedMeeting, trainee);
  }

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
}
