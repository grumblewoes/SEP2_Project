package viewModel;

import modelClient.Model;

public class ManageCoachViewModel extends ViewModel {
    private Model model;
    private ViewState viewState;

    public ManageCoachViewModel(Model model, ViewState viewState) {
        this.model = model;
        this.viewState = viewState;
    }
    @Override
    public void clear() {

    }

    public void requestCoach() {
        if (model.requestCoach(viewState.getUsername(), coachField.get()))
        {
            errorLabel.set("Pending");
            //set label to approved or sum
        }

        else
        {}
            //set label to error, clear text field
    }
}
