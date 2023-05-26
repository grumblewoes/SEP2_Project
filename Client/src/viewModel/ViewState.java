package viewModel;

import mediator.User;

/**
 * 
 * 
 * 
 * @author 
 * @version 
 */
public class ViewState {
    private String username,folderName,exerciseName,goBack,profileUsername;
    private int folderId;
    private boolean manageFolderEditable, newFolder;
    private boolean isCoach;

    private User trainee;



    /**
     * 0-argument constructor 
     * 
     * 
     */
    public ViewState(){
        this.username=null;
        this.folderName=null;
        this.exerciseName=null;
        this.folderId=0;
        this.manageFolderEditable=false;
        this.newFolder=false;
        this.goBack=null;
        this.profileUsername=null;
        this.isCoach = false;
    }

    /**
     * 
     * 
     *
     * @return 
     *        
     */
    public String getUsername() {
        return username;
    }

    /**
     * 
     * 
     * @param username 
     *        
     */
    public void setUsername(String username) {
       this.username = username;
    }

    /**
     * 
     * 
     *
     * @return 
     *        
     */
    public boolean getManageFolderEditable() {
        return manageFolderEditable;
    }

    /**
     * 
     * 
     * @param editable 
     *        
     */
    public void setManageFolderEditable(boolean editable) {
        manageFolderEditable = editable;
    }

    /**
     * 
     * 
     *
     * @return 
     *        
     */
    public boolean getNewFolder() { return newFolder; }

    /**
     * 
     * 
     * @param newFolder 
     *        
     */
    public void setNewFolder(boolean newFolder) { this.newFolder = newFolder; }

    /**
     * 
     * 
     *
     * @return 
     *        
     */
    public String getFolderName() { return folderName; }
    /**
     * 
     * 
     * @param folderName 
     *        
     */
    public void setFolderName(String folderName) { this.folderName=folderName; }
    /**
     * 
     * 
     *
     * @return 
     *        
     */
    public int getFolderId() {
        return folderId;
    }
    /**
     * 
     * 
     * @param folderId 
     *        
     */
    public void setFolderId(int folderId) {
        this.folderId = folderId;
    }
    /**
     * 
     * 
     *
     * @return 
     *        
     */
    public String getExerciseName() {
        return exerciseName;
    }
    /**
     * 
     * 
     * @param exerciseName 
     *        
     */
    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    /**
     * 
     * 
     * @param isCoach 
     *        
     */
    public void setIsCoach(boolean isCoach) {
        this.isCoach = isCoach;
    }
    /**
     * 
     * 
     *
     * @return 
     *        
     */
    public boolean isCoach() {
        return isCoach;
    }
    /**
     * 
     * 
     *
     * @return 
     *        
     */
    public String getGoBack() { return goBack; }
    /**
     * 
     * 
     * @param goBack 
     *        
     */
    public void setGoBack(String goBack) { this.goBack = goBack; }
    /**
     * 
     * 
     *
     * @return 
     *        
     */
    public String getProfileUsername() { return profileUsername; }
    /**
     * 
     * 
     * @param profileUsername 
     *        
     */
    public void setProfileUsername(String profileUsername) { this.profileUsername = profileUsername; }

    /**
     * 
     * 
     * @param trainee 
     *        
     *
     * @return 
     *        
     */
    public User getTrainee(User trainee){
        return trainee;
    }

}
