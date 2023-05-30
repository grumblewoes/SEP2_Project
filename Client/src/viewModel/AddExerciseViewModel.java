package viewModel;


import com.google.gson.Gson;
import javafx.beans.property.*;
import modelClient.Model;

import java.util.ArrayList;

/**
 * The viewmodel class for the AddExerciseViewController class.
 *
 * @author Damian Trafialek
 * @version 1.0
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
     * Properties for each of the info fields are initialized, and viewState is passed so info
     * can be displayed.
     * 
     * @param model from the model layer
     *        
     * @param viewState stores info from previous screen
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
     * returns the errorLabel text
     *
     * @return StringProperty for the errorLabel
     *        
     */
    public StringProperty getErrorProperty(){
        return errorProperty;
    }
    /**
     * 
     * returns the number of repetitions for that specific exercise
     *
     * @return IntegerProperty for the repetitions
     *        
     */
    public IntegerProperty getRepetitionsProperty(){
        return repetitionsProperty;
    }
    /**
     * 
     * returns the different available exercises to add.
     *
     * @return StringProperty for the list of exercises
     *        
     */
    public StringProperty getExerciseListProperty(){ return exerciseListProperty;    }
    /**
     * 
     * returns the name of the user-inputted exercise
     *
     * @return StringProperty for that specific exercise
     *        
     */
    public StringProperty getExerciseProperty(){ return exerciseProperty;    }
    /**
     * 
     * returns the weight for the user-inputted weight
     *
     * @return IntegerProperty for the amount of weight chosen
     *        
     */
    public IntegerProperty getWeightProperty(){
        return weightProperty;
    }
    /**
     * 
     * returns whether the exercise field is editable
     *
     * @return BooleanProperty for the editable field
     *        
     */
    public BooleanProperty getEditableProperty(){ return editableProperty; }
    /**
     * 
     * returns the go back string
     *
     * @return StringProperty that is bound to the view controller
     *        
     */
    public StringProperty getGoBackProperty(){ return goBackProperty; }

    /**
     * 
     * returns true or false depending on whether the exercise was successfully added to the user's folder
     *
     * @return boolean for success/failure
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
     * refreshes the screen upon initial load and when the screen is accessed by the user
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
