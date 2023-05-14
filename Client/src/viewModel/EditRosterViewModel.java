package viewModel;

import com.google.gson.Gson;
import javafx.beans.Observable;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.Button;
import mediator.*;
import modelClient.Model;
import util.Logger;
import view.ViewController;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;

public class EditRosterViewModel extends ViewModel
{
  private Button acceptButton;
  private Button denyButton;
  private Button removeButton;

  private StringProperty errorProperty, traineeList, usernameProperty, selectedTraineeName;

  private Model model;
  private ViewState viewState;

  private StringProperty traineeRequestList;
  private StringProperty meetingsListProperty;

  private Gson gson;

  public EditRosterViewModel(Model model, ViewState viewState)
  {
    this.model = model;
    this.viewState = viewState;
    usernameProperty = new SimpleStringProperty();
    errorProperty = new SimpleStringProperty();
    traineeRequestList = new SimpleStringProperty();
    traineeList = new SimpleStringProperty();
    selectedTraineeName = new SimpleStringProperty();
    meetingsListProperty = new SimpleStringProperty();

    gson = new Gson();
  }

  public StringProperty getErrorProperty()
  {
    return errorProperty;
  }

  public StringProperty getTraineeRequestList()
  {
    return traineeRequestList;
  }

  public StringProperty getTraineeList()
  {
    return traineeList;
  }

  public StringProperty getMeetingsListProperty()
  {
    return meetingsListProperty;
  }

  private void loadTrainee()
  {
    try
    {
      ArrayList<String> traineesArrayList = model.getTraineeList(
          viewState.getUsername());
      TraineeList trainees = new TraineeList();
      for (String s : traineesArrayList)
      {
        trainees.addTrainee(new Trainee(s));
      }
      String coachUsername = viewState.getUsername();
      ArrayList<String> requestArrayList = model.getTraineeRequest(
          coachUsername);
      TraineeList requests = new TraineeList();
      for (String s : requestArrayList)
      {
        requests.addTrainee(new Trainee(s));
      }
      Logger.log(coachUsername + " " + requests);

      traineeRequestList.set(gson.toJson(requests));
      traineeList.set(gson.toJson(trainees));
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
  }

  private void loadMeetings()
  {

    //get list of folders from the database

    MeetingList meetingList = model.getCoachMeetingList(usernameProperty.get());
    meetingsListProperty.set("");
    meetingsListProperty.set(gson.toJson(meetingList));

  }

  public boolean removeMeeting(LocalDate date)
  {
    viewState.setUsername(usernameProperty.get());
    try
    {
      model.removeMeeting(usernameProperty.get(), date);
      clear();
      return true;
    }
    catch (Exception e)
    {
      errorProperty.set("Error deleting from DB");
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

  @Override public void clear()
  {
    loadTrainee();

    errorProperty.set("");
    usernameProperty.set(viewState.getUsername());
    loadMeetings();

  }

  public StringProperty getUsernameProperty()
  {
    return usernameProperty;
  }

  public StringProperty getSelectedTraineeName()
  {
    return selectedTraineeName;
  }

  public void setSelectedTraineeName(String selectedTraineeName)
  {
    System.out.println("Clicked");
    this.selectedTraineeName = new SimpleStringProperty(selectedTraineeName);
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
