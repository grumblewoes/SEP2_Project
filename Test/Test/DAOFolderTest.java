import modelServer.DAO.implementation.FolderDAO;
import modelServer.DAO.implementation.TraineeDAO;
import modelServer.DAO.implementation.UserDAO;
import modelServer.DAO.interfaces.IFolderDAO;
import modelServer.DAO.interfaces.ITraineeDAO;
import modelServer.DAO.interfaces.IUserDAO;
import modelServer.DbContext.DBService;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class DAOFolderTest {
    private IFolderDAO fdao;
    private ITraineeDAO udao;
    private DBService service;
    private String username,title1,title2,title3;
    private int id;
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        fdao = new FolderDAO();
        udao = new TraineeDAO();
        service=new DBService();
        username = "dsadasdasdasd";
        title1 = "sdasdasdasdasdas";
        title2 = "sdasddsadasdaasdasdasdas";
        title3 = "sdasdasddsadasdasdasdasdasdas";
SetupTestDatabase();
    }
    private void SetupTestDatabase(){
        service.restartDatabase();
        service.switchToTestDatabase();
    }
    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        service.switchToProductionDatabase();
    }


    /**
     * 
     * 
     */
    @Test public void createZero(){
        assertDoesNotThrow(()->fdao.createFolder(null,null));
    }
    /**
     * 
     * 
     */
    @Test public void createOne() throws SQLException
    {
        udao.createTrainee(username,null,null,null,0,0);
        assertDoesNotThrow(()->fdao.createFolder(username,title1));
    }
    /**
     * 
     * 
     */
    @Test public void createMany() throws SQLException
    {
        udao.createTrainee(username,null,null,null,0,0);
        udao.createTrainee(username,null,null,null,0,0);
        assertDoesNotThrow(()->fdao.createFolder(username,title2));
        assertDoesNotThrow(()->fdao.createFolder(username,title3));
    }
    /**
     * 
     * 
     */
    @Test public void renameZero() throws SQLException {
        assertDoesNotThrow(()->fdao.renameFolder(null,id,"aaa"));
    }
    /**
     * 
     * 
     */
    @Test public void renameOne() throws SQLException {
        udao.createTrainee(username,null,null,null,0,0);
        assertDoesNotThrow(()->fdao.renameFolder(username,id,"aaa"));
    }
    /**
     * 
     * 
     */
    @Test public void renameMany() throws SQLException {
        udao.createTrainee(username,null,null,null,0,0);
        udao.createTrainee(username,null,null,null,0,0);
        udao.createTrainee(username,null,null,null,0,0);
        assertDoesNotThrow(()->fdao.renameFolder(username,id,"aaa"));
        assertDoesNotThrow(()->fdao.renameFolder(username,id,"bbb"));
        assertDoesNotThrow(()->fdao.renameFolder(username,id,"ccc"));
    }

    /**
     * 
     * 
     */
    @Test public void listZero() throws SQLException {
        assertDoesNotThrow(()->fdao.getFolderList(null));
    }
    /**
     * 
     * 
     */
    @Test public void listOne() throws SQLException {
        udao.createTrainee(username,null,null,null,0,0);
        fdao.createFolder(username,title1);
        assertDoesNotThrow(()->fdao.getFolderList(username));
        assert(fdao.getFolderList(username).size()==1);
    }
    /**
     * 
     * 
     */
    @Test public void listMany() throws SQLException {
        udao.createTrainee(username,null,null,null,0,0);
        fdao.createFolder(username,title1);
        fdao.createFolder(username,title2);
        fdao.createFolder(username,title3);
        assertDoesNotThrow(()->fdao.getFolderList(username));
        assert(fdao.getFolderList(username).size()>0);
        assertDoesNotThrow(()->fdao.getFolderList(username));
//        assert(fdao.getFolderList(username).size()==0);
    }

}