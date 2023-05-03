package view;
import javafx.scene.layout.Region;
import viewModel.CreateUserViewModel;
import viewModel.HomeViewModel;
import viewModel.ViewModel;

public abstract class ViewController {

	protected Region root;

	private ViewHandler viewHandler;

	public abstract void init(
			ViewHandler viewHandler, ViewModel viewModel, Region root, HomeViewModel createUserViewModel, Region root);

	public abstract void reset();

	public Region getRoot() {
		return root;
	}

}
