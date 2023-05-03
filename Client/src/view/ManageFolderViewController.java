package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import viewModel.HomeViewModel;
import viewModel.ManageFolderViewModel;
import viewModel.ViewModel;

public class ManageFolderViewController extends ViewController
{

  @FXML private TextField nameField;
  @FXML private Label headerLabel;

  private ManageFolderViewModel manageFolderViewModel;


  @Override public void init(ViewHandler viewHandler, ViewModel viewModel,
      Region root, HomeViewModel createUserViewModel, Region root2)
  {

  }


  public void submit(){

  }

  @Override public void reset()
  {
    manageFolderViewModel.clear();
  }
}
