package viewModel;


import com.google.gson.Gson;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import modelClient.Model;

import java.util.ArrayList;

public class AddExerciseViewModel extends ViewModel {
    private StringProperty repetitionsProperty;
    private StringProperty weightProperty;
    private StringProperty exerciseListProperty;
    private StringProperty errorProperty;
    private Gson gson;
    private Model model;
    private ViewState viewState;

    public AddExerciseViewModel(Model model, ViewState viewState){
        this.model=model;
        this.viewState = viewState;
        repetitionsProperty = new SimpleStringProperty();
        weightProperty = new SimpleStringProperty();
        exerciseListProperty = new SimpleStringProperty();
        gson = new Gson();
    }

    public StringProperty getErrorProperty(){
        return errorProperty;
    }
    public StringProperty getRepetitionsProperty(){
        return repetitionsProperty;
    }
    public StringProperty getWeightProperty(){
        return weightProperty;
    }

    public boolean addExercise(String exerciseName){
        String username = viewState.getUsername();
        String folderName = viewState.getFolderName();
        int weight = Integer.parseInt(weightProperty.get());
        int repetitions = Integer.parseInt(repetitionsProperty.get());
        return model.addExercise(username,folderName,exerciseName,weight,repetitions);
    }

    @Override
    public void clear() {
        loadExercisesList();
    }

    private void loadExercisesList(){
        ArrayList<String> exercisesListToChooseFrom = model.getPossibleExercises();
        exerciseListProperty.set(gson.toJson(exercisesListToChooseFrom));
    }


}
