package viewModel;

import javafx.beans.property.*;
import mediator.Exercise;
import mediator.User;
import modelClient.Model;

public class ProfileViewModel extends ViewModel{
    private Model model;
    private ViewState viewState;

    private StringProperty firstNameProperty, lastNameProperty,usernameProperty, genderProperty,bmiProperty,errorProperty;

    private IntegerProperty weightProperty,heightProperty,benchPressProperty,deadliftProperty,squatProperty;

    private BooleanProperty shareProfileProperty;

    public ProfileViewModel(Model model, ViewState viewState){
        this.model = model;
        this.viewState = viewState;

        firstNameProperty= new SimpleStringProperty();
        lastNameProperty= new SimpleStringProperty();
        usernameProperty= new SimpleStringProperty();
        genderProperty= new SimpleStringProperty();
        bmiProperty= new SimpleStringProperty();
        errorProperty= new SimpleStringProperty();

        weightProperty = new SimpleIntegerProperty();
        heightProperty = new SimpleIntegerProperty();
        benchPressProperty = new SimpleIntegerProperty();
        deadliftProperty = new SimpleIntegerProperty();
        squatProperty = new SimpleIntegerProperty();
        shareProfileProperty = new SimpleBooleanProperty();

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



    public StringProperty genderProperty() {
        return genderProperty;
    }



    public StringProperty bmiProperty() {
        return bmiProperty;
    }



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

    @Override
    public void clear() {
        String u = viewState.getProfileUsername();
        User profileUser = model.getTrainee(u);
        int benchPressBest = model.getBestBenchPress(u);
        int squatBest = model.getBestSquat(u);
        int deadliftBest = model.getBestDeadlift(u);

        usernameProperty.set(u);
        errorProperty.set("");
        deadliftProperty.set(deadliftBest);
        squatProperty.set(squatBest);
        benchPressProperty.set(benchPressBest);

        firstNameProperty.set(profileUser.getFirstName());
        lastNameProperty.set(profileUser.getLastName());
        genderProperty.set(profileUser.getGender());
        int h = profileUser.getHeight();
        int w = profileUser.getWeight();

        heightProperty.set(h);
        weightProperty.set(w);
        shareProfileProperty.set(profileUser.isShareProfile() );
        bmiProperty.set( String.valueOf( 1.0*w/(1.0*h*h/100/100) ) );
    }

    public boolean update() {
        int h = heightProperty.get();
        int w = weightProperty().get();
        String u = viewState.getProfileUsername();
        boolean s = shareProfileProperty.get();

        boolean success = model.updateTrainee(u,h,w,s);
        if(success)clear();
        return success;
    }

    public String getGoBack(){
        String b = viewState.getGoBack();

        return b==null ? "home" : b;
    }
}
