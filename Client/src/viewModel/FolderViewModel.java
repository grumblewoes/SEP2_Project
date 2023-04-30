package viewModel;

import com.google.gson.Gson;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import modelClient.Model;

import java.util.ArrayList;

public class FolderViewModel extends ViewModel {
    private StringProperty folderTitleProperty;
    private StringProperty errorProperty;
    private Gson gson;
    private Model model;
    private ViewState viewState;

    public FolderViewModel(Model model, ViewState viewState){
        this.model=model;
        this.viewState = viewState;
        folderTitleProperty = new SimpleStringProperty();
        errorProperty = new SimpleStringProperty();
        gson = new Gson();
    }

    public StringProperty getFolderTitleProperty(){
        return folderTitleProperty;
    }

    public StringProperty ErrorProperty(){
        return errorProperty;
    }

    private void loadExercises(){
        String username = viewState.getUsername();
        String folderName = viewState.getFolderName();
        ArrayList<String> exercises = model.getExerciseList(username);
        folderTitleProperty.set(gson.toJson(exercises));
    }

    public boolean removeExercise(String name){
        String username = viewState.getUsername();
        String folderName = viewState.getFolderName();
        return model.removeExercise(username,folderName,name);
    }

    @Override
    public void clear() {
        loadExercises();
    }
}
