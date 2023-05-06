package view;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import viewModel.AddExerciseViewModel;
import viewModel.FolderViewModel;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import viewModel.ViewModel;

import java.awt.*;
import java.awt.event.ActionEvent;

public class AddExerciseViewController extends ViewController
{
  @FXML private TextField exerciseName, weightField, repetitionsField;
  @FXML private Label errorLabel;
  private ViewHandler viewHandler;
  private AddExerciseViewModel exerciseViewModel;

  @Override public void init(ViewHandler viewHandler, ViewModel viewModel,
      Region root)
  {
    this.viewHandler = viewHandler;
    this.exerciseViewModel = (AddExerciseViewModel) viewModel;
    this.root = root;
   errorLabel.textProperty().bind(exerciseViewModel.getErrorProperty());
   weightField.textProperty().bindBidirectional(exerciseViewModel.getWeightProperty());
   repetitionsField.textProperty().bindBidirectional(exerciseViewModel.getRepetitionsProperty());
   exerciseName.textProperty().bindBidirectional(exerciseViewModel.);


  }
  @FXML public void addExercise(ActionEvent evt, String exerciseName) {
    String buttonLabel = ((Button) evt.getSource()).getText();
    if (buttonLabel.equals("Remove"))
    {
      exerciseViewModel.addExercise(exerciseName);
    }
  }
  private void populateExercisesToChooseFrom(String exerciseListString) {

  }

  @Override public void reset()
  {
    exerciseViewModel.clear();
  }
}
