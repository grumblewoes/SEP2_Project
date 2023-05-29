package view;
import javafx.scene.layout.Region;
import viewModel.CreateUserViewModel;
import viewModel.HomeViewModel;
import viewModel.ViewModel;

/**
 * Higher class that sets some rules for view controllers to follow.
 * 
 * 
 * @author Damian Trafiałek
 * @version 2.0
 */
public abstract class ViewController {

	protected Region root;

	protected ViewHandler viewHandler;

	public abstract void init(
			ViewHandler viewHandler, ViewModel viewModel, Region root);

/**
 * Reset method that is to be implemented.
 * 
 */
	public abstract void reset();

/**
 * Method that returns the region - root.
 * 
 *
 * @return root
 *        
 */
	public Region getRoot() {
		return root;
	}

}
