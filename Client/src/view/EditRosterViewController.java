

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
import mediator.Meeting;
import mediator.MeetingList;
import mediator.TraineeList;
import util.Logger;
import viewModel.EditRosterViewModel;
import viewModel.ViewModel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class EditRosterViewController extends ViewController {

  @FXML
  private Label errorLabel;
  @FXML
  private VBox requestBox, traineeBox;

  @FXML
  private HBox meetingBox, meetingRequestBox;

  @FXML
  private Label usernameLabel;

  private ViewHandler viewHandler;
  private EditRosterViewModel editRosterViewModel;

  private Gson gson;

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
    editRosterViewModel.getTraineeRequestList().addListener((obs, oldVal, newVal) -> {
      populateTraineeRequests(newVal);
    });
    editRosterViewModel.getMeetingRequestList().addListener((obs, oldVal, newVal) -> {
      populateMeetingRequests(newVal);
    });
  }

  @FXML
  void removeMeeting(ActionEvent event) {
  }

  @FXML
  void removeTrainee(ActionEvent event) {
    if (editRosterViewModel.removeTrainee());
    editRosterViewModel.clear();
  }

  @FXML
  void usernameLabelClicked(MouseEvent event) {
    viewHandler.openView(editRosterViewModel.getUsernameProperty().get());
  }

  private void populateMeetings(String meetingString) {
    //gets the list from a gson string
    ArrayList<String> list = gson.fromJson(meetingString, ArrayList.class);

    meetingBox.getChildren().remove(0, meetingBox.getChildren().size());

    //loops through the list, adding each element. formatted as date,trainee for each entry. delimited by comma
    for (int i = 0; i < list.size(); ++i) {
      String[] mtg = list.get(i).split(",");
      String date = mtg[0];
      String trainee = mtg[1];
      meetingBox.getChildren().add(createMeetingComponent(date, trainee));
    }
  }

  private void populateMeetingRequests(String meetingString) {
    //gets the list from a gson string
    ArrayList<String> list = gson.fromJson(meetingString, ArrayList.class);

    //loops through the list, adding each element. formatted as date,trainee for each entry. delimited by comma
    for (int i = 0; i < list.size(); ++i) {
      String[] mtg = list.get(i).split(",");
      String date = mtg[0];
      String trainee = mtg[1];
      meetingRequestBox.getChildren().add(createMeetingRequestComponent(date, trainee));
    }
  }

  private HBox createMeetingRequestComponent(String date, String trainee) {
    HBox hBox = new HBox();

    hBox.getStyleClass().addAll("bg-primary","fs-2");
    hBox.setAlignment(Pos.CENTER_LEFT);
    hBox.setPadding( new Insets(30,30,30,30));

    Button acceptBtn = new Button("✓");
    Button rejectBtn = new Button("X");
    acceptBtn.getStyleClass().addAll("btn-success");
    rejectBtn.getStyleClass().addAll("btn-danger");

    acceptBtn.onActionProperty().setValue((evt)->{
      editRosterViewModel.setSelectedMeeting(date + "," + trainee);
      acceptMtgRequest();
    });
    rejectBtn.onActionProperty().setValue((evt)-> {
      editRosterViewModel.setSelectedMeeting(date + "," + trainee);
      denyMtgRequest();
    });

    DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate dateDispIn = LocalDate.parse(date, inputFormatter);
    DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    String dateDispOut = dateDispIn.format(outputFormatter);

    Label meetingLabel = new Label(dateDispOut + " " + trainee);
    meetingLabel.getStyleClass().addAll("fs-2","btn-success","cursor-default");
    hBox.getChildren().addAll(acceptBtn, meetingLabel, rejectBtn);

    return hBox;
  }

  private HBox createMeetingComponent(String date, String trainee) {
    HBox hBox = new HBox();

    hBox.getStyleClass().addAll("bg-primary","fs-2");
    hBox.setAlignment(Pos.CENTER_LEFT);
    hBox.setPadding( new Insets(30,30,30,30));

    DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate dateDispIn = LocalDate.parse(date, inputFormatter);
    DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    String dateDispOut = dateDispIn.format(outputFormatter);

    Label meetingLabel = new Label(dateDispOut + " "  + trainee);
    meetingLabel.getStyleClass().addAll("fs-2","btn-success","cursor-default");
    hBox.getChildren().addAll(meetingLabel);

    return hBox;
  }

  private void denyMtgRequest() {
    if (editRosterViewModel.denyMeeting())
      reset();
  }

  private void acceptMtgRequest() {
    if (editRosterViewModel.approveMeeting())
      reset();
  }

  private void populateTraineeRequests(String requestListString)
  {
    //gets the list from a gson string
    TraineeList traineeList = gson.fromJson(requestListString, TraineeList.class);

    //resets the list to 0 so it can refresh
    traineeBox.getChildren().remove(0, traineeBox.getChildren().size());
    requestBox.getChildren().remove(0, requestBox.getChildren().size());

    //loops through the list, adding each element
    for (int i = 0; i < traineeList.getSize(); ++i) {
      String u = traineeList.getTrainee(i);
      requestBox.getChildren().add(createTraineeRequestComponent(u));
    }
  }

  private void populateTrainees(String traineeListString) {
    //gets the list from a gson string
    System.out.println(traineeListString);
    TraineeList traineeList = gson.fromJson(traineeListString, TraineeList.class);

    //resets the list to 0 so it can refresh
    traineeBox.getChildren().remove(0, traineeBox.getChildren().size());

    //loops through the list, adding each element
    for (int i = 0; i < traineeList.getSize(); ++i) {
      String u = traineeList.getTrainee(i);
      traineeBox.getChildren().add(createTraineeComponent(u));
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

    //figure out how to load status
    Label statusLabel = new Label("On dat grind");
    statusLabel.getStyleClass().addAll("fs-2","btn-success","cursor-default");
    statusLabel.setOnMouseClicked(event -> {
      editRosterViewModel.setSelectedTraineeName(username);
    });

    hBox.getChildren().addAll(seeBtn,statusLabel);

    return hBox;
  }

  private HBox createTraineeRequestComponent(String username){
    HBox hBox = new HBox();

    hBox.getStyleClass().addAll("bg-primary","fs-2");
    hBox.setPadding( new Insets(10,10,10,10));

    Label label = new Label(username);
    label.getStyleClass().addAll("btn-warning","cursor-default");

    Button acceptBtn = new Button("✓");
    Button rejectBtn = new Button("X");
    acceptBtn.getStyleClass().addAll("btn-success");
    rejectBtn.getStyleClass().addAll("btn-danger");

    acceptBtn.onActionProperty().setValue((evt)->acceptTraineeRequest(username));
    rejectBtn.onActionProperty().setValue((evt)->rejectTraineeRequest(username));

    hBox.getChildren().addAll(label,acceptBtn,rejectBtn);

    return hBox;
  }

  private void rejectTraineeRequest(String username) {
    Logger.log("acceptning :" + username);
    editRosterViewModel.denyRequest(username);

    reset();
  }

  private void acceptTraineeRequest(String username) {
    Logger.log("rejecting :" + username);
    editRosterViewModel.acceptRequest(username);
    reset();
  }

  private void manageTraineeOpen(String username)
  {
    viewHandler.openView("profile");
  }

  @Override
  public void reset() {
    editRosterViewModel.clear();
  }

  public void logout(MouseEvent mouseEvent) {
    editRosterViewModel.logout();
    viewHandler.openView("logIn");
  }
}

