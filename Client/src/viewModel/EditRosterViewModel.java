package viewModel;

import com.google.gson.Gson;
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

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class EditRosterViewModel extends ViewModel
{

  private StringProperty errorProperty, traineeList, usernameProperty, selectedTraineeName, selectedMtg;

  private Model model;
  private ViewState viewState;

  private StringProperty traineeRequestList;

  private Gson gson;
  private DateTimeFormatter formatter;

  public EditRosterViewModel(Model model, ViewState viewState){
    this.model =model;
    this.viewState = viewState;
    usernameProperty = new SimpleStringProperty();
    errorProperty = new SimpleStringProperty();
    traineeRequestList = new SimpleStringProperty();
    traineeList = new SimpleStringProperty();
    selectedTraineeName = new SimpleStringProperty();
    selectedMtg = new SimpleStringProperty();
    gson = new Gson();
    formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
  }

  public StringProperty getErrorProperty(){
    return errorProperty;
  }

  public StringProperty getTraineeRequestList (){
    return traineeRequestList;
  }

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

  public boolean acceptRequest(String username) {
    return model.acceptRequest(username, viewState.getUsername());
  }
  public boolean denyRequest(String username) {
    return model.denyRequest(username);
  }

  public boolean removeTrainee(){
    if (model.removeTraineeFromRoster(selectedTraineeName.get()))
      return true;
    else
      errorProperty.set("An error occurred while trying to remove trainee.");
      return false;
  }

  public boolean approveMeeting() {
    String[] s = selectedMtg.get().split(",");
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
    loadTrainee();
    errorProperty.set("");
    usernameProperty.set(viewState.getUsername());
  }

  public StringProperty getUsernameProperty() {
    return usernameProperty;
  }

  public void setSelectedTraineeName(String selectedTraineeName) {
    this.selectedTraineeName = new SimpleStringProperty(selectedTraineeName);
  }
  public void setSelectedMeeting(String mtgString) {
    this.selectedMtg = new SimpleStringProperty(mtgString);
  }

  public void logout() {
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
