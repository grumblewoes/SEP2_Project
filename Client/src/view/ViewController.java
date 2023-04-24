package view;
import javafx.scene.layout.Region;
import viewModel.ViewModel;

public abstract class ViewController {

	protected Region root;

	private ViewHandler viewHandler;

	public abstract void init(
			ViewHandler viewHandler, ViewModel viewModel, Region root);

	public abstract void reset();

	public Region getRoot() {
		return null;
	}

}
