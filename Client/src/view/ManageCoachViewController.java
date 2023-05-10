package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import viewModel.ManageCoachViewModel;
import viewModel.ViewModel;
import javafx.scene.layout.Region;

public class ManageCoachViewController extends ViewController{
    private ViewHandler viewHandler;
    private ManageCoachViewModel viewModel;

    @FXML
    private TextField coachField;

    @FXML
    private Label errorLabel;

    @FXML
    private Label headerLabel;

    @Override
    public void init(ViewHandler viewHandler, ViewModel viewModel, Region root) {
        this.viewHandler = viewHandler;
        this.viewModel = (ManageCoachViewModel)viewModel;
        this.root = root;

        coachField.textProperty().bindBidirectional(this.viewModel.getCoachProperty());
    }

    @Override
    public void reset() {
        viewModel.clear();
    }

    @FXML
    void cancel() {
        viewHandler.openView("profile");
    }

    @FXML
    void submit() {
        //if success, go back. if fail, stay
    }

    private void requestCoach() {
        viewModel.requestCoach();
    }
}
