package viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import modelClient.Model;

public class ManageCoachViewModel extends ViewModel {
    private Model model;
    private ViewState viewState;
    private StringProperty coachProperty, errorProperty;

    public ManageCoachViewModel(Model model, ViewState viewState) {
        this.model = model;
        this.viewState = viewState;
        coachProperty = new SimpleStringProperty();
        errorProperty = new SimpleStringProperty();
    }
    @Override
    public void clear() {
        //check to see if user is coach, or if they have a coach already
        errorProperty.set("");
        if (model.getCoach(viewState.getUsername()) != null)
        {
            //if coach is viewing, should I remove the button from their profile entirely?
            //where to put logic for hiding/showing buttons
            //where in sep1 is the hiding buttons logic
            //request_list? or just have a trigger that does something
        }
    }

    public void requestCoach() {
        //if the request goes through
        if (model.requestCoach(viewState.getUsername(), coachProperty.get()))
        {
            errorProperty.set("Pending");
        }
        else
        {
            errorProperty.set("An error occurred during request transaction.");
            coachProperty.set("");
        }
    }

    public StringProperty getCoachProperty() {
        return coachProperty;
    }
}
