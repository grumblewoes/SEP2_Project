package viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import modelClient.Model;

public class ManageFolderViewModel extends ViewModel{
    private StringProperty errorLabel, nameProperty, headerLabel;
    private ViewState viewState;
    private Model model;

    public ManageFolderViewModel(Model model, ViewState viewState) {
        this.model = model;
        this.viewState = viewState;
        errorLabel = new SimpleStringProperty();
        nameProperty = new SimpleStringProperty();
        headerLabel = new SimpleStringProperty();
    }

    public void submit() {
        //if no username, then add (no folder created). if editable, then edit. if !editable, then remove?
    }

    private boolean createFolder() {
        //wut
    }

    private boolean editFolderName() { //takes a string arg?

    }

    @Override
    public void clear() {
        //change header and name depending on viewstate, clear error label
    }
}
