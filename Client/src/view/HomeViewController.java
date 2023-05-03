package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;
import viewModel.HomeViewModel;
import viewModel.ViewModel;

import java.awt.event.ActionEvent;

import javafx.scene.control.Button;

public class HomeViewController extends ViewController
{

  private ViewHandler viewHandler;

  private HomeViewModel homeViewModel;

  @Override public void init(ViewHandler viewHandler, ViewModel viewModel,
      Region root, HomeViewModel createUserViewModel, Region root2)
  {
    this.viewHandler = viewHandler;
    this.homeViewModel = createUserViewModel;

  }

  @FXML private void createFolder(){
    homeViewModel.createFolder();
  }

  @FXML private boolean removeFolder(ActionEvent evt){
    String buttonLabel = ((Button)evt.getSource()).getText();
    if (buttonLabel.equals("Remove")){
      homeViewModel.removeFolder(homeViewModel.getFolderListProperty().getName());
    }
    return false;
  }

  @FXML private void renameFolder(ActionEvent evt){

    String buttonLabel = ((Button)evt.getSource()).getText();
    if (buttonLabel.equals("Rename")){
      homeViewModel.rename
    }

  }

  private void populateFolders(int folderListString){

  }

  @Override public void reset()
  {
    homeViewModel.clear();
  }
}
