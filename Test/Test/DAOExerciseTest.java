import mediator.ExerciseList;
import modelServer.DAO.implementation.ExerciseDAO;
import modelServer.DAO.implementation.FolderDAO;
import modelServer.DAO.implementation.UserDAO;
import modelServer.DAO.interfaces.IExerciseDAO;
import modelServer.DAO.interfaces.IFolderDAO;
import modelServer.DAO.interfaces.IUserDAO;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

class DAOExerciseTest {
    private IFolderDAO fao;
    private IUserDAO uao;
    private IExerciseDAO eao;
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        fao=new FolderDAO();
        uao=new UserDAO();
        eao=new ExerciseDAO();
    }

    @Test public void iAmTiredLetsTestThisShit() throws SQLException {
        String username = "your_mom"+Math.random();
        String folderName = "your_dad"+Math.random();
        int folderId = 0;
        String exerciseName = "deadlift";

        uao.createTrainee(username,null,null,null,0,0);
        fao.createFolder(username,folderName);
        folderId = fao.getFolderList(username).get(0).getId();
        eao.addExercise(username,exerciseName,folderId,10,10);

        ExerciseList l1 = eao.getExerciseList(0);
        assert l1.size()==0;
        ExerciseList l2 = eao.getExerciseList(folderId);
        assert l2.size()==1;
        int exerciseId = l2.get(0).getId();

        eao.removeExercise(exerciseId);
        ExerciseList l3 = eao.getExerciseList(folderId);
        assert l3.size()==0;

        eao.addExercise(username,exerciseName,folderId,10,10);
        eao.removeExerciseByName(exerciseName,folderId);
        ExerciseList l4 = eao.getExerciseList(folderId);
        assert l4.size()==0;

        assert eao.getPossibleExercises().size()>0;

        eao.addExercise(username,"deadlift",folderId,10,10);
        eao.addExercise(username,"squat",folderId,11,10);
        eao.addExercise(username,"bench_press",folderId,12,10);

        assert eao.getBestDeadlift(username)==10;
        assert eao.getBestSquat(username)==11;
        assert eao.getBestBenchPress(username)==12;
    }
}