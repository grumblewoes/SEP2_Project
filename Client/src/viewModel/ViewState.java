package viewModel;

import mediator.User;

public class ViewState {
    private String username,folderName,exerciseName,goBack,profileUsername;
    private int folderId;
    private boolean manageFolderEditable, newFolder;
    private boolean isCoach;

    private User trainee;



    public ViewState(){
        this.username="coach";
        this.folderName=null;
        this.exerciseName=null;
        this.folderId=0;
        this.manageFolderEditable=false;
        this.newFolder=false;
        this.goBack=null;
        this.profileUsername=null;
        this.isCoach = true;
    }

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

    public boolean getNewFolder() { return newFolder; }

    public void setNewFolder(boolean newFolder) { this.newFolder = newFolder; }

    public String getFolderName() { return folderName; }
    public void setFolderName(String folderName) { this.folderName=folderName; }
    public int getFolderId() {
        return folderId;
    }
    public void setFolderId(int folderId) {
        this.folderId = folderId;
    }
    public String getExerciseName() {
        return exerciseName;
    }
    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public void setIsCoach(boolean isCoach) {
        this.isCoach = isCoach;
    }
    public boolean isCoach() {
        return isCoach;
    }
    public String getGoBack() { return goBack; }
    public void setGoBack(String goBack) { this.goBack = goBack; }
    public String getProfileUsername() { return profileUsername; }
    public void setProfileUsername(String profileUsername) { this.profileUsername = profileUsername; }

    public User getTrainee(User trainee){
        return trainee;
    }
}
