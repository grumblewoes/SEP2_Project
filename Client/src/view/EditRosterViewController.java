

package view;

import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import mediator.TraineeList;
import util.Logger;
import viewModel.EditRosterViewModel;
import viewModel.ViewModel;

public class EditRosterViewController extends ViewController {

  @FXML
  private Label errorLabel;
  @FXML
  private VBox meetingBox, requestBox, traineeBox;

  @FXML
  private Label usernameLabel;

  private ViewHandler viewHandler;
  private EditRosterViewModel editRosterViewModel;

  private Gson gson;

  @FXML
  void acceptRequest(ActionEvent event) {
    //editRosterViewModel.acceptRequest();
  }

  @FXML
  void denyRequest(ActionEvent event) {
    //editRosterViewModel.denyRequest();
  }

  @FXML
  void removeMeeting(ActionEvent event) {

  }

  @FXML
  void removeTrainee(ActionEvent event) {

    //editRosterViewModel.removeTrainee();
  }

  @FXML
  void usernameLabelClicked(MouseEvent event) {

  }

  public void init(ViewHandler viewHandler, ViewModel viewModel, Region root){
    this.viewHandler = viewHandler;
    editRosterViewModel = (EditRosterViewModel) viewModel;
    this.root = root;
    gson = new Gson();

    usernameLabel.textProperty().bind(editRosterViewModel.getUsernameProperty());

    errorLabel.textProperty().bind(editRosterViewModel.getErrorProperty());//not bidirectional, because you are not editing it from view
    editRosterViewModel.getTraineeList().addListener( (obs,oldVal,newVal) -> {
      populateTrainees(newVal);
    });
//    editRosterViewModel.getTraineeRequestList().addListener((obs, oldVal, newVal) -> {
//      populateTraineeRequests(newVal);
//    });
  }

  private void populateTrainees(String traineeListString) {
    //gets the list from a gson string
    System.out.println(traineeListString);
    TraineeList traineeList = gson.fromJson(traineeListString, TraineeList.class);

    //resets the list to 0 so it can refresh
    traineeBox.getChildren().remove(0, traineeBox.getChildren().size());

    //loops through the list, adding each element
    for (int i = 0; i < traineeList.getSize(); ++i) {
      int traineeId = traineeList.getTraineeId(i);
      String title = traineeList.getTrainee(i);
      traineeBox.getChildren().add(createTraineeComponent(traineeList.getUsername(i)));
      Logger.log(title + " " + traineeId);
      createTraineeComponent(traineeList.getUsername(i));
    }
  }

  private HBox createTraineeComponent(String username){
    HBox hBox = new HBox();

    hBox.getStyleClass().addAll("bg-primary","fs-2");
    hBox.setAlignment(Pos.CENTER_LEFT);
    hBox.setPadding( new Insets(10,10,10,10));

    Button seeBtn = new Button(username);
    seeBtn.getStyleClass().addAll("btn-info");
    seeBtn.onActionProperty().setValue( (evt)-> manageTraineeOpen(username));
    Label statusLabel = new Label("");
    statusLabel.getStyleClass().addAll("fs-2","btn-success","cursor-default");

    hBox.getChildren().addAll(seeBtn,statusLabel);

    return hBox;
  }

  private void manageTraineeOpen(String username)
  {
    viewHandler.openView("profile");
  }

  @Override
  public void reset() {
    editRosterViewModel.clear();
  }
}

