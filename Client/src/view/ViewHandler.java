package view;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import viewModel.ViewModel;
import viewModel.ViewModelFactory;

public class ViewHandler {

	private Stage primaryStage;

	private Scene currentScene;

	private Region root;

	private CreateUserViewController createUserViewController;

	private ViewModelFactory viewModelFactory;

	private ViewController viewController;

	public ViewHandler(ViewModelFactory viewFactory) {
		this.viewModelFactory = viewFactory;
		currentScene = new Scene(new Region());
	}

	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		openView("createProfile");
	}

	public void openView(String id) {
		Region root = null;

		switch (id)
		{
			case "createProfile":
				root = loadCreateProfileView("createProfile.fxml", viewController, viewModelFactory.getCreateUserViewModel());
				break;
		}
		currentScene.setRoot(root);
		String title = "Valhalla Fitness - ";
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

	public Region loadCreateProfileView(String fxmlFile, ViewController viewController, ViewModel viewModel) {
		if (createUserViewController == null)
		{
			try
			{
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource(fxmlFile));
				Region root = loader.load();
				createUserViewController = loader.getController();
				createUserViewController
						.init(this, viewModelFactory.getCreateUserViewModel(), root);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			createUserViewController.reset();
		}
		return createUserViewController.getRoot();
	}

}
