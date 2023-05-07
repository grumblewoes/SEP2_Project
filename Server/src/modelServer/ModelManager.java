package modelServer;

import com.sun.security.jgss.GSSUtil;
import modelServer.DAO.implementation.UserDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public class ModelManager implements Model
{
    private UserDAO userDAO;
    private TraineeList traineeList;
    public ModelManager() {
            this.userDAO=new UserDAO();
            this.traineeList=new TraineeList();

    }
    @Override public boolean createUser(String firstName, String lastName,
                                        String username, String password, int height, int weight)
    {
        try
        {
            userDAO.createTrainee(username, password, firstName, lastName,
                height, weight);
        }
        catch (SQLException e){
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
        Trainee trainee = (Trainee) traineeList.getUserByUsername(username);

        //if trainee doesn't exist on the list
        if (trainee == null)
            System.out.println("Server Model Manager: User does not appear to be in the system.");

        else {
            if (trainee.getPassword().equals(password))
            {
                System.out.println("Server Model Manager: User logged in.");
                return true;
            }
            System.out.println("Server Model Manager: Incorrect password.");
        }
        return false;
    }

//    @Override public boolean createFolder(String username, String name)
//    {
//        if (!(traineeList.getUserByUsername(username)==null)){
//            Folder folder = new Folder(name);
//            getFolderList(username).add(folder);
//            System.out.println("New folder created successfully");
//        }else{
//            System.out.println("Could not create new folder");
//        }
//        return false;
//    }

    @Override public boolean createFolder(String username, String name)
    {
        try
        {
            userDAO.createFolder(username,name);
        }
        catch (SQLException e){
            return false;
        }
        return true;
    }

//    @Override public boolean removeFolder(String username, String name)
//    {
//        if (!(traineeList.getUserByUsername(username)==null)){
//            traineeList.getUserByUsername(username).getFolderList().remove(name);
//            System.out.println("Folder was successfully removed");
//        }else{
//            System.out.println("Folder could not be removed");
//        }
//        return false;
//    }
    @Override public boolean removeFolder(String username, String name)
    {
        try
        {
            userDAO.removeFolder(username,name);
        }
        catch (SQLException e){
            return false;
        }
        return true;
    }
//
//    @Override public boolean editFolder(String username, String oldName,
//        String newName)
//    {
//        if (!(traineeList.getUserByUsername(username)==null)){
//            traineeList.getUserByUsername(username).getFolder().edit(username, oldName, newName);
//        }else{
//            System.out.println("Folder could not be edited");
//        }
//        return false;
//    }

    @Override public boolean editFolder(String username, String oldName, String newName)
    {
        try
        {
            userDAO.editFolder(username,oldName,newName);
        }
        catch (SQLException e){
            return false;
        }
        return true;
    }

    @Override public ArrayList<String> getFolderList(String username)
    {
        return traineeList.getUserByUsername(username)
    }

    @Override public boolean addExercise(String username, String folderName,
        int repetitions, int weight)
    {

    }

    @Override public boolean removeExercise(String username, String folderName,
        String exerciseName)
    {
        return false;
    }

    @Override public ArrayList<String> getExercise(String username,
        String folderName)
    {
        return null;
    }

    @Override public ArrayList<String> getPossibleExercises()
    {
        return null;
    }

}