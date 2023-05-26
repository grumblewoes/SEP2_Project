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
 * 
 * 
 * @author 
 * @version 
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
 * 
 * 
 * @param model 
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
 * 
 *
 * @return 
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
 * 
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
/**
 * 
 * 
 * @param evt 
 *        
 */
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
 * 
 *
 * @return 
 *        
 */
	public StringProperty getFirstNameProperty() {
		return firstNameProperty;
	}

/**
 * 
 * 
 *
 * @return 
 *        
 */
	public StringProperty getLastNameProperty() {
		return lastNameProperty;
	}

/**
 * 
 * 
 *
 * @return 
 *        
 */
	public StringProperty getUserNameProperty() {
		return userNameProperty;
	}

/**
 * 
 * 
 *
 * @return 
 *        
 */
	public StringProperty getPasswordProperty() {
		return passwordProperty;
	}

/**
 * 
 * 
 *
 * @return 
 *        
 */
	public IntegerProperty getHeightProperty() {
		return heightProperty;
	}

/**
 * 
 * 
 *
 * @return 
 *        
 */
	public IntegerProperty getWeightProperty() {
		return weightProperty;
	}

/**
 * 
 * 
 *
 * @return 
 *        
 */
	public StringProperty getErrorLabel() {
		return errorLabel;
	}
}
