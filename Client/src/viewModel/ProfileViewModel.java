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

public class ProfileViewModel extends ViewModel implements LocalListener<String,String> {
    private Model model;
    private ViewState viewState;

    private StringProperty firstNameProperty, lastNameProperty,usernameProperty, statusProperty,bmiProperty,errorProperty, coachProperty;

    private IntegerProperty weightProperty,heightProperty,benchPressProperty,deadliftProperty,squatProperty;

    private BooleanProperty shareProfileProperty,editableProperty, coachStateProperty, isCoachProperty;

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


    public StringProperty firstNameProperty() {
        return firstNameProperty;
    }


    public StringProperty lastNameProperty() {
        return lastNameProperty;
    }


    public StringProperty usernameProperty() {
        return usernameProperty;
    }



    public StringProperty statusProperty() {
        return statusProperty;
    }



    public StringProperty bmiProperty() {
        return bmiProperty;
    }
    public BooleanProperty editableProperty() {  return editableProperty;  }

public BooleanProperty isCoachProperty() { return isCoachProperty; }

    public StringProperty errorProperty() {
        return errorProperty;
    }



    public IntegerProperty weightProperty() {
        return weightProperty;
    }



    public IntegerProperty heightProperty() {
        return heightProperty;
    }
    public IntegerProperty benchPressProperty() {
        return benchPressProperty;
    }



    public IntegerProperty deadliftProperty() {
        return deadliftProperty;
    }



    public IntegerProperty squatProperty() {
        return squatProperty;
    }
    public BooleanProperty shareProfileProperty() {  return shareProfileProperty;  }
    public BooleanProperty coachStateProperty() { return coachStateProperty; }
    public StringProperty coachProperty() {
        return coachProperty;
    }

    @Override
    public void clear() {
        String u = viewState.getProfileUsername();
        //is the user a coach or trainee? -> changes depending on which one
        User profileUser = viewState.isCoach() ? model.getCoach(u): model.getTrainee(u);
        String pUsername = profileUser.getUsername();
        boolean wantsToShare = profileUser.isShareProfile();
        boolean owns = viewState.getProfileUsername().equals(viewState.getUsername());
        editableProperty.set(owns);


        if( owns || wantsToShare){

            int benchPressBest = model.getBestBenchPress(u);
            int squatBest = model.getBestSquat(u);
            int deadliftBest = model.getBestDeadlift(u);

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
            isCoachProperty.set(viewState.isCoach());

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

    public String getGoBack(){
        String b = viewState.getGoBack();

        return b==null ? "home" : b;
    }

    @Override
    public void propertyChange(ObserverEvent<String, String> event) {
        String name = event.getPropertyName();
        String value = event.getValue2();
        Platform.runLater(()->{
            errorProperty.set(value);
        });
    }

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
