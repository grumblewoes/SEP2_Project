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

public class ManageSpecificExercisesViewModel extends ViewModel{
    private Model model;
    private ViewState viewState;
    private Gson gson;
    private StringProperty usernameProperty,exercisesListProperty,errorProperty,exerciseNameProperty,folderNameProperty;
    private BooleanProperty isSpecificProperty;
    private ObjectProperty<LineChart<?, ?>> lineChartProperty;

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
    public void clear() {
        exerciseNameProperty.set(viewState.getExerciseName());
        usernameProperty.set(viewState.getUsername());
        folderNameProperty.set(viewState.getFolderName());
        updateLineChart(viewState.getExerciseName());
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

    public ObjectProperty getLineChartProperty() {
        return lineChartProperty;
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
        updateLineChart(name);
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


    public void setLineChart(LineChart<Number, Number> lineChart) {
        lineChartProperty.set(lineChart);
    }

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
