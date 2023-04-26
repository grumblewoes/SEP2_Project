package viewModel;

public class ViewState {
    private String username;
    private boolean manageFolderEditable;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
       this.username = username;
    }

    public boolean getManageFolderEditable() {
        return manageFolderEditable;
    }

    public void setManageFolderEditable(boolean editable) {
        manageFolderEditable = editable;
    }
}
