//package view;
//
//import javafx.beans.property.Property;
//import javafx.fxml.FXML;
//import javafx.scene.Node;
//import javafx.scene.control.Label;
//import javafx.scene.layout.Region;
//import viewModel.EditRosterViewModel;
//import viewModel.ViewModel;
//
//public class EditRosterViewController extends ViewController
//{
//  @FXML private Label acceptTraineeLabel;
//  @FXML private Label denyTraineeLabel;
//  @FXML private Label removeTraineeLabel;
//
//  @FXML private Label errorLabel;
//  private ViewHandler viewHandler;
//  private EditRosterViewModel editRosterViewModel;
//
//  public void init(ViewHandler viewHandler, ViewModel viewModel, Region root){
//    this.viewHandler = viewHandler;
//    editRosterViewModel = (EditRosterViewModel) viewModel;
//    this.root = root;
//
//    acceptTraineeLabel.labelForProperty().bindBidirectional(
//        (Property<Node>) editRosterViewModel.getAcceptButton());
//    denyTraineeLabel.labelForProperty().bindBidirectional(
//        (Property<Node>) editRosterViewModel.getDenyButton());
//    removeTraineeLabel.labelForProperty().bindBidirectional(
//        (Property<Node>) editRosterViewModel.getRemoveButton());
//    errorLabel.textProperty().bindBidirectional(editRosterViewModel.getErrorProperty());
//
//  }
//
//  @Override public void reset()
//  {
//
//  }
//}
