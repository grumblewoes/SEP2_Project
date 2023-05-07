package viewModel;

import com.google.gson.Gson;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import mediator.ExerciseList;
import modelClient.Model;
import util.Logger;

public class ManageSpecificExercisesViewModel extends ViewModel{
    private Model model;
    private ViewState viewState;
    private Gson gson;
    private StringProperty usernameProperty,exercisesListProperty,errorProperty,exerciseNameProperty,folderNameProperty;
    private BooleanProperty isSpecificProperty;
    public ManageSpecificExercisesViewModel(Model model, ViewState viewState) {
        this.model=model;
        this.viewState=viewState;
        gson = new Gson();
        usernameProperty = new SimpleStringProperty();
        exercisesListProperty = new SimpleStringProperty();
        errorProperty = new SimpleStringProperty();
        exerciseNameProperty = new SimpleStringProperty();
        folderNameProperty = new SimpleStringProperty();
        isSpecificProperty = new SimpleBooleanProperty(false);
    }

    private void populateExercises(){
        ExerciseList exercisesList = null;
        if(isSpecificProperty.get())
            exercisesList = model.getExerciseListByNameAndFolderId(viewState.getExerciseName(), viewState.getFolderId());
        else
            exercisesList = model.getExerciseList(viewState.getFolderId());

        exercisesListProperty.set(null);
        exercisesListProperty.set( gson.toJson(exercisesList) );
    }
    @Override
    public void clear() {
        exerciseNameProperty.set(viewState.getExerciseName());
        usernameProperty.set(viewState.getUsername());
        folderNameProperty.set(viewState.getFolderName());
        populateExercises();
    }

    public void setupOpenExercisesByName(String name){
        isSpecificProperty.set(true);
        viewState.setExerciseName(name);
    }
    public String setupGoBack(){
        isSpecificProperty.set(false);
        if(viewState.getExerciseName()==null)
            viewState.setGoBack("home");
        else {
            viewState.setExerciseName(null);
            viewState.setGoBack("manageExercises");
        }
        return viewState.getGoBack();
    }

    public StringProperty getUsernameProperty() {
        return usernameProperty;
    }


    public StringProperty getExercisesListProperty() {
        return exercisesListProperty;
    }


    public StringProperty getErrorProperty() {
        return errorProperty;
    }
    public StringProperty getExerciseNameProperty() {
        return exerciseNameProperty;
    }
    public StringProperty getFolderNameProperty() { return folderNameProperty; }
    public BooleanProperty getIsSpecificProperty() { return isSpecificProperty; }


    public void removeExercise(int exerciseId) {
        model.removeExercise(exerciseId);
    }
    public void removeExercisesByName(String name) {
        model.removeExercisesByName(name,viewState.getFolderId());
    }
    public void setupAddExercise() {
        if(isSpecificProperty.get())
            viewState.setGoBack("specificExercises");
        else{
            viewState.setGoBack("manageExercises");
            viewState.setExerciseName(null);
        }

    }

    public void setupProfile() {
        viewState.setGoBack("manageExercises");
        viewState.setProfileUsername(viewState.getUsername());
    }
}
