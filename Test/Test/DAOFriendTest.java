import mediator.User;
import modelServer.DAO.implementation.FriendDAO;
import modelServer.DAO.implementation.UserDAO;
import modelServer.DAO.interfaces.IFriendDAO;
import modelServer.DAO.interfaces.IUserDAO;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DAOFriendTest {
    private IUserDAO dao;
    private IFriendDAO fao;
    private String[] usernames;
    private String password,firstName,lastName;
    private int height,weight;
    @org.junit.jupiter.api.BeforeEach
    void setUp() throws SQLException {
        dao = new UserDAO();
        fao = new FriendDAO();

        usernames = new String[10];
        for(int i=1;i<10;++i)
            usernames[i]="username"+Math.random();
        password = "password"+Math.random();
        firstName = "firstName"+Math.random();
        lastName = "lastName"+Math.random();
        dao.createTrainee(usernames[0],"a",firstName,lastName,height,weight);
        dao.createTrainee(usernames[1],"a",firstName,lastName,height,weight);
        dao.createTrainee(usernames[2],"a",firstName,lastName,height,weight);
        dao.createTrainee(usernames[3],"a",firstName,lastName,height,weight);
        dao.createTrainee(usernames[4],"a",firstName,lastName,height,weight);
        dao.createTrainee(usernames[5],"a",firstName,lastName,height,weight);
        dao.createTrainee(usernames[6],"a",firstName,lastName,height,weight);
        dao.createTrainee(usernames[7],"a",firstName,lastName,height,weight);
        dao.createTrainee(usernames[8],"a",firstName,lastName,height,weight);
        dao.createTrainee(usernames[9],"a",firstName,lastName,height,weight);


    }

    @Test public void getFriendsZero(){
        assertDoesNotThrow(()-> fao.getFriends(null));
    }
    @Test public void getFriendsOne(){
        assertDoesNotThrow(()-> fao.getFriends(usernames[0]));
    }
    @Test public void getFriendsMany(){
        assertDoesNotThrow(()-> fao.getFriends(usernames[0]));
        assertDoesNotThrow(()-> fao.getFriends(usernames[1]));
        assertDoesNotThrow(()-> fao.getFriends(usernames[2]));
    }

    @Test public void getFriendsRequestsZero(){
        assertDoesNotThrow(()-> fao.getFriendRequests(null));
    }
    @Test public void getFriendsRequestsOne(){
        assertDoesNotThrow(()-> fao.getFriendRequests(usernames[0]));
    }
    @Test public void getFriendsRequestsMany(){
        assertDoesNotThrow(()-> fao.getFriendRequests(usernames[0]));
        assertDoesNotThrow(()-> fao.getFriendRequests(usernames[1]));
        assertDoesNotThrow(()-> fao.getFriendRequests(usernames[2]));
    }

    @Test public void acceptZero() throws SQLException {
        assertDoesNotThrow(()-> fao.acceptFriendRequest(null,null));
        ArrayList<String> list = fao.getFriendRequests(null);
        for(String s : list)
            assert !s.equals(null);
    }
    @Test public void acceptOne() throws SQLException {
        assertDoesNotThrow(()-> fao.acceptFriendRequest(usernames[0], usernames[1]));
        ArrayList<String> list = fao.getFriendRequests(usernames[1]);
        for(String s : list)
            assert !s.equals(usernames[0]);
    }
    @Test public void acceptMany() throws SQLException {
        //test that it is actually in frendship_list?
        assertDoesNotThrow(()-> fao.acceptFriendRequest(usernames[0], usernames[1]));
        assertDoesNotThrow(()-> fao.acceptFriendRequest(usernames[1], usernames[2]));
        assertDoesNotThrow(()-> fao.acceptFriendRequest(usernames[3], usernames[4]));
        ArrayList<String> list = fao.getFriendRequests(usernames[4]);
        for(String s : list)
            assert !s.equals(usernames[3]);
    }


    @Test public void rejectZero() throws SQLException {
        assertDoesNotThrow(()-> fao.rejectFriendRequest(null,null));
        ArrayList<String> list = fao.getFriendRequests(null);
        for(String s : list)
            assert !s.equals(null);
    }
    @Test public void rejectOne() throws SQLException {
        assertDoesNotThrow(()-> fao.rejectFriendRequest(usernames[0], usernames[1]));
        ArrayList<String> list = fao.getFriendRequests(usernames[1]);
        for(String s : list)
            assert !s.equals(usernames[0]);
    }
    @Test public void rejectMany() throws SQLException {
        assertDoesNotThrow(()-> fao.rejectFriendRequest(usernames[0], usernames[1]));
        assertDoesNotThrow(()-> fao.rejectFriendRequest(usernames[1], usernames[2]));
        assertDoesNotThrow(()-> fao.rejectFriendRequest(usernames[3], usernames[4]));
        ArrayList<String> list = fao.getFriendRequests(usernames[4]);
        for(String s : list)
            assert !s.equals(usernames[3]);
    }




}