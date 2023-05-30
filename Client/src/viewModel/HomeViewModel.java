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
 * ViewModel for HomeViewController class.
 * 
 * @author Damian Trafialek
 * @version 1.0
 */
public class HomeViewModel extends ViewModel{
    private StringProperty usernameProperty, folderListProperty, errorProperty,friendshipListProperty,friendshipRequestListProperty,meetingListProperty,meetingRequestListProperty;
    private Model model;
    private ViewState viewState;
    private Gson gson;

    /**
     * 2-argument constructor 
     * accepts a model and viewstate object, and initialises the rest of the bound properties,
     * along with a Gson interpreter
     * 
     * @param model for the model layer of MVVM, which communicates with the server
     *        
     * @param viewState to store information when switching screens
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
     * getter for the username property
     *
     * @return StringProperty for the name of the user
     *        
     */
    public StringProperty getUsernameProperty() { return usernameProperty; }

    /**
     * 
     * getter for the error label text
     *
     * @return StringProperty that contains the error text
     *        
     */
    public StringProperty getErrorLabel() { return errorProperty; }

    /**
     * 
     * getter for the JSON string that represents the list of folders
     *
     * @return StringProperty that contains the String of folders
     *        
     */
    public StringProperty getFolderListProperty() {
        return folderListProperty;
    }
    /**
     * 
     * getter for the JSON string that represents the list of friend requests
     *
     * @return StringProperty that contains the String of requests
     *        
     */
    public StringProperty getFriendshipRequestListProperty() {     return friendshipRequestListProperty;  }
    /**
     * 
     * getter for the JSON string that represents the list of friends the current user has
     *
     * @return StringProperty that contains the String of friends
     *        
     */
    public StringProperty getFriendshipListProperty() {     return friendshipListProperty;  }
    /**
     * 
     * getter for the JSON string that represents the list of meetings with the user's coach
     *
     * @return StringProperty that contains the String of meetings
     *        
     */
    public StringProperty getMeetingListProperty()
    {
        return meetingListProperty;
    }
    /**
     * 
     * getter for the JSON string that represents the list of meeting requests made by the current user
     *
     * @return StringProperty that contains the String of meeting requests
     *        
     */
    public StringProperty getMeetingRequestListProperty()
    {
        return meetingRequestListProperty;
    }



    /**
     * 
     * method to create a new folder. sets ViewState variables to the appropriate values to store information
     * when swapping screens
     *
     * @return boolean that is true in all cases
     *        
     */
    public boolean createFolder() {
        //pass to ViewState, view will switch to manage folder screen. DB call will be made there
        viewState.setNewFolder(true);
        viewState.setManageFolderEditable(true);
        return true; //why a bool here? ig if there's issues with the DB?
    }


    /**
     * method that requests to remove a folder from the user's profile. the appropriate values
     * in viewstate are set, and an attempt is made to remove the folder, returning a boolean
     * upon success/failure
     * 
     * @param folderId integer to represent the folder ID
     *        
     *
     * @return boolean to represent success/failure of the request
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
     * method that prepares the viewstate in order to edit a user's folder
     * 
     * @param title String for the title of the folder
     *        
     * @param id integer for the folder's ID
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
     * method that refreshes the screen upon controller initialisation and screen swap, and requests updated information
     * from the server
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
     * prepares the viewstate to open the user's selected folder from the home screen
     * 
     * @param folderName String for the name of the folder
     *        
     * @param folderId integer for the folder's ID
     *        
     */
    public void setupOpenFolder(String folderName,int folderId) {
        viewState.setFolderName(folderName);
        viewState.setFolderId(folderId);

    }

    /**
     * prepares the viewstate in order to open the profile of the current user
     * 
     */
    public void setupProfile() {
        viewState.setProfileUsername(viewState.getUsername() );
    }
    /**
     * prepares the viewstate by setting the name of the current user
     * 
     * @param username String to represent the username of the current user
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
     * method that requests to the server to accept a friend request from another user
     * 
     * @param username String that contains the username of the person requesting
     *        
     */
    public void acceptRequest(String username) {
        model.acceptFriendRequest(username, viewState.getUsername());
    }
    /**
     * method that requests to the server to reject a friend request sent by another user
     * 
     * @param username 
     *        
     */
    public void rejectRequest(String username) {
        model.rejectFriendRequest(username, viewState.getUsername());
    }

    /**
     * method that cancels a meeting scheduled to take place with the trainee's coach
     * 
     * @param dateOfMeeting LocalDate that contains the date of the meeting
     *        
     *
     * @return boolean that represents success or failure
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
     * resets all viewstate variables to their defaults, and disconnects the listener for the current trainee
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
     * method that determines whether it's possible to schedule an appointment with a coach or not
     *
     * @return boolean that represents whether creating a meeting would be valid or not
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
