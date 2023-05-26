package viewModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mediator.Trainee;
import mediator.TraineeList;
import mediator.User;
import modelClient.Model;
import util.Logger;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.LocalListener;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

/**
 * 
 * 
 * 
 * @author 
 * @version 
 */
public class LeaderboardViewModel extends ViewModel  implements LocalListener<String,String>
{
  private ObservableList<SimpleLeaderboardViewModel> squats;
  private ObservableList<SimpleLeaderboardViewModel> deadlifts;
  private ObservableList<SimpleLeaderboardViewModel> benches;
  private Model model;

  /**
   * 1-argument constructor 
   * 
   * 
   * @param model 
   *        
   */
  public LeaderboardViewModel(Model model) {
    this.model=model;
    squats = FXCollections.observableArrayList();
    deadlifts = FXCollections.observableArrayList();
    benches = FXCollections.observableArrayList();

    model.addListener(this);
  }

  /**
   * 
   * 
   *
   * @return 
   *        
   */
  public ObservableList<SimpleLeaderboardViewModel> getSquats() {
    return squats;
  }

  /**
   * 
   * 
   *
   * @return 
   *        
   */
  public ObservableList<SimpleLeaderboardViewModel> getBenches() {
    return benches;
  }

  /**
   * 
   * 
   *
   * @return 
   *        
   */
  public ObservableList<SimpleLeaderboardViewModel> getDeadlifts() {
    return deadlifts;
  }

  private void loadSquatLeaders(){
    squats.clear();
    TraineeList squatList = model.getSquatLeaders();
    ArrayList<Trainee> squatLeaders = squatList.getList();
    for( Trainee trainee :  squatLeaders){
      squats.add( new SimpleLeaderboardViewModel(trainee) );
    }
  }

  private void loadDeadliftLeaders(){
    deadlifts.clear();
    TraineeList deadliftList = model.getDeadliftLeaders();
    ArrayList<Trainee> deadliftLeaders = deadliftList.getList();
    for( Trainee trainee :  deadliftLeaders){
      deadlifts.add( new SimpleLeaderboardViewModel(trainee) );
    }
  }

  private void loadBenchLeaders(){
    benches.clear();
    TraineeList benchList = model.getBenchLeaders();
    ArrayList<Trainee> benchLeaders = benchList.getList();
    for( Trainee trainee :  benchLeaders){
      benches.add( new SimpleLeaderboardViewModel(trainee) );
    }
  }
  /**
   * 
   * 
   */
  @Override public void clear()
  {
    loadSquatLeaders();
    loadDeadliftLeaders();
    loadBenchLeaders();
  }


  /**
   * 
   * 
   */
  @Override public void propertyChange(ObserverEvent<String, String> event)
  {
    loadSquatLeaders();
    loadDeadliftLeaders();
    loadBenchLeaders();
  }
}
