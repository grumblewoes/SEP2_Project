import mediator.User;
import modelServer.DAO.implementation.UserDAO;
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
    private String username1,username2,username3,password,firstName,lastName;
    private int height,weight;
    DBService service;
    @org.junit.jupiter.api.BeforeEach
    void setUp() throws SQLException {
        service=new DBService();
        dao = new UserDAO();
        username1 = "username"+Math.random();
        username2 = "username"+Math.random();
        username3 = "username"+Math.random();
        password = "password"+Math.random();
        firstName = "firstName"+Math.random();
        lastName = "lastName"+Math.random();
        dao.createTrainee("a","a",firstName,lastName,height,weight);
    }

    private void SetupTestDatabase(){
        service.restartDatabase();
        service.switchToTestDatabase();
    }

/**
 * 
 * 
 */
//    @Test public void tttt() throws SQLException {
//        assertDoesNotThrow(()->dao.createTrainee(username1,password,firstName,lastName,height,weight));
//        User user = dao.getTrainee(username1);
//        System.out.println(user.getUsername());
//    }
//
    /**
     * 
     * 
     */
    @Test public void createZero(){
        SetupTestDatabase();
        assertDoesNotThrow(()->dao.createTrainee(null,null,null,null,0,0));
        service.switchToProductionDatabase();
    }
    /**
     * 
     * 
     */
    @Test public void createOne(){
        SetupTestDatabase();
        assertDoesNotThrow(()->dao.createTrainee(username1,password,firstName,lastName,height,weight));
        service.switchToProductionDatabase();
    }
    /**
     * 
     * 
     */
    @Test public void createMany(){
        SetupTestDatabase();
        assertDoesNotThrow(()->dao.createTrainee(username2,password,firstName,lastName,height,weight));
        assertDoesNotThrow(()->dao.createTrainee(username3,password,firstName,lastName,height,weight));
        service.switchToProductionDatabase();
    }


    /**
     * 
     * 
     */
    @Test public void createBoundary(){
        //pass
    }
    /**
     * 
     * 
     */
    @Test public void createExceptions(){
        //tested in Z
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

    /**
     * 
     * 
     */
    @Test public void getTraineeZero(){
        SetupTestDatabase();
        assertDoesNotThrow(()-> dao.getTrainee(null));
        service.switchToProductionDatabase();
    }
    /**
     * 
     * 
     */
    @Test public void getTraineeOne() throws SQLException {
        SetupTestDatabase();
        dao.createTrainee("a", "a", "a", "a", 100, 100);
        User user = dao.getTrainee("a");

        assertEquals("a",user.getUsername());
        service.switchToProductionDatabase();
    }
    /**
     * 
     * 
     */
    @Test public void getTraineeMany(){
        SetupTestDatabase();
        assertDoesNotThrow(()-> dao.getTrainee(username1));
        assertDoesNotThrow(()-> dao.getTrainee(username1));
        assertDoesNotThrow(()-> dao.getTrainee(username1));
        try{
            assert dao.getTrainee("dasdadsadasdadsadasdsad")==null;
        }catch (SQLException e){
            //pass
        }
        service.switchToProductionDatabase();
    }
    /**
     * 
     * 
     */
    @Test public void getTraineeBoundaryException(){
        //pass
    }

    /**
     * 
     * 
     */
    @Test public void updateZero(){
        SetupTestDatabase();
        assertDoesNotThrow(()->{
            dao.updateTrainee(null,0,0,false,"");
        });
        service.switchToProductionDatabase();
    }
    /**
     * 
     * 
     */
    @Test public void updateOne(){
        SetupTestDatabase();
        assertDoesNotThrow(()->{
            dao.updateTrainee(null,0,0,false,"");
        });
        service.switchToProductionDatabase();
    }
    /**
     * 
     * 
     */
    @Test public void updateMany(){
        SetupTestDatabase();
        assertDoesNotThrow(()->{
            dao.updateTrainee(username1,212,50,false,"");
        });
        assertDoesNotThrow(()->{
            dao.updateTrainee(username1,132,60,false,"");
        });
        assertDoesNotThrow(()->{
            dao.updateTrainee(username2,200,70,true,"");
        });
        service.switchToProductionDatabase();
    }







    @Test
    /**
     * 
     * 
     */
    public void Zdelete(){
        assertDoesNotThrow(()->dao.deleteTrainee("a"));
        assertDoesNotThrow(()->dao.deleteTrainee(username1));
        assertDoesNotThrow(()->dao.deleteTrainee(username2));
        assertDoesNotThrow(()->dao.deleteTrainee(username3));
    }
}