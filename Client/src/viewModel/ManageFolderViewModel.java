package viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import modelClient.Model;

/**
 * 
 * 
 * 
 * @author 
 * @version 
 */
public class ManageFolderViewModel extends ViewModel{
    private StringProperty errorProperty, nameProperty, headerLabel;
    private ViewState viewState;
    private Model model;

    /**
     * 2-argument constructor 
     * 
     * 
     * @param model 
     *        
     * @param viewState 
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
     * 
     *
     * @return 
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
     * 
     *
     * @return 
     *        
     */
    public StringProperty getErrorProperty() {return errorProperty;}
    /**
     * 
     * 
     *
     * @return 
     *        
     */
    public StringProperty getHeaderProperty() {return headerLabel;}
    /**
     * 
     * 
     *
     * @return 
     *        
     */
    public StringProperty getNameProperty() {return nameProperty;}


    @Override
    /**
     * 
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
