import mediator.Folder;
import mediator.FolderList;
import mediator.Friend;
import mediator.FriendList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FriendListTest {
    private FriendList list;
    @BeforeEach public void setup(){
        list = new FriendList();
    }

    @Test public void addZero(){
        assertDoesNotThrow(()->list.add(null));
    }
    @Test public void addOne(){
        assertDoesNotThrow(()->list.add(new Friend("aa","bb")));
//        assertEquals(list.get(0).getUsername(),"aa");
//        assertEquals(list.get(0).getStatus(),"bb");
    }
    @Test public void addMany(){
        assertDoesNotThrow(()->list.add(new Friend("aa","bb")));
        assertDoesNotThrow(()->list.add(new Friend("aa","bb")));
        assertDoesNotThrow(()->list.add(new Friend("cc","dd")));
//        assertEquals(list.get(0).getUsername(),"cc");
//        assertEquals(list.get(0).getStatus(),"dd");
    }

    @Test public void getZero(){
        assertThrows(IndexOutOfBoundsException.class, ()->list.get(0));
    }
    @Test public void getOne(){
        list.add(new Friend("dsad","dsad"));
        assertEquals(list.get(0).getUsername(),"dsad");
        assertEquals(list.get(0).getStatus(),"dsad");
    }
    @Test public void getMany(){
        list.add(new Friend("dsad","dsad"));
        list.add(new Friend("aaa","bbb"));
        assertEquals(list.get(0).getUsername(),"dsad");
        assertEquals(list.get(0).getStatus(),"dsad");
        assertEquals(list.get(1).getUsername(),"aaa");
        assertEquals(list.get(1).getStatus(),"bbb");
    }

    @Test public void sizeZero(){
        assertEquals(0,list.size());
    }
    @Test public void sizeOne(){
        list.add(new Friend("sadas","asdasd"));
        assertEquals(1,list.size());
    }
    @Test public void sizeMany(){
        list.add(new Friend("sadas","asdasd"));
        assertEquals(1,list.size());
        list.add(new Friend("sadas","asdasd"));
        assertEquals(2,list.size());
        list.add(new Friend("sadas","asdasd"));
        assertEquals(3,list.size());
    }

    @Test public void string(){
        assertDoesNotThrow(()->list.toString());
    }



}
