package view;
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

	}

	public void start(Stage primaryStage) {

	}

	public void openView(String id) {

	}

	public Region loadViewController(String fxmlFile, ViewController viewController, ViewModel viewModel) {
		return null;
	}

}
