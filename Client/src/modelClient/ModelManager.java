package modelClient;

import mediator.*;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.GeneralListener;
import utility.observer.listener.LocalListener;
import utility.observer.subject.PropertyChangeHandler;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Class that delegates tasks from UI to the client mediator.
 * @author Damian Trafiałek
 * @version 1.0
 */
public class ModelManager implements Model, LocalListener<String, String> {
    private Client client;
    private PropertyChangeHandler<String, String> property;

    /**
     * 1-argument constructor that initialises the class object and sets the client and listeners.
     *
     * @param client - Client mediator
     */
    public ModelManager(Client client) {
        this.client = client;
        this.property = new PropertyChangeHandler<>(this);
        this.client.addListener(this);
        //property = new PropertyChangeSupport(this);
        //listens for new info from the server
        //client.addListener(this);
    }

    /**
   * Calls client to create a new user.
   *
   * @param firstName - string value
   *
   * @param lastName - string value
   *
   * @param userName - string value
   *
   * @param password - string value
   *
   * @param height - integer value
   *
   * @param weight - integer value
   *
   *
   * @return boolean if everything went ok
   */
    public boolean createUser(String firstName, String lastName, String userName, String password, int height, int weight) {
        return client.createUser(firstName, lastName, userName, password, height, weight);
    }

    /**
   * Calls client to check if the user and password match.
   *
   * @param username - string value
   *
   * @param password - string value
   *
   *
   * @return boolean if everything went ok
   *
   */
    public boolean login(String username, String password) {
        return client.login(username, password);
    }

    /**
   * Calls sever to create a new folder.
   *
   * @param username - string value
   *
   * @param name - string value
   *
   *
   * @return boolean if everything went ok
   *
   */
    @Override
    public boolean createFolder(String username, String name) {
        return client.createFolder(username, name);
    }

    @Override
/**
   * Calls client to remove folder.
   *
   * @param username - string value
   *
   * @param folderId - integer value
   *
   *
   * @return boolean if everything went ok
   *
   */
    public boolean removeFolder(String username, int folderId) {
        return client.removeFolder(username, folderId);
    }

    @Override
     /**
   * Calls client to change the folder name.
   *
   * @param username - string value
   *
   * @param folderId - integer value
   *
   * @param newName - string value
   *
   *
   * @return boolean if everything went ok
   *
   */
    public boolean editFolder(String username, int folderId, String newName) {
        return client.editFolder(username, folderId, newName);
    }

    /**
   * Gets the list of user's folder.
   *
   * @param username - string value
   *
   *
   * @return list of folders
   *
   */
    @Override
    public FolderList getFolderList(String username) {
        return client.getFolderList(username);
    }

    @Override
 /**
   * Gets the list of exercises within the folder.
   *
   * @param folderId - integer value
   *
   *
   * @return list of exercises
   *
   */
    public ExerciseList getExerciseList(int folderId) {
        return client.getExerciseList(folderId);
    }

    @Override
 /**
   * Gets the list of exercises by name and folder id.
   *
   * @param name - string value
   *
   * @param folderId - integer value
   * @return ExerciseList - list of exercises
   */
    public ExerciseList getExerciseListByNameAndFolderId(String name, int folderId) {
        return client.getExerciseListByNameAndFolderId(name, folderId);
    }

    @Override
  /**
   * Removes exercises from database.
   *
   * @param exerciseId - integer value
   *
   *
   * @return boolean if everything went ok
   *
   */
    public boolean removeExercise(int exerciseId) {
        return client.removeExercise(exerciseId);
    }

    @Override
/**
   * Removes exercises by name and folder id from database.
   *
   * @param name - string value
   *
   * @param folderId - integer value
   *
   *
   * @return boolean if everything went ok
   *
   */
    public boolean removeExercisesByName(String name, int folderId) {
        return client.removeExercisesByName(name, folderId);
    }

  /**
   * Calls client to add the exercise to the database.
   * @param username - string value
   * @param exerciseName - string value
   * @param folderId - integer value
   * @param weight - integer value
   * @param repetitions - integer value
   * @return boolean if everything went ok
   */
    @Override
    public boolean addExercise(String username, String exerciseName, int folderId, int weight, int repetitions) {
        return client.addExercise(username, exerciseName, folderId, weight, repetitions);
    }

    /**
   * Calls the client to get the list of possible exercises.
   *
   *
   * @return array list of possible exercises names
   *
   */
    @Override
    public ArrayList<String> getPossibleExercises() {
        return client.getPossibleExercises();
    }
 
/**
   * Gets the trainee from client.
   *
   * @param username - stirng value
   *
   *
   * @return User
   *
   */
    @Override
    public User getTrainee(String username) {
        return client.getTrainee(username);
    }

    @Override
 /**
   * Calls client to update the trainee.
   * @param u - trainee username
   * @param h - trainee height
   * @param w - trainee weight
   * @param s - trainee wants to share their profile
   * @param st - trainee status
   * @return boolean if everything went ok
   */
    public boolean updateTrainee(String u, int h, int w, boolean s, String st) {
        return client.updateTrainee(u, h, w, s, st);
    }

 /**
   * Calls the client to accept friend request.
   * @param requester_username - string value
   * @param accepter_username - string value
   * @return boolean if everything went ok
   */
    @Override
    public boolean acceptFriendRequest(String requester_username, String accepter_username) {
        return client.acceptFriendRequest(requester_username, accepter_username);
    }

/**
   * Calls the client to reject friend request.
   * @param requester_username - string value
   * @param accepter_username - string value
   * @return boolean if everything went ok
   */
    @Override
    public boolean rejectFriendRequest(String requester_username, String accepter_username) {
        return client.rejectFriendRequest(requester_username, accepter_username);
    }

/**
   * Calls the client to get the list of trainee's friends.
   *
   * @param username - string value
   *
   * @return list of friends
   *
   */
    @Override
    public FriendList getFriends(String username) {
        return client.getFriends(username);
    }

/**
   * Calls the client to get the list of the friend requests.
   *
   * @param username - trainee username - string value
   *
   *
   * @return ArrayList of friend requests usernames
   *
   */
    @Override
    public ArrayList<String> getFriendRequests(String username) {
        return client.getFriendRequests(username);
    }

  /**
   * Calls the client to get the list of meeting requests.
   *
   * @param coach - string value
   *
   *
   * @return arrayList of meeting requests
   *
   */
    @Override
    public ArrayList<String> getMeetingRequests(String coach) {
        return client.getMeetingRequests(coach);
    }

    @Override
/**
 *  Methods that reeives the notification.
 *
 */
    public void propertyChange(ObserverEvent<String, String> event) {
        property.firePropertyChange(event);
    }


    @Override
/**
 *  Method that adds the listener to the subject.
 *
 *
 * @return
 *
 */
    public boolean addListener(GeneralListener<String, String> listener, String... propertyNames) {
        property.addListener(listener, propertyNames);
        return true;
    }

    @Override
/**
 * Method that removes the listener to the subject.
 *
 *
 * @return
 *
 */
    public boolean removeListener(GeneralListener<String, String> listener, String... propertyNames) {
        property.addListener(listener, propertyNames);
        return true;
    }

/**
   * Calls the client to send the friend request.
   * @param requesterUsername
   * @param accepterUsername
   * @return boolean if everything went ok
   */
    @Override
    public boolean sendFriendRequest(String requesterUsername,
                                     String accepterUsername) {
        return client.sendFriendRequest(requesterUsername, accepterUsername);
    }

  /**
   * Calls the client to remove a friend.
   * @param requesterUsername
   * @param accepterUsername
   * @return boolean if everything went ok
   */
    @Override
    public boolean removeFriend(String requesterUsername,
                                String accepterUsername) {
        return client.removeFriend(requesterUsername, accepterUsername);

    }

 /**
   * Calls the client to request the coach for a trainee.
   * @param requesterUsername
   * @param accepterUsername
   * @return boolean if everything went ok
   */
    @Override
    public boolean requestCoach(String requesterUsername, String accepterUsername) {
        return client.requestCoach(requesterUsername, accepterUsername);
    }

  /**
   * Calls the client to get the coach of the trainee.
   *
   * @param traineeUsername - string value
   *
   *
   * @return User
   *
   */
    @Override
    public User getCoach(String traineeUsername) {
        return client.getCoach(traineeUsername);
    }

  /**
   * Calls the client to check if the user is a coach.
   *
   * @param username - string value
   *
   *
   * @return boolean if user is a coach
   *
   */
    @Override
    public boolean isCoach(String username) {
        return client.isCoach(username);
    }

/**
   * Calls the client to remove the coach fromthe trainee
   *
   * @param traineeUsername - string value
   *
   *
   * @return boolean if everything went ok
   *
   */
    @Override
    public boolean removeCoachAssignment(String traineeUsername) {
        return client.removeCoachAssignment(traineeUsername);
    }

 /**
   * Calls the client to accept trainee request.
   * @param traineeUsername - string value
   * @param coachUsername - string value
   * @return boolean if everything went ok
   */
    @Override
    public boolean acceptRequest(String traineeUsername,
                                 String coachUsername) {
        return client.acceptRequest(traineeUsername, coachUsername);
    }

    @Override
/**
 *  Method that delegates the task to disconnect the remote listener to the client mediator.
 *
 * @param username - string value
 *
 */
    public void disconnectListener(String username) {
        client.disconnectListener(username);
    }

   /**
   * Calls the client to remove the meeting.
   * @param traineeUsername - string value
   * @param coachName - string value
   * @param date - localDate value
   * @return boolean if everything went ok
   */
    @Override
    public boolean removeMeeting(String traineeUsername, String coachName, LocalDate date) {
        return client.removeMeeting(traineeUsername, coachName, date);
    }

  /**
   * Calls the client to deny trainee request.
   * @param traineeUsername - string value
   * @return boolean if everything went ok
   */
    @Override
    public boolean denyRequest(String traineeUsername) {
        return client.denyRequest(traineeUsername);
    }

  /**
   * Calls the client to remove trainee from a roaster.
   * @param traineeUsername - string value
   * @return boolean if everything went ok
   */
    @Override
    public boolean removeTraineeFromRoster(String traineeUsername) {
        return client.removeTraineeFromRoster(traineeUsername);
    }

  /**
   * Calls the client to get the list of trainees.
   *
   * @param username - string value
   *
   *
   * @return TraineeList
   *
   */
    @Override
    public TraineeList getTraineeList(String username) {
        return client.getTraineeList(username);
    }

  /**
   * Calls the client to get the list of trainees requests.
   *
   * @param username - string value
   *
   *
   * @return arraylist of strings (trainees request usernames)
   *
   */
    @Override
    public ArrayList<String> getTraineeRequest(String username) {
        return client.getTraineeRequest(username);
    }

    @Override
  /**
   * Calls the client to approve the meeting.
   * @param trainee - string value
   * @param coach - string value
   * @param date - localDate value
   * @return boolean if everything went ok
   */
    public boolean approveMeeting(String trainee, String coach, LocalDate date) {
        return client.approveMeeting(trainee, coach, date);
    }

  /**
   * Calls the client to deny the meeting.
   * @param trainee - string value
   * @param coach - string value
   * @param date - localDate value
   * @return boolean if everything went ok
   */
    @Override
    public boolean denyMeeting(String trainee, String coach, LocalDate date) {
        return client.denyMeeting(trainee, coach, date);
    }

  /**
   * Calls the client to get the list of coaches meetings.
   *
   * @param coach - string value
   *
   *
   * @return arrayList of coaches meetings
   *
   */
    @Override
    public ArrayList<String> getCoachMeetings(String coach) {
        return client.getCoachMeetings(coach);
    }

      /**
   * Calls the client to get the list of trainee's meetings
   *
   * @param traineeUsername - string value
   *
   *
   * @return arrayList of strings
   *
   */
    @Override
    public ArrayList<String> getTraineeMeetingList(String traineeUsername) {
        return client.getTraineeMeetingList(traineeUsername);
    }

    /**
   * Calls the client to get the list of trainee meeting requests.
   *
   * @param traineeUsername - string value
   *
   *
   * @return list of meetings requests
   *
   */
    @Override
    public ArrayList<String> getTraineeMeetingRequests(String traineeUsername) {
        return client.getTraineeMeetingRequests(traineeUsername);
    }

 /**
   * Calls the client to add the meeting request.
   * @param traineeUsername - string value
   * @param coachUsername - string value
   * @param dateOfMeeting - localDate value
   * @return boolean if everything went ok
   */
    @Override
    public boolean sendMeetingRequest(String traineeUsername,
                                      String coachUsername, LocalDate dateOfMeeting) {
        return client.sendMeetingRequest(traineeUsername, coachUsername, dateOfMeeting);
    }

 /**
   * Method that gets the dates that are already taken/reserved with the coach.
   *
   * @param coachUsername - username
   *
   *
   * @return ArrayList of local Dates
   *
   */
    @Override
    public ArrayList<LocalDate> getTakenDates(String coachUsername) {
        return client.getTakenDates(coachUsername);
    }

  /**
   * Calls the client to get the list of squat leaders.
   *
   *
   * @return TraineeList
   *
   */
    @Override
    public TraineeList getSquatLeaders() {
        return client.getSquatLeaders();
    }

 /**
   * Calls the client to get the deadlift leaders
   *
   *
   * @return TraineList
   *
   */
    @Override
    public TraineeList getDeadliftLeaders() {
        return client.getDeadliftLeaders();
    }
    
  /**
   * Calls the client to get the bench press leaders.
   *
   *
   * @return TraineeList
   *
   */
    @Override
    public TraineeList getBenchLeaders() {
        return client.getBenchLeaders();
    }

}


