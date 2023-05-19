package view;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import viewModel.ViewModel;
import viewModel.ViewModelFactory;

import java.util.concurrent.atomic.AtomicReference;

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

	private ViewModelFactory viewModelFactory;




	public ViewHandler(ViewModelFactory viewModelFactory) {
		this.viewModelFactory = viewModelFactory;
		this.currentScene = new Scene(new Region());
	}

	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		createCustomTitleBar();
		openView("logIn");
	}

	public void openView(String id) {
		switch (id)
		{
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
}
