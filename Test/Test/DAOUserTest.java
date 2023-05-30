import mediator.User;
import modelServer.DAO.implementation.TraineeDAO;
import modelServer.DAO.implementation.UserDAO;
import modelServer.DAO.interfaces.ITraineeDAO;
import modelServer.DAO.interfaces.IUserDAO;
//import org.junit.After;
import modelServer.DbContext.DBService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class DAOUserTest {
    private IUserDAO dao;
    private ITraineeDAO tDao;
    private String username1,username2,username3,password,firstName,lastName;
    private int height,weight;
    DBService service;
    @org.junit.jupiter.api.BeforeEach
    void setUp() throws SQLException {
        service=new DBService();
        dao = new UserDAO();
        tDao =new TraineeDAO();
        username1 = "username"+Math.random();
        username2 = "username"+Math.random();
        username3 = "username"+Math.random();
        password = "password"+Math.random();
        firstName = "firstName"+Math.random();
        lastName = "lastName"+Math.random();
        tDao.createTrainee("a","a",firstName,lastName,height,weight);
    }

    private void SetupTestDatabase(){
        service.restartDatabase();
        service.switchToTestDatabase();
    }


    /**
     * 
     * 
     */
    @Test public void loginZero(){
        SetupTestDatabase();
        assertDoesNotThrow(()->dao.login(null,null));
        service.switchToProductionDatabase();
    }
    /**
     * 
     * 
     */
    @Test public void loginOne(){
        SetupTestDatabase();
        assertDoesNotThrow(()->dao.login(username1,password));
        service.switchToProductionDatabase();
    }
    /**
     * 
     * 
     */
    @Test public void loginMany(){
        SetupTestDatabase();
        assertDoesNotThrow(()->dao.login(username1,password));
        assertDoesNotThrow(()->dao.login(username1,password));
        assertDoesNotThrow(()->dao.login(username1,password));
        service.switchToProductionDatabase();
    }
    /**
     * 
     * 
     */
    @Test public void loginBoundaryException(){
        //pass
    }

}