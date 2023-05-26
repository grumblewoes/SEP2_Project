import modelServer.DAO.implementation.*;
import modelServer.DAO.interfaces.*;
import modelServer.DbContext.DBService;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import util.Logger;

import static org.junit.jupiter.api.Assertions.*;

class DAOLeaderboard
{
  DBService service;
  IExerciseDAO eDao;
  ILeaderboardDAO lDao;
  IUserDAO uDao;
  IFolderDAO fDao;
  String traineeA, traineeB, traineeC, squat, bench, deadlift, folderA, folderB, folderC;
  int weightA, weightB, weightC, folder0, folder1, folder2, folder3, repetition;

  @org.junit.jupiter.api.BeforeEach
  void setUp() throws SQLException
  {
    service=new DBService();
    eDao = new ExerciseDAO();
    lDao = new LeaderboardDAO();
    uDao = new UserDAO();
    fDao=new FolderDAO();
    traineeC = "c";
    traineeB = "b";
    traineeA = "a";
    weightA=100;
    weightB=200;
    weightC=300;
    repetition=1;
    folderA="a";
    folderB="b";
    folderC="c";
    folder0=0;
    folder1=1;
    folder2=2;
    folder3=3;
    squat="squat";
    bench="bench_press";
    deadlift="deadlift";
  }
  private void SetupTestDatabase(){
    service.restartDatabase();
    service.switchToTestDatabase();
  }

  /**
   * 
   * 
   */
  @Test public void getSquatBestZero() {
    try
    {
      SetupTestDatabase();
      assertTrue(lDao.getSquatLeaders().getSize() == 0);
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
    finally
    {
      service.switchToProductionDatabase();
    }
  }
    /**
     * 
     * 
     */
    @Test public void getSquatBestOne(){
    try
    {
      SetupTestDatabase();
      uDao.createTrainee(traineeC,null,null,null,0,0);
      fDao.createFolder(traineeC,folderA);
      eDao.addExercise(traineeC,squat,folder0,weightA,repetition);
      assertTrue(lDao.getSquatLeaders().getUsername(0).equals(traineeC));
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
    finally
    {
      service.switchToProductionDatabase();
    }
  }
  /**
   * 
   * 
   */
  @Test public void getSquatBestMany(){
    try
    {
      SetupTestDatabase();
      uDao.createTrainee(traineeA,null,null,null,0,0);
      uDao.createTrainee(traineeB,null,null,null,0,0);
      fDao.createFolder(traineeA,folderA);
      fDao.createFolder(traineeB,folderB);
      fDao.createFolder(traineeA,folderC);
      eDao.addExercise(traineeA,squat,folder0,weightA,repetition);
      eDao.addExercise(traineeB,squat,folder1,weightB,repetition);
      eDao.addExercise(traineeA,squat,folder2,weightC,repetition);
      assertTrue(lDao.getSquatLeaders().getUsername(0).equals(traineeA));
      assertTrue(lDao.getSquatLeaders().getUsername(1).equals(traineeB));
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
    finally
    {
      service.switchToProductionDatabase();
    }
  }
  /**
   * 
   * 
   */
  @Test public void getSquatBestBoundary(){
  }
  /**
   * 
   * 
   */
  @Test public void getSquatBestException(){
  }

  /**
   * 
   * 
   */
  @Test public void getBenchBestZero() {
    try
    {
      SetupTestDatabase();

      assertTrue(lDao.getBenchLeaders().getSize() == 0);
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
    finally
    {
      service.switchToProductionDatabase();
    }
  }
  /**
   * 
   * 
   */
  @Test public void getBenchBestOne(){
    try
    {
      SetupTestDatabase();

      uDao.createTrainee(traineeC,null,null,null,0,0);
      fDao.createFolder(traineeC,folderA);
      eDao.addExercise(traineeC,bench,folder0,weightA,repetition);
      assertTrue(lDao.getBenchLeaders().getUsername(0).equals(traineeC));
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
    finally
    {
      service.switchToProductionDatabase();
    }
  }
  /**
   * 
   * 
   */
  @Test public void getBenchBestMany(){
    try
    {
      SetupTestDatabase();

      uDao.createTrainee(traineeA,null,null,null,0,0);
      uDao.createTrainee(traineeB,null,null,null,0,0);
      fDao.createFolder(traineeA,folderA);
      fDao.createFolder(traineeB,folderB);
      fDao.createFolder(traineeA,folderC);
      eDao.addExercise(traineeA,bench,folder0,weightA,repetition);
      eDao.addExercise(traineeB,bench,folder1,weightB,repetition);
      eDao.addExercise(traineeA,bench,folder2,weightC,repetition);
      assertTrue(lDao.getBenchLeaders().getUsername(0).equals(traineeA));
      assertTrue(lDao.getBenchLeaders().getUsername(1).equals(traineeB));
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
    finally
    {
      service.switchToProductionDatabase();
    }
  }
  /**
   * 
   * 
   */
  @Test public void getBenchBestBoundary(){
  }
  /**
   * 
   * 
   */
  @Test public void getBenchBestException(){
  }

  /**
   * 
   * 
   */
  @Test public void getDeadliftBestZero() {
    try
    {
      SetupTestDatabase();

      assertTrue(lDao.getDeadliftLeaders().getSize() == 0);
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
    finally
    {
      service.switchToProductionDatabase();
    }
  }
  /**
   * 
   * 
   */
  @Test public void getDeadliftBestOne(){
    try
    {
      SetupTestDatabase();

      uDao.createTrainee(traineeC,null,null,null,0,0);
      fDao.createFolder(traineeC,folderA);
      eDao.addExercise(traineeC,deadlift,folder0,weightA,repetition);
      assertTrue(lDao.getDeadliftLeaders().getUsername(0).equals(traineeC));
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
    finally
    {
      service.switchToProductionDatabase();
    }
  }
  /**
   * 
   * 
   */
  @Test public void getDeadliftBestMany(){
    try
    {
      SetupTestDatabase();

      uDao.createTrainee(traineeA,null,null,null,0,0);
      uDao.createTrainee(traineeB,null,null,null,0,0);
      fDao.createFolder(traineeA,folderA);
      fDao.createFolder(traineeB,folderB);
      fDao.createFolder(traineeA,folderC);
      eDao.addExercise(traineeA,deadlift,folder0,weightA,repetition);
      eDao.addExercise(traineeB,deadlift,folder1,weightB,repetition);
      eDao.addExercise(traineeA,deadlift,folder2,weightC,repetition);
      assertTrue(lDao.getDeadliftLeaders().getUsername(0).equals(traineeA));
      assertTrue(lDao.getDeadliftLeaders().getUsername(1).equals(traineeB));
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
    finally
    {
      service.switchToProductionDatabase();
    }
  }
  /**
   * 
   * 
   */
  @Test public void getDeadliftBestBoundary(){
  }
  /**
   * 
   * 
   */
  @Test public void getDeadliftBestException(){
  }


}
