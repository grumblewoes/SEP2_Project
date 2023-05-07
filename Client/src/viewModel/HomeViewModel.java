package viewModel;

import com.google.gson.Gson;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import mediator.Folder;
import mediator.FolderList;
import modelClient.Model;

import java.util.ArrayList;

public class HomeViewModel extends ViewModel{
    private StringProperty usernameProperty, folderListProperty, errorProperty;
    private Model model;
    private ViewState viewState;
    private Gson gson;

    public HomeViewModel(Model model, ViewState viewState) {
        this.model = model;
        this.viewState = viewState;
        usernameProperty = new SimpleStringProperty();
        errorProperty = new SimpleStringProperty();
        folderListProperty = new SimpleStringProperty();
        gson = new Gson();
    }

    public StringProperty getUsernameProperty() { return usernameProperty; }

    public StringProperty getErrorLabel() { return errorProperty; }

    public StringProperty getFolderListProperty() {
        return folderListProperty;
    }

    private void loadFolders() {
        //get list of folders from the database
        //can't simply cast string to StringProperty, so I made a temp variable
        FolderList folderList = model.getFolderList(usernameProperty.get());

        folderListProperty.set(gson.toJson(folderList));
    }

    public boolean createFolder() {
        //pass to ViewState, view will switch to manage folder screen. DB call will be made there
        viewState.setNewFolder(true);
        viewState.setManageFolderEditable(true);
        return true; //why a bool here? ig if there's issues with the DB?
    }

    public boolean removeFolder(int folderId) {
        //call DB, then call clear
        viewState.setUsername(usernameProperty.get());
        viewState.setNewFolder(false);
        viewState.setManageFolderEditable(false);

        try {
            model.removeFolder(usernameProperty.get(), folderId);
            clear();
            return true;
        }
        catch (Exception e)
        {
            errorProperty.set("Error deleting from DB");
            return false;
        }
    }

    public void editFolder(String title,int id) {
        //pass to ViewState, view will switch to manage folder screen. DB called from there
        viewState.setNewFolder(false);
        viewState.setManageFolderEditable(true);
        viewState.setFolderName(title);
        viewState.setFolderId(id);
    }

    @Override
    public void clear() {
        //receiving folder names from the database

        usernameProperty.set(viewState.getUsername());

        viewState.setNewFolder(true);
        viewState.setManageFolderEditable(true);
        errorProperty.set("");
        loadFolders();
    }

    public void setupOpenFolder(String folderName,int folderId) {
        viewState.setFolderName(folderName);
        viewState.setFolderId(folderId);

    }

    public void setupProfile() {
        viewState.setProfileUsername(viewState.getUsername() );
    }
}
