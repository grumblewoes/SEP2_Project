//package view;
//
//import com.google.gson.Gson;
//import javafx.beans.property.Property;
//import javafx.fxml.FXML;
//import javafx.geometry.Insets;
//import javafx.geometry.Pos;
//import javafx.scene.Node;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.Region;
//import javafx.scene.layout.VBox;
//import mediator.FolderList;
//import mediator.TraineeList;
//import mediator.User;
//import util.Logger;
//import viewModel.EditRosterViewModel;
//import viewModel.ViewModel;
//
//import java.security.cert.PolicyNode;
//
//public class EditRosterViewController extends ViewController
//{
//  @FXML private Label errorLabel;
//  private ViewHandler viewHandler;
//  private EditRosterViewModel editRosterViewModel;
//
//  private Gson gson;
//  @FXML private VBox traineeBox, requestBox, meetingBox;
//  public void init(ViewHandler viewHandler, ViewModel viewModel, Region root){
//    this.viewHandler = viewHandler;
//    editRosterViewModel = (EditRosterViewModel) viewModel;
//    this.root = root;
//
////    acceptTraineeLabel.labelForProperty().bindBidirectional(
////        (Property<Node>) editRosterViewModel.getAcceptButton());
////    denyTraineeLabel.labelForProperty().bindBidirectional(
////        (Property<Node>) editRosterViewModel.getDenyButton());
////    removeTraineeLabel.labelForProperty().bindBidirectional(
////        (Property<Node>) editRosterViewModel.getRemoveButton());
//    errorLabel.textProperty().bind(editRosterViewModel.getErrorProperty());//not bidirectional, because you are not editing it from view
//    editRosterViewModel.getTraineeList().addListener( (obs,oldVal,newVal) -> {
//      populateTrainees(newVal);
//    });
////    editRosterViewModel.getMeetingList().addListener( (obs,oldVal,newVal) -> {
////      populateMeetings(newVal);
////    });
//  }
//  private void populateTrainees(String traineeListString) {
//    //gets the list from a gson string
//    TraineeList traineeList = gson.fromJson(traineeListString, TraineeList.class);
//
//    //resets the list to 0 so it can refresh
//    traineeBox.getChildren().remove(0, traineeBox.getChildren().size());
//
//    //loops through the list, adding each element
//    for (int i = 0; i < traineeList.getSize(); ++i) {
//      int traineeId = traineeList.getTraineeId(i);
//      String title = traineeList.getTrainee(i);
//      traineeBox.getChildren().add(createTraineeComponent(traineeList.getUsername(i)));
//      Logger.log(title + " " + traineeId);
//      createTraineeComponent(traineeList.getUsername(i));
//    }
//  }
//
//  private HBox createTraineeRequestComponent(String username){
//    HBox hBox = new HBox();
//
//    hBox.getStyleClass().addAll("bg-primary","fs-2");
//    hBox.setPadding( new Insets(10,10,10,10));
//
//    Label label = new Label(username);
//    label.getStyleClass().addAll("btn-warning","cursor-default");
//
//    Button acceptBtn = new Button("Accept");
//    Button rejectBtn = new Button("Deny");
//    acceptBtn.getStyleClass().addAll("btn-success");
//    rejectBtn.getStyleClass().addAll("btn-danger");
//
//    acceptBtn.onActionProperty().setValue((evt)->acceptButton(username));
//    rejectBtn.onActionProperty().setValue((evt)->denyButton(username));
//
//    hBox.getChildren().addAll(label,acceptBtn,rejectBtn);
//
//    return hBox;
//  }
//
//  //combine the create friend request component with create folder
//
//  private HBox createTraineeComponent(String username){
//    HBox hBox = new HBox();
//
//    hBox.getStyleClass().addAll("bg-primary","fs-2");
//    hBox.setAlignment(Pos.CENTER_LEFT);
//    hBox.setPadding( new Insets(10,10,10,10));
//
//    Button seeBtn = new Button(username);
//    seeBtn.getStyleClass().addAll("btn-info");
//    seeBtn.onActionProperty().setValue( (evt)-> manageTraineeOpen(username));
//    Label statusLabel = new Label("");
//    statusLabel.getStyleClass().addAll("fs-2","btn-success","cursor-default");
//
//    hBox.getChildren().addAll(seeBtn,statusLabel);
//
//    return hBox;
//  }
//
//  private void manageTraineeOpen(String username)
//  {
//    viewHandler.openView("profile");
//  }
//
//  public boolean acceptButton(String username){
//    return editRosterViewModel.acceptRequest(username);
//  }
//
//  public boolean denyButton(String username){
//    return editRosterViewModel.denyRequest(username);
//  }
//
//  public boolean removeButton(String username){
//    return editRosterViewModel.removeTrainee(username);
//  }
//  //methods for buttons, accept remove, deny
//
//  @Override public void reset()
//  {
//
//  }
//}

package view;

import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
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
    editRosterViewModel.acceptRequest();
  }

  @FXML
  void denyRequest(ActionEvent event) {
    editRosterViewModel.denyRequest();
  }

  @FXML
  void removeMeeting(ActionEvent event) {

  }

  @FXML
  void removeTrainee(ActionEvent event) {
    editRosterViewModel.removeTrainee();
  }

  @FXML
  void usernameLabelClicked(MouseEvent event) {

  }

  public void init(ViewHandler viewHandler, ViewModel viewModel, Region root){
    this.viewHandler = viewHandler;
    editRosterViewModel = (EditRosterViewModel) viewModel;
    this.root = root;

    usernameLabel.textProperty().bind(editRosterViewModel.getUsernameProperty());

//    acceptTraineeLabel.labelForProperty().bindBidirectional(
//        (Property<Node>) editRosterViewModel.getAcceptButton());
//    denyTraineeLabel.labelForProperty().bindBidirectional(
//        (Property<Node>) editRosterViewModel.getDenyButton());
//    removeTraineeLabel.labelForProperty().bindBidirectional(
//        (Property<Node>) editRosterViewModel.getRemoveButton());
    errorLabel.textProperty().bind(editRosterViewModel.getErrorProperty());//not bidirectional, because you are not editing it from view
    editRosterViewModel.getTraineeList().addListener( (obs,oldVal,newVal) -> {
      populateTrainees(newVal);
    });
//    editRosterViewModel.getMeetingList().addListener( (obs,oldVal,newVal) -> {
//      populateMeetings(newVal);
//    });
  }

  @Override
  public void reset() {

  }
}

