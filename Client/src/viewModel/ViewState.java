package viewModel;

import mediator.User;

/**
 * 
 * A class intended to store information while switching between screens and user accounts.
 * 
 * @author Damian Trafialek
 * @version 1.0
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
     * Initializes all properties to default states tow ork with later
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
     * getter for the current user's username
     *
     * @return String to represent username
     *        
     */
    public String getUsername() {
        return username;
    }

    /**
     * setter for the current user's username
     * 
     * @param username the username value which the username variable will be set to
     *        
     */
    public void setUsername(String username) {
       this.username = username;
    }

    /**
     * 
     * getter for the boolean that determines whether folders are editable
     *
     * @return boolean to represent editable/read-only
     *        
     */
    public boolean getManageFolderEditable() {
        return manageFolderEditable;
    }

    /**
     * setter for the boolean to determine whether folders are editable/read-only
     * 
     * @param editable to determine the ability to edit. True = editable, False = read-only
     *        
     */
    public void setManageFolderEditable(boolean editable) {
        manageFolderEditable = editable;
    }

    /**
     * 
     * fetches the boolean that indicates if the folder selected will be a new addition or not
     *
     * @return boolean, True = new, False = edit/remove
     *        
     */
    public boolean getNewFolder() { return newFolder; }

    /**
     * setter for the newFolder boolean
     * 
     * @param newFolder argument to set the newFolder boolean value
     *        
     */
    public void setNewFolder(boolean newFolder) { this.newFolder = newFolder; }

    /**
     * 
     * getter for the currently selected folder's name
     *
     * @return String that contains the folder's title
     *        
     */
    public String getFolderName() { return folderName; }
    /**
     * setter for the name of the folder that is currently selected
     * 
     * @param folderName String to indicate the name of the folder
     *        
     */
    public void setFolderName(String folderName) { this.folderName=folderName; }
    /**
     * 
     * getter for the currently selected folder's id
     *
     * @return integer to represent the id
     *        
     */
    public int getFolderId() {
        return folderId;
    }
    /**
     * setter for the currently selected folder's id
     * 
     * @param folderId integer value of the folder's id
     *        
     */
    public void setFolderId(int folderId) {
        this.folderId = folderId;
    }
    /**
     * 
     * getter for the name of the currently selected exercise
     *
     * @return String to represent the name of the exercise
     *        
     */
    public String getExerciseName() {
        return exerciseName;
    }
    /**
     * setter for the name of the currently selected exercise
     * 
     * @param exerciseName String to represent the exercise name
     *        
     */
    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    /**
     * setter to determine whether the current user is a coach
     * 
     * @param isCoach boolean to determine if the user is a coach
     *        
     */
    public void setIsCoach(boolean isCoach) {
        this.isCoach = isCoach;
    }
    /**
     * 
     * getter to determine if the current user is a coach
     *
     * @return boolean value for the current user's state
     *        
     */
    public boolean isCoach() {
        return isCoach;
    }
    /**
     * 
     * getter for the goBack value
     *
     * @return String value for the goBack display
     *        
     */
    public String getGoBack() { return goBack; }
    /**
     * setter for the string value on the goBack function
     * 
     * @param goBack String value for the text
     *        
     */
    public void setGoBack(String goBack) { this.goBack = goBack; }
    /**
     * 
     * getter for the profile username of the current user
     *
     * @return String representing the user's username
     *        
     */
    public String getProfileUsername() { return profileUsername; }
    /**
     * setter for the current user's profile username
     * 
     * @param profileUsername String to represent the user's username
     *        
     */
    public void setProfileUsername(String profileUsername) { this.profileUsername = profileUsername; }

//    /**
//     * fetches the User object for the requested trainee
//     *
//     * @param trainee
//     *
//     *
//     * @return
//     *
//     */
//    public User getTrainee(User trainee){
//        return trainee;
//    }

}
