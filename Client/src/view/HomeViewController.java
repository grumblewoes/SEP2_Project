package view;


import com.google.gson.Gson;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mediator.*;
import util.Logger;
import viewModel.HomeViewModel;
import viewModel.LeaderboardViewModel;
import viewModel.ViewModel;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class HomeViewController extends ViewController
{
  @FXML private Label usernameLabel, errorLabel;
  @FXML private VBox folderBox, friendshipBox, meetingBox;
  private ViewHandler viewHandler;

  private HomeViewModel homeViewModel;
  private StringProperty friendRequestList, meetingRequestsList;
  private Gson gson;

  @Override public void init(ViewHandler viewHandler, ViewModel viewModel, Region root)
  {
    this.viewHandler = viewHandler;
    this.homeViewModel = (HomeViewModel) viewModel;
    this.root = root;
    this.gson = new Gson();

    friendRequestList = new SimpleStringProperty();
    friendRequestList.bind(homeViewModel.getFriendshipRequestListProperty());
    meetingRequestsList=new SimpleStringProperty();
    meetingRequestsList.bind(homeViewModel.getMeetingRequestListProperty());
    usernameLabel.textProperty().bind(homeViewModel.getUsernameProperty() );
    errorLabel.textProperty().bind(homeViewModel.getErrorLabel());
    homeViewModel.getFolderListProperty().addListener( (obs,oldVal,newVal) -> {
      populateFolders(newVal);
    });

    homeViewModel.getFriendshipListProperty().addListener( (obs,oldVal,newVal)->{
      populateFriendships(newVal);
    });
    homeViewModel.getMeetingListProperty().addListener( (obs,oldVal,newVal)->{
      populateMeetings(newVal);
    });
  }

  @FXML private void createFolder(){

    homeViewModel.createFolder();
    viewHandler.openView("manageFolder");
  }

  private void removeFolder(int id){
    homeViewModel.removeFolder(id);
    reset();
  }

  private void renameFolder(String title,int id){
    homeViewModel.editFolder(title,id);
    viewHandler.openView("manageFolder");
  }

  private void openFolder(String folderName,int folderId){
    homeViewModel.setupOpenFolder(folderName,folderId);
    viewHandler.openView("manageExercises");
  }

  private void populateFolders(String folderListString){
    FolderList folderList = gson.fromJson(folderListString, FolderList.class);

    folderBox.getChildren().remove(0, folderBox.getChildren().size());
    for (int i = 0; i < folderList.size(); ++i)
    {
      int folderId = folderList.get(i).getId();
      String title = folderList.get(i).getTitle();
      folderBox.getChildren().add(createFolderElement(title, folderId));
      Logger.log(title + " " + folderId);
    }

  }
  private HBox createFolderElement(String title,int id){
    HBox hBox = new HBox();

    hBox.getStyleClass().addAll("bg-primary","fs-2");
    hBox.setPadding( new Insets(10,10,10,10));
    HBox.setMargin(hBox,new Insets(0,0,5,0));
    VBox v1 = new VBox();
    HBox.setHgrow(v1, Priority.ALWAYS);
    v1.setAlignment(Pos.CENTER_LEFT);

    Label label = new Label(title);
    Button renameBtn = new Button("Rename");
    renameBtn.getStyleClass().addAll("btn-warning");
    renameBtn.onActionProperty().setValue((evt)->renameFolder(title,id));


    Button removeBtn = new Button("Remove");
    removeBtn.getStyleClass().addAll("btn-danger");
    removeBtn.onActionProperty().setValue((evt)->removeFolder(id));

    Button openBtn = new Button("Open");
    openBtn.getStyleClass().addAll("btn-success");
    openBtn.onActionProperty().setValue((evt)->openFolder(title,id));

    int r = 5,l=5;
    HBox.setMargin(renameBtn,new Insets(0,r,0,l));
    HBox.setMargin(openBtn,new Insets(0,r,0,l));
    HBox.setMargin(removeBtn,new Insets(0,r,0,l));

    hBox.getChildren().add(v1);
    v1.getChildren().add(label);
    hBox.getChildren().add(openBtn);
    hBox.getChildren().add(renameBtn);
    hBox.getChildren().add(removeBtn);

    return hBox;
  }

  @FXML private void sendFriendRequest()
  {
    viewHandler.openView("addFriend");
  }
  @Override public void reset()
  {

    homeViewModel.clear();
  }

  @FXML private void usernameLabelClicked(){
    homeViewModel.setupProfile();
    viewHandler.openView("profile");
  }
  @FXML private void usernamePictureClicked(){
    homeViewModel.setupProfile();
    viewHandler.openView("profile");
  }

  @FXML private void logout() {
    homeViewModel.logout();
    viewHandler.openView("logIn");
  }

  private void populateFriendships(String friendListJson){
    FriendList friends = gson.fromJson(friendListJson,FriendList.class);
    ArrayList<String> requests = gson.fromJson(friendRequestList.get(),ArrayList.class);

    friendshipBox.getChildren().remove(0, friendshipBox.getChildren().size());

    if(requests!=null)
      for(String username: requests)
        friendshipBox.getChildren().add( createFriendRequestComponent(username) );
    if(friends!=null)
      for(int i=0;i<friends.size();++i){
        Friend friend = friends.get(i);
        friendshipBox.getChildren().add( createFriendComponent(friend.getUsername(),friend.getStatus()) );
      }

  }

  private HBox createFriendComponent(String username,String status){
    HBox hBox = new HBox();

    hBox.getStyleClass().addAll("bg-primary","fs-2");
    hBox.setAlignment(Pos.CENTER_LEFT);
    hBox.setPadding( new Insets(10,10,10,10));

    Button seeBtn = new Button(username);
    seeBtn.getStyleClass().addAll("btn-info");
    seeBtn.onActionProperty().setValue( (evt)-> manageFriendOpen(username));
    Label statusLabel = new Label(status);
    statusLabel.getStyleClass().addAll("fs-2","btn-success","cursor-default");

    hBox.getChildren().addAll(seeBtn,statusLabel);

    return hBox;
  }
  private HBox createFriendRequestComponent(String username){
    HBox hBox = new HBox();

    hBox.getStyleClass().addAll("bg-primary","fs-2");
    hBox.setPadding( new Insets(10,10,10,10));

    Label label = new Label(username);
    label.getStyleClass().addAll("btn-warning","cursor-default");

    Button acceptBtn = new Button("✓");
    Button rejectBtn = new Button("X");
    acceptBtn.getStyleClass().addAll("btn-success");
    rejectBtn.getStyleClass().addAll("btn-danger");

    acceptBtn.onActionProperty().setValue((evt)->acceptRequest(username));
    rejectBtn.onActionProperty().setValue((evt)->rejectRequest(username));

    hBox.getChildren().addAll(label,acceptBtn,rejectBtn);

    return hBox;
  }

  private void acceptRequest(String username) {
    homeViewModel.acceptRequest(username);
    reset();
  }
  private void rejectRequest(String username) {
    homeViewModel.rejectRequest(username);
    reset();
  }

  private void manageFriendOpen(String username){
    homeViewModel.setupProfile(username);
    viewHandler.openView("profile");
  }


  @FXML private void addMeeting(){
    // if there is no coach write an error message
    boolean hasCoach=homeViewModel.setupMeeting();
    if (hasCoach){
      homeViewModel.setupProfile();
      viewHandler.openView("addMeeting");
    }
  }

  private void populateMeetings(String meetingListString){
    ArrayList<String> meetingList = gson.fromJson(meetingListString, ArrayList.class);
    ArrayList<String> meetingRequests = gson.fromJson(meetingRequestsList.get(),ArrayList.class);

    meetingBox.getChildren().remove(0, meetingBox.getChildren().size());

    if (meetingRequests!=null){
      for(int i=0;i<meetingRequests.size();++i){
        String date = meetingRequests.get(i);
        LocalDate localDate=LocalDate.parse(date);
        String status = "Pending...";
        meetingBox.getChildren().add( createMeetingRequestElement(localDate,date,status));
      }
    }
    if (meetingList!=null)
    {
      for(int i=0;i<meetingList.size();++i){
        String date = meetingList.get(i);
        LocalDate localDate=LocalDate.parse(date);
        meetingBox.getChildren().add( createMeetingElement(localDate, date) );
      }
    }
  }

  private void removeMeeting(LocalDate date){
    homeViewModel.removeMeeting(date);
    reset();
  }
  private HBox createMeetingElement(LocalDate localDate, String date){
    HBox hBox = new HBox();

    hBox.getStyleClass().addAll("bg-primary","fs-2");
    hBox.setPadding( new Insets(10,10,10,10));
    VBox v1 = new VBox();
    HBox.setHgrow(v1, Priority.ALWAYS);
    v1.setAlignment(Pos.CENTER_LEFT);

    Label dateLabel = new Label(date);


    Button removeBtn = new Button("Remove");
    removeBtn.getStyleClass().addAll("btn-danger");
    removeBtn.onActionProperty().setValue((evt)->removeMeeting(localDate));

    int r = 5,l=5;
    HBox.setMargin(removeBtn,new Insets(0,r,0,l));

    hBox.getChildren().add(v1);
    v1.getChildren().add(dateLabel);
    hBox.getChildren().add(removeBtn);


    return hBox;
  }
  private HBox createMeetingRequestElement(LocalDate localDate, String date,String status){
    HBox hBox = new HBox();
    hBox.setAlignment(Pos.CENTER);
    hBox.getStyleClass().addAll("bg-light","fs-2");
    hBox.setPadding( new Insets(10,10,10,10));
    VBox v1 = new VBox();
    HBox.setHgrow(v1, Priority.ALWAYS);
    v1.setAlignment(Pos.CENTER_LEFT);

    Label dateLabel = new Label(date);
    Label statusLabel = new Label(status);
    statusLabel.setAlignment(Pos.CENTER);


    Button removeBtn = new Button("Remove");
    removeBtn.getStyleClass().addAll("btn-danger");
    removeBtn.onActionProperty().setValue((evt)->removeMeeting(localDate));

    int r = 5,l=10;
    HBox.setMargin(removeBtn,new Insets(0,r,0,l));
    HBox.setMargin(statusLabel,new Insets(0,r,0,l));

    hBox.getChildren().add(v1);
    v1.getChildren().add(dateLabel);
    hBox.getChildren().add(statusLabel);
    hBox.getChildren().add(removeBtn);


    return hBox;
  }

  @FXML private void getLeaderboard()
  {
    viewHandler.openLeaderboardView();
  }
}
