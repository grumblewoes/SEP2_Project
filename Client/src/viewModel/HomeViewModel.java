package viewModel;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.layout.VBox;
import modelClient.Model;

public class HomeViewModel extends ViewModel{
    private StringProperty usernameProperty, errorProperty;
    private BooleanProperty toggleUpdateFoldersProperty;
    private Model model;
    private ViewState viewState;

    public HomeViewModel(Model model, ViewState viewState) {
        this.model = model;
        this.viewState = viewState;
        usernameProperty = new SimpleStringProperty();
        toggleUpdateFoldersProperty = new SimpleBooleanProperty();
        errorProperty = new SimpleStringProperty();
    }

    public StringProperty getUsernameProperty() { return usernameProperty; }

    public StringProperty getErrorLabel() { return errorProperty; }

    public StringProperty getFoldersProperty() {
        //???
        return null;
    }

    private void loadFolders() {
        //get folders from the database
    }

    public void createFolder() {}

    public boolean removeFolder(String name) {
        return false;
    }

    public void editFolder() {}

    public void populateFoldersToFolderPane(VBox vbox) {

    }

    @Override
    public void clear() {

    }
}
