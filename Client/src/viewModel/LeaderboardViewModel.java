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
 * ViewModel class for the LeaderBoardViewController class.
 * 
 * @author Jakub Cerovsky
 * @version 1.0
 */
public class LeaderboardViewModel extends ViewModel  implements LocalListener<String,String>
{
  private ObservableList<SimpleLeaderboardViewModel> squats;
  private ObservableList<SimpleLeaderboardViewModel> deadlifts;
  private ObservableList<SimpleLeaderboardViewModel> benches;
  private Model model;

  /**
   * 1-argument constructor 
   * accepts the model object, and initializes array lists for each of the three categories
   * for which there is a leaderboard.
   * 
   * @param model for the model layer which communicates with the server
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
   * getter for the arraylist of squats
   *
   * @return ObservableList<SimpleLeaderboardViewModel> to represent the names+weights of the top ranked members for squats
   *        
   */
  public ObservableList<SimpleLeaderboardViewModel> getSquats() {
    return squats;
  }

  /**
   *
   getter for the arraylist of benches
   *
   * @return ObservableList<SimpleLeaderboardViewModel> to represent the names+weights of the top ranked members for bench press
   *        
   */
  public ObservableList<SimpleLeaderboardViewModel> getBenches() {
    return benches;
  }

  /**
   *
   getter for the arraylist of deadlifts
   *
   * @return ObservableList<SimpleLeaderboardViewModel> to represent the names+weights of the top ranked members for deadlifts
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
   * refreshes the screen upon controller initialisation and screen swap
   * 
   */
  @Override public void clear()
  {
    loadSquatLeaders();
    loadDeadliftLeaders();
    loadBenchLeaders();
  }


  /**
   * refreshes data fetched from the server upon property change via observers
   * 
   */
  @Override public void propertyChange(ObserverEvent<String, String> event)
  {
    loadSquatLeaders();
    loadDeadliftLeaders();
    loadBenchLeaders();
  }
}
