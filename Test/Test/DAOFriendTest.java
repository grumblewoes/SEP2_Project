import mediator.Friend;
import mediator.FriendList;
import mediator.User;
import modelServer.DAO.implementation.FriendDAO;
import modelServer.DAO.implementation.UserDAO;
import modelServer.DAO.interfaces.IFriendDAO;
import modelServer.DAO.interfaces.IUserDAO;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

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
        for(int i=0;i<10;++i)
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
    @Test public void getFriendsOne() throws SQLException {
        fao.sendFriendRequest(usernames[1],usernames[2]);
        fao.acceptFriendRequest(usernames[1],usernames[2]);
        assertEquals(fao.getFriends(usernames[1]).get(0).getUsername(), usernames[2]);
    }
    @Test public void getFriendsMany() throws SQLException {
        fao.sendFriendRequest(usernames[1],usernames[2]);
        fao.acceptFriendRequest(usernames[1],usernames[2]);
        assertEquals(fao.getFriends(usernames[1]).get(0).getUsername(), usernames[2]);
        fao.sendFriendRequest(usernames[1],usernames[3]);
        fao.acceptFriendRequest(usernames[1],usernames[3]);
        assertEquals(fao.getFriends(usernames[1]).get(1).getUsername(), usernames[3]);
        fao.sendFriendRequest(usernames[1],usernames[4]);
        fao.acceptFriendRequest(usernames[1],usernames[4]);
        assertEquals(fao.getFriends(usernames[1]).get(2).getUsername(), usernames[4]);
    }

    @Test public void getFriendsRequestsZero(){
        assertDoesNotThrow(()-> fao.getFriendRequests(null));
    }
    @Test public void getFriendsRequestsOne() throws SQLException {
        fao.sendFriendRequest(usernames[1],usernames[2]);
        assertEquals(fao.getFriendRequests(usernames[2]).get(0),usernames[1]);
    }
    @Test public void getFriendsRequestsMany() throws SQLException {
        fao.sendFriendRequest(usernames[1],usernames[2]);
        fao.sendFriendRequest(usernames[1],usernames[3]);
        fao.sendFriendRequest(usernames[1],usernames[4]);
        assertEquals(fao.getFriendRequests(usernames[4]).get(0),usernames[1]);
        assertEquals(fao.getFriendRequests(usernames[3]).get(0),usernames[1]);
        assertEquals(fao.getFriendRequests(usernames[2]).get(0),usernames[1]);
    }

    @Test public void acceptZero() throws SQLException {
        assertDoesNotThrow(()-> fao.acceptFriendRequest(null,null));
        ArrayList<String> list = fao.getFriendRequests(null);
        for(String s : list)
            assert !s.equals(null);
    }
    @Test public void acceptOne() throws SQLException {

        fao.sendFriendRequest(usernames[0],usernames[1]);
        assertDoesNotThrow(()-> fao.acceptFriendRequest(usernames[0], usernames[1]));
        ArrayList<String> list = fao.getFriendRequests(usernames[1]);
        for(String s : list)
            assert !s.equals(usernames[0]);
        FriendList list2 = fao.getFriends(usernames[1]);
        boolean doesExists = false;
        for(int i=0;i<list2.size();++i)
            if(usernames[0].equals(list2.get(i).getUsername()))
                doesExists=true;
        assert doesExists==true;

    }
    @Test public void acceptMany() throws SQLException {

        fao.sendFriendRequest(usernames[0],usernames[1]);
        assertDoesNotThrow(()-> fao.acceptFriendRequest(usernames[0], usernames[1]));
        ArrayList<String> list = fao.getFriendRequests(usernames[1]);
        for(String s : list)
            assert !s.equals(usernames[0]);
        FriendList list2 = fao.getFriends(usernames[1]);
        boolean doesExists = false;
        for(int i=0;i<list2.size();++i)
            if(usernames[0].equals(list2.get(i).getUsername()))
                doesExists=true;
        assert doesExists==true;


        fao.sendFriendRequest(usernames[2],usernames[3]);
        assertDoesNotThrow(()-> fao.acceptFriendRequest(usernames[2], usernames[3]));
        list = fao.getFriendRequests(usernames[3]);
        for(String s : list)
            assert !s.equals(usernames[2]);
        list2 = fao.getFriends(usernames[3]);
        doesExists = false;
        for(int i=0;i<list2.size();++i)
            if(usernames[2].equals(list2.get(i).getUsername()))
                doesExists=true;
        assert doesExists==true;

        fao.sendFriendRequest(usernames[4],usernames[5]);
        assertDoesNotThrow(()-> fao.acceptFriendRequest(usernames[4], usernames[5]));
        list = fao.getFriendRequests(usernames[5]);
        for(String s : list)
            assert !s.equals(usernames[4]);
        list2 = fao.getFriends(usernames[5]);
        doesExists = false;
        for(int i=0;i<list2.size();++i)
            if(usernames[4].equals(list2.get(i).getUsername()))
                doesExists=true;
        assert doesExists==true;

    }


    @Test public void rejectZero() throws SQLException {

        assertDoesNotThrow(()-> fao.rejectFriendRequest(null,null));
        ArrayList<String> list = fao.getFriendRequests(null);
        for(String s : list)
            assert !s.equals(null);
    }
    @Test public void rejectOne() throws SQLException {
        fao.sendFriendRequest(usernames[0],usernames[1]);
        assertDoesNotThrow(()-> fao.rejectFriendRequest(usernames[0], usernames[1]));
        ArrayList<String> list = fao.getFriendRequests(usernames[1]);
        for(String s : list)
            assert !s.equals(usernames[0]);
    }
    @Test public void rejectMany() throws SQLException {
        fao.sendFriendRequest(usernames[0],usernames[1]);
        fao.sendFriendRequest(usernames[1],usernames[2]);
        fao.sendFriendRequest(usernames[3],usernames[4]);
        assertDoesNotThrow(()-> fao.rejectFriendRequest(usernames[0], usernames[1]));
        assertDoesNotThrow(()-> fao.rejectFriendRequest(usernames[1], usernames[2]));
        assertDoesNotThrow(()-> fao.rejectFriendRequest(usernames[3], usernames[4]));
        ArrayList<String> list = fao.getFriendRequests(usernames[4]);
        for(String s : list)
            assert !s.equals(usernames[3]);
    }
    @Test public void sendZOMBIE() throws SQLException{
        //Zero
        assertDoesNotThrow(()-> fao.sendFriendRequest(null,null));
        assertDoesNotThrow(()-> fao.sendFriendRequest(null,usernames[0]));

        //One
        assertDoesNotThrow(()-> fao.sendFriendRequest(usernames[1], usernames[6]));
        ArrayList<String> oneList = fao.getFriendRequests(usernames[1]);
        for(String s : oneList)
            assert oneList.size()==1;

        //Many
        fao.sendFriendRequest(usernames[1], usernames[2]);
        assertDoesNotThrow(()-> fao.sendFriendRequest(usernames[1], usernames[4]));


        ArrayList<String> list = fao.getFriendRequests(usernames[1]);
        for(String s : list)
            assert list.size()==3;
    }
    @Test public void removeZOMBIE() throws SQLException{
        fao.sendFriendRequest(usernames[6], usernames[1]);
        fao.sendFriendRequest(usernames[6], usernames[2]);
        fao.sendFriendRequest(usernames[6], usernames[3]);
        fao.sendFriendRequest(usernames[6], usernames[4]);

        fao.acceptFriendRequest(usernames[6], usernames[1]);
        fao.acceptFriendRequest(usernames[6], usernames[2]);
        fao.acceptFriendRequest(usernames[6], usernames[3]);
        fao.acceptFriendRequest(usernames[6], usernames[4]);

        //Zero
        assertDoesNotThrow(()-> fao.removeFriend(null,null));
        assertDoesNotThrow(()-> fao.removeFriend(null,usernames[1]));

        //One
        assertDoesNotThrow(()-> fao.removeFriend(usernames[6], usernames[1]));
        FriendList list = fao.getFriends(usernames[6]);
        assert list.size()==3;

        //Many
        fao.removeFriend(usernames[6], usernames[2]);
        assertDoesNotThrow(()-> fao.removeFriend(usernames[6], usernames[3]));

        FriendList friendList=fao.getFriends(usernames[6]);
        assert friendList.size()==1;


    }



}