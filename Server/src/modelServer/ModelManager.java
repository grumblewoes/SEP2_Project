package modelServer;

import mediator.*;
import modelServer.DAO.implementation.*;
import modelServer.DAO.implementation.ExerciseAdminDAO;
import modelServer.DAO.implementation.ExerciseDAO;
import modelServer.DAO.implementation.FolderDAO;
import modelServer.DAO.implementation.UserDAO;
import util.Logger;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * The ModelManager class implements the Model interface and manages the application's data and logic.
 * It provides methods for creating users, logging in, managing folders and exercises, and handling user interactions with the system.
 *
 * 
 * @author Jakub Cerovsky, Damian Trafiałek, Julija Gramovica, Anna Pomerantz
 * @version 1.0
 */
public class ModelManager implements Model
{
  private UserDAO userDAO;
  private ExerciseAdminDAO exerciseAdminDAO;
  private MeetingDAO meetingDAO;

  /**
   * 0-argument constructor, creating a ModelManager object
   * 
   * 
   */
  public ModelManager()
  {
    this.userDAO = new UserDAO();
    this.meetingDAO = new MeetingDAO();

  }
  /**
   * Creates a new user with the specified details.
   *
   * @param firstName the first name of the user
   * @param lastName the last name of the user
   * @param username the username of the user
   * @param password the password of the user
   * @param height the height of the user
   * @param weight the weight of the user
   * @return true if the user was created successfully, false otherwise
   */

  @Override public boolean createUser(String firstName, String lastName,
      String username, String password, int height, int weight)
  {
    boolean ans = false;
    try
    {
      ans = new TraineeDAO().createTrainee(username, password, firstName, lastName,
          height, weight);
      Logger.log("created user: " + ans);
    }
    catch (SQLException e)
    {
      Logger.log(e);
      return false;
    }
    return true;
  }


  /**
   * Logs in a user with the specified username and password.
   *
   * @param username the username of the user
   * @param password the password of the user
   * @return true if the login was successful, false otherwise
   *        
   */
  @Override public boolean login(String username, String password)
  {
    boolean ans = false;
    try
    {

      ans = new UserDAO().login(username, password);
      Logger.log("Logged in ? : " + username + " , " + password + " | " + ans);
    }
    catch (SQLException e)
    {
      return false;
    }
    return ans;
  }

  /**
   * Creates a new folder for the given user.
   *
   * @param username the username of the user
   * @param name     the name of the folder to be created
   * @return true if the folder is created successfully, false otherwise
   *        
   */
  @Override public boolean createFolder(String username, String name)
  {
    boolean ans = false;
    try
    {

      ans = new FolderDAO().createFolder(username, name);
      Logger.log("Created folder ? : " + username + " , " + name + " | " + ans);
    }
    catch (SQLException e)
    {
      return false;
    }
    return ans;
  }

  /**
   * Removes the folder with the specified folder Id for the given user.
   *
   * @param username  the username of the user
   * @param folderId  the ID of the folder to be removed
   * @return true if the folder is removed successfully, false otherwise
   */
  @Override public boolean removeFolder(String username, int folderId)
  {
    boolean ans = false;
    try
    {

      ans = new FolderDAO().removeFolder(username, folderId);
      Logger.log(
          "Removed folder? : " + username + " , " + folderId + " | " + ans);
    }
    catch (SQLException e)
    {
      return false;
    }
    return ans;
  }
  /**
   * Renames the folder with the specified folder Id for the given user.
   *
   * @param username  the username of the user
   * @param folderId  the ID of the folder to be renamed
   * @param newName   the new name for the folder
   * @return true if the folder is renamed successfully, false otherwise
   */

  @Override public boolean editFolder(String username, int folderId,
      String newName)
  {
    boolean ans = false;
    try
    {

      ans = new FolderDAO().renameFolder(username, folderId, newName);
      Logger.log(
          "Updated folder? : " + username + " , " + folderId + " , " + newName
              + " | " + ans);
    }
    catch (SQLException e)
    {
      return false;
    }
    return ans;
  }

  /**
   * Retrieves the list of folders for the given user.
   *
   * @param username the username of the user
   * @return the list of folders for the user, or null if SQLException is caught
   */
  @Override public FolderList getFolderList(String username)
  {
    try
    {
      return new FolderDAO().getFolderList(username);
    }
    catch (SQLException e)
    {
      return null;
    }
  }
  /**
   * Adds an exercise to the specified folder for the given user.
   *
   * @param username     the username of the user
   * @param exerciseName the name of the exercise to be added
   * @param folderId     the ID of the folder to which the exercise should be added
   * @param weight       the weight of the exercise
   * @param repetition   the number of repetitions for the exercise
   * @return true if the exercise is added successfully, false otherwise
   */

  @Override public boolean addExercise(String username, String exerciseName,
      int folderId, int weight, int repetition)
  {
    try
    {
      return new ExerciseDAO().addExercise(username, exerciseName, folderId,
          weight, repetition);
    }
    catch (SQLException e)
    {
      return false;
    }
  }

  /**
   * Removes the exercise with the specified exercise Id.
   *
   * @param exerciseId the ID of the exercise to be removed
   * @return true if the exercise is removed successfully, false otherwise
   */
  @Override public boolean removeExercise(int exerciseId)
  {
    try
    {
      return new ExerciseDAO().removeExercise(exerciseId);
    }
    catch (SQLException e)
    {
      return false;
    }
  }

  /**
   * Removes exercises with the specified name from the given folder.
   *
   * @param name     the name of the exercises to be removed
   * @param folderId the ID of the folder from which the exercises should be removed
   * @return true if the exercises are removed successfully, false otherwise
   */
  @Override public boolean removeExercisesByName(String name, int folderId)
  {

    try
    {
      return new ExerciseDAO().removeExerciseByName(name, folderId);
    }
    catch (SQLException e)
    {
      return false;
    }

  }

  /**
   * Retrieves the list of exercises for the specified folder.
   *
   * @param folderId the ID of the folder
   * @return the list of exercises for the folder, or null if an error occurs
   */
  @Override public ExerciseList getExerciseList(int folderId)
  {
    try
    {
      return new ExerciseDAO().getExerciseList(folderId);
    }
    catch (SQLException e)
    {
      return null;
    }
  }
  /**
   * Retrieves an ExerciseList by name and folder ID.
   *
   * @param name       the name of the exercise list
   * @param folderId   the ID of the folder
   * @return           the ExerciseList matching the specified name and folder ID,
   *                   or null if an SQLException occurs
   */

  @Override public ExerciseList getExerciseListByNameAndFolderId(String name,
      int folderId)
  {
    try
    {
      return new ExerciseDAO().getExerciseList(folderId).filterByName(name);
    }
    catch (SQLException e)
    {
      return null;
    }
  }

  /**
   * Retrieves a list of possible exercises.
   *
   * @return           an ArrayList of possible exercises,
   *                   or null if an SQLException occurs
   */
  @Override public ArrayList<String> getPossibleExercises()
  {
    try
    {
      return new ExerciseDAO().getPossibleExercises();
    }
    catch (SQLException e)
    {
      return null;
    }
  }

  /**
   * Retrieves a trainee by username.
   *
   * @param username   the username of the trainee
   * @return           the User object representing the trainee,
   *                   or null if an SQLException occurs
   */
  @Override public User getTrainee(String username)
  {
    try
    {
      return new TraineeDAO().getTrainee(username);
    }
    catch (SQLException e)
    {
      return null;
    }
  }

  /**
   * Adds an exercise with the specified title.
   *
   * @param title      the title of the exercise to be added
   * @return           true if the exercise is added successfully,
   *                   false if an SQLException occurs
   */
  @Override public boolean addExercise(String title)
  {
    try{
      return new ExerciseAdminDAO().addExercise(title);
    }catch(SQLException e){
      return false;
    }
  }

  /**
   * Removes an exercise with the specified title.
   *
   * @param title      the title of the exercise to be removed
   * @return           true if the exercise is removed successfully,
   *                   false if an SQLException occurs
   */
  @Override public boolean removeExercise(String title)
  {
    try{
      return new ExerciseAdminDAO().removeExercise(title);
    }catch(SQLException e){
      return false;
    }
  }

  /**
   * Updates the trainee with the specified details.
   *
   * @param u          the username of the trainee
   * @param h          the height of the trainee
   * @param w          the weight of the trainee
   * @param s          sharing of trainee's profile
   * @param st         the status text of the trainee
   * @return           true if the trainee is updated successfully,
   *                   false if an SQLException occurs
   */
  @Override public boolean updateTrainee(String u, int h, int w,boolean s,String st){
        try{
            return new TraineeDAO().updateTrainee(u,h,w,s,st);
        }catch(SQLException e){
            return false;
        }
    }


    @Override
    /**
     * Accepts a friend request between two users.
     *
     * @param requester_username   the username of the requester
     * @param accepter_username    the username of the accepter
     * @return                     true if the friend request is accepted successfully,
     *                             false if an SQLException occurs
     */
    public boolean acceptFriendRequest(String requester_username,String accepter_username) {
        Logger.log("accepting "+ requester_username + " , "+ accepter_username);
        try{
            return new FriendDAO().acceptFriendRequest(requester_username,accepter_username);
        }catch(SQLException e){
            return false;
        }
    }
    @Override
    /**
     * Rejects a friend request between two users.
     *
     * @param requester_username   the username of the requester
     * @param accepter_username    the username of the accepter
     * @return                     true if the friend request is rejected successfully,
     *                             false if an SQLException occurs
     */
    public boolean rejectFriendRequest(String requester_username,String accepter_username) {
        try{
            Logger.log("rejecting "+ requester_username + " , "+ accepter_username);
            return new FriendDAO().rejectFriendRequest(requester_username,accepter_username);
        }catch(SQLException e){
            return false;
        }
    }
  /**
   * Sends a friend request from the requester to the accepter.
   *
   * @param requesterUsername   the username of the requester
   * @param accepterUsername    the username of the accepter
   * @return                    true if the friend request is sent successfully,
   *                            false if an SQLException occurs
   */

    @Override public boolean sendFriendRequest(String requesterUsername,
        String accepterUsername) {
        try {
            return new FriendDAO().sendFriendRequest(requesterUsername, accepterUsername);
        } catch (SQLException e) {
            return false;
        }
    }


    @Override
    /**
     * Retrieves the list of friends for a given username.
     *
     * @param username   the username of the user
     * @return           the FriendList object containing the user's friends,
     *                   or an empty FriendList if an SQLException occurs
     */
    public FriendList getFriends(String username) {
        try{
            return new FriendDAO().getFriends(username);
        }catch(SQLException e){
            return new FriendList();
        }
    }

    @Override
    /**
     * Retrieves the list of friend requests for a given username.
     *
     * @param username   the username of the user
     * @return           an ArrayList of friend requests received by the user,
     *                   or an empty ArrayList if an SQLException occurs
     */
    public ArrayList<String> getFriendRequests(String username) {
        try{
            return new FriendDAO().getFriendRequests(username);
        }catch(SQLException e){
            return new ArrayList<>();
        }
    }
  /**
   * Removes a friend relationship between two users.
   *
   * @param requesterUsername   the username of the requester
   * @param accepterUsername    the username of the accepter
   * @return                    true if the friend relationship is removed successfully,
   *                            false if an SQLException occurs
   */

    @Override public boolean removeFriend(String requesterUsername,
        String accepterUsername)
    {
        try{
            return new FriendDAO().removeFriend(requesterUsername,accepterUsername);
        }catch(SQLException e){
            return false;
        }
    }

    @Override

    public boolean requestCoach(String requesterUsername, String accepterUsername) {
        try{
            return new CoachDAO().requestCoach(requesterUsername,accepterUsername);
        }catch(SQLException e){
            return false;
        }
    }

    @Override
    /**
     * Checks if a user is a coach.
     *
     * @param username   the username of the user
     * @return           true if the user is a coach, false otherwise or if an SQLException occurs
     */
    public boolean isCoach(String username) {
        try{
            return new CoachDAO().isCoach(username);
        }catch(SQLException e){
            return false;
        }
    }

  /**
   * Removes the coach for a trainee.
   *
   * @param traineeUsername the username of the trainee
   * @return true if the coach assignment is successfully removed, false otherwise
   */
    @Override public boolean removeCoachAssignment(String traineeUsername)
    {
        try{
            return new CoachDAO().removeCoachAssignment(traineeUsername);
        }catch(SQLException e){
            return false;
        }
    }

  /**
   * Adds a coach with the specified details.
   *
   * @param coachUsername the username of the coach
   * @param coachPassword the password of the coach
   * @param coachName the name of the coach
   * @param coachLName the last name of the coach
   * @param coachHeight the height of the coach
   * @param coachWeight the weight of the coach
   * @param pbBench the personal best bench press of the coach
   * @param pbSquat the personal best squat of the coach
   * @param pbLift the personal best lift of the coach
   * @param status the status of the coach
   * @return true if the coach is successfully added, false otherwise
   */


  @Override public boolean addCoach(String coachUsername,
      String coachPassword, String coachName, String coachLName,
      int coachHeight, int coachWeight, int pbBench, int pbSquat,
      int pbLift, String status )
  {
    try {
      return new CoachAdminDAO().addCoach(coachUsername, coachPassword, coachName, coachLName, coachHeight, coachWeight, pbBench, pbSquat, pbLift, status);
    }
    catch (SQLException e)
    {
      return false;
    }
  }
  /**
   * Accepts a coach request from a trainee.
   *
   * @param traineeUsername the username of the trainee
   * @param coachUsername the username of the coach
   * @return true if the coach request is successfully accepted, false otherwise
   */

  @Override public boolean acceptRequest(String traineeUsername,
      String coachUsername)
  {
    try
    {
      return new CoachDAO().acceptRequest(traineeUsername, coachUsername);
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
  }

  /**
   * Denies a coach request from a trainee.
   *
   * @param traineeUsername the username of the trainee
   * @return true if the coach request is successfully denied, false otherwise
   */
  @Override public boolean denyRequest(String traineeUsername)
  {
    try
    {
      return new CoachDAO().denyRequest(traineeUsername);
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
  }

  /**
   * Removes a trainee from the coach's roster.
   *
   * @param traineeUsername the username of the trainee
   * @return true if the trainee is successfully removed, false otherwise
   */
  @Override public boolean removeTraineeFromRoster(String traineeUsername)
  {
    try
    {
      return new CoachDAO().removeTraineeFromRoster(traineeUsername);
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
  }

  /**
   * Retrieves the list of trainees associated with a coach.
   *
   * @param username the username of the coach
   * @return the list of trainees associated with the coach
   */
  @Override public TraineeList getTraineeList(String username)
  {
    try
    {
      return new CoachDAO().getTraineeList(username);
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
  }
  /**
   * Retrieves the list of all trainee requests received by a coach.
   *
   * @param username the username of the coach
   * @return the list of trainee requests received by the coach
   */
  @Override public ArrayList<String> getTraineeRequest(String username)
  {
    try
    {
      return new CoachDAO().getTraineeRequest(username);
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
  }

  /**
   * Retrieves the list of meeting requests for a coach.
   *
   * @param coach the username of the coach
   * @return the list of meeting requests for the coach
   */
  @Override public ArrayList<String> getMeetingRequests(String coach)
  {
    try
    {
      return new MeetingDAO().getCoachMeetingRequests(coach);
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
  }
  /**
   * Removes a coach with the specified name.
   *
   * @param name the name of the coach
   * @return true if the coach is successfully removed, false otherwise
   */
  @Override public boolean removeCoach(String name)
    {
        try {
            return new CoachAdminDAO().removeCoach(name);
        }
        catch (SQLException e)
        {
            return false;
        }
    }

    @Override

    public User getCoach(String traineeUsername) {
        try {
            return new TraineeDAO().getCoach(traineeUsername);
        }
        catch (SQLException e)
        {
            return null;
        }
    }

  /**
   * Retrieves the list of meetings for a trainee.
   *
   * @param traineeUsername the username of the trainee
   * @return the list of meetings for the trainee
   */
  @Override public ArrayList<String> getTraineeMeetingList(String traineeUsername)
  {
    try {
      return new MeetingDAO().getTraineeMeetingList(traineeUsername);
    }
    catch (SQLException e)
    {
      return null;
    }
  }


  /**
   * Retrieves the list of meeting requests for a trainee.
   *
   * @param traineeUsername the username of the trainee
   * @return the list of meeting requests for the trainee
   */
  @Override public ArrayList<String> getTraineeMeetingRequests(String traineeUsername)
  {
    try {
      return new MeetingDAO().getTraineeMeetingRequests(traineeUsername);
    }
    catch (SQLException e)
    {
      return null;
    }
  }
  /**
   * Sends a meeting request from a trainee to a coach for a specified date.
   *
   * @param traineeUsername the username of the trainee sending the meeting request
   * @param coachUsername the username of the coach receiving the meeting request
   * @param dateOfMeeting the date of the meeting
   * @return true if the meeting request was sent successfully, false otherwise
   */

  @Override public boolean sendMeetingRequest(String traineeUsername,
      String coachUsername, LocalDate dateOfMeeting)
  {
    try {
      return new MeetingDAO().sendMeetingRequest(traineeUsername,coachUsername,dateOfMeeting);
    }
    catch (SQLException e)
    {
      return false;
    }
  }

  /**
   * Retrieves a list of taken dates for a coach.
   *
   * @param coachUsername the username of the coach
   * @return the list of taken dates for the coach, or null if an error occurs
   */
  @Override public ArrayList<LocalDate> getTakenDates(String coachUsername)
  {
    try {
      return new MeetingDAO().getTakenDates(coachUsername);
    }
    catch (SQLException e)
    {
      return null;
    }
  }
  /**
   * Retrieves the leaderboard of trainees based on their squat performance.
   *
   * @return the leaderboard of trainees based on squat performance, or null if an error occurs
   */
  @Override public TraineeList getSquatLeaders()
  {
    try {
      return new LeaderboardDAO().getSquatLeaders();
    }
    catch (SQLException e)
    {
      return null;
    }
  }
  /**
   * Retrieves the leaderboard of trainees based on their deadlift performance.
   *
   * @return the leaderboard of trainees based on deadlift performance, or null if an error occurs
   */
  @Override public TraineeList getDeadliftLeaders()
  {
    try {
      return new LeaderboardDAO().getDeadliftLeaders();
    }
    catch (SQLException e)
    {
      return null;
    }
  }
  /**
   * Retrieves the leaderboard of trainees based on their bench press performance.
   *
   * @return the leaderboard of trainees based on bench press performance, or null if an error occurs
   */
  @Override public TraineeList getBenchLeaders()
  {
    try {
      return new LeaderboardDAO().getBenchLeaders();
    }
    catch (SQLException e)
    {
      return null;
    }
  }
  /**
   * Removes an existing meeting between a trainee and a coach for a specified date.
   *
   * @param traineeUsername the username of the trainee
   * @param coachUsername the username of the coach
   * @param dateOfMeeting the date of the meeting
   * @return true if the meeting removal was approved successfully, false otherwise
   */

  @Override public boolean removeMeeting(String traineeUsername,
      String coachUsername, LocalDate dateOfMeeting)
  {
    try {
      return new MeetingDAO().removeMeeting(traineeUsername,coachUsername,dateOfMeeting);
    }
    catch (SQLException e)
    {
      return false;
    }
  }

  /**
   * Approves a meeting request between a trainee and a coach for a specified date.
   *
   * @param trainee the username of the trainee
   * @param coach the username of the coach
   * @param date the date of the meeting
   * @return true if the meeting request was approved successfully, false otherwise
   */
  @Override public boolean approveMeeting(String trainee, String coach,
      LocalDate date)
  {
    try
    {
      return new MeetingDAO().approveMeeting(trainee, coach, date);
    }
    catch (SQLException e)
    {
      return false;
    }
  }
  /**
   * Denies a meeting request between a trainee and a coach for a specified date.
   *
   * @param trainee the username of the trainee
   * @param coach the username of the coach
   * @param date the date of the meeting
   * @return true if the meeting request was denied successfully, false otherwise
   * @throws RuntimeException if an SQL exception occurs
   */

  @Override public boolean denyMeeting(String trainee, String coach,
      LocalDate date)
  {
    try
    {
      return new MeetingDAO().denyMeeting(trainee, coach, date);
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
  }

  /**
   * Retrieves a list of meetings associated with a coach.
   *
   * @param coach the username of the coach
   * @return the list of meetings associated with the coach, or null if an error occurs
   * @throws RuntimeException if an SQL exception occurs
   */
  @Override public ArrayList<String> getCoachMeetings(String coach)
  {
    try
    {
      return new MeetingDAO().getCoachMeetings(coach);
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
  }
}