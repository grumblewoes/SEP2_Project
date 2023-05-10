package view;


import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import mediator.Folder;
import mediator.FolderList;
import util.Logger;
import viewModel.HomeViewModel;
import viewModel.ViewModel;


import java.util.ArrayList;

public class HomeViewController extends ViewController
{
  @FXML private Label usernameLabel;
  @FXML private VBox folderBox;
  private ViewHandler viewHandler;

  private HomeViewModel homeViewModel;
  private Gson gson;

  @Override public void init(ViewHandler viewHandler, ViewModel viewModel, Region root)
  {
    this.viewHandler = viewHandler;
    this.homeViewModel = (HomeViewModel) viewModel;
    this.root = root;
    this.gson = new Gson();

    usernameLabel.textProperty().bind(homeViewModel.getUsernameProperty() );
    homeViewModel.getFolderListProperty().addListener( (obs,oldVal,newVal) -> {
      populateFolders(newVal);
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
    for(int i=0;i<folderList.size();++i){
        int folderId = folderList.get(i).getId();
        String title = folderList.get(i).getTitle();
        folderBox.getChildren().add( createFolderElement(title,folderId) );
        Logger.log(title+" "+folderId);
    }


  }

  private HBox createFolderElement(String title,int id){
    HBox hBox = new HBox();

    hBox.getStyleClass().addAll("bg-primary","fs-2");
    hBox.setPadding( new Insets(10,10,10,10));
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
    hBox.getChildren().add(renameBtn);
    hBox.getChildren().add(removeBtn);
    hBox.getChildren().add(openBtn);



    return hBox;
  }

  @Override public void reset()
  {
    homeViewModel.clear();
  }

  @FXML private void usernameLabelClicked(){
    homeViewModel.setupProfile();
    viewHandler.openView("profile");
  }
}
