package viewModel;

import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.beans.property.*;
import mediator.Exercise;
import mediator.User;
import modelClient.Model;
import util.Logger;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.LocalListener;

/**
 * 
 * ViewModel for the ProfileViewController class
 * 
 * @author Damian Trafialek
 * @version 1.0
 */
public class ProfileViewModel extends ViewModel implements LocalListener<String,String> {
    private Model model;
    private ViewState viewState;

    private StringProperty firstNameProperty, lastNameProperty,usernameProperty, statusProperty,bmiProperty,errorProperty, coachProperty;

    private IntegerProperty weightProperty,heightProperty,benchPressProperty,deadliftProperty,squatProperty;

    private BooleanProperty shareProfileProperty,editableProperty, coachStateProperty, isCoachProperty;

    /**
     * 2-argument constructor 
     * accepts a model and viewstate object as arguments
     * 
     * @param model for the model layer of MVVM, which communicates with the server
     *        
     * @param viewState to store information while switching screens
     *        
     */
    public ProfileViewModel(Model model, ViewState viewState){
        this.model = model;
        this.model.addListener(this);
        this.viewState = viewState;

        firstNameProperty= new SimpleStringProperty();
        lastNameProperty= new SimpleStringProperty();
        usernameProperty= new SimpleStringProperty();
        statusProperty= new SimpleStringProperty();
        bmiProperty= new SimpleStringProperty();
        errorProperty= new SimpleStringProperty();
        editableProperty = new SimpleBooleanProperty(true);

        weightProperty = new SimpleIntegerProperty();
        heightProperty = new SimpleIntegerProperty();
        benchPressProperty = new SimpleIntegerProperty();
        deadliftProperty = new SimpleIntegerProperty();
        squatProperty = new SimpleIntegerProperty();
        shareProfileProperty = new SimpleBooleanProperty();
        coachProperty = new SimpleStringProperty();
        coachStateProperty = new SimpleBooleanProperty();
        isCoachProperty = new SimpleBooleanProperty();

    }


    /**
     * 
     * getter for the first name property
     *
     * @return StringProperty that contains the first name of the user
     *        
     */
    public StringProperty firstNameProperty() {
        return firstNameProperty;
    }


    /**
     * 
     * getter for the last name property
     *
     * @return StringProperty for the last name of the user
     *        
     */
    public StringProperty lastNameProperty() {
        return lastNameProperty;
    }


    /**
     * 
     * getter for the username property
     *
     * @return StringProperty that contains the username of the user
     *        
     */
    public StringProperty usernameProperty() {
        return usernameProperty;
    }



    /**
     * 
     * getter for the status of the current user
     *
     * @return StringProperty that contains the status of the user
     *        
     */
    public StringProperty statusProperty() {
        return statusProperty;
    }



    /**
     * 
     * getter for the BMI of the current user
     *
     * @return StringProperty that contains the BMI
     *        
     */
    public StringProperty bmiProperty() {
        return bmiProperty;
    }
    /**
     * 
     * getter for the boolean property for whether the profile is editable
     *
     * @return BooleanProperty to determine whether the profile is read-only/editable
     *        
     */
    public BooleanProperty editableProperty() {  return editableProperty;  }

    /**
     * 
     * getter for the coach property to determine if the user is a coach or not
     *
     * @return BooleanProperty that contains whether or not the user is a coach
     *        
     */
    public BooleanProperty isCoachProperty() { return isCoachProperty; }

    /**
     * 
     * getter for the error property
     *
     * @return StringProperty that contains the error label text
     *        
     */
    public StringProperty errorProperty() {
        return errorProperty;
    }



    /**
     * 
     * getter for the weight property
     *
     * @return IntegerProperty that contains the weight of the user
     *        
     */
    public IntegerProperty weightProperty() {
        return weightProperty;
    }



    /**
     * 
     * getter for the height property
     *
     * @return IntegerProperty that contains the height of the user
     *        
     */
    public IntegerProperty heightProperty() {
        return heightProperty;
    }
    /**
     * 
     * getter for the bench press property
     *
     * @return IntegerProperty that contains the record for the bench press weight of the user
     *        
     */
    public IntegerProperty benchPressProperty() {
        return benchPressProperty;
    }



    /**
     *
     * getter for the deadlift property
     *
     * @return IntegerProperty that contains the record for the deadlift weight of the user
     *        
     */
    public IntegerProperty deadliftProperty() {
        return deadliftProperty;
    }



    /**
     *
     * getter for the squat property
     *
     * @return IntegerProperty that contains the record for the squat weight of the user
     *        
     */
    public IntegerProperty squatProperty() {
        return squatProperty;
    }
    /**
     * 
     * getter for the share property of the current user
     *
     * @return BooleanProperty that contains whether the user would like to share their personal information with others
     * on the leaderboard
     *        
     */
    public BooleanProperty shareProfileProperty() {  return shareProfileProperty;  }
    /**
     * 
     * getter for the coach state property, determining if the user has a coach or not
     *
     * @return BooleanProperty that contains whether the user has a coach or not
     *        
     */
    public BooleanProperty coachStateProperty() { return coachStateProperty; }
    /**
     * 
     * getter for the coach property of the current user
     *
     * @return StringProperty that contains the name of the user's coach
     *        
     */
    public StringProperty coachProperty() {
        return coachProperty;
    }

    @Override
    /**
     * refreshes the screen during controller initialisation and screen swap.
     * If the user is a trainee and is viewing their own profile, the fields will be editable,
     * and they can update their profile. If the user is a friend of said trainee, the fields will
     * be read-only, and the update button will be hidden. If the user is a coach, the fields will
     * be read-only, and they will not have the option to request a coach.
     * 
     */
    public void clear() {
        String u = viewState.getProfileUsername();
        //is the user a coach or trainee? -> changes depending on which one
        User profileUser = null;
        if( viewState.isCoach() && viewState.getUsername().equals(viewState.getProfileUsername()) )
            profileUser = model.getCoach(u);
        else
            profileUser = model.getTrainee(u);

        String pUsername = profileUser.getUsername();
        boolean wantsToShare = profileUser.isShareProfile();
        boolean owns = viewState.getProfileUsername().equals(viewState.getUsername()) ;
        boolean isCoach = viewState.isCoach();
        editableProperty.set(owns && !isCoach);
        isCoachProperty.set(isCoach);

        if( owns || wantsToShare){

            int benchPressBest = profileUser.getBench();
            int squatBest = profileUser.getSquat();
            int deadliftBest = profileUser.getDeadlift();

            usernameProperty.set(pUsername);
            errorProperty.set("");
            deadliftProperty.set(deadliftBest);
            squatProperty.set(squatBest);
            benchPressProperty.set(benchPressBest);

            firstNameProperty.set(profileUser.getFirstName());
            lastNameProperty.set(profileUser.getLastName());
            statusProperty.set(profileUser.getStatus());
            int h = profileUser.getHeight();
            int w = profileUser.getWeight();

            heightProperty.set(h);
            weightProperty.set(w);
            shareProfileProperty.set(profileUser.isShareProfile() );
            bmiProperty.set( String.valueOf( 1.0*w/(1.0*h*h/100/100) ) );

            if (model.getCoach(viewState.getProfileUsername()) != null)
            {
                coachStateProperty.set(true);
                coachProperty.set(model.getCoach(viewState.getProfileUsername()).getUsername());
            }
            else
            {
                coachStateProperty.set(false);
                coachProperty.set("");
            }
        }
        else //does not own and does not want to share
        {
            usernameProperty.set(pUsername);
            errorProperty.set("");
            deadliftProperty.set(0);
            squatProperty.set(0);
            benchPressProperty.set(0);

            firstNameProperty.set(profileUser.getFirstName());
            lastNameProperty.set(profileUser.getLastName());
            statusProperty.set(profileUser.getStatus());

            heightProperty.set(0);
            weightProperty.set(0);
            shareProfileProperty.set(false );
            bmiProperty.set( "" );

            if (model.getCoach(viewState.getProfileUsername()) != null)
            {
                coachStateProperty.set(true);
                coachProperty.set(model.getCoach(viewState.getProfileUsername()).getUsername());
            }
            else
            {
                coachStateProperty.set(false);
                coachProperty.set("");
            }
        }

    }

    /**
     * 
     * method that requests to the server to update the information on the profile screen
     *
     * @return boolean that signifies success/failure of the request
     *        
     */
    public boolean update() {
        int h = heightProperty.get();
        int w = weightProperty().get();
        String u = viewState.getProfileUsername();
        boolean s = shareProfileProperty.get();
        String st = statusProperty().get();

        boolean success = model.updateTrainee(u,h,w,s,st);
        if(success)clear();
        return success;
    }

    /**
     * 
     * method that requests to the server to remove the person whose profile the user is currently viewing
     *
     * @return boolean to signify success/failure of the request
     *        
     */
    public boolean removeFriend(){
        String username = viewState.getUsername();
        String friendUsername = viewState.getProfileUsername();


        boolean success =  model.removeFriend(username,friendUsername);
        if(!success)
            errorProperty.set("An error has occurred.");

        return success;
    }

    /**
     * 
     * method fetches the text for the back button
     *
     * @return String that contains the text
     *        
     */
    public String getGoBack(){
        String b = viewState.getGoBack();

        return b==null ? "home" : b;
    }

    @Override
    /**
     * method that updates the error label with information received from the server
     * 
     */
    public void propertyChange(ObserverEvent<String, String> event) {
        String name = event.getPropertyName();
        String value = event.getValue2();
        Platform.runLater(()->{
            errorProperty.set(value);
        });
    }

    /**
     * method that makes a request to the server to remove the coach of the current trainee
     * 
     */
    public void removeCoach()
    {
        //if the request goes through
        if (model.removeCoachAssignment(viewState.getProfileUsername()))
        {
            errorProperty.set("Removed");
            coachProperty.set("");
            coachStateProperty.set(false);
        }
        else
        {
            errorProperty.set("An error occurred during removal.");
            coachProperty.set("");
        }
    }

    /**
     * method that requests to the server to make the coach by the name of the current coachProperty value
     * the current trainee's new coach
     * 
     */
    public void requestCoach()
    {
        //if the request goes through
        if (model.requestCoach(viewState.getUsername(), coachProperty.get()))
        {
            errorProperty.set("Pending");
        }
        else
        {
            errorProperty.set("Can only request one coach at a time.");
            coachProperty.set("");
        }
    }


}
