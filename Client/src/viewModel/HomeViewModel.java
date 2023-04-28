package viewModel;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.layout.VBox;
import modelClient.Model;

public class HomeViewModel extends ViewModel{
    private StringProperty usernameProperty, folderListProperty, errorProperty;
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
        //??? what property is this
        return null;
    }

    private void loadFolders() {
        //get folders from the database
        //put names in arraylist, cast to json, update the folderProperty
    }

    public boolean createFolder() {
        //pass to viewstate, view will switch to manage folder screen
    }

    public boolean removeFolder(String name) {
        //call db, then call clear
        return false;
    }

    public void editFolder() {
        //pass to viewstate, view will switch to manage folder screen
    }

    public void populateFoldersToFolderPane(VBox vbox) {
        //idek
    }

    @Override
    public void clear() {
        //receiving folder names from the database
        //load folders

    }
}
