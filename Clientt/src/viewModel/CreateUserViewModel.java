package viewModel;

.Model;

public class CreateUserViewModel extends ViewModel implements PropertyChangeListener {

	private StringProperty firstNameProperty;

	private StringProperty lastNameProperty;

	private StringProperty userNameProperty;

	private StringProperty passwordProperty;

	private IntegerProperty heightProperty;

	private IntegerProperty weightProperty;

	private StringProperty errorLabel;

	private Model model;

	public CreateUserViewModel(Model model) {

	}

	public boolean createUser() {
		return false;
	}

	public StringProperty getFirstNameProperty() {
		return null;
	}

	public StringProperty getLastNameProperty() {
		return null;
	}

	public StringProperty getUserNameProperty() {
		return null;
	}

	public StringProperty getPasswordProperty() {
		return null;
	}

	public IntegerProperty getHeightProperty() {
		return null;
	}

	public IntegerProperty getWeightProperty() {
		return null;
	}

	public StringProperty getErrorLabel() {
		return null;
	}


	/**
	 * @see PropertyChangeListener#propertyChange(PropertyChangeEvent)
	 *  
	 */
	private void propertyChange(PropertyChangeEvent evt) {

	}

}
