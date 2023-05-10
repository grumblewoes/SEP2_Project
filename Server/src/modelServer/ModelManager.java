package modelServer;

import mediator.ExerciseList;
import mediator.Folder;
import mediator.FolderList;
import mediator.User;
import modelServer.DAO.implementation.ExerciseAdminDAO;
import modelServer.DAO.implementation.ExerciseDAO;
import modelServer.DAO.implementation.FolderDAO;
import modelServer.DAO.implementation.UserDAO;
import util.Logger;

import java.sql.SQLException;
import java.util.ArrayList;

public class ModelManager implements Model
{
  private UserDAO userDAO;

  public ModelManager()
  {
    this.userDAO = new UserDAO();

  }

  @Override public boolean createUser(String firstName, String lastName,
      String username, String password, int height, int weight)
  {
    boolean ans = false;
    try
    {
      ans = userDAO.createTrainee(username, password, firstName, lastName,
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

  @Override public int getBestSquat(String username)
  {
    try
    {
      return new ExerciseDAO().getBestSquat(username);
    }
    catch (SQLException e)
    {
      return 0;
    }
  }

  @Override public int getBestDeadlift(String username)
  {
    try
    {
      return new ExerciseDAO().getBestDeadlift(username);
    }
    catch (SQLException e)
    {
      return 0;
    }
  }

  @Override public int getBestBenchPress(String username)
  {
    try
    {
      return new ExerciseDAO().getBestBenchPress(username);
    }
    catch (SQLException e)
    {
      return 0;
    }
  }

  @Override public User getTrainee(String username)
  {
    try
    {
      return new UserDAO().getTrainee(username);
    }
    catch (SQLException e)
    {
      return null;
    }
  }

  @Override public boolean updateTrainee(String u, int h, int w, boolean s)
  {
    try
    {
      return new UserDAO().updateTrainee(u, h, w, s);
    }
    catch (SQLException e)
    {
      return false;
    }
  }

  @Override public boolean addExercise(String title)
  {
    try
    {
      return new ExerciseAdminDAO().addExercise(title);
    }
    catch (SQLException e)
    {
      return false;
    }
  }

  @Override public boolean removeExercise(String title)
  {
    try
    {
      return new ExerciseAdminDAO().removeExercise(title);
    }
    catch (SQLException e)
    {
      return false;
    }
  }
}