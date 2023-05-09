package viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import modelClient.Model;

public class ManageCoachViewModel extends ViewModel {
    private Model model;
    private ViewState viewState;
    private StringProperty coachProperty;

    public ManageCoachViewModel(Model model, ViewState viewState) {
        this.model = model;
        this.viewState = viewState;
        coachProperty = new SimpleStringProperty();
    }
    @Override
    public void clear() {

    }

    public void requestCoach() {
        if (model.requestCoach(viewState.getUsername(), coachProperty.get()))
        {
            errorLabel.set("Pending");
            //set label to approved or sum
        }

        else
        {}
            //set label to error, clear text field
    }

    public StringProperty getCoachProperty() {
        return coachProperty;
    }
}
