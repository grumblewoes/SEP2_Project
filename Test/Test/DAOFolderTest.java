import modelServer.DAO.implementation.FolderDAO;
import modelServer.DAO.implementation.UserDAO;
import modelServer.DAO.interfaces.IFolderDAO;
import modelServer.DAO.interfaces.IUserDAO;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class DAOFolderTest {
    private IFolderDAO fdao;
    private IUserDAO udao;
    private String username,title1,title2,title3;
    private int id;
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        fdao = new FolderDAO();
        udao = new UserDAO();
        username = "dsadasdasdasd";
        title1 = "sdasdasdasdasdas";
        title2 = "sdasddsadasdaasdasdasdas";
        title3 = "sdasdasddsadasdasdasdasdasdas";

    }
    /**
     * 
     * 
     */
    @Test public void AAAA(){
        try{

            udao.createTrainee(username,null,null,null,0,0);
            fdao.createFolder(username,title1);
            id = fdao.getFolderList(username).get(0).getId();
        }catch (SQLException e){
            //pass
        }
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
    @Test public void createOne(){
        assertDoesNotThrow(()->fdao.createFolder(username,title1));
    }
    /**
     * 
     * 
     */
    @Test public void createMany(){
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
        assertDoesNotThrow(()->fdao.renameFolder(username,id,"aaa"));
    }
    /**
     * 
     * 
     */
    @Test public void renameMany() throws SQLException {
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
        assertDoesNotThrow(()->fdao.getFolderList(username));
        assert(fdao.getFolderList(username).size()>0);
    }
    /**
     * 
     * 
     */
    @Test public void listMany() throws SQLException {
        assertDoesNotThrow(()->fdao.getFolderList(username));
//        assert(fdao.getFolderList(username).size()>0);
        assertDoesNotThrow(()->fdao.getFolderList("ddsadsadsadsasadsad"));
//        assert(fdao.getFolderList(username).size()==0);
    }

}