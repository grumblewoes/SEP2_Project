package viewModel;

import com.google.gson.Gson;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import mediator.ExerciseList;
import modelClient.Model;

import java.util.ArrayList;

/**
 * 
 * ViewModel for the FolderViewController class.
 * 
 * @author Damian Trafialek
 * @version 1.0
 */
public class FolderViewModel extends ViewModel {
    private StringProperty folderTitleProperty;
    private StringProperty errorProperty;
    private Gson gson;
    private Model model;
    private ViewState viewState;

    /**
     * 2-argument constructor 
     * accepts the model and viewstate objects as arguments, and initialises the rest of the bound properties
     * along with a Gson interpreter.
     * 
     * @param model for the model layer of MVVM, which communicates with the server
     *        
     * @param viewState to store information when swapping screens
     *        
     */
    public FolderViewModel(Model model, ViewState viewState){
        this.model=model;
        this.viewState = viewState;
        folderTitleProperty = new SimpleStringProperty();
        errorProperty = new SimpleStringProperty();
        gson = new Gson();
    }

    /**
     * 
     * getter for the folder title property
     *
     * @return StringProperty to represent the title of the folder
     *        
     */
    public StringProperty getFolderTitleProperty(){
        return folderTitleProperty;
    }

    /**
     * 
     * getter for the error property
     *
     * @return StringProperty to represent the error label text
     *        
     */
    public StringProperty ErrorProperty(){
        return errorProperty;
    }

    private void loadExercises(){
        String username = viewState.getUsername();
        String folderName = viewState.getFolderName();
        ExerciseList exercises = model.getExerciseList(viewState.getFolderId());
        folderTitleProperty.set(gson.toJson(exercises));
    }

    /**
     * method that requests to the server to remove an exercise from the current user's folder
     * 
     * @param exerciseId int ID which represents the exercise's ID
     *        
     *
     * @return boolean to represent success/failure of the request
     *        
     */
    public boolean removeExercise(int exerciseId){
        return model.removeExercise(exerciseId);
    }

    @Override
    /**
     * called upon screen refresh, and requests updated information from the server
     * 
     */
    public void clear() {
        loadExercises();
    }
}
