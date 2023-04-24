package view;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import viewModel.CreateUserViewModel;
import viewModel.ViewModel;
import viewModel.ViewModelFactory;

public class ViewHandler {

	private Stage primaryStage;

	private Scene currentScene;

	private Region root;

	private ViewController createUserViewController;
	private ViewController logInViewController;

	private ViewModelFactory viewModelFactory;

	private ViewController viewController;

	public ViewHandler(ViewModelFactory viewModelFactory) {
		this.viewModelFactory = viewModelFactory;
		currentScene = new Scene(new Region());
	}

	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
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
	}

	public ViewController loadViewController(String fxmlFile, ViewController viewController, ViewModel viewModel) {
		if (viewController == null)
		{
			try
			{
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource(fxmlFile));
				root = loader.load();
				viewController = loader.getController();
				viewController
						.init(this, viewModel, root);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			viewController.reset();
		}
		return viewController;
	}

}
