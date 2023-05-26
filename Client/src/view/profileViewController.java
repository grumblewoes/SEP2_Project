package view;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import util.Logger;
import viewModel.ProfileViewModel;
import viewModel.ViewModel;

/**
 * 
 * 
 * 
 * @author 
 * @version 
 */
public class profileViewController extends  ViewController{
    @FXML private TextField firstNameField, lastNameField,usernameField,statusField,weightField,benchPressField,heightField,squatField,bmiField,deadliftField, coachField;
    @FXML private RadioButton shareProfileRadio;
    @FXML private Button updateBtn, removeBtn, goBackView, removeCoachBtn, sendBtn;
    @FXML private Label shareInfoLabel,errorLabel;

    private ProfileViewModel profileViewModel;
    @Override
    /**
     * 
     * 
     * @param viewHandler 
     *        
     * @param viewModel 
     *        
     * @param root 
     *        
     */
    public void init(ViewHandler viewHandler, ViewModel viewModel, Region root) {
        this.viewHandler=viewHandler;
        this.profileViewModel = (ProfileViewModel) viewModel;
        this.root = root;

//        bindings here
        firstNameField.textProperty().bind(profileViewModel.firstNameProperty());
        lastNameField.textProperty().bind(profileViewModel.lastNameProperty());
        usernameField.textProperty().bind(profileViewModel.usernameProperty());
        statusField.textProperty().bindBidirectional(profileViewModel.statusProperty());
        errorLabel.textProperty().bind(profileViewModel.errorProperty());
        coachField.textProperty().bindBidirectional(profileViewModel.coachProperty());


        Bindings.bindBidirectional(
                heightField.textProperty(),
                profileViewModel.heightProperty(),
                new IntStringConverter()
        );
        Bindings.bindBidirectional(
                weightField.textProperty(),
                profileViewModel.weightProperty(),
                new IntStringConverter()
        );
        Bindings.bindBidirectional(
                benchPressField.textProperty(),
                profileViewModel.benchPressProperty(),
                new IntStringConverter()
        );
        Bindings.bindBidirectional(
                squatField.textProperty(),
                profileViewModel.squatProperty(),
                new IntStringConverter()
        );
        Bindings.bindBidirectional(
                squatField.textProperty(),
                profileViewModel.squatProperty(),
                new IntStringConverter()
        );
        Bindings.bindBidirectional(
                deadliftField.textProperty(),
                profileViewModel.deadliftProperty(),
                new IntStringConverter()
        );
        bmiField.textProperty().bind(profileViewModel.bmiProperty());
        shareProfileRadio.selectedProperty().bindBidirectional(profileViewModel.shareProfileProperty());

        profileViewModel.editableProperty().addListener((obs,oldVal,newVal)->{
            heightField.setDisable(!newVal);
            weightField.setDisable(!newVal);
            shareProfileRadio.setVisible(newVal);
            shareInfoLabel.setVisible(newVal);
            coachField.setDisable(!newVal);

            statusField.setDisable(!newVal);
            coachField.setDisable(!newVal);
        });
        Logger.log(profileViewModel.isCoachProperty().get());
        removeBtn.managedProperty().bind(profileViewModel.editableProperty().not().and(profileViewModel.isCoachProperty().not()));
        updateBtn.managedProperty().bind(profileViewModel.editableProperty());
        removeBtn.visibleProperty().bind(profileViewModel.editableProperty().not().and(profileViewModel.isCoachProperty().not()));
        updateBtn.visibleProperty().bind(profileViewModel.editableProperty());

        //only seen if profile is editable, you have a coach, and you yourself are not a coach
        removeCoachBtn.visibleProperty().bind(
            profileViewModel.editableProperty().and(profileViewModel.coachStateProperty()).and(profileViewModel.isCoachProperty().not()));
        sendBtn.visibleProperty().bind(profileViewModel.editableProperty().and(profileViewModel.coachStateProperty().not()).and(profileViewModel.isCoachProperty().not()));
        coachField.editableProperty().bind(profileViewModel.editableProperty().and(profileViewModel.coachStateProperty().not()).and(profileViewModel.isCoachProperty().not()));
        coachField.visibleProperty().bind(profileViewModel.isCoachProperty().not());
    }

    @Override
    /**
     * 
     * 
     */
    public void reset() {
        profileViewModel.clear();
    }

    /**
     * 
     * 
     * @param actionEvent 
     *        
     */
    public void update(ActionEvent actionEvent) {
        profileViewModel.update();
    }

    /**
     * 
     * 
     * @param actionEvent 
     *        
     */
    public void goBack(ActionEvent actionEvent) {
        viewHandler.openView(profileViewModel.getGoBack());
    }

    @FXML
    void removeCoach(ActionEvent event) {
        profileViewModel.removeCoach();
    }

    @FXML
    void sendCoachRequest(ActionEvent event) {
        profileViewModel.requestCoach();
    }


    @FXML private void remove(){
        boolean ans = profileViewModel.removeFriend();
        if(ans)
            viewHandler.openView("home");
    }

}
