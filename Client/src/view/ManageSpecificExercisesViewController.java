package view;

import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import mediator.ExerciseList;
import util.Logger;
import viewModel.ManageSpecificExercisesViewModel;
import viewModel.ViewModel;

import java.util.HashSet;
import java.util.Set;

public class ManageSpecificExercisesViewController extends ViewController
{
  @FXML private Label usernameLabel,folderNameLabel,exerciseNameLabel,errorLabel;
  @FXML private VBox exerciseBox;
  @FXML private Button addBtn,backBtn;

  @FXML private NumberAxis x;
  @FXML private NumberAxis y;
  @FXML private LineChart<?, ?> lineChart;


  private ManageSpecificExercisesViewModel exViewModel;
  private Gson gson;
  private boolean isSpecific;

  @Override public void init(ViewHandler viewHandler, ViewModel viewModel, Region root)
  {
    this.viewHandler = viewHandler;
    this.exViewModel = (ManageSpecificExercisesViewModel) viewModel;
    this.root = root;
    this.gson = new Gson();
    this.isSpecific=false;


    lineChart.setVisible(false);
    lineChart.setTitle("Exercise Progress");
    lineChart.setCreateSymbols(false);
    lineChart.setLegendVisible(false);
    exViewModel.setLineChart((LineChart<Number, Number>) lineChart);
    x.setLabel("Repetitions");
    y.setLabel("Weight");


    usernameLabel.textProperty().bind(exViewModel.getUsernameProperty() );
    exerciseNameLabel.textProperty().bind(exViewModel.getExerciseNameProperty());
    errorLabel.textProperty().bind(exViewModel.getErrorProperty());
    exViewModel.getExercisesListProperty().addListener((obs,oldVal,newVal)->{
      populateExercises(newVal);
    });
    folderNameLabel.textProperty().bind(exViewModel.getFolderNameProperty());

    exViewModel.getLineChartProperty().addListener((obs, oldChart, newChart) -> {
      if (newChart != null) {
        exViewModel.updateLineChart(
            String.valueOf(exViewModel.getExerciseNameProperty()));
      }
    });


  }

  private void populateExercises(String exercisesListJson){

    ExerciseList list = gson.fromJson(exercisesListJson, ExerciseList.class);
    if(list==null) return;

    exerciseBox.getChildren().remove(0,exerciseBox.getChildren().size());

    isSpecific = exViewModel.getIsSpecificProperty().get();
    if(isSpecific)
      populateExercisesNamed(list);
    else
      populateCatalogues(list);

  }

  private void populateExercisesNamed(ExerciseList list){
    for(int i=0;i<list.size();++i){
      double weight = list.get(i).getWeight();
      int repetitions = list.get(i).getRepetitions();
      String name = list.get(i).getName();
      int id = list.get(i).getId();
      exerciseBox.getChildren().add( createExerciseComponent(id,name,repetitions,weight));
    }
  }

  private void populateCatalogues(ExerciseList list){
    Set<String> set= new HashSet<>();
    for(int i=0;i<list.size();++i){
      String name = list.get(i).getName();
      set.add(name);
    }

    for(String name : set)
      exerciseBox.getChildren().add( createExerciseCatalogue(name) );
  }

  private void openExercisesByName(String name){
    exViewModel.setupOpenExercisesByName(name);
    viewHandler.openView("specificExercises");

    lineChart.setVisible(true);
    lineChart.setCreateSymbols(true);
    exViewModel.updateLineChart(name);
  }

  @FXML private void addExercise(){
    exViewModel.setupAddExercise();
    viewHandler.openView("addExercise");
  }

  @FXML private void goBack(){

      viewHandler.openView(exViewModel.setupGoBack());
      lineChart.setVisible(false);
  }

  @Override public void reset()
  {
    exViewModel.clear();
  }


  private void removeExercise(int exerciseId){
    exViewModel.removeExercise(exerciseId);
    reset();
  }
  private void removeExercisesByName(String name){
    exViewModel.removeExercisesByName(name);
    exViewModel.updateLineChart(name);
    reset();
  }

  private HBox createExerciseComponent(int exerciseId,String title, int repetitions,double weight){
    Logger.log(exerciseId+" "+title+" " +repetitions+" "+weight);
    HBox hBox = new HBox();
    hBox.getStyleClass().addAll("fs-2","bg-primary");
    hBox.setPadding( new Insets(10,10,10,10));
    VBox v1 = new VBox();
    HBox hhh = new HBox();
    HBox.setHgrow(v1, Priority.ALWAYS);
    v1.setAlignment(Pos.CENTER_LEFT);


    Label label = new Label(title);
    Label reps = new Label(""+repetitions+"reps");
    Label weights = new Label(weight+"kg");


    Button removeBtn = new Button("Remove");
    removeBtn.onActionProperty().setValue((evt)-> removeExercise(exerciseId));
    removeBtn.getStyleClass().addAll("btn-danger");


    int r = 5,l=5;
    HBox.setMargin(removeBtn,new Insets(0,r,0,l));

    hBox.getChildren().add(v1);
    v1.getChildren().add(hhh);
    hhh.getChildren().addAll(label,reps,weights);
    hBox.getChildren().add(removeBtn);

    return hBox;
  }


  private HBox createExerciseCatalogue(String title){
    HBox hBox = new HBox();
    hBox.getStyleClass().addAll("fs-2","bg-primary");
    hBox.setPadding( new Insets(10,10,10,10));
    VBox v1 = new VBox();
    HBox.setHgrow(v1, Priority.ALWAYS);
    v1.setAlignment(Pos.CENTER_LEFT);

    Label label = new Label(title);
    Button openBtn = new Button("Open");
    openBtn.onActionProperty().setValue((evt)-> openExercisesByName(title));
    openBtn.getStyleClass().addAll("btn-success");

    Button removeBtn = new Button("Remove");
    removeBtn.onActionProperty().setValue((evt)-> removeExercisesByName(title));
    removeBtn.getStyleClass().addAll("btn-danger");

    int r = 5,l=5;
    HBox.setMargin(openBtn,new Insets(0,r,0,l));
    HBox.setMargin(removeBtn,new Insets(0,r,0,l));

    hBox.getChildren().add(v1);
    v1.getChildren().add(label);
    hBox.getChildren().add(openBtn);
    hBox.getChildren().add(removeBtn);

    return hBox;
  }

  @FXML private void usernameLabelClicked(){
    exViewModel.setupProfile();
    viewHandler.openView("profile");
  }

}
