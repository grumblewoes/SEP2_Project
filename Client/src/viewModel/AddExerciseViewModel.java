package viewModel;


import com.google.gson.Gson;
import javafx.beans.property.*;
import modelClient.Model;

import java.util.ArrayList;

/**
 * 
 * 
 * 
 * @author 
 * @version 
 */
public class AddExerciseViewModel extends ViewModel {
    private IntegerProperty repetitionsProperty;
    private IntegerProperty weightProperty;
    private StringProperty exerciseListProperty, exerciseProperty, goBackProperty;
    private StringProperty errorProperty;
    private BooleanProperty editableProperty;
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
    public AddExerciseViewModel(Model model, ViewState viewState){
        this.model=model;
        this.viewState = viewState;
        repetitionsProperty = new SimpleIntegerProperty();
        weightProperty = new SimpleIntegerProperty();
        exerciseListProperty = new SimpleStringProperty();
        exerciseProperty = new SimpleStringProperty();
        editableProperty = new SimpleBooleanProperty(false);
        errorProperty = new SimpleStringProperty();
        goBackProperty = new SimpleStringProperty();
        gson = new Gson();
    }

    /**
     * 
     * 
     *
     * @return 
     *        
     */
    public StringProperty getErrorProperty(){
        return errorProperty;
    }
    /**
     * 
     * 
     *
     * @return 
     *        
     */
    public IntegerProperty getRepetitionsProperty(){
        return repetitionsProperty;
    }
    /**
     * 
     * 
     *
     * @return 
     *        
     */
    public StringProperty getExerciseListProperty(){ return exerciseListProperty;    }
    /**
     * 
     * 
     *
     * @return 
     *        
     */
    public StringProperty getExerciseProperty(){ return exerciseProperty;    }
    /**
     * 
     * 
     *
     * @return 
     *        
     */
    public IntegerProperty getWeightProperty(){
        return weightProperty;
    }
    /**
     * 
     * 
     *
     * @return 
     *        
     */
    public BooleanProperty getEditableProperty(){ return editableProperty; }
    /**
     * 
     * 
     *
     * @return 
     *        
     */
    public StringProperty getGoBackProperty(){ return goBackProperty; }

    /**
     * 
     * 
     *
     * @return 
     *        
     */
    public boolean addExercise(){
        String username = viewState.getUsername();
        int folderId = viewState.getFolderId();
        int weight = weightProperty.get();
        int repetitions = repetitionsProperty.get();
        String exerciseName = exerciseProperty.get();

//        make validation here that checks if the exercise name is allowed (is in exercisePropertyList)

        boolean success = model.addExercise(username,exerciseName,folderId,weight,repetitions);
        if(!success)
            errorProperty.set("Failed to add an exercise -> check validity of the input!");

        return success;
    }

    @Override
    /**
     * 
     * 
     */
    public void clear() {

        loadExercisesList();
        String exerciseName = viewState.getExerciseName();
        editableProperty.set( exerciseName==null );
        goBackProperty.set(viewState.getGoBack());


        exerciseProperty.set(exerciseName);
        repetitionsProperty.set(0);
        weightProperty.set(0);
        errorProperty.set("");

    }

    private void loadExercisesList(){
        ArrayList<String> exercisesListToChooseFrom = model.getPossibleExercises();
        exerciseListProperty.set(gson.toJson(exercisesListToChooseFrom));
    }


}
