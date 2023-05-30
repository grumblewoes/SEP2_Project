package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import util.Logger;
import viewModel.CreateUserViewModel;
import viewModel.ViewModel;

/**
 * View controller responsible for displaying the create user page.
 *
 * @author Damian Trafiałek
 * @version 1.0
 */
public class CreateUserViewController extends ViewController
{

	@FXML
	private TextField firstName, lastName, username, height, weight;
	@FXML private PasswordField password;


	@FXML
	private Label errorLabel;

	private ViewHandler viewHandler;

	private CreateUserViewModel createUserViewModel;

	private IntStringConverter converter;



	@Override
/**
 * Method that initialise the controller and sets up all instance variables and bindings.
 *
 * @param viewHandler - handles changing views
 *
 * @param viewModel - view model related to the controller
 *
 * @param root - region that is being displayed
 *
 */
	public void init(ViewHandler viewHandler, ViewModel viewModel, Region root) {
		this.viewHandler = viewHandler;
		createUserViewModel = (CreateUserViewModel) viewModel;
		this.root = root;

		converter = new IntStringConverter();
		//binding
		firstName.textProperty().bindBidirectional(createUserViewModel.getFirstNameProperty());
		lastName.textProperty().bindBidirectional(createUserViewModel.getLastNameProperty());
		username.textProperty().bindBidirectional(createUserViewModel.getUserNameProperty());
		password.textProperty().bindBidirectional(createUserViewModel.getPasswordProperty());
		errorLabel.textProperty().bind(createUserViewModel.getErrorLabel());

		height.textProperty().bindBidirectional(createUserViewModel.getHeightProperty(), converter);
		weight.textProperty().bindBidirectional(createUserViewModel.getWeightProperty(), converter);

	}

	/**
	 * Reset method that calls view model to trigger the reset.
	 *
	 */
	@Override public void reset() {
		createUserViewModel.clear();
	}

	@FXML private void submitButton() {
		if( createUserViewModel.createUser() )
			viewHandler.openView("logIn");
		//if true, go to next screen
	}

	@FXML private void signIn() {
		Logger.log("lol");
		//to implement later ig
		viewHandler.openView("logIn");
		Logger.log("end");
	}

}
