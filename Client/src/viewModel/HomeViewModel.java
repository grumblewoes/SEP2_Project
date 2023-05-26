package viewModel;


import com.google.gson.Gson;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import mediator.*;
import modelClient.Model;
import util.Logger;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * 
 * 
 * 
 * @author 
 * @version 
 */
public class HomeViewModel extends ViewModel{
    private StringProperty usernameProperty, folderListProperty, errorProperty,friendshipListProperty,friendshipRequestListProperty,meetingListProperty,meetingRequestListProperty;
    private Model model;
    private ViewState viewState;
    private Gson gson;

    /**
     * 2-argument constructor 
     * 
     * 
     * @param model 
     *        
     * @param viewState 
     *        
     */
    public HomeViewModel(Model model, ViewState viewState) {
        this.model = model;
        this.viewState = viewState;
        usernameProperty = new SimpleStringProperty();
        errorProperty = new SimpleStringProperty();
        folderListProperty = new SimpleStringProperty();
        friendshipListProperty = new SimpleStringProperty();
        friendshipRequestListProperty = new SimpleStringProperty();
        meetingListProperty = new SimpleStringProperty();
        meetingRequestListProperty = new SimpleStringProperty();
        gson = new Gson();
    }

    /**
     * 
     * 
     *
     * @return 
     *        
     */
    public StringProperty getUsernameProperty() { return usernameProperty; }

    /**
     * 
     * 
     *
     * @return 
     *        
     */
    public StringProperty getErrorLabel() { return errorProperty; }

    /**
     * 
     * 
     *
     * @return 
     *        
     */
    public StringProperty getFolderListProperty() {
        return folderListProperty;
    }
    /**
     * 
     * 
     *
     * @return 
     *        
     */
    public StringProperty getFriendshipRequestListProperty() {     return friendshipRequestListProperty;  }
    /**
     * 
     * 
     *
     * @return 
     *        
     */
    public StringProperty getFriendshipListProperty() {     return friendshipListProperty;  }
    /**
     * 
     * 
     *
     * @return 
     *        
     */
    public StringProperty getMeetingListProperty()
    {
        return meetingListProperty;
    }
    /**
     * 
     * 
     *
     * @return 
     *        
     */
    public StringProperty getMeetingRequestListProperty()
    {
        return meetingRequestListProperty;
    }



    /**
     * 
     * 
     *
     * @return 
     *        
     */
    public boolean createFolder() {
        //pass to ViewState, view will switch to manage folder screen. DB call will be made there
        viewState.setNewFolder(true);
        viewState.setManageFolderEditable(true);
        return true; //why a bool here? ig if there's issues with the DB?
    }


    /**
     * 
     * 
     * @param folderId 
     *        
     *
     * @return 
     *        
     */
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

    /**
     * 
     * 
     * @param title 
     *        
     * @param id 
     *        
     */
    public void editFolder(String title,int id) {
        //pass to ViewState, view will switch to manage folder screen. DB called from there
        viewState.setNewFolder(false);
        viewState.setManageFolderEditable(true);
        viewState.setFolderName(title);
        viewState.setFolderId(id);
    }

    @Override
    /**
     * 
     * 
     */
    public void clear() {
        //receiving folder names from the database
        Logger.log("reseting the home");
        usernameProperty.set(viewState.getUsername());

        viewState.setNewFolder(true);
        viewState.setManageFolderEditable(true);
        errorProperty.set("");
        loadFolders();
        loadFriendships();
        loadMeetings();
    }

    /**
     * 
     * 
     * @param folderName 
     *        
     * @param folderId 
     *        
     */
    public void setupOpenFolder(String folderName,int folderId) {
        viewState.setFolderName(folderName);
        viewState.setFolderId(folderId);

    }

    /**
     * 
     * 
     */
    public void setupProfile() {
        viewState.setProfileUsername(viewState.getUsername() );
    }
    /**
     * 
     * 
     * @param username 
     *        
     */
    public void setupProfile(String username) {
        viewState.setProfileUsername(username );
    }

    private void loadFriendships(){
        FriendList friends = model.getFriends(viewState.getUsername());

        ArrayList<String> requests = model.getFriendRequests(viewState.getUsername());
//        Logger.log(model.getFriends(viewState.getUsername()));
//        Logger.log(model.getFriendRequests(viewState.getUsername()));

        Logger.log(friends);
        friendshipRequestListProperty.set("");
        friendshipListProperty.set("");
        friendshipRequestListProperty.set(gson.toJson(requests) );
        friendshipListProperty.set( gson.toJson(friends) );

    }
    private void loadFolders() {
        //get list of folders from the database
        //can't simply cast string to StringProperty, so I made a temp variable
        FolderList folderList = model.getFolderList(usernameProperty.get());

        folderListProperty.set(gson.toJson(folderList));
    }

    private void loadMeetings(){
        ArrayList<String> meetingList = model.getTraineeMeetingList(viewState.getUsername());

        ArrayList<String> meetingRequests = model.getTraineeMeetingRequests(viewState.getUsername());

        meetingListProperty.set("");
        meetingRequestListProperty.set("");
        meetingRequestListProperty.set(gson.toJson(meetingRequests) );
        meetingListProperty.set( gson.toJson(meetingList) );

    }

    /**
     * 
     * 
     * @param username 
     *        
     */
    public void acceptRequest(String username) {
        model.acceptFriendRequest(username, viewState.getUsername());
    }
    /**
     * 
     * 
     * @param username 
     *        
     */
    public void rejectRequest(String username) {
        model.rejectFriendRequest(username, viewState.getUsername());
    }

    /**
     * 
     * 
     * @param dateOfMeeting 
     *        
     *
     * @return 
     *        
     */
    public boolean removeMeeting(LocalDate dateOfMeeting){

        String traineeUsername = viewState.getUsername();
        String coachUsername = null;
        User coach = model.getCoach(viewState.getUsername());
        if (coach != null) {
            coachUsername = coach.getUsername();
        }
        Logger.log(traineeUsername);
        Logger.log(coachUsername);
        try
        {
            if (coachUsername!=null){
                model.removeMeeting(traineeUsername, coachUsername, dateOfMeeting);
                clear();
                return true;
            }
            else {
                errorProperty.set("You cannot remove a meeting since you do not have a coach.");
                return false;
            }

        }
        catch (Exception e){
            errorProperty.set(e.getMessage());
            return false;
        }
    }

    /**
     * 
     * 
     */
    public void logout() {
        model.disconnectListener(viewState.getUsername());
        viewState.setIsCoach(false);
        viewState.setUsername(null);
        viewState.setProfileUsername(null);
        viewState.setGoBack(null);
        viewState.setExerciseName(null);
        viewState.setFolderName(null);
        viewState.setFolderId(0);
        viewState.setNewFolder(false);
        viewState.setManageFolderEditable(false);
    }

    /**
     * 
     * 
     *
     * @return 
     *        
     */
    public boolean setupMeeting()
    {
        String coachUsername = null;
        User coach = model.getCoach(viewState.getUsername());
        if (coach != null) {
            coachUsername = coach.getUsername();
        }

        if (coachUsername == null) {
            errorProperty.set("You do not have a coach.");
            return false;
        }
        return true;
    }
}
