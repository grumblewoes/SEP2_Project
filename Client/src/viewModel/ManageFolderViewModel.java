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
        if (viewState.getNewFolder())
            createFolder();
        else if (viewState.getManageFolderEditable())
            editFolderName(nameProperty.get());
    }

    private boolean createFolder() {
        return model.createFolder(viewState.getUsername(), nameProperty.get());
    }

    private boolean editFolderName(String newName) {
        return model.editFolder(viewState.getUsername(), nameProperty.get(), newName);
    }

    @Override
    public void clear() {
        errorLabel.set("");
        //change header and name depending on viewstate, clear error label
        if (viewState.getNewFolder())
        {
            headerLabel.set("Add Folder");
        }

        else if (viewState.getManageFolderEditable()) {
            headerLabel.set("Edit Folder");
        }

        else if (!viewState.getManageFolderEditable())
            headerLabel.set("Remove folder");
    }
}