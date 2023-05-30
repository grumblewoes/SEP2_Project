package view;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import viewModel.ViewModel;
import viewModel.ViewModelFactory;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

/**
 * A class responsible for managing the views and switching them.
 * 
 * 
 * @author Damian Trafiałek, Jakub Cerovsky
 * @version 1.0
 */
public class ViewHandler {

	private Stage primaryStage;

	private Scene currentScene;

	private Region root;

//	abstract factory
	private ViewController createUserViewController;
	private ViewController logInViewController;
	private ViewController homeViewController;
	private ViewController manageFolderViewController;
	private ViewController manageExercisesViewController;
	private ViewController manageSpecificExercisesViewController;
	private ViewController addExerciseViewController;
	private ViewController profileViewController;
	private ViewController addFriendViewController;
	//private ViewController manageCoachViewController;
	private ViewController editRosterViewController;
	private ViewController addMeetingViewController;
	private ViewController leaderboardViewController;

	private ViewModelFactory viewModelFactory;




/**
 * 1-argument constructor that sets all instance variables and initialises the current scene.
 * 
 * 
 * @param viewModelFactory - class that creates all view models
 *        
 */
	public ViewHandler(ViewModelFactory viewModelFactory) {
		this.viewModelFactory = viewModelFactory;
		this.currentScene = new Scene(new Region());
	}

/**
 * Method that runs start the view.
 * 
 * @param primaryStage - primary stage of the view
 *        
 */
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		createCustomTitleBar();
		openView("logIn");
	}

/**
 * Method that opens certain view.
 * 
 * @param id - unique identifier of the view.
 *        
 */
	public void openView(String id) {
		switch (id)
		{
			/***
			 * Abstract factory - a pattern that creates one superior class (factory) that is abstract and other classes should extend/implement all its methods
			 * Factory method - a pattern that allows to create similar objects of the same class with some automation
			 * Factory is producing objects
			 *
			 * Why here the abstract factory is not a good solution? Patterns should simplify some actions or make executing them easier or more understandable
			 * In this case we would have as many factories as controllers so it doesnt make sense - doesnt not simplify anything. Products are of the same type - viewController
			 *
			 * Why factory method is not the best idea - products that are being produced are of the same type but they must be connected with unique viewModel.
			 * each of view models extend viewModel class but they are unique as well. There would have to be a logic somewhere that would assign a correct viewModel to correct viewController.
			 * So even though the first part would simplify the code the second part makes it impossible to write less code.
			 *
			 *
			 * Why multiton might be a good idea, there all are of the same type so it would simplify as well.
			 * The issue is with viewModels that are different for each viewController -> there would have to be some logic deciding which one is a correct one
			 *
			 * CONCLUSION:
			 * It is impossible to simplify the code using any of patterns listed above. Since they won't simplify abything there is no use of using them.
			 *
			 */
			case "xxx":
				loadViewController("xxx");
				break;

			case "createProfile":
				createUserViewController = loadViewController("createProfile.fxml", createUserViewController, viewModelFactory.getCreateUserViewModel());
				break;
			case "logIn":
				logInViewController = loadViewController("login.fxml", logInViewController, viewModelFactory.getLoginViewModel());
				break;
			case "home":
				homeViewController = loadViewController("homeView.fxml", homeViewController, viewModelFactory.getHomeViewModel());
				break;
			case "manageFolder":
				manageFolderViewController = loadViewController("manageFolderView.fxml", manageFolderViewController, viewModelFactory.getManageFolderViewModel());
				break;
//			case "manageExercises":
//				manageExercisesViewController = loadViewController("manageExercisesView.fxml", manageExercisesViewController, viewModelFactory.getManageExercisesViewModel());
//				break;
			case "manageExercises","specificExercises":
				manageSpecificExercisesViewController = loadViewController("manageSpecificExercisesView.fxml", manageSpecificExercisesViewController, viewModelFactory.getManageSpecificExercisesViewModel());
				break;
			case "addExercise":
				addExerciseViewController = loadViewController("addExerciseView.fxml", addExerciseViewController, viewModelFactory.getAddExerciseViewModel());
				break;
			case "profile":
				profileViewController = loadViewController("profileView.fxml", profileViewController, viewModelFactory.getProfileViewModel());
				break;
			case "manageRoster":
				editRosterViewController = loadViewController("manageRosterView.fxml", editRosterViewController, viewModelFactory.getEditRosterViewModel());
				break;

			case "addMeeting":
				addMeetingViewController =loadViewController("addMeeting.fxml", addMeetingViewController,
						viewModelFactory.getAddMeetingViewModel());
				break;
			case "addFriend":
				addFriendViewController =loadViewController("sendFriendRequest.fxml", addFriendViewController,
						viewModelFactory.getAddFriendViewModel());
				break;
		}
		currentScene.setRoot(root);
		String title = "";
		if (root.getUserData() != null)
		{
			title += root.getUserData();
		}

		primaryStage.setTitle(title);
		primaryStage.setScene(currentScene);
		primaryStage.setWidth(root.getPrefWidth());
		primaryStage.setHeight(root.getPrefHeight());
		primaryStage.show();
		primaryStage.getIcons().add(0,new Image("/assets/ValhallaFitness.png"));
	}
	private void loadViewController(String fxmlFileName){

	}
	private ViewController loadViewController(String fxmlFile, ViewController viewController, ViewModel viewModel) {
		if (viewController == null)
		{
			try
			{
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource(fxmlFile));
				this.root = loader.load();
				viewController = loader.getController();
				viewController
						.init(this,viewModel, this.root);
				viewController.reset();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			root=viewController.getRoot();

			viewController.reset();
		}
		return viewController;
	}

	private void createCustomTitleBar(){

//  		custom title bar here


	}

/**
 * Method that open leaderboard view.
 * 
 */
	public void openLeaderboardView() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("leaderboard.fxml"));
		LeaderboardViewController popupController = null;
		try {
			Parent layout = loader.load();
			Scene scene = new Scene(layout);
			popupController = loader.getController(); // Get the controller instance from the FXMLLoader
			popupController.init(this, viewModelFactory.getLeaderboardViewModel(), root);
			Stage popupStage = new Stage();
			popupController.setStage(popupStage);
			popupStage.initModality(Modality.WINDOW_MODAL);
			popupStage.setScene(scene);
			popupStage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
			new Alert(Alert.AlertType.ERROR, "There was an error trying to load the popup fxml file.").show();
		}
	}


}
