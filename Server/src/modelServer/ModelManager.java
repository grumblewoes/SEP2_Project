package modelServer;

import mediator.*;
import modelServer.DAO.implementation.*;
import util.Logger;

import java.sql.SQLException;
import java.util.ArrayList;

public class ModelManager implements Model
{
    private UserDAO userDAO;
    public ModelManager() {
            this.userDAO=new UserDAO();

    }
    @Override public boolean createUser(String firstName, String lastName,
                                        String username, String password, int height, int weight)
    {
        boolean ans = false;
        try
        {
            ans = userDAO.createTrainee(username, password, firstName, lastName,
                height, weight);
            Logger.log("created user: "+ans);
        }
        catch (SQLException e){
            Logger.log(e);
            return false;
        }
        return true;
    }

//    @Override public boolean createUser(String firstName, String lastName,
//        String username, String password, int height, int weight)
//    {
//        Trainee trainee = null;
//        try{
//            trainee = new Trainee(firstName, lastName, username, password, height, weight);
//        }catch (Exception e){
//            return false;
//        }
//
//        if (!trainee.getUserName().equals(traineeList.getUserByUsername(username))) {
//            traineeList.addUser(trainee);
//            System.out.println(traineeList);
//            return true;
//        }
//        return false;
//    }

    @Override public boolean login(String username, String password) {
        boolean ans = false;
        try {

            ans = new UserDAO().login(username, password);
            Logger.log("Logged in ? : " + username + " , " + password + " | " + ans);
        }catch(SQLException e){
                return false;
        }
        return ans;
    }

    @Override public boolean createFolder(String username, String name)
    {
        boolean ans = false;
        try {

            ans = new FolderDAO().createFolder(username, name);
            Logger.log("Created folder ? : " + username + " , " + name + " | " + ans);
        }catch(SQLException e){
            return false;
        }
        return ans;
    }

    @Override public boolean removeFolder(String username, int folderId)
    {
        boolean ans = false;
        try {

            ans = new FolderDAO().removeFolder(username, folderId);
            Logger.log("Removed folder? : " + username + " , " + folderId + " | " + ans);
        }catch(SQLException e){
            return false;
        }
        return ans;
    }

    @Override public boolean editFolder(String username, int folderId, String newName)
    {
        boolean ans = false;
        try {

            ans = new FolderDAO().renameFolder(username, folderId,newName);
            Logger.log("Updated folder? : " + username + " , " + folderId +" , " + newName+ " | " +  ans);
        }catch(SQLException e){
            return false;
        }
        return ans;
    }

    @Override public FolderList getFolderList(String username)
    {
        try{
            return new FolderDAO().getFolderList(username);
        }catch(SQLException e){
            return null;
        }
    }

    @Override
    public boolean addExercise(String username, String exerciseName, int folderId, int weight, int repetition) {
        try{
            return new ExerciseDAO().addExercise(username,exerciseName,folderId,weight,repetition);
        }catch(SQLException e){
            return false;
        }
    }

    @Override
    public boolean removeExercise(int exerciseId) {

        try{
            return new ExerciseDAO().removeExercise(exerciseId);
        }catch(SQLException e){
            return false;
        }

    }
    @Override
    public boolean removeExercisesByName(String name, int folderId) {

        try{
            return new ExerciseDAO().removeExerciseByName(name,folderId);
        }catch(SQLException e){
            return false;
        }

    }

    @Override
    public ExerciseList getExerciseList(int folderId) {
        try{
            return new ExerciseDAO().getExerciseList(folderId);
        }catch(SQLException e){
            return null;
        }
    }

    @Override
    public ExerciseList getExerciseListByNameAndFolderId(String name, int folderId) {
        try{
            return new ExerciseDAO().getExerciseList(folderId).filterByName(name);
        }catch(SQLException e){
            return null;
        }
    }

    @Override
    public ArrayList<String> getPossibleExercises() {
        try{
            return new ExerciseDAO().getPossibleExercises();
        }catch(SQLException e){
            return null;
        }
    }

    @Override
    public int getBestSquat(String username) {
        try{
            return new ExerciseDAO().getBestSquat(username);
        }catch(SQLException e){
            return 0;
        }
    }

    @Override
    public int getBestDeadlift(String username) {
        try{
            return new ExerciseDAO().getBestDeadlift(username);
        }catch(SQLException e){
            return 0;
        }
    }

    @Override
    public int getBestBenchPress(String username) {
        try{
            return new ExerciseDAO().getBestBenchPress(username);
        }catch(SQLException e){
            return 0;
        }
    }

    @Override
    public User getTrainee(String username) {
        try{
            return new UserDAO().getTrainee(username);
        }catch(SQLException e){
            return null;
        }
    }

    @Override
    public boolean updateTrainee(String u, int h, int w, boolean s) {
        return false;
    }

    @Override public boolean updateTrainee(String u, int h, int w,boolean s,String st){
        try{
            return new UserDAO().updateTrainee(u,h,w,s,st);
        }catch(SQLException e){
            return false;
        }
    }


    @Override
    public boolean acceptFriendRequest(String requester_username,String accepter_username) {
        Logger.log("accepting "+ requester_username + " , "+ accepter_username);
        try{
            return new FriendDAO().acceptFriendRequest(requester_username,accepter_username);
        }catch(SQLException e){
            return false;
        }
    }
    @Override
    public boolean rejectFriendRequest(String requester_username,String accepter_username) {
        try{
            Logger.log("rejecting "+ requester_username + " , "+ accepter_username);
            return new FriendDAO().rejectFriendRequest(requester_username,accepter_username);
        }catch(SQLException e){
            return false;
        }
    }

    @Override public boolean sendFriendRequest(String requesterUsername,
        String accepterUsername) {
        try {
            return new FriendDAO().sendFriendRequest(requesterUsername, accepterUsername);
        } catch (SQLException e) {
            return false;
        }
    }


    @Override
    public FriendList getFriends(String username) {
        try{
            return new FriendDAO().getFriends(username);
        }catch(SQLException e){
            return new FriendList();
        }
    }

    @Override
    public ArrayList<String> getFriendRequests(String username) {
        try{
            return new FriendDAO().getFriendRequests(username);
        }catch(SQLException e){
            return new ArrayList<>();
        }
    }

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
    public boolean isCoach(String username) {
        try{
            return new CoachDAO().isCoach(username);
        }catch(SQLException e){
            return false;
        }
    }

    @Override public boolean removeCoachAssignment(String traineeUsername)
    {
        try{
            return new CoachDAO().removeCoachAssignment(traineeUsername);
        }catch(SQLException e){
            return false;
        }
    }

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

    @Override public ArrayList<String> getTraineeList(String username)
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

    @Override public boolean addCoach(String coachUsername,
        String coachPassword, String coachName, String coachLName,
        int coachHeight, int coachWeight, int pbBench, int pbSquat,
        int pbLift, String status, boolean share)
    {
        try {
            return new CoachDAO().addCoach(coachUsername, coachPassword, coachName, coachLName, coachHeight, coachWeight, pbBench, pbSquat, pbLift, status, share);
        }
        catch (SQLException e)
        {
            return false;
        }
    }

    @Override public boolean removeCoach(String name)
    {
        try {
            return new CoachDAO().removeCoach(name);
        }
        catch (SQLException e)
        {
            return false;
        }
    }

    @Override
    public User getCoach(String traineeUsername) {
        try {
            return new CoachDAO().getCoach(traineeUsername);
        }
        catch (SQLException e)
        {
            return null;
        }
    }
}