package viewModel;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import jdk.jshell.spi.ExecutionControl;
import modelClient.Model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * 
 * ViewModel class for the CreateUserViewController class.
 * 
 * @author Damian Trafialek
 * @version 1.0
 */
public class CreateUserViewModel extends ViewModel implements
		PropertyChangeListener
{

	private StringProperty firstNameProperty;

	private StringProperty lastNameProperty;

	private StringProperty userNameProperty;

	private StringProperty passwordProperty;

	private IntegerProperty heightProperty;

	private IntegerProperty weightProperty;

	private StringProperty errorLabel;

	private Model model;

/**
 * 1-argument constructor 
 * accepts the model object as an argument, and initializes all the bound fields in the view controller to default values.
 * 
 * @param model for the model layer of MVVM, which communicates with the server
 *        
 */
	public CreateUserViewModel(Model model) {
		this.model=model;
		this.firstNameProperty=new SimpleStringProperty("");
		this.lastNameProperty = new SimpleStringProperty("");
		this.userNameProperty=new SimpleStringProperty("");
		this.passwordProperty=new SimpleStringProperty("");
		this.heightProperty=new SimpleIntegerProperty(0);
		this.weightProperty=new SimpleIntegerProperty(0);
		this.errorLabel = new SimpleStringProperty("");
		//model.addListener(null, this);
	}

/**
 * 
 * method that requests the server to create a new user, passing in their personal information.
 *
 * @return boolean value to signify success/failure of the request
 *        
 */
	public boolean createUser() {
		boolean userCreated = model.createUser(getFirstNameProperty().get(), getLastNameProperty().get(), getUserNameProperty().get(),
				getPasswordProperty().get(), getHeightProperty().get(), getWeightProperty().get());
		if (userCreated)
		{
			return true;
		}
		else
		{
			errorLabel.set("Invalid input.");
			return false;
		}
	}

	@Override
/**
 * method to refresh the screen and the displayed values upon controller initialisation
 * and screen swap
 * 
 */
	public void clear(){
		Platform.runLater(() -> {
			errorLabel.set("");
			firstNameProperty.set("");
			lastNameProperty.set("");
			userNameProperty.set("");
			passwordProperty.set("");
			weightProperty.set(0);
			heightProperty.set(0);
		});
	}

  @Override
	public void propertyChange(PropertyChangeEvent evt) {
//		Platform.runLater(() -> {
//			switch (evt.getPropertyName())
//			{
//				case "success":
//					errorLabel.set((String) evt.getNewValue());
//					break;
//				case "fail":
//					errorLabel.set((String) evt.getNewValue());
//					break;
//			}
//		});
	}

/**
 * 
 * getter for the first name property
 *
 * @return StringProperty for the first name text
 *        
 */
	public StringProperty getFirstNameProperty() {
		return firstNameProperty;
	}

/**
 * 
 * getter for the last name property
 *
 * @return StringProperty for the last name text
 *        
 */
	public StringProperty getLastNameProperty() {
		return lastNameProperty;
	}

/**
 * 
 * getter for the username property
 *
 * @return StringProperty for the username text
 *        
 */
	public StringProperty getUserNameProperty() {
		return userNameProperty;
	}

/**
 * 
 * getter for the password property
 *
 * @return StringProperty for the password
 *        
 */
	public StringProperty getPasswordProperty() {
		return passwordProperty;
	}

/**
 * 
 * getter for the height property
 *
 * @return IntegerProperty for the height text
 *        
 */
	public IntegerProperty getHeightProperty() {
		return heightProperty;
	}

/**
 * 
 * getter for the weight property
 *
 * @return IntegerProperty for the weight text
 *        
 */
	public IntegerProperty getWeightProperty() {
		return weightProperty;
	}

/**
 * 
 * getter for the error property
 *
 * @return StringProperty for the error text
 *        
 */
	public StringProperty getErrorLabel() {
		return errorLabel;
	}
}
