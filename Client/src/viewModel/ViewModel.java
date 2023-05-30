package viewModel;

/**
 * An abstract class that all viewmodel subclasses extend.
 * 
 * 
 * @author Damian Trafialek
 * @version 1.0
 */
public abstract class ViewModel {

/**
 * a method to refresh the screen upon controller initialization or when the screen switches to the current viewmodel
 * 
 */
	public abstract void clear();

}
