package viewModel;

import com.google.gson.Gson;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import mediator.ExerciseList;
import modelClient.Model;

import java.util.ArrayList;

/**
 * 
 * 
 * 
 * @author 
 * @version 
 */
public class FolderViewModel extends ViewModel {
    private StringProperty folderTitleProperty;
    private StringProperty errorProperty;
    private Gson gson;
    private Model model;
    private ViewState viewState;

    /**
     * 2-argument constructor 
     * 
     * 
     * @param model 
     *        
     * @param viewState 
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
     * 
     *
     * @return 
     *        
     */
    public StringProperty getFolderTitleProperty(){
        return folderTitleProperty;
    }

    /**
     * 
     * 
     *
     * @return 
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
     * 
     * 
     * @param exerciseId 
     *        
     *
     * @return 
     *        
     */
    public boolean removeExercise(int exerciseId){
        return model.removeExercise(exerciseId);
    }

    @Override
    /**
     * 
     * 
     */
    public void clear() {
        loadExercises();
    }
}
