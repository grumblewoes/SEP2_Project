package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import viewModel.CreateUserViewModel;
import viewModel.ViewModel;

public class CreateUserViewController extends ViewController
{

	@FXML
	private TextField firstName, lastName, username, password, height, weight;

	@FXML
	private Label errorLabel;

	private ViewHandler viewHandler;

	private CreateUserViewModel createUserViewModel;

	private IntStringConverter converter;

	public CreateUserViewController() {
		converter = new IntStringConverter();
	}

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

	@Override public void init(ViewHandler viewHandler,
			CreateUserViewModel createUserViewModel, Region root)
	{

	}

	public void reset() {

	}

	private void submitButton() {

	}

	private void cancelButton() {

	}

}
