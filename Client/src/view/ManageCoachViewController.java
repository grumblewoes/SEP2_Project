package view;

import viewModel.ManageCoachViewModel;
import viewModel.ViewModel;
import javafx.scene.layout.Region;

public class ManageCoachViewController extends ViewController{
    private ViewHandler viewHandler;
    private ManageCoachViewModel viewModel;

    @Override
    public void init(ViewHandler viewHandler, ViewModel viewModel, Region root) {
        this.viewHandler = viewHandler;
        this.viewModel = (ManageCoachViewModel)viewModel;
        this.root = root;
    }

    @Override
    public void reset() {
        viewModel.clear();
    }

    public void requestCoach() {
        viewModel.requestCoach();
    }
}
