package viewModel;

import com.google.gson.Gson;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.layout.VBox;
import modelClient.Model;

import java.util.ArrayList;

public class HomeViewModel extends ViewModel{
    private StringProperty usernameProperty, folderListProperty, errorProperty;
    private BooleanProperty toggleUpdateFoldersProperty;
    private Model model;
    private ViewState viewState;
    private Gson gson;

    public HomeViewModel(Model model, ViewState viewState) {
        this.model = model;
        this.viewState = viewState;
        usernameProperty = new SimpleStringProperty();
        toggleUpdateFoldersProperty = new SimpleBooleanProperty();
        errorProperty = new SimpleStringProperty();
        folderListProperty = new SimpleStringProperty();
        gson = new Gson();
    }

    public StringProperty getUsernameProperty() { return usernameProperty; }

    public StringProperty getErrorLabel() { return errorProperty; }

    public StringProperty getFoldersProperty() {
        return folderListProperty;
    }

    private void loadFolders() {
        //get list of folders from the database

        //SELECT w.title
        //FROM Trainee t
        //JOIN WorkoutPlan w ON t.username = w.username
        //WHERE t.username = usernameProperty;

        ArrayList folders = new ArrayList();

        //can't simply cast string to StringProperty, so I made a temp variable
        folderListProperty = new SimpleStringProperty(gson.toJson(folders));
    }

    public boolean createFolder() {
        //pass to ViewState, view will switch to manage folder screen. DB call will be made there
        viewState.setUsername(null);
        viewState.setManageFolderEditable(true);
        return true; //why a bool here? ig if there's issues with the DB?
    }

    public boolean removeFolder(String nameOfFolder) {
        //call DB, then call clear
        viewState.setUsername(usernameProperty.get());
        viewState.setManageFolderEditable(false);

        try {
            //DELETE FROM ExerciseInWorkout
            //WHERE id = (
            //  SELECT id
            //  FROM WorkoutPlan
            //  WHERE trainee_username = usernameProperty )
            //AND title = nameOfFolder;

            clear();
            return true;
        }
        catch (Exception e)
        {
            errorProperty.set("Error deleting from DB");
            return false;
        }
    }

    public void editFolder() {
        //pass to ViewState, view will switch to manage folder screen. DB called from there
        viewState.setUsername(usernameProperty.get());
        viewState.setManageFolderEditable(true);
    }

    public void populateFoldersToFolderPane(VBox vbox) {
        //waiting on class diagram update
    }

    @Override
    public void clear() {
        //receiving folder names from the database

        viewState.setUsername(null);
        viewState.setManageFolderEditable(true);
        errorProperty.set("");
        loadFolders();
    }
}
