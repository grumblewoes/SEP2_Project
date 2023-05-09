import mediator.User;
import modelServer.DAO.implementation.UserDAO;
import modelServer.DAO.interfaces.IUserDAO;
//import org.junit.After;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class DAOUserTest {
    private IUserDAO dao;
    private String username1,username2,username3,password,firstName,lastName;
    private int height,weight;
    @org.junit.jupiter.api.BeforeEach
    void setUp() throws SQLException {
        dao = new UserDAO();
        username1 = "username"+Math.random();
        username2 = "username"+Math.random();
        username3 = "username"+Math.random();
        password = "password"+Math.random();
        firstName = "firstName"+Math.random();
        lastName = "lastName"+Math.random();
        dao.createTrainee("a","a",firstName,lastName,height,weight);
    }

//    @Test public void tttt() throws SQLException {
//        assertDoesNotThrow(()->dao.createTrainee(username1,password,firstName,lastName,height,weight));
//        User user = dao.getTrainee(username1);
//        System.out.println(user.getUsername());
//    }
//
    @Test public void createZero(){
        assertDoesNotThrow(()->dao.createTrainee(null,null,null,null,0,0));
    }
    @Test public void createOne(){
        assertDoesNotThrow(()->dao.createTrainee(username1,password,firstName,lastName,height,weight));
    }
    @Test public void createMany(){

        assertDoesNotThrow(()->dao.createTrainee(username2,password,firstName,lastName,height,weight));
        assertDoesNotThrow(()->dao.createTrainee(username3,password,firstName,lastName,height,weight));
    }


    @Test public void createBoundary(){
        //pass
    }
    @Test public void createExceptions(){
        //tested in Z
    }

    @Test public void loginZero(){
        assertDoesNotThrow(()->dao.login(null,null));
    }
    @Test public void loginOne(){
        assertDoesNotThrow(()->dao.login(username1,password));
    }
    @Test public void loginMany(){
        assertDoesNotThrow(()->dao.login(username1,password));
        assertDoesNotThrow(()->dao.login(username1,password));
        assertDoesNotThrow(()->dao.login(username1,password));
    }
    @Test public void loginBoundaryException(){
        //pass
    }

    @Test public void getTraineeZero(){
        assertDoesNotThrow(()-> dao.getTrainee(null));
    }
    @Test public void getTraineeOne() throws SQLException {
        User user = dao.getTrainee("a");
        System.out.println(user.getUsername());

        assertEquals("a",user.getUsername());

    }
    @Test public void getTraineeMany(){
        assertDoesNotThrow(()-> dao.getTrainee(username1));
        assertDoesNotThrow(()-> dao.getTrainee(username1));
        assertDoesNotThrow(()-> dao.getTrainee(username1));
        try{
            assert dao.getTrainee("dasdadsadasdadsadasdsad")==null;
        }catch (SQLException e){
            //pass
        }
    }
    @Test public void getTraineeBoundaryException(){
        //pass
    }

    @Test public void updateZero(){
        assertDoesNotThrow(()->{
            dao.updateTrainee(null,0,0,false,"");
        });
    }
    @Test public void updateOne(){
        assertDoesNotThrow(()->{
            dao.updateTrainee(null,0,0,false,"");
        });
    }
    @Test public void updateMany(){
        assertDoesNotThrow(()->{
            dao.updateTrainee(username1,212,50,false,"");
        });
        assertDoesNotThrow(()->{
            dao.updateTrainee(username1,132,60,false,"");
        });
        assertDoesNotThrow(()->{
            dao.updateTrainee(username2,200,70,true,"");
        });
    }







    @Test
    public void Zdelete(){
        assertDoesNotThrow(()->dao.deleteTrainee("a"));
        assertDoesNotThrow(()->dao.deleteTrainee(username1));
        assertDoesNotThrow(()->dao.deleteTrainee(username2));
        assertDoesNotThrow(()->dao.deleteTrainee(username3));
    }
}