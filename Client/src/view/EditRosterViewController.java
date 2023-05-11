package view;

import com.google.gson.Gson;
import javafx.beans.property.Property;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import mediator.FolderList;
import mediator.TraineeList;
import mediator.User;
import util.Logger;
import viewModel.EditRosterViewModel;
import viewModel.ViewModel;

import java.security.cert.PolicyNode;

public class EditRosterViewController extends ViewController
{
  @FXML private Label acceptTraineeLabel;
  @FXML private Label denyTraineeLabel;
  @FXML private Label removeTraineeLabel;

  @FXML private Label errorLabel;
  private ViewHandler viewHandler;
  private EditRosterViewModel editRosterViewModel;

  private Gson gson;
  @FXML private VBox traineeBox, requestBox;
  public void init(ViewHandler viewHandler, ViewModel viewModel, Region root){
    this.viewHandler = viewHandler;
    editRosterViewModel = (EditRosterViewModel) viewModel;
    this.root = root;

    acceptTraineeLabel.labelForProperty().bindBidirectional(
        (Property<Node>) editRosterViewModel.getAcceptButton());
    denyTraineeLabel.labelForProperty().bindBidirectional(
        (Property<Node>) editRosterViewModel.getDenyButton());
    removeTraineeLabel.labelForProperty().bindBidirectional(
        (Property<Node>) editRosterViewModel.getRemoveButton());
    errorLabel.textProperty().bindBidirectional(editRosterViewModel.getErrorProperty());

  }
  private void populateFolders(String traineeListString, String username, String status) {
    TraineeList traineeList = gson.fromJson(traineeListString, TraineeList.class);
    traineeBox.getChildren().remove(0, traineeBox.getChildren().size());
    for (int i = 0; i < traineeList.getSize(); ++i) {
      int folderId = traineeList.getTraineeId(i);
      String title = traineeList.getTrainee(i);
      traineeBox.getChildren().add(createTraineeComponent(title, status));
      Logger.log(title + " " + folderId);
      createTraineeComponent(username, status);
    }
  }

  private HBox createFriendRequestComponent(String username){
    HBox hBox = new HBox();

    hBox.getStyleClass().addAll("bg-primary","fs-2");
    hBox.setPadding( new Insets(10,10,10,10));

    Label label = new Label(username);
    label.getStyleClass().addAll("btn-warning","cursor-default");

    Button acceptBtn = new Button("Accept");
    Button rejectBtn = new Button("Deny");
    acceptBtn.getStyleClass().addAll("btn-success");
    rejectBtn.getStyleClass().addAll("btn-danger");

    acceptBtn.onActionProperty().setValue((evt)->acceptButton(username));
    rejectBtn.onActionProperty().setValue((evt)->denyButton(username));

    hBox.getChildren().addAll(label,acceptBtn,rejectBtn);

    return hBox;
  }

  //combine the create friend request component with create folder

  private HBox createTraineeComponent(String username,String status){
    HBox hBox = new HBox();

    hBox.getStyleClass().addAll("bg-primary","fs-2");
    hBox.setAlignment(Pos.CENTER_LEFT);
    hBox.setPadding( new Insets(10,10,10,10));

    Button seeBtn = new Button(username);
    seeBtn.getStyleClass().addAll("btn-info");
    seeBtn.onActionProperty().setValue( (evt)-> manageTraineeOpen(username));
    Label statusLabel = new Label(status);
    statusLabel.getStyleClass().addAll("fs-2","btn-success","cursor-default");

    hBox.getChildren().addAll(seeBtn,statusLabel);

    return hBox;
  }

  private void manageTraineeOpen(String username)
  {
    viewHandler.openView("profile");
  }

  public boolean acceptButton(String username){
    return editRosterViewModel.acceptRequest(username);
  }

  public boolean denyButton(String username){
    return editRosterViewModel.denyRequest(username);
  }

  public boolean removeButton(String username){
    return editRosterViewModel.removeTrainee(username);
  }
  //methods for buttons, accept remove, deny

  @Override public void reset()
  {

  }
}
