import modelServer.DAO.implementation.*;
import modelServer.DAO.interfaces.IExerciseDAO;
import modelServer.DAO.interfaces.IFriendDAO;
import modelServer.DAO.interfaces.ILeaderboardDAO;
import modelServer.DAO.interfaces.IUserDAO;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class DAOLeaderboard
{

  IExerciseDAO eDao;
  ILeaderboardDAO lDao;
  String traineeA, traineeB, traineeD, squat, bench, deadlift;
  int weightA, weightB, weightC, folderA, folderB, folderC, repetition;

  @org.junit.jupiter.api.BeforeEach
  void setUp() throws SQLException
  {
    eDao = new ExerciseDAO();
    lDao = new LeaderboardDAO();
    traineeD = "d";
    traineeB = "b";
    traineeA = "a";
    weightA=100;
    weightB=200;
    weightC=300;
    repetition=1;
    folderA=1;
    folderB=2;
    folderC=3;
    squat="squat";
    bench="bench";
    deadlift="deadlift";
  }

  @Test public void getSquatBestZero() {
    try
    {
      eDao.addExercise(traineeA,squat,folderA,weightA,repetition);
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
  }
  @Test public void getSquatBestOne(){
    try
    {
      eDao.addExercise(traineeA,squat,folderA,weightA,repetition);
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
  }
  @Test public void getSquatBestMany(){
    try
    {
      eDao.addExercise(traineeA,squat,folderA,weightA,repetition);
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
  }
  @Test public void getSquatBestBoundary(){
  }
  @Test public void getSquatBestException(){
  }


}
