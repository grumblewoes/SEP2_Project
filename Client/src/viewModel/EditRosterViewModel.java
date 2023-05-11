package viewModel;

import com.google.gson.Gson;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import javafx.scene.control.Button;
import mediator.FriendList;
import mediator.Trainee;
import mediator.TraineeList;
import modelClient.Model;
import util.Logger;
import view.ViewController;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class EditRosterViewModel extends ViewModel
{
  private Button acceptButton;
  private Button denyButton;
  private Button removeButton;

  private StringProperty errorProperty, traineeList, usernameProperty;

  private Model model;
  private ViewState viewState;

  private StringProperty traineeRequestList;

  private Gson gson;

  public EditRosterViewModel(Model model, ViewState viewState){
    this.model =model;
    this.viewState = viewState;
    usernameProperty = new SimpleStringProperty();
    errorProperty = new SimpleStringProperty();
    traineeRequestList = new SimpleStringProperty();
    traineeList = new SimpleStringProperty();
    gson = new Gson();
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
    try
    {
      ArrayList<String> traineesArrayList = model.getTraineeList(viewState.getUsername());
      TraineeList trainees = new TraineeList();
      for (String s : traineesArrayList) {
        trainees.addTrainee(new Trainee(model.getTrainee(s).getUsername()));
      }

      ArrayList<String> requestArrayList = model.getTraineeRequest(
          viewState.getUsername());
      TraineeList requests = new TraineeList();
      for (String s : requestArrayList) {
        requests.addTrainee(new Trainee(s));
      }

      traineeRequestList.set(gson.toJson(requests));
      traineeList.set(gson.toJson(trainees));
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
  }

  public boolean acceptRequest(String username) {
    return model.acceptRequest(username, viewState.getUsername());
  }
  public boolean denyRequest(String username) {
    return model.denyRequest(username);
  }

  public boolean removeTrainee(String username){
    return model.removeTraineeFromRoster(username);
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
}
