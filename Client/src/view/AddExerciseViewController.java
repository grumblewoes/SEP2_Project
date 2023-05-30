package view;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import util.Logger;
import viewModel.AddExerciseViewModel;
import viewModel.ViewModel;
import javafx.beans.binding.Bindings;

/**
 * View controller responsible for displaying the add exercise view.
 *
 * @author Damian Trafiałek
 * @version 1.0
 */
public class AddExerciseViewController extends ViewController {

    @FXML
    private Text exercisePrompt;
    @FXML
    private TextField exerciseField, weightField, repetitionField;
    @FXML
    private Label errorLabel;
    private StringProperty goBackString;
    private AddExerciseViewModel addViewModel;

    /**
     * Method that initialise the controller and sets up all instance variables and bindings.
     *
     * @param viewHandler - handles changing views
     *
     * @param viewModel - view model related to the controller
     *
     * @param root - region that is being displayed
     *
     */
    @Override public void init(ViewHandler viewHandler, ViewModel viewModel, Region root) {
        addViewModel = (AddExerciseViewModel) viewModel;
        this.root = root;
        this.viewHandler = viewHandler;

        addViewModel.getExerciseListProperty().addListener((obs, oldVal, newVal) -> {
//            do sth else , display it here a little differently
            exercisePrompt.textProperty().set(newVal);
        });
        exerciseField.textProperty().bindBidirectional(addViewModel.getExerciseProperty());
        Bindings.bindBidirectional(
                weightField.textProperty(),
                addViewModel.getWeightProperty(),
                new IntStringConverter()
        );
        Bindings.bindBidirectional(
                repetitionField.textProperty(),
                addViewModel.getRepetitionsProperty(),
                new IntStringConverter()
        );
        exerciseField.setDisable(!addViewModel.getEditableProperty().get());
        addViewModel.getEditableProperty().addListener((obs, oldVal, newVal) -> {
            Logger.log(" disabled ?  " + !newVal);
            exerciseField.setDisable(!newVal);
        });
        errorLabel.textProperty().bind(addViewModel.getErrorProperty());

        goBackString = new SimpleStringProperty();
        goBackString.bind(addViewModel.getGoBackProperty());
    }

    @Override
    /**
     * Reset method that calls view model to trigger the reset.
     *
     */
    public void reset() {
        addViewModel.clear();
    }

    /**
     * Method that calls view model to submit the exercise.
     * @param actionEvent - event that is trigerred
     */
    public void submit(ActionEvent actionEvent) {
//        submit exercise
        if (addViewModel.addExercise())
            viewHandler.openView(goBackString.get());

    }

    /**
     * Method that calls view handler to go back to another page.
     *
     * @param actionEvent - event that is trigerred
     */
    public void goBack(ActionEvent actionEvent) {
        viewHandler.openView(goBackString.get());
    }
}
