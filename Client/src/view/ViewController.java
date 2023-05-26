package view;
import javafx.scene.layout.Region;
import viewModel.CreateUserViewModel;
import viewModel.HomeViewModel;
import viewModel.ViewModel;

/**
 * 
 * 
 * 
 * @author 
 * @version 
 */
public abstract class ViewController {

	protected Region root;

	protected ViewHandler viewHandler;

	public abstract void init(
			ViewHandler viewHandler, ViewModel viewModel, Region root);

/**
 * 
 * 
 */
	public abstract void reset();

/**
 * 
 * 
 *
 * @return 
 *        
 */
	public Region getRoot() {
		return root;
	}

}
