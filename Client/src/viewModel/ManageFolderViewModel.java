package viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import modelClient.Model;

/**
 * 
 * ViewModel for the ManageFolderViewController class.
 * 
 * @author Damian Trafialek
 * @version 1.0
 */
public class ManageFolderViewModel extends ViewModel{
    private StringProperty errorProperty, nameProperty, headerLabel;
    private ViewState viewState;
    private Model model;

    /**
     * 2-argument constructor 
     * accepts the model and viewstate objects as arguments, and initializes the other bound
     * properties.
     * 
     * @param model for the model layer of MVVM, which communicates with the server
     *        
     * @param viewState to store information when switching screens
     *        
     */
    public ManageFolderViewModel(Model model, ViewState viewState) {
        this.model = model;
        this.viewState = viewState;
        errorProperty = new SimpleStringProperty();
        nameProperty = new SimpleStringProperty();
        headerLabel = new SimpleStringProperty();
    }

    /**
     * 
     * method to either create a new folder or edit an existing one's name
     *
     * @return boolean to represent success/failure of either function
     *        
     */
    public boolean submit() {
        if (viewState.getNewFolder())
            return createFolder();
        else
            //not working :? |:(
            return editFolderName(nameProperty.get());

    }

    private boolean createFolder() {
        return model.createFolder(viewState.getUsername(), nameProperty.get());
    }

    private boolean editFolderName(String newName) {
        return model.editFolder(viewState.getUsername(), viewState.getFolderId(), newName);
    }

    /**
     * 
     * getter for the error text property
     *
     * @return StringProperty to represent error text
     *        
     */
    public StringProperty getErrorProperty() {return errorProperty;}
    /**
     * 
     * getter for the header property
     *
     * @return StringProperty to represent the header
     *        
     */
    public StringProperty getHeaderProperty() {return headerLabel;}
    /**
     * 
     * getter for the name property
     *
     * @return StringProperty to represent the name of the folder
     *        
     */
    public StringProperty getNameProperty() {return nameProperty;}


    @Override
    /**
     * method that refreshes the screen upon controller initialisation and screen swap
     * 
     */
    public void clear() {
        errorProperty.set("");
        //change header and name depending on viewstate, clear error label
        if (viewState.getNewFolder())
        {
            headerLabel.set("Add Folder");
            nameProperty.set("");
        }

        else if (viewState.getManageFolderEditable()) {
            headerLabel.set("Edit Folder");
            nameProperty.set(viewState.getFolderName());
        }

        else if (!viewState.getManageFolderEditable())
            headerLabel.set("Remove folder");
    }
}
