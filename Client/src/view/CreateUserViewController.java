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
 * 
 * 
 * 
 * @author 
 * @version 
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

/**
 * 0-argument constructor 
 * 
 * 
 */
	public CreateUserViewController() {
		converter = new IntStringConverter();
	}

	@Override
/**
 * 
 * 
 * @param viewHandler 
 *        
 * @param viewModel 
 *        
 * @param root 
 *        
 */
	public void init(ViewHandler viewHandler, ViewModel viewModel, Region root) {
		this.viewHandler = viewHandler;
		createUserViewModel = (CreateUserViewModel) viewModel;
		this.root = root;

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
 * 
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
