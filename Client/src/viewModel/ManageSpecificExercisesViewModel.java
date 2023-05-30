package viewModel;

import com.google.gson.Gson;
import javafx.beans.property.*;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import mediator.Exercise;
import mediator.ExerciseList;
import modelClient.Model;
import util.Logger;

import java.net.DatagramSocket;

/**
 * 
 * ViewModel class for the ManageSpecificExercisesViewController class.
 * 
 * @author Damian Trafialek
 * @version 1.0
 */
public class ManageSpecificExercisesViewModel extends ViewModel{
    private Model model;
    private ViewState viewState;
    private Gson gson;
    private StringProperty usernameProperty,exercisesListProperty,errorProperty,exerciseNameProperty,folderNameProperty;
    private BooleanProperty isSpecificProperty;
    private ObjectProperty<LineChart<?, ?>> lineChartProperty;

    /**
     * 2-argument constructor 
     * accepts the model and viewstate objects as arguments, and initialises the rest of the
     * bound properties, along with a Gson interpreter
     * 
     * @param model for the model layer of MVVM, which communicates with the server
     *        
     * @param viewState to store information when switcing screens
     *        
     */
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
        lineChartProperty = new SimpleObjectProperty<>();
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
    /**
     * refreshes the screen upon controller initialisation and screen swap,
     * and requests updated exercise information from the server to update the
     * progress graph
     */
    public void clear() {
        exerciseNameProperty.set(viewState.getExerciseName());
        usernameProperty.set(viewState.getUsername());
        folderNameProperty.set(viewState.getFolderName());
        updateLineChart(viewState.getExerciseName());
        populateExercises();
    }

    /**
     * method that prepares to switch to the screen to modify a selected exercise
     * 
     * @param name String for the name of the exercise
     *        
     */
    public void setupOpenExercisesByName(String name){
        isSpecificProperty.set(true);
        viewState.setExerciseName(name);
    }
    /**
     * 
     * method that prepares the viewstate to display text unique to the previous screen
     * upon pressing the back button
     *
     * @return String for the text value
     *        
     */
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

    /**
     * 
     * getter for the username property
     *
     * @return StringProperty that contains the name
     *        
     */
    public StringProperty getUsernameProperty() {
        return usernameProperty;
    }

    /**
     * 
     * getter for the line chart property
     *
     * @return ObjectProperty that contains the line chart
     *        
     */
    public ObjectProperty getLineChartProperty() {
        return lineChartProperty;
    }

    /**
     * 
     * getter for the JSON string that has the list of exercises from the server
     *
     * @return StringProperty that contains the list of exercises
     *        
     */
    public StringProperty getExercisesListProperty() {
        return exercisesListProperty;
    }


    /**
     * 
     * getter for the error property
     *
     * @return StringProperty for the error text
     *        
     */
    public StringProperty getErrorProperty() {
        return errorProperty;
    }
    /**
     * 
     * getter for the exercise name property
     *
     * @return StringProperty that contains the name of the specified exercise
     *        
     */
    public StringProperty getExerciseNameProperty() {
        return exerciseNameProperty;
    }
    /**
     * 
     * getter for the folder name property
     *
     * @return StringProperty for the name of the folder
     *        
     */
    public StringProperty getFolderNameProperty() { return folderNameProperty; }
    /**
     * 
     * getter for the isSpecificProperty
     *
     * @return BooleanProperty that contains whether an exercise is selected or not
     *        
     */
    public BooleanProperty getIsSpecificProperty() { return isSpecificProperty; }


    /**
     * method that requests to the server to remove a specific exercise from the trainee's
     * folder by its ID
     * 
     * @param exerciseId integer for the ID of the exercise
     *        
     */
    public void removeExercise(int exerciseId) {
        model.removeExercise(exerciseId);
    }
    /**
     * method that requests to the server to remove a specific exercise from the trainee's
     * folder by its name
     * 
     * @param name String to represent the name of the exercise
     *        
     */
    public void removeExercisesByName(String name) {
        model.removeExercisesByName(name,viewState.getFolderId());
        updateLineChart(name);
    }
    /**
     * method that prepares the viewstate so that upon switching to the "Add Exercise"
     * screen, the back button will display the correct text
     * 
     */
    public void setupAddExercise() {
        if(isSpecificProperty.get())
            viewState.setGoBack("specificExercises");
        else{
            viewState.setGoBack("manageExercises");
            viewState.setExerciseName(null);
        }

    }

    /**
     * method that prepares the viewstate so that upon switching to the "Profile"
     * screen, the back button will display the correct text and the profile
     * will display the correct username
     * 
     */
    public void setupProfile() {
        viewState.setGoBack("manageExercises");
        viewState.setProfileUsername(viewState.getUsername());
    }


    /**
     * method that sets the properties for the displayed line chart showing workout progress
     * 
     */
    public void setLineChart(LineChart<Number, Number> lineChart) {
        lineChartProperty.set(lineChart);
    }

    /**
     * method that adds a new entry into the line chart depending on the exercise. A request
     * is made to the server to fetch information on the exercise's history, and updates it
     * accordingly
     * 
     * @param exerciseName String for the name of the exercise
     *        
     */
    public void updateLineChart(String exerciseName)
    {
        LineChart<Number, Number> chart = (LineChart<Number, Number>) lineChartProperty.get();
        if (chart != null)
        {
            chart.getData().clear();

            ExerciseList exerciseList = model.getExerciseListByNameAndFolderId(exerciseName, viewState.getFolderId());
            XYChart.Series<Number, Number> series = new XYChart.Series<>();
            for (int i = 0; i < exerciseList.size(); i++)
            {
                Exercise exercise = exerciseList.get(i);
                series.getData().add(new XYChart.Data<>(exercise.getRepetitions(), exercise.getWeight()));
            }

            chart.getData().addAll(series);
        }
    }

}
